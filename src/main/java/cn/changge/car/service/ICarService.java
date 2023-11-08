package cn.changge.car.service;

import cn.changge.car.domain.Car;
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
public interface ICarService extends BaseService<Car> {

    void onSale(List<Long> ids);
    Car carDetail(Long id);
}
