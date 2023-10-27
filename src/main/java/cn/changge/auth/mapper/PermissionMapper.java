package cn.changge.auth.mapper;

import cn.changge.auth.domain.Permission;
import cn.changge.base.mapper.BaseMapper;
import cn.changge.org.domain.OrgEmployee;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    void deleteAll();

    List<String> queryPermByuserId(Long id);
}
