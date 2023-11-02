package cn.changge.car.mapper;

import cn.changge.car.domain.CarType;
import cn.changge.base.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangxi
 * @since 2023-11-01
 */
public interface CarTypeMapper extends BaseMapper<CarType> {

    List<CarType> queryByPid(Long pid);
}
