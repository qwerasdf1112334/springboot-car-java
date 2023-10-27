package cn.changge.org.service;

import cn.changge.base.service.BaseService;
import cn.changge.org.domain.Department;

import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.service
 * @Author: WangXi
 * @CreateTime: 2023-10-09  16:11
 * @Description: TODO
 * @Version: 1.0
 */
public interface IDempartmentService extends BaseService<Department> {


    List<Department> getDeptTree();
}
