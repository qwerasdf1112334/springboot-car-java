package cn.changge.car.controller;

import cn.changge.base.utils.AjaxResult;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.PageInfo;
import cn.changge.car.esdoc.CarDoc;
import cn.changge.car.query.CarDocQuery;
import cn.changge.car.service.ICarDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carDoc")
public class CarDocController {
    @Autowired
    private ICarDocService carDocService;
    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AxiosResult queryPage(@RequestBody CarDocQuery query)
    {

        PageInfo<CarDoc> pageList = carDocService.queryPage(query);
        return AxiosResult.me().setData(pageList);

    }



}