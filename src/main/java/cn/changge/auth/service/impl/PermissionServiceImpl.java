package cn.changge.auth.service.impl;

import cn.changge.auth.domain.Permission;
import cn.changge.auth.mapper.PermissionMapper;
import cn.changge.auth.service.IPermissionService;
import cn.changge.base.service.impl.BaseServiceImpl;
import cn.changge.org.domain.OrgEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
@Service
public  class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void deleteAll() {
        permissionMapper.deleteAll();

    }

    @Override
    public List<String> queryPermByuserId(Long id) {
        return permissionMapper.queryPermByuserId(id);
    }

    @Override
    public List<Permission> queryAll() {
        return permissionMapper.queryAll();
    }
}
