package cn.changge.car.service.impl;

import cn.changge.base.constants.SystemConstants;
import cn.changge.car.domain.CarType;
import cn.changge.car.mapper.CarTypeMapper;
import cn.changge.car.service.ICarTypeService;
import cn.changge.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangxi
 * @since 2023-11-01
 */
@Service
public class CarTypeServiceImpl extends BaseServiceImpl<CarType> implements ICarTypeService {
    @Autowired
    private CarTypeMapper carTypeMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
 //   @Cacheable(cacheNames = SystemConstants.TREE_TYPE,key = "'ALL'")
    public List<CarType> treeData() {
//        List<CarType> values = (List<CarType>)redisTemplate.opsForValue().get(SystemConstants.TREE_TYPE);//拿到redis存的数据
//        if (values!=null){
//            return values;
//        }


        //全部查出来
        List<CarType> carTypes = carTypeMapper.queryAll();

        HashMap<Long, CarType> map = new HashMap<>();
        for (CarType carType : carTypes) {
            map.put(carType.getId(),carType);//将id作为key
        }
        ArrayList<CarType> arrayList = new ArrayList<>();
        for (CarType carType : carTypes) {
            if (carType.getPid()==0){
                arrayList.add(carType);//将pid为0的放入arraylist
            }else {
                CarType parent = map.get(carType.getPid());//不是顶级父类的，在map里找到自己父类
                parent.getChildren().add(carType);//，将自己放到children节点里
            }
        }
   //     redisTemplate.opsForValue().set(SystemConstants.TREE_TYPE, arrayList);
        return arrayList;
     //   return treeDataRecursion(0l);
    }

    @Override
    public List<CarType> getTypes() {
        return carTypeMapper.queryByPid(0L);
    }

    //递归查询
    private List<CarType> treeDataRecursion(Long pid){
        List<CarType> carTypes = carTypeMapper.queryByPid(pid);//查找顶级父类pid为0l
        if (carTypes!=null){
            for (CarType carType : carTypes) {
                List<CarType> children = treeDataRecursion(carType.getId());//查找的以父类id为pid的子类
                carType.setChildren(children);//将子类设置到父类child里
            }
        }
        return carTypes;
    }

//    @Override
//    @CacheEvict(cacheNames = SystemConstants.TREE_TYPE,key = "'ALL'")
//    public void insert(CarType carType) {
//        super.insert(carType);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = SystemConstants.TREE_TYPE,key = "'ALL'")
//    public void delete(Serializable id) {
//        super.delete(id);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = SystemConstants.TREE_TYPE,key = "'ALL'")
//    public void update(CarType carType) {
//        super.update(carType);
//    }
}
