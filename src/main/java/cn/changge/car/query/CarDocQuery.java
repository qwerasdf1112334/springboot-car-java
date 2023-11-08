package cn.changge.car.query;


import cn.changge.base.query.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDocQuery extends BaseQuery {
    //==========高级查询条件============//
    private Long carType;
    private Double priceMin;//最小价格
    private Double priceMax;//最大价格
    private Integer carAgeMin;
    private Integer carAgeMax;
    //是否超值
    private Integer costeffective;
    //急售
    private Integer rushsale;
    //准新车
    private Integer quasinewcar;
    //可迁全国
    private Integer transitivecountry;
    //==========高级查询条件============//
    //==========排序条件============//
    private String orderField;//排序字段
    private String orderType; //排序类型 desc降序 asc升序号
    //根据品牌查询数据
    private String brand;

    //经纬度
    private Double lat;
    private Double lng;
    //距离(km)
    private Integer distance;

    //距离范围


    //==========排序条件============//
    //==========分页条件============//
    //不用写了BaseQuery已经有了
    //==========分页条件============//
}
