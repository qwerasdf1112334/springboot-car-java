package cn.changge.car.domain;

import cn.changge.base.domain.BaseDomain;
import cn.changge.org.domain.Shop;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author wangxi
 * @since 2023-11-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseDomain {

    private static final long serialVersionUID = 1L;
    /** 上架 **/
    public static final Integer STATE_ONSALE=1;
    /** 下架 **/
    public static final Integer STATE_OFFSALE=0;


    private Long id;
    private String title;
    /**
     * 封面 fastdfs地址
     */
    private String cover;
    /**
     * 原价
     */
    private BigDecimal saleprice;
    /**
     * 售价
     */
    private BigDecimal costprice;
    private Integer isnew;
    /**
     * 上牌时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date reigstertime;
    /**
     * 里程
     */
    private Double mileage;
    /**
     * 店铺Id 如果被领养店铺id为null
     */
    private Long shopId;
    private String shopname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date onsaletime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date offsaletime;
    /**
     * 状态：0下架 1上架
     */
    private Integer state;
    /**
     * 是否超值
     */
    private Integer costeffective;
    /**
     * 急售
     */
    private Integer rushsale;
    /**
     * 准新车
     */
    private Integer quasinewcar;
    private Integer transitivecountry;
    /**
     * 类型id
     */
    private Long typeId;
    private Long sellerId;
    /**
     * 如果被领养为领养用户id
     */
    private Long userId;
    /**
     * 如果是由别人发布的卖车消息那收购来的，那么就记录卖车信息id
     */
    private Long searchMasterMsgId;
    /**
     * 审核状态
     */
    private Integer auditstate;
    /**
     * 品牌
     */
    private String brand;

    private CarDetail carDetail;
    private Shop shop;

    private CarType carType;



}
