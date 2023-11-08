package cn.changge.car.domain;

import cn.changge.base.domain.BaseDomain;

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
public class CarOnlineAuditLog extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 消息id
     */
    private Long carId;
    /**
     * 状态 0失败 1成功
     */
    private Integer state;
    /**
     * 审核人 如果为null系统审核
     */
    private Long auditId;
    /**
     * 审核时间
     */
    private Date auditTime;
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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CarOnlineAuditLog{" +
        ", id=" + id +
        ", carId=" + carId +
        ", state=" + state +
        ", auditId=" + auditId +
        ", auditTime=" + auditTime +
        ", note=" + note +
        "}";
    }
}
