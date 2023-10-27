package cn.changge.org.service.impl;

import cn.changge.base.query.BaseQuery;
import cn.changge.base.service.impl.BaseServiceImpl;
import cn.changge.base.utils.EmployeeValidator;
import cn.changge.base.utils.PageInfo;
import cn.changge.org.domain.ExcelEmployee;
import cn.changge.org.domain.OrgEmployee;
import cn.changge.org.mapper.ExcelMapper;
import cn.changge.org.mapper.OrgEmployeeMapper;
import cn.changge.org.service.IEmployeeService;
import cn.changge.org.service.IExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-10-21  10:23
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class IExcelServiceImpl extends BaseServiceImpl<ExcelEmployee> implements IExcelService {
    @Autowired
    private ExcelMapper excelMapper;

    @Override
    public ExcelEmployee queryByName(String name) {
        return excelMapper.quertByName(name);
    }

    @Override
    public void batchInsertEmployees(List<ExcelEmployee> employees) {
        boolean allDataValid = employees.stream().allMatch(excelEmployee ->
                EmployeeValidator.isValidPhoneNumber(excelEmployee.getPhone()) &&
                        EmployeeValidator.isValidEmail(excelEmployee.getEmail())
        );

        if (allDataValid) {
            excelMapper.batchInsertEmployees(employees);
        } else {
            // 处理数据不合法的情况，例如记录错误信息或抛出异常
            throw new RuntimeException("数据不合法");
        }

    }
}
