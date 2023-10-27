package cn.changge.org.service.impl;

import cn.changge.base.service.impl.BaseServiceImpl;
import cn.changge.org.domain.Department;
import cn.changge.org.mapper.DepartmentMapper;
import cn.changge.org.service.IDempartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-10-09  16:12
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class DempartmentServiceImpl extends BaseServiceImpl<Department> implements IDempartmentService  {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    @Transactional
    public void insert(Department department) {

        departmentMapper.insert(department);
        if (Objects.isNull(department.getParent().getId())){
            department.setPath("/" + department.getId());
        }else {
            Department parent = departmentMapper.queryById(department.getParent().getId());
            department.setPath(parent.getPath()+"/" + department.getId());
        }
        departmentMapper.update(department);
    }



    @Override
    @Transactional
    public void update(Department department) {


        if (Objects.isNull(department.getParent().getId())){
            department.setPath("/" + department.getId());
        }else {
            Department parent = departmentMapper.queryById(department.getParent().getId());
            department.setPath(parent.getPath()+"/" + department.getId());
        }
        departmentMapper.update(department);
    }




    @Override
    public List<Department> getDeptTree() {
        return departmentMapper.getTree();
    }
}
