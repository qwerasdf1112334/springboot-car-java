package cn.changge.org.service.impl;

import cn.changge.base.enums.ResponseCode;
import cn.changge.base.utils.ChangGeAssert;
import cn.changge.base.utils.BaiduAuditUtils;
import cn.changge.base.utils.DistanceUtil;
import cn.changge.base.utils.Point;
import cn.changge.org.domain.OrgEmployee;
import cn.changge.org.domain.Shop;
import cn.changge.org.domain.ShopEmployee;
import cn.changge.org.domain.ShopOperateLog;
import cn.changge.org.mapper.OrgEmployeeMapper;
import cn.changge.org.mapper.ShopEmployeeMapper;
import cn.changge.org.mapper.ShopOperateLogMapper;
import cn.changge.org.service.IShopService;
import cn.changge.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

   @Resource
    private OrgEmployeeMapper orgEmployeeMapper;
    @Resource
    private ShopEmployeeMapper shopEmployeeMapper;
    @Resource
    private ShopOperateLogMapper shopOperateLogMapper;
    //注入邮件对象
    @Resource
    private JavaMailSender javaMailSender;
    private int autoAuditCount = 0;
    private static final int MAX_AUTO_AUDIT_COUNT = 3;


    @Override
    public void settlement(Shop shop) {

        //校验数据
      //  validate(shop);
        ChangGeAssert.isEq(shop.getAdmin().getPassword(),shop.getAdmin().getConfirmPassword(), ResponseCode.RESPONSE_CODE_400001);

        audit(shop);
        //解析经纬度
        Point point = DistanceUtil.getPoint(shop.getAddress());
        Double lat = point.getLat();
        Double lng = point.getLng();
        shop.setLat(lat);
        shop.setLng(lng);


        //保存数据

        super.insert(shop);

        shop.getAdmin().setState(OrgEmployee.STATE_WAIT_AUTID);
        orgEmployeeMapper.insert(shop.getAdmin());//保存用户表
        ShopEmployee shopEmployee = new ShopEmployee();
        shopEmployee.setEmployeeId(shop.getAdmin().getId());

        shopEmployee.setIsAdmin(1);//设置为管理员
        shopEmployee.setShopId(shop.getId());
        shopEmployeeMapper.insert(shopEmployee);//保存中间表

    }

    @Override
    public void shenHe(Shop shop) {
        manualAudit(shop);
        super.update(shop);
    }

    public void audit(Shop shop) {
        // 如果已经超过3次自动审核，则保存到数据库方便手动审核
        if (autoAuditCount >= MAX_AUTO_AUDIT_COUNT) {
            shop.setState(shop.STATE_WAIT_AUTID);//设置为待审核
        } else {
            // 执行自动审核
            Map<String, Object> textCensor = BaiduAuditUtils.textCensor(shop.getName());
                if(!textCensor.isEmpty()&&textCensor.containsKey("success")){
                shop.setState(shop.STATE_WAIT_ACTIVE);//设置审核通过待激活
                //保存到日志表
                ShopOperateLog shopOperateLog = new ShopOperateLog();
                shopOperateLog.setShop(shop);
                shopOperateLog.setOperateType(shop.STATE_WAIT_ACTIVE);//设置为审核通过，待激活
                shopOperateLog.setNote("系统自动审核通过");
                shopOperateLogMapper.insert(shopOperateLog);
                //邮件对象
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                //设置发件人
                simpleMailMessage.setFrom("1678825097@qq.com");
                //设置收件人
                simpleMailMessage.setTo("1714422316@qq.com");
                //设置主题
                simpleMailMessage.setSubject("恭喜激活你店铺审核通过！");
                //设置正文
                simpleMailMessage.setText("点击链接激活店铺，http://localhost:8081/shop/"+shop.getId()+"");
                //发送邮件
                javaMailSender.send(simpleMailMessage);

                // 通过审核
                autoAuditCount = 0; // 重置自动审核计数
            } else {
                // 不合规，增加自动审核计数
                autoAuditCount++;
                throw new RuntimeException(textCensor.get("message").toString());
            }
        }
    }
    //手动审核
    public  void manualAudit(Shop shop){
        if (shop.getState()==2) {



            ShopOperateLog shopOperateLog = new ShopOperateLog();
            shopOperateLog.setNote("手动审核通过");
            shopOperateLog.setShop(shop);
            shopOperateLog.setOperateType(shop.STATE_WAIT_ACTIVE);//设置为审核通过
            shopOperateLogMapper.insert(shopOperateLog);
            //邮件对象
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //设置发件人
            simpleMailMessage.setFrom("1678825097@qq.com");
            //设置收件人
            simpleMailMessage.setTo("1714422316@qq.com");
            //设置主题
            simpleMailMessage.setSubject("恭喜激活你店铺审核通过！");
            //设置正文
            simpleMailMessage.setText("点击链接激活店铺，http://localhost:8081/shop/"+shop.getId()+"");
            //发送邮件
            javaMailSender.send(simpleMailMessage);
        }
        else {
            ShopOperateLog shopOperateLog = new ShopOperateLog();
            shopOperateLog.setNote("驳回");
            shopOperateLog.setShop(shop);
            shopOperateLog.setOperateType(shop.STATE_REJECT_AUDIT);//设置为驳回
            shopOperateLogMapper.insert(shopOperateLog);
            //邮件对象
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //设置发件人
            simpleMailMessage.setFrom("1678825097@qq.com");
            //设置收件人
            simpleMailMessage.setTo("1714422316@qq.com");
            //设置主题
            simpleMailMessage.setSubject("你店铺审核被驳回！");
            //设置正文
            simpleMailMessage.setText("点击链接查看驳回原因店铺，http://localhost:8081/shop/"+shop.getMsg()+"");
            //发送邮件
            javaMailSender.send(simpleMailMessage);

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
