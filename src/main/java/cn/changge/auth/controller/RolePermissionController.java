package cn.changge.auth.controller;

import cn.changge.auth.service.IRolePermissionService;
import cn.changge.auth.domain.RolePermission;
import cn.changge.auth.query.RolePermissionQuery;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {
    @Autowired
    public IRolePermissionService rolePermissionService;


    /**
     * 保存和修改公用的
     * @param rolePermission  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody RolePermission rolePermission){
        try {
            if( rolePermission.getId()!=null)
                rolePermissionService.update(rolePermission);
            else
                rolePermissionService.insert(rolePermission);
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
            rolePermissionService.delete(id);
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
            RolePermission rolePermission = rolePermissionService.queryById(id);
            return AjaxResult.success(rolePermission);
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
            List< RolePermission> list = rolePermissionService.queryAll();
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
    public AjaxResult json(@RequestBody RolePermissionQuery query)
    {
        try {
            PageInfo<RolePermission> pageList = rolePermissionService.queryPage(query);
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
                rolePermissionService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
