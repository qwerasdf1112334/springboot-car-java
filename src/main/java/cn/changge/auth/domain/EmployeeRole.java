package cn.changge.auth.domain;

import cn.changge.base.domain.BaseDomain;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public class EmployeeRole extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long employeeId;
    private Long roleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "EmployeeRole{" +
        ", id=" + id +
        ", employeeId=" + employeeId +
        ", roleId=" + roleId +
        "}";
    }
}
