package cn.changge.car.service;

import cn.changge.base.service.BaseService;
import cn.changge.base.utils.PageInfo;
import cn.changge.car.esdoc.CarDoc;
import cn.changge.car.query.CarDocQuery;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.car.service
 * @Author: WangXi
 * @CreateTime: 2023-11-03  21:13
 * @Description: TODO
 * @Version: 1.0
 */
public interface ICarDocService {
    PageInfo<CarDoc> queryPage(CarDocQuery query);
}
