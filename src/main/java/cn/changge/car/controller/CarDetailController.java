package cn.changge.car.controller;

import cn.changge.car.service.ICarDetailService;
import cn.changge.car.domain.CarDetail;
import cn.changge.car.query.CarDetailQuery;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carDetail")
public class CarDetailController {
    @Autowired
    public ICarDetailService carDetailService;


    /**
     * 保存和修改公用的
     * @param carDetail  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AxiosResult addOrUpdate(@RequestBody CarDetail carDetail){
       
            if( carDetail.getId()!=null)
                carDetailService.update(carDetail);
            else
                carDetailService.insert(carDetail);
            return AxiosResult.me().setSuccess(true);
  
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AxiosResult delete(@PathVariable("id") Long id){

            carDetailService.delete(id);
            return AxiosResult.me().setSuccess(true);
      
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AxiosResult get(@PathVariable("id")Long id)
    {

            CarDetail carDetail = carDetailService.queryById(id);
            return AxiosResult.me().setData(carDetail);
       
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AxiosResult list(){

        
            List< CarDetail> list = carDetailService.queryAll();
            return AxiosResult.me().setSuccess(true).setData(list);
       
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AxiosResult pageList(@RequestBody CarDetailQuery query)
    {
        
            PageInfo<CarDetail> pageList = carDetailService.queryPage(query);
            return  AxiosResult.me().setSuccess(true).setData(pageList);
       
    }

    /**
   * 批量删除
   */
    @PatchMapping
    public AxiosResult batchDelete(@RequestBody List<Long> ids)
    {

                carDetailService.batchDelete(ids);
            return AxiosResult.me().setSuccess(true);

    }
}
