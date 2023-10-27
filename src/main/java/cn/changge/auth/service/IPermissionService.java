package cn.changge.auth.service;

import cn.changge.auth.domain.Permission;
import cn.changge.base.service.BaseService;
import cn.changge.org.domain.OrgEmployee;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface IPermissionService extends BaseService<Permission> {
    void deleteAll();

    List<String> queryPermByuserId(Long id);
}
