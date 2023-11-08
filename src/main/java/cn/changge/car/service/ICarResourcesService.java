package cn.changge.car.service;

import cn.changge.car.domain.CarResources;
import cn.changge.base.service.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxi
 * @since 2023-11-02
 */
public interface ICarResourcesService extends BaseService<CarResources> {


    List<CarResources> queryByCarId(Long id);
}
