package cn.changge.car.controller;

import cn.changge.car.service.ICarOnlineAuditLogService;
import cn.changge.car.domain.CarOnlineAuditLog;
import cn.changge.car.query.CarOnlineAuditLogQuery;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carOnlineAuditLog")
public class CarOnlineAuditLogController {
    @Autowired
    public ICarOnlineAuditLogService carOnlineAuditLogService;


    /**
     * 保存和修改公用的
     * @param carOnlineAuditLog  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AxiosResult addOrUpdate(@RequestBody CarOnlineAuditLog carOnlineAuditLog){
       
            if( carOnlineAuditLog.getId()!=null)
                carOnlineAuditLogService.update(carOnlineAuditLog);
            else
                carOnlineAuditLogService.insert(carOnlineAuditLog);
            return AxiosResult.me().setSuccess(true);
  
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AxiosResult delete(@PathVariable("id") Long id){

            carOnlineAuditLogService.delete(id);
            return AxiosResult.me().setSuccess(true);
      
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AxiosResult get(@PathVariable("id")Long id)
    {

            CarOnlineAuditLog carOnlineAuditLog = carOnlineAuditLogService.queryById(id);
            return AxiosResult.me().setData(carOnlineAuditLog);
       
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AxiosResult list(){

        
            List< CarOnlineAuditLog> list = carOnlineAuditLogService.queryAll();
            return AxiosResult.me().setSuccess(true).setData(list);
       
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AxiosResult pageList(@RequestBody CarOnlineAuditLogQuery query)
    {
        
            PageInfo<CarOnlineAuditLog> pageList = carOnlineAuditLogService.queryPage(query);
            return  AxiosResult.me().setSuccess(true).setData(pageList);
       
    }

    /**
   * 批量删除
   */
    @PatchMapping
    public AxiosResult batchDelete(@RequestBody List<Long> ids)
    {

                carOnlineAuditLogService.batchDelete(ids);
            return AxiosResult.me().setSuccess(true);

    }
}
