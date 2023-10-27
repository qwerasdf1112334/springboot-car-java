package cn.changge.auth.controller;

import cn.changge.auth.service.IEmployeeRoleService;
import cn.changge.auth.domain.EmployeeRole;
import cn.changge.auth.query.EmployeeRoleQuery;
import cn.changge.base.annotation.ChangGePermission;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeRole")
@ChangGePermission(name = "员工和角色关系表")
public class EmployeeRoleController {
    @Autowired
    public IEmployeeRoleService employeeRoleService;


    /**
     * 保存和修改公用的
     * @param employeeRole  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    @ChangGePermission(name = "员工和角色保存和修改")
    public AjaxResult addOrUpdate(@RequestBody EmployeeRole employeeRole){
        try {
            if( employeeRole.getId()!=null)
                employeeRoleService.update(employeeRole);
            else
                employeeRoleService.insert(employeeRole);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            employeeRoleService.delete(id);
            return AjaxResult.success();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.error();
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            EmployeeRole employeeRole = employeeRoleService.queryById(id);
            return AjaxResult.success(employeeRole);
        } catch (Exception e) {
            e.printStackTrace();
           return AjaxResult.error();
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AjaxResult list(){

        try {
            List< EmployeeRole> list = employeeRoleService.queryAll();
            return AjaxResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AjaxResult json(@RequestBody EmployeeRoleQuery query)
    {
        try {
            PageInfo<EmployeeRole> pageList = employeeRoleService.queryPage(query);
            return AjaxResult.success(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    /**
   * 批量删除
   */
    @PatchMapping
    public AjaxResult json(@RequestBody List<Long> ids)
    {
        try {
                employeeRoleService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
