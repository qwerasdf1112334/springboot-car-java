package cn.changge.auth.service;

import cn.changge.auth.domain.Role;
import cn.changge.auth.dto.RoleDTO;
import cn.changge.base.service.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface IRoleService extends BaseService<Role> {

    void roleAuth(RoleDTO roleDTO);

    List<Long> queryRolePermissionIds(Long id);
}
