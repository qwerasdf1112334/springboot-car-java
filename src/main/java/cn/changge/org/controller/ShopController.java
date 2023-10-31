package cn.changge.org.controller;

import cn.changge.base.enums.ResponseCode;
import cn.changge.base.utils.AxiosResult;
import cn.changge.org.service.IShopService;
import cn.changge.org.domain.Shop;
import cn.changge.org.query.ShopQuery;
import cn.changge.base.utils.AxiosResult;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    public IShopService shopService;

    @PostMapping("/settlement")
    public AxiosResult settlement(@RequestBody @Valid Shop shop) {

        shopService.settlement(shop);
        return AxiosResult.me().setSuccess(true).setMessage("入驻成功");

    }


    /**
     * 保存和修改公用的
     *
     * @param shop 传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AxiosResult addOrUpdate(@RequestBody Shop shop) {

        if (shop.getId() != null)
            shopService.update(shop);
        else
            shopService.insert(shop);
        return AxiosResult.me().setSuccess(true);


    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public AxiosResult delete(@PathVariable("id") Long id) {

        shopService.delete(id);
        return AxiosResult.me().setSuccess(true);

    }

    //获取用户
    @GetMapping("/{id}")
    public AxiosResult get(@PathVariable("id") Long id) {

        Shop shop = shopService.queryById(id);
        return AxiosResult.me().setSuccess(true).setData(shop);

    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @GetMapping
    public AxiosResult list() {


        List<Shop> list = shopService.queryAll();

        return AxiosResult.me().setSuccess(true).setData(list);


    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @PostMapping
    public AxiosResult pageList(@RequestBody ShopQuery query) {

        PageInfo<Shop> pageList = shopService.queryPage(query);

        return AxiosResult.me().setSuccess(true).setData(pageList);

    }

    /**
     * 批量删除
     */
    @PatchMapping
    public AxiosResult batchDelete(@RequestBody List<Long> ids) {

        shopService.batchDelete(ids);
        return AxiosResult.me().setSuccess(true);


    }
}
