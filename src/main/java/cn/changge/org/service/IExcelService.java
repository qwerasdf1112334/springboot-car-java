package cn.changge.org.service;

import cn.changge.base.service.BaseService;
import cn.changge.org.domain.ExcelEmployee;
import cn.changge.org.domain.OrgEmployee;
import cn.changge.org.query.ExcelEmployeeQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface IExcelService extends BaseService<ExcelEmployee> {
    ExcelEmployee queryByName(String name);

    void batchInsertEmployees(List<ExcelEmployee> employees);
}
