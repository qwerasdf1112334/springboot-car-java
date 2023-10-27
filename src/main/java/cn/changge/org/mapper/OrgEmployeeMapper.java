package cn.changge.org.mapper;

import cn.changge.org.domain.OrgEmployee;
import cn.changge.base.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface OrgEmployeeMapper extends BaseMapper<OrgEmployee> {
    OrgEmployee quertByName(String name);

}
