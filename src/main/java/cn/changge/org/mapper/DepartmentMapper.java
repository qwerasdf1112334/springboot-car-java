package cn.changge.org.mapper;

import cn.changge.base.mapper.BaseMapper;
import cn.changge.org.domain.Department;

import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.mapper
 * @Author: WangXi
 * @CreateTime: 2023-10-09  13:10
 * @Description: TODO
 * @Version: 1.0
 */
public interface DepartmentMapper extends BaseMapper<Department> {


    List<Department> getTree();
}
