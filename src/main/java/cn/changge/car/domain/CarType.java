package cn.changge.car.domain;

import cn.changge.base.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author wangxi
 * @since 2023-11-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarType extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String icon;
    /**
     * 排序用
     */
    private Integer index;
    private String description;
    /**
     * 父类型id
     */
    private Long pid;
    private List<CarType> children=new ArrayList<>();



}
