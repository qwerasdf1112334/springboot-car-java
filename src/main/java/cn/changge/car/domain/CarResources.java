package cn.changge.car.domain;

import cn.changge.base.domain.BaseDomain;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarResources extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 车辆id
     */
    private Long carId;
    /**
     * 资源路径[多个资源以逗号隔开,或者一个资源就是一条数据]
     */
    private String resourceUrl;
    /**
     * 资源类型 1.车辆图片  2.细节图  3.检测报告  4.视频 5.vr
     */
    private Integer resourceType;



}
