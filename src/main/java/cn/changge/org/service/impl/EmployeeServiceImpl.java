package cn.changge.org.service.impl;

import cn.changge.base.service.impl.BaseServiceImpl;
import cn.changge.org.domain.Employee;
import cn.changge.org.mapper.EmployeeMapper;
import cn.changge.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-10-11  17:35
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> queryAll() {
        return employeeMapper.queryAll();
    }
}
