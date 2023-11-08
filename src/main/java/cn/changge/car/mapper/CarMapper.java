package cn.changge.car.mapper;

import cn.changge.car.domain.Car;
import cn.changge.base.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangxi
 * @since 2023-11-02
 */
public interface CarMapper extends BaseMapper<Car> {



    List<Car> queryByIds(List<Long> ids);

    void updateOnSale(List<Long> ids);
}
