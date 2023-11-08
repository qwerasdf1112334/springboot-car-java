package cn.changge.car.controller;

import cn.changge.base.utils.AjaxResult;
import cn.changge.car.service.ICarTypeService;
import cn.changge.car.domain.CarType;
import cn.changge.car.query.CarTypeQuery;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carType")
public class CarTypeController {
    @Autowired
    public ICarTypeService carTypeService;


    /**
     * 保存和修改公用的
     * @param carType  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AxiosResult addOrUpdate(@RequestBody CarType carType){
       
            if( carType.getId()!=null)
                carTypeService.update(carType);
            else
                carTypeService.insert(carType);
            return AxiosResult.me().setSuccess(true);
  
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AxiosResult delete(@PathVariable("id") Long id){

            carTypeService.delete(id);
            return AxiosResult.me().setSuccess(true);
      
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AxiosResult get(@PathVariable("id")Long id) {

            CarType carType = carTypeService.queryById(id);
            return AxiosResult.me().setData(carType);


    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AxiosResult list(){

        
            List< CarType> list = carTypeService.queryAll();
            return AxiosResult.me().setSuccess(true).setData(list);
       
    }
    /**
     *车辆类型面包屑
     */
    @GetMapping("/treeData")
    public AxiosResult treeDate(){


        List< CarType> list = carTypeService.treeData();

        return AxiosResult.me().setSuccess(true).setData(list);

    }
    /**
     用来加载树形列表
     */
    @GetMapping("/getTypes")
    public AxiosResult getTypes()
    {
        List<CarType> list= carTypeService.getTypes();
        return AxiosResult.me().setData(list);

    }



    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AxiosResult pageList(@RequestBody CarTypeQuery query)
    {
        
            PageInfo<CarType> pageList = carTypeService.queryPage(query);
            return  AxiosResult.me().setSuccess(true).setData(pageList);
       
    }

    /**
   * 批量删除
   */
    @PatchMapping
    public AxiosResult batchDelete(@RequestBody List<Long> ids)
    {

                carTypeService.batchDelete(ids);
            return AxiosResult.me().setSuccess(true);

    }
}
