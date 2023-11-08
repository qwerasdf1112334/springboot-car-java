package cn.changge.car.controller;

import cn.changge.base.utils.AjaxResult;
import cn.changge.car.service.ICarService;
import cn.changge.car.domain.Car;
import cn.changge.car.query.CarQuery;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    public ICarService carService;


    /**
     * 保存和修改公用的
     * @param car  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AxiosResult addOrUpdate(@RequestBody Car car){
       
            if( car.getId()!=null)
                carService.update(car);
            else
                carService.insert(car);
            return AxiosResult.me().setSuccess(true);
  
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AxiosResult delete(@PathVariable("id") Long id){

            carService.delete(id);
            return AxiosResult.me().setSuccess(true);
      
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AxiosResult get(@PathVariable("id")Long id)
    {

            Car car = carService.queryById(id);
            return AxiosResult.me().setData(car);
       
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AxiosResult list(){

        
            List< Car> list = carService.queryAll();
            return AxiosResult.me().setSuccess(true).setData(list);
       
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AxiosResult pageList(@RequestBody CarQuery query)
    {
        
            PageInfo<Car> pageList = carService.queryPage(query);
            return  AxiosResult.me().setSuccess(true).setData(pageList);
       
    }

    /**
   * 批量删除
   */
    @PatchMapping
    public AxiosResult batchDelete(@RequestBody List<Long> ids)
    {

                carService.batchDelete(ids);
            return AxiosResult.me().setSuccess(true);

    }
    /**
     * 上架
     */
    @PostMapping("/onSale")
    public AxiosResult onSale(@RequestBody List<Long> ids)
    {

        carService.onSale(ids);
        return AxiosResult.me();

    }
    @GetMapping("/carDetail/{id}")
    public AxiosResult carDetail(@PathVariable("id") Long id){
        Car car = carService.carDetail(id);
        System.out.println(car);
        return AxiosResult.me().setData(car);
    }
}
