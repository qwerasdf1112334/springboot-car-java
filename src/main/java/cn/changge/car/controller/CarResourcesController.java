package cn.changge.car.controller;

import cn.changge.car.service.ICarResourcesService;
import cn.changge.car.domain.CarResources;
import cn.changge.car.query.CarResourcesQuery;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carResources")
public class CarResourcesController {
    @Autowired
    public ICarResourcesService carResourcesService;


    /**
     * 保存和修改公用的
     * @param carResources  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AxiosResult addOrUpdate(@RequestBody CarResources carResources){
       
            if( carResources.getId()!=null)
                carResourcesService.update(carResources);
            else
                carResourcesService.insert(carResources);
            return AxiosResult.me().setSuccess(true);
  
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AxiosResult delete(@PathVariable("id") Long id){

            carResourcesService.delete(id);
            return AxiosResult.me().setSuccess(true);
      
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AxiosResult get(@PathVariable("id")Long id)
    {

            CarResources carResources = carResourcesService.queryById(id);
            return AxiosResult.me().setData(carResources);
       
    }
    //获取用户
    @GetMapping("/byCarId/{id}")
    public AxiosResult getByCarId(@PathVariable("id")Long id)
    {

        List< CarResources> list = carResourcesService.queryByCarId(id);
        System.out.println(list);
        return AxiosResult.me().setData(list);

    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AxiosResult list(){

        
            List< CarResources> list = carResourcesService.queryAll();
            return AxiosResult.me().setSuccess(true).setData(list);
       
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AxiosResult pageList(@RequestBody CarResourcesQuery query)
    {
        
            PageInfo<CarResources> pageList = carResourcesService.queryPage(query);
            return  AxiosResult.me().setSuccess(true).setData(pageList);
       
    }

    /**
   * 批量删除
   */
    @PatchMapping
    public AxiosResult batchDelete(@RequestBody List<Long> ids)
    {

                carResourcesService.batchDelete(ids);
            return AxiosResult.me().setSuccess(true);

    }
}
