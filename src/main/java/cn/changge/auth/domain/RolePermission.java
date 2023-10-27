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
public class RolePermission extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long roleId;
    private Long permissionId;
    /**
     * 权限的唯一标识
     */
    private String permissionSn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionSn() {
        return permissionSn;
    }

    public void setPermissionSn(String permissionSn) {
        this.permissionSn = permissionSn;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", permissionId=" + permissionId +
        ", permissionSn=" + permissionSn +
        "}";
    }
}
