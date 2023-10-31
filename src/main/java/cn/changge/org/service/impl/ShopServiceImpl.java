package cn.changge.org.service.impl;

import cn.changge.base.enums.ResponseCode;
import cn.changge.base.utils.ChangGeAssert;
import cn.changge.base.utils.BaiduAuditUtils;
import cn.changge.base.utils.DistanceUtil;
import cn.changge.base.utils.Point;
import cn.changge.org.domain.OrgEmployee;
import cn.changge.org.domain.Shop;
import cn.changge.org.domain.ShopEmployee;
import cn.changge.org.mapper.OrgEmployeeMapper;
import cn.changge.org.mapper.ShopEmployeeMapper;
import cn.changge.org.service.IShopService;
import cn.changge.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-28
 */
@Service
@Transactional
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {

    @Autowired
    private OrgEmployeeMapper orgEmployeeMapper;
    @Autowired
    private ShopEmployeeMapper shopEmployeeMapper;
    private int autoAuditCount = 0;
    private static final int MAX_AUTO_AUDIT_COUNT = 3;


    @Override
    public void settlement(Shop shop) {

        //校验数据
      //  validate(shop);
        ChangGeAssert.isEq(shop.getAdmin().getPassword(),shop.getAdmin().getConfirmPassword(), ResponseCode.RESPONSE_CODE_400001);
        //审核
        audit(shop);

        //解析经纬度
        Point point = DistanceUtil.getPoint(shop.getAddress());
        Double lat = point.getLat();
        Double lng = point.getLng();
        shop.setLat(lat);
        shop.setLng(lng);


        //保存数据
        shop.setState(shop.STATE_WAIT_AUTID);//设置为待审核
        super.insert(shop);

        shop.getAdmin().setState(OrgEmployee.STATE_WAIT_AUTID);
        orgEmployeeMapper.insert(shop.getAdmin());//保存用户表
        ShopEmployee shopEmployee = new ShopEmployee();
        shopEmployee.setEmployeeId(shop.getAdmin().getId());

        shopEmployee.setIsAdmin(1);//设置为管理员
        shopEmployee.setShopId(shop.getId());
        shopEmployeeMapper.insert(shopEmployee);//保存中间表

    }

    public void audit(Shop shop) {
        // 如果已经超过3次自动审核，则执行手动审核
        if (autoAuditCount >= MAX_AUTO_AUDIT_COUNT) {
         //   manualAudit(shop);
        } else {
            // 执行自动审核
            Map<String, Object> textCensor = BaiduAuditUtils.textCensor(shop.getName());

            if (Boolean.valueOf(textCensor.get("success").toString())) {
                // 通过审核
                autoAuditCount = 0; // 重置自动审核计数
            } else {
                // 不合规，增加自动审核计数
                autoAuditCount++;
                throw new RuntimeException(textCensor.get("message").toString());
            }
        }
    }
    public void validate(Shop shop){
//        //1.非空校验
//
//        ChangGeAssert.isNotNull(shop.getName(), "店铺名字不能为空");
//        ChangGeAssert.isNotNull(shop.getTel(), "电话不能为空");
//        ChangGeAssert.isNotNull(shop.getAddress(), "店铺地址不能为空");
//
//        if(Objects.isNull(shop.getLogo())){
//            throw new RuntimeException("店铺名字不能为空");
//        }
//
//        //employee非空
//        if(Objects.isNull(shop.getAdmin())){
//            throw new RuntimeException("用户不能为空");
//        }
        //进行两次密码校验
//        if (!shop.getAdmin().getPassword().equals(shop.getAdmin().getConfirmPassword())){
//            throw new RuntimeException("两次密码不一致");
//        }

    }
}
