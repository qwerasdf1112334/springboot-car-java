package cn.changge.org.service;

import cn.changge.org.domain.OrgEmployee;
import cn.changge.base.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface IOrgEmployeeService extends BaseService<OrgEmployee> {
    OrgEmployee queryByName(String name);

}
