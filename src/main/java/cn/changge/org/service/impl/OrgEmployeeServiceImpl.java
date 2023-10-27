package cn.changge.org.service.impl;

import cn.changge.org.domain.OrgEmployee;
import cn.changge.org.mapper.OrgEmployeeMapper;
import cn.changge.org.service.IOrgEmployeeService;
import cn.changge.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
@Service
public class OrgEmployeeServiceImpl extends BaseServiceImpl<OrgEmployee> implements IOrgEmployeeService {
    @Autowired
    private OrgEmployeeMapper orgEmployeeMapper;

    @Override
    public OrgEmployee queryByName(String name) {
        return orgEmployeeMapper.quertByName(name);
    }
}
