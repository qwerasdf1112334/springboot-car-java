package cn.changge.car.service.impl;

import cn.changge.base.config.HighlightResultMapper;
import cn.changge.base.service.impl.BaseServiceImpl;
import cn.changge.base.utils.DistanceUtil;
import cn.changge.base.utils.PageInfo;
import cn.changge.car.domain.vo.BucketVo;
import cn.changge.car.esdoc.CarDoc;
import cn.changge.car.query.CarDocQuery;
import cn.changge.car.repository.CarDocRepository;
import cn.changge.car.service.ICarDocService;

import org.apache.commons.lang3.time.DateUtils;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.car.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-11-03  21:14
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class CarDocServiceImpl  implements ICarDocService {

    @Autowired
    private CarDocRepository carDocRepository;

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private HighlightResultMapper highlightResultMapper;
    @Override
    public PageInfo<CarDoc> queryPage(CarDocQuery query) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //构建组合查询对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //dsl查询，类似模糊查询，分词匹配计算相关度
        if (Objects.nonNull(query.getKeyword())){
            MatchQueryBuilder title = QueryBuilders.matchQuery("title", query.getKeyword());
            boolQueryBuilder.must(title);
        }
        //以下为dsl过滤查询
        //价格搜索
        if (Objects.nonNull(query.getPriceMin())){
            RangeQueryBuilder costprice = QueryBuilders.rangeQuery("costprice").gte(query.getPriceMin());
            boolQueryBuilder.filter(costprice);
        };
        if (Objects.nonNull(query.getPriceMax())){
            RangeQueryBuilder costprice = QueryBuilders.rangeQuery("costprice").lte(query.getPriceMax());
            boolQueryBuilder.filter(costprice);
        };
        //车龄搜索   今年2023，3-5年的车要在2020-2018之间，所以上牌时间小于min()大于max
        if (Objects.nonNull(query.getCarAgeMin())){
            //计算时间
            Date date = DateUtils.addYears(new Date(), query.getCarAgeMin());
            //假设reigstering为2019，要小于3（min）年的2020
            RangeQueryBuilder reigstertime = QueryBuilders.rangeQuery("reigstertime").lte(date);
            boolQueryBuilder.filter(reigstertime);
        }
        if (Objects.nonNull(query.getCarAgeMax())){
            //计算时间
            Date date = DateUtils.addYears(new Date(), query.getCarAgeMax());

            RangeQueryBuilder reigstertime = QueryBuilders.rangeQuery("reigstertime").gte(date);
            boolQueryBuilder.filter(reigstertime);
        }
        //-----------------急售,超值,准新,可迁全国查询----------------------
        if (Objects.nonNull(query.getCosteffective())) {
            TermQueryBuilder termQuery = QueryBuilders.termQuery("costeffective", query.getCosteffective());
            boolQueryBuilder.filter(termQuery);
        }
        if (Objects.nonNull(query.getRushsale())) {
            TermQueryBuilder termQuery = QueryBuilders.termQuery("rushsale", query.getRushsale());
            boolQueryBuilder.filter(termQuery);
        }
        if (Objects.nonNull(query.getQuasinewcar())) {
            TermQueryBuilder termQuery = QueryBuilders.termQuery("quasinewcar", query.getQuasinewcar());
            boolQueryBuilder.filter(termQuery);
        }
        if (Objects.nonNull(query.getTransitivecountry())) {
            TermQueryBuilder termQuery = QueryBuilders.termQuery("transitivecountry", query.getTransitivecountry());
            boolQueryBuilder.filter(termQuery);
        }
        //-----类型和品牌查询
        if (Objects.nonNull(query.getCarType())){
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("typeId", query.getCarType());
            boolQueryBuilder.filter(termQueryBuilder);
        }

        if (Objects.nonNull(query.getBrand())){
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brand", query.getBrand());
            boolQueryBuilder.filter(termQueryBuilder);
        }
        //-------------------添加排序条件-------------------
        if (Objects.nonNull(query.getOrderField())){
            queryBuilder.withSort(new FieldSortBuilder(query.getOrderField())
                    .order(query.getOrderType().equals("ASC")? SortOrder.ASC:SortOrder.DESC));
        }
        //地理位置
        if (Objects.nonNull(query.getLng())&&Objects.nonNull(query.getLat())&&Objects.nonNull(query.getDistance())){
            //1.距离范围的查询构建
            GeoDistanceQueryBuilder shopPoint = QueryBuilders.geoDistanceQuery("shopPoint");
            //把用户的经纬度设置到查询对象
            GeoDistanceQueryBuilder point = shopPoint.point(query.getLat(), query.getLng());
            System.out.println(point+"经纬度");
            //设置要查询的距离
            GeoDistanceQueryBuilder distance = shopPoint.distance(query.getDistance(), DistanceUnit.KILOMETERS);

            boolQueryBuilder.filter(shopPoint);

        }
        //关键字高亮
        HighlightBuilder.Field field = new HighlightBuilder.Field("title");
        field.preTags("<span style='color:red'>");
        field.postTags("</span>");
        queryBuilder.withHighlightFields(field);
        //聚合查询
        TermsAggregationBuilder brandBuilder = AggregationBuilders.terms("brandBuckets").field("brand").order(BucketOrder.count(false)).size(50);
        TermsAggregationBuilder shopNameBuilder = AggregationBuilders.terms("shopNameBuckets").field("shopName").order(BucketOrder.count(false)).size(50);
        queryBuilder.addAggregation(brandBuilder);
        queryBuilder.addAggregation(shopNameBuilder);

        //添加分页
        queryBuilder.withPageable(PageRequest.of(query.getCurrentPage()-1,query.getPageSize()));
        //添加boolQueryBuilder上面的查询条件
        queryBuilder.withQuery(boolQueryBuilder);

        //更换为elasticsearchRestTemplate核心对象方便处理结果集
        AggregatedPage<CarDoc> docPage = elasticsearchRestTemplate.queryForPage(queryBuilder.build(), CarDoc.class, highlightResultMapper);

      //  AggregatedPage<CarDoc> docPage = elasticsearchTemplate.queryForPage(queryBuilder.build(), CarDoc.class, highlightResultMapper);
        //  Page<CarDoc> docPage = carDocRepository.search(queryBuilder.build());

        List<Aggregation> aggregationList = docPage.getAggregations().asList();
        //封装一个map用来封装返回数据
        HashMap<String, List<BucketVo>> hashMap = new HashMap<>();
        aggregationList.forEach(aggregation -> {
            //elasticsearchRestTemplate这个核心类里才ParsedStringTerms对象，强转.
           ParsedStringTerms parsedStringTerms=(ParsedStringTerms) aggregation;

            List<? extends Terms.Bucket> buckets = parsedStringTerms.getBuckets();
            ArrayList<BucketVo> list = new ArrayList<>();
            buckets.forEach(bucket -> {
                BucketVo bucketVo = new BucketVo(bucket.getKeyAsString(), bucket.getDocCount());
                list.add(bucketVo);
            });
            hashMap.put(aggregation.getName(), list);

        });

        //获取命中的总数
        long total = docPage.getTotalElements();
        //获取命中的数据
        List<CarDoc> content = docPage.getContent();
        return new PageInfo<>(total,content,hashMap);
    }
}
