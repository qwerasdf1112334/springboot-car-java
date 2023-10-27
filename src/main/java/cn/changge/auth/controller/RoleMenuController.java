package cn.changge.auth.controller;

import cn.changge.auth.service.IRoleMenuService;
import cn.changge.auth.domain.RoleMenu;
import cn.changge.auth.query.RoleMenuQuery;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    @Autowired
    public IRoleMenuService roleMenuService;


    /**
     * 保存和修改公用的
     * @param roleMenu  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody RoleMenu roleMenu){
        try {
            if( roleMenu.getId()!=null)
                roleMenuService.update(roleMenu);
            else
                roleMenuService.insert(roleMenu);
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
            roleMenuService.delete(id);
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
            RoleMenu roleMenu = roleMenuService.queryById(id);
            return AjaxResult.success(roleMenu);
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
            List< RoleMenu> list = roleMenuService.queryAll();
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
    public AjaxResult json(@RequestBody RoleMenuQuery query)
    {
        try {
            PageInfo<RoleMenu> pageList = roleMenuService.queryPage(query);
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
                roleMenuService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
