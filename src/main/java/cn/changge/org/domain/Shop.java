package cn.changge.org.domain;

import cn.changge.base.domain.BaseDomain;
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
 * @since 2023-10-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends BaseDomain {

    private static final long serialVersionUID = 1L;
    /**待审核 **/
    public static final int STATE_WAIT_AUTID = 1 ; //"待审核";
    /**审核通过，待激活 **/
    public static final int STATE_WAIT_ACTIVE = 2 ; //"审核通过，待激活";
    /**激活成功 **/
    public static final int STATE_ACTIVE_SUCCESS = 3 ; //"激活成功";
    /**驳回 **/
    public static final int STATE_REJECT_AUDIT = 4 ; //"驳回";

    private Long id;
    private String name;
    private String tel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = ("GMT+8"))
    private Date registerTime;
    /**
     * 1=待审核 2=审核通过待激活 3=激活成功 4=驳回
     */
    private Integer state;
    private String address;
    private String logo;

    /** 店铺管理 **/
    private OrgEmployee admin;
    /**纬度**/
   private Double lat;
    /**经度**/
    private Double      lng;




}
