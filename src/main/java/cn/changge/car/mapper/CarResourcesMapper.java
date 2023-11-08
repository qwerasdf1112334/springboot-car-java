package cn.changge.car.mapper;

import cn.changge.car.domain.CarResources;
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
public interface CarResourcesMapper extends BaseMapper<CarResources> {

    List<CarResources> queryByCarId(Long id);
}
