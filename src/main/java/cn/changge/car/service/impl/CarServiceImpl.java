package cn.changge.car.service.impl;

import cn.changge.base.context.LoginContext;
import cn.changge.base.utils.VelocityUtils;
import cn.changge.car.domain.Car;
import cn.changge.car.domain.CarDetail;
import cn.changge.car.esdoc.CarDoc;
import cn.changge.car.mapper.CarDetailMapper;
import cn.changge.car.mapper.CarMapper;
import cn.changge.car.repository.CarDocRepository;
import cn.changge.car.service.ICarService;
import cn.changge.base.service.impl.BaseServiceImpl;
import cn.changge.org.domain.Employee;
import cn.changge.org.domain.OrgEmployee;
import cn.changge.org.domain.Shop;
import cn.changge.org.mapper.ShopMapper;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
public class CarServiceImpl extends BaseServiceImpl<Car> implements ICarService {
    @Autowired
    private CarDetailMapper carDetailMapper;

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CarDocRepository carDocRepository;
    @Value("${velocity.templatePath}")
    private  String templateFilePathAndName;
    @Value("${velocity.targetPath}")
    private  String targetFilePath;
    @Override
    public void insert(Car car) {
        car.setCreatetime(new Date());
        car.setState(Car.STATE_OFFSALE);
        //添加销售顾问--默认当前的登录用户
        OrgEmployee employee = LoginContext.getLoginAdmin();
        car.setSellerId(employee.getId());
        //车辆所属的店铺id  和 名字
        Shop shop =shopMapper.queryByEmpId(employee.getId());

        car.setShopId(shop.getId());
        car.setShopname(shop.getName());
        //1.先保存car
        super.insert(car);
        //2.获取车辆详情
        CarDetail carDetail = car.getCarDetail();
        //3.给详情补全一些数据
        carDetail.setCarId(car.getId());
        carDetail.setCartitle(car.getTitle());
        carDetailMapper.insert(carDetail);
    }

    @Override
    public void onSale(List<Long> ids) {
     List<Car>  carList= carMapper.queryByIds(ids);
        List<CarDoc> carDocs = buildCarDocs(carList);
        carDocRepository.saveAll(carDocs);//保存到es
        carMapper.updateOnSale(ids);//改变状态

        //页面静态化
        buildHtml(carList);


    }
    private void buildHtml(List<Car> carList) {
        //生成静态页面
        carList.forEach(car -> {
            VelocityUtils.staticByTemplate(car,templateFilePathAndName, targetFilePath+car.getId()+".html");
        });


    }
    @Override
    public Car carDetail(Long id) {
        List<Long> longs = Arrays.asList(id);
        List<Car> cars = carMapper.queryByIds(longs);
        if (cars.size()>0){
            return cars.get(0);
        }
        return null;
    }

    private List<CarDoc> buildCarDocs(List<Car> carList){
        ArrayList<CarDoc> carDocs = new ArrayList<>();
        for (Car car : carList) {
            CarDoc carDoc = new CarDoc();
            BeanUtils.copyProperties(car, carDoc);//拷贝数据到cardoc
            Shop shop = car.getShop();
            if (shop!=null){
                carDoc.setShopAddress(shop.getAddress());
//                carDoc.setLng(shop.getLng());
//                carDoc.setLat(shop.getLat());
                carDoc.setShopPoint(new GeoPoint(shop.getLat(),shop.getLng()));
            }
            carDoc.setShopName(car.getShopname());
            CarDetail carDetail = car.getCarDetail();
           if (carDetail!=null){
               carDoc.setCarInfo(carDetail.getInfo());
           }
           carDoc.setTypeName(car.getCarType()==null?"":car.getCarType().getName());
           carDoc.setOnsaletime(new Date());
           carDocs.add(carDoc);
        }
        return carDocs;

    }

}
