package cn.changge.org.controller;

import cn.changge.org.service.IShopService;
import cn.changge.org.domain.Shop;
import cn.changge.org.query.ShopQuery;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    public IShopService shopService;


    /**
     * 保存和修改公用的
     * @param shop  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Shop shop){
        try {
            if( shop.getId()!=null)
                shopService.update(shop);
            else
                shopService.insert(shop);
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
            shopService.delete(id);
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
            Shop shop = shopService.queryById(id);
            return AjaxResult.success(shop);
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
            List< Shop> list = shopService.queryAll();
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
    public AjaxResult pageList(@RequestBody ShopQuery query)
    {
        try {
            PageInfo<Shop> pageList = shopService.queryPage(query);
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
                shopService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
