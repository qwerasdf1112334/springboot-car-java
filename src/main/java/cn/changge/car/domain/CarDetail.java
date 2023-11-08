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
public class CarDetail extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 基本信息
     */
    private String info;
    private Long carId;
    /**
     * 车辆标题
     */
    private String cartitle;
    private Date detectiontime;
    private Long detectorId;
    /**
     * 检测员名称
     */
    private String detectorname;
    /**
     * 事故排查 图片地址
     */
    private String accidentInvestigation;
    /**
     * 核心部件检测
     */
    private String coreComponentsInvestigation;
    /**
     * 常用功能检测
     */
    private String commonFunctionsInvestigation;
    /**
     * 外观内饰检测
     */
    private String appearanceInspection;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCartitle() {
        return cartitle;
    }

    public void setCartitle(String cartitle) {
        this.cartitle = cartitle;
    }

    public Date getDetectiontime() {
        return detectiontime;
    }

    public void setDetectiontime(Date detectiontime) {
        this.detectiontime = detectiontime;
    }

    public Long getDetectorId() {
        return detectorId;
    }

    public void setDetectorId(Long detectorId) {
        this.detectorId = detectorId;
    }

    public String getDetectorname() {
        return detectorname;
    }

    public void setDetectorname(String detectorname) {
        this.detectorname = detectorname;
    }

    public String getAccidentInvestigation() {
        return accidentInvestigation;
    }

    public void setAccidentInvestigation(String accidentInvestigation) {
        this.accidentInvestigation = accidentInvestigation;
    }

    public String getCoreComponentsInvestigation() {
        return coreComponentsInvestigation;
    }

    public void setCoreComponentsInvestigation(String coreComponentsInvestigation) {
        this.coreComponentsInvestigation = coreComponentsInvestigation;
    }

    public String getCommonFunctionsInvestigation() {
        return commonFunctionsInvestigation;
    }

    public void setCommonFunctionsInvestigation(String commonFunctionsInvestigation) {
        this.commonFunctionsInvestigation = commonFunctionsInvestigation;
    }

    public String getAppearanceInspection() {
        return appearanceInspection;
    }

    public void setAppearanceInspection(String appearanceInspection) {
        this.appearanceInspection = appearanceInspection;
    }

    @Override
    public String toString() {
        return "CarDetail{" +
        ", id=" + id +
        ", info=" + info +
        ", carId=" + carId +
        ", cartitle=" + cartitle +
        ", detectiontime=" + detectiontime +
        ", detectorId=" + detectorId +
        ", detectorname=" + detectorname +
        ", accidentInvestigation=" + accidentInvestigation +
        ", coreComponentsInvestigation=" + coreComponentsInvestigation +
        ", commonFunctionsInvestigation=" + commonFunctionsInvestigation +
        ", appearanceInspection=" + appearanceInspection +
        "}";
    }
}
