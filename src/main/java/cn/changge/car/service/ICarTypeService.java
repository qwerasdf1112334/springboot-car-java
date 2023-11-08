package cn.changge.car.service;

import cn.changge.car.domain.CarType;
import cn.changge.base.service.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxi
 * @since 2023-11-01
 */
public interface ICarTypeService extends BaseService<CarType> {

    List<CarType> treeData();

    List<CarType> getTypes();
}
