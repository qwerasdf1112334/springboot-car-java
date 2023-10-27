package cn.changge.auth.controller;

import cn.changge.auth.dto.RoleDTO;
import cn.changge.auth.service.IRoleService;
import cn.changge.auth.domain.Role;
import cn.changge.auth.query.RoleQuery;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public IRoleService roleService;

    /**
     * 授权
     *
     */
    @PutMapping("/permission")
    public AjaxResult roleAuth(@RequestBody RoleDTO roleDTO)
    {
        try {
           roleService.roleAuth(roleDTO);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("授权失败"+e.getMessage());
        }
    }
    /**
     * 授权
     *
     */
    @GetMapping("/permission/{id}")
    public AjaxResult queryRolePermissionIds(@PathVariable("id") Long id)
    {
        try {
         List<Long> ids=  roleService.queryRolePermissionIds(id);
            return AjaxResult.success(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("授权失败"+e.getMessage());
        }
    }


    /**
     * 保存和修改公用的
     * @param role  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Role role){
        try {
            if( role.getId()!=null)
                roleService.update(role);
            else
                roleService.insert(role);
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
            roleService.delete(id);
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
            Role role = roleService.queryById(id);
            return AjaxResult.success(role);
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
            List< Role> list = roleService.queryAll();
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
    public AjaxResult json(@RequestBody RoleQuery query)
    {
        try {
            PageInfo<Role> pageList = roleService.queryPage(query);
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
                roleService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
