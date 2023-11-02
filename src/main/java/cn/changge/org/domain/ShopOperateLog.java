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
 * @since 2023-10-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOperateLog extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Shop shop;
    /**
     * 操作类型:1=入驻 2=审核成功 3=审核驳回 4=激活
     */
    private Integer operateType;
    /**
     * 操作人
     */
    private Long operateId;
    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = ("GMT+8"))
    private Date operateTime;
    /**
     * 备注
     */
    private String note;



}
