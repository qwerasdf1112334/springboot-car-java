package cn.changge.car.service.impl;

import cn.changge.car.domain.CarResources;
import cn.changge.car.mapper.CarResourcesMapper;
import cn.changge.car.service.ICarResourcesService;
import cn.changge.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangxi
 * @since 2023-11-02
 */
@Service
public class CarResourcesServiceImpl extends BaseServiceImpl<CarResources> implements ICarResourcesService {
    @Autowired
    private CarResourcesMapper carResourcesMapper;

    @Override
    public List<CarResources> queryByCarId(Long id) {
        return carResourcesMapper.queryByCarId(id);

    }
}
