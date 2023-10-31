package cn.changge.org.domain;

import cn.changge.base.domain.BaseDomain;

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
public class ShopOperateLog extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long shopId;
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
    private Date operateTime;
    /**
     * 备注
     */
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ShopOperateLog{" +
        ", id=" + id +
        ", shopId=" + shopId +
        ", operateType=" + operateType +
        ", operateId=" + operateId +
        ", operateTime=" + operateTime +
        ", note=" + note +
        "}";
    }
}
