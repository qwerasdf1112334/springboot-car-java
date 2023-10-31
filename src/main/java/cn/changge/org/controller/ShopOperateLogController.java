package cn.changge.org.controller;

import cn.changge.org.service.IShopOperateLogService;
import cn.changge.org.domain.ShopOperateLog;
import cn.changge.org.query.ShopOperateLogQuery;
import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopOperateLog")
public class ShopOperateLogController {
    @Autowired
    public IShopOperateLogService shopOperateLogService;


    /**
     * 保存和修改公用的
     * @param shopOperateLog  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody ShopOperateLog shopOperateLog){
        try {
            if( shopOperateLog.getId()!=null)
                shopOperateLogService.update(shopOperateLog);
            else
                shopOperateLogService.insert(shopOperateLog);
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
            shopOperateLogService.delete(id);
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
            ShopOperateLog shopOperateLog = shopOperateLogService.queryById(id);
            return AjaxResult.success(shopOperateLog);
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
            List< ShopOperateLog> list = shopOperateLogService.queryAll();
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
    public AjaxResult pageList(@RequestBody ShopOperateLogQuery query)
    {
        try {
            PageInfo<ShopOperateLog> pageList = shopOperateLogService.queryPage(query);
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
                shopOperateLogService.batchDelete(ids);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
