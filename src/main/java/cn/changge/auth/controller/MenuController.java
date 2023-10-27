package cn.changge.auth.controller;

import cn.changge.auth.service.IMenuService;
import cn.changge.auth.domain.Menu;
import cn.changge.auth.query.MenuQuery;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    public IMenuService menuService;


    /**
     * 保存和修改公用的
     * @param menu  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Menu menu){
        try {
            if( menu.getId()!=null)
                menuService.update(menu);
            else
                menuService.insert(menu);
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
            menuService.delete(id);
            return AjaxResult.success();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.error();
        }
    }
    //获取用户
    @GetMapping("/menuTree/{id}")
    public AjaxResult menuTree(@PathVariable("id")Long id)
    {
        try {
           List<Menu> menuList = menuService.menuTree(id);
            return AjaxResult.success(menuList);
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
            Menu menu = menuService.queryById(id);
            return AjaxResult.success(menu);
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
            List< Menu> list = menuService.queryAll();
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
    public AjaxResult json(@RequestBody MenuQuery query)
    {
        try {
            PageInfo<Menu> pageList = menuService.queryPage(query);
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
                menuService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
