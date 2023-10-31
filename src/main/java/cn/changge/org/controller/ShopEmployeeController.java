package cn.changge.org.controller;

import cn.changge.org.service.IShopEmployeeService;
import cn.changge.org.domain.ShopEmployee;
import cn.changge.org.query.ShopEmployeeQuery;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopEmployee")
public class ShopEmployeeController {
    @Autowired
    public IShopEmployeeService shopEmployeeService;


    /**
     * 保存和修改公用的
     * @param shopEmployee  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody ShopEmployee shopEmployee){
        try {
            if( shopEmployee.getId()!=null)
                shopEmployeeService.update(shopEmployee);
            else
                shopEmployeeService.insert(shopEmployee);
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
            shopEmployeeService.delete(id);
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
            ShopEmployee shopEmployee = shopEmployeeService.queryById(id);
            return AjaxResult.success(shopEmployee);
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
            List< ShopEmployee> list = shopEmployeeService.queryAll();
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
    public AjaxResult pageList(@RequestBody ShopEmployeeQuery query)
    {
        try {
            PageInfo<ShopEmployee> pageList = shopEmployeeService.queryPage(query);
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
    public AjaxResult batchDelete(@RequestBody List<Long> ids)
    {
        try {
                shopEmployeeService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
