package cn.changge.org.controller;

import cn.changge.base.annotation.ChangGePermission;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import cn.changge.org.domain.Department;
import cn.changge.org.service.IDempartmentService;
import cn.changge.org.vo.DepartmentVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.controller
 * @Author: WangXi
 * @CreateTime: 2023-10-09  16:41
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/dept")
@ChangGePermission(name = "部门管理表")
//接口文档的注解
@Api(value = "部门的API",description="部门相关的CRUD功能")
public class DepartmentController {
    @Autowired
    private IDempartmentService service;
    /**
     * @description:
     * @author: WangXi
     * @date: 2023/10/9 17:09
     * @param: [department]
     * @return: cn.changge.base.utils.AjaxResult
     **/
    @PutMapping
    @ChangGePermission(name = "部门管理保存和修改")
    public AjaxResult insertAndUpdate(@RequestBody Department department){
        try {
            if (Optional.ofNullable(department.getId()).isPresent()){
                service.update(department);
            }else {
                service.insert(department);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
    /**
     * @description:
     * @author: WangXi
     * @date: 2023/10/9 17:09
     * @param: [id]
     * @return: cn.changge.base.utils.AjaxResult
     **/
    @ChangGePermission(name = "部门删除通过id")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            service.delete(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }

    }
    /**
     * @description:
     * @author: WangXi 
     * @date: 2023/10/9 17:13
     * @param: [id]
     * @return: cn.changge.base.utils.AjaxResult
     **/
    @GetMapping("/{id}")
    @ChangGePermission(name = "部门管理查询通过id")
    public AjaxResult findById(@PathVariable("id") Long id){
        try {
            return AjaxResult.success(service.queryById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
    /**
     * @description:
     * @author: WangXi 
     * @date: 2023/10/9 17:13
     * @param: []
     * @return: cn.changge.base.utils.AjaxResult
     **/
    @GetMapping
    @ChangGePermission(name = "部门管理查询所有")
    public AjaxResult findAll(){
        try {
            return AjaxResult.success(service.queryAll());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
    /**
     * @description:
     * @author: WangXi
     * @date: 2023/10/10 22:10
     * @param: [departmentVo]
     * @return: cn.changge.base.utils.AjaxResult
     **/
    @PostMapping
    @ChangGePermission(name = "部门管理分页查询")
    public AjaxResult pageList(@RequestBody DepartmentVo departmentVo){
        try {
            PageInfo<Department> pageInfo =service.queryPage(departmentVo);
            pageInfo.getData().forEach(System.out::println);
            return AjaxResult.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
    /**
     * @description:
     * @author: WangXi 
     * @date: 2023/10/10 22:25
     * @param: [ids]
     * @return: cn.changge.base.utils.AjaxResult
     **/
    @PatchMapping
    @ChangGePermission(name = "部门管理批量删除")
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
            service.batchDelete(ids);
            return  AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return  AjaxResult.error();
        }
    }
    @GetMapping("/tree")
    public AjaxResult getDeptTree(){
        try {
            List<Department> deptTree=service.getDeptTree();
            return AjaxResult.success(deptTree);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

}
