package cn.changge.car.esdoc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.search.aggregations.support.ValuesSource;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Document(indexName = "ronghua-car", type = "car")
public class CarDoc {

    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_smart"
            , searchAnalyzer = "ik_smart")
    private String title;
    @Field(type = FieldType.Keyword)
    private String cover;
    @Field(type = FieldType.Double)
    private BigDecimal saleprice;
    @Field(type = FieldType.Double)
    private BigDecimal costprice;
    @Field(type = FieldType.Integer)
    private Integer isnew;
    @Field(type = FieldType.Date)
//    @JsonFormat(pattern = "yyyy年MM月上牌")
    private Date reigstertime;
    @Field(type = FieldType.Double)
    private Double mileage;
    @Field(type = FieldType.Long)
    private Long shopId;
//    @Field(type = FieldType.Text, analyzer = "ik_smart"
//            , searchAnalyzer = "ik_smart")
    @Field(type = FieldType.Keyword)
    private String shopName;

    @Field(type = FieldType.Keyword)
    private String shopAddress;
    @Field(type = FieldType.Date)
    private Date onsaletime;
    @Field(type = FieldType.Integer)
    private Integer costeffective;
    @Field(type = FieldType.Integer)
    private Integer rushsale;
    @Field(type = FieldType.Integer)
    private Integer quasinewcar;
    @Field(type = FieldType.Integer)
    private Integer transitivecountry;
    @Field(type = FieldType.Long)
    private Long typeId;
    @Field(type = FieldType.Text, analyzer = "ik_smart"
            , searchAnalyzer = "ik_smart")
    private String typeName;
    @Field(type = FieldType.Text, analyzer = "ik_smart"
            , searchAnalyzer = "ik_smart")
    private String carInfo;

    @Field(type = FieldType.Keyword)
    private String brand;
    //店铺的经纬度
//    private Double lng;
//
//    private Double lat;
    @GeoPointField
    private GeoPoint shopPoint;


}