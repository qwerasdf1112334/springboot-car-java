package cn.changge.auth.mapper;

import cn.changge.auth.domain.Role;
import cn.changge.auth.dto.RoleDTO;
import cn.changge.base.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface RoleMapper extends BaseMapper<Role> {

    void deleteAll(RoleDTO roleDTO);

    void roleAuth(RoleDTO roleDTO);

    List<Long> queryRolePermissionIds(Long id);
}
