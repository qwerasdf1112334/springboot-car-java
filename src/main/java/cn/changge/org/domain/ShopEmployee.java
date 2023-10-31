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
public class ShopEmployee extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 关联店铺表id
     */
    private Long shopId;
    /**
     * 关联员工表id
     */
    private Long employeeId;
    /**
     * 是否是管理者 1=管理者 0-员工
     */
    private Integer isAdmin;


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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "ShopEmployee{" +
        ", id=" + id +
        ", shopId=" + shopId +
        ", employeeId=" + employeeId +
        ", isAdmin=" + isAdmin +
        "}";
    }
}
