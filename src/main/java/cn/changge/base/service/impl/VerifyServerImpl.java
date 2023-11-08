package cn.changge.base.service.impl;

import cn.changge.base.constants.SystemConstants;
import cn.changge.base.domain.dto.RegFormDto;
import cn.changge.base.exception.BusinessException;
import cn.changge.base.service.IVerifyServer;
import cn.changge.base.utils.ChangGeAssert;
import cn.changge.base.utils.SendCodeUtils;
import cn.changge.base.utils.VerifyCodeUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.base.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-11-08  17:09
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class VerifyServerImpl implements IVerifyServer {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public String creatImgCode(String uuid) {
        try {
            //生成随机4位的验证码
            String code = VerifyCodeUtils.generateVerifyCode(4);
            //储存到redis设置5分钟过期
            redisTemplate.opsForValue().set(uuid, code,5, TimeUnit.MINUTES);
            //生成验证码并返回
            String verifyCode = VerifyCodeUtils.VerifyCode(100, 40, code);

            return verifyCode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void phoneCode(RegFormDto regFormDto) {
        //先判断进来请求里面携带验证码功能字段,使用常量加手机号作为key
        String redisKey=null;

        switch (regFormDto.getCodeType()){

            case SystemConstants.REDIS_CODE_REGISTER:
                redisKey=SystemConstants.REDIS_CODE_REGISTER+":"+regFormDto.getPhone();break;
            case SystemConstants.REDIS_CODE_LOGIN:
                redisKey=SystemConstants.REDIS_CODE_LOGIN+":"+regFormDto.getPhone();break;
            case SystemConstants.REDIS_CODE_BIND:
                redisKey=SystemConstants.REDIS_CODE_BIND+":"+regFormDto.getPhone();break;
            default:
                redisKey=regFormDto.getPhone();
        }
        //根据输入的uuid获取图形验证码
        if (regFormDto.getCodeType().equals(SystemConstants.REDIS_CODE_REGISTER)){
            String imgCode = redisTemplate.opsForValue().get(regFormDto.getUuid()).toString();

            ChangGeAssert.isEq(regFormDto.getImgCode(), imgCode, "图形验证码不一致");

        }
        //生成手机验证码
        String phoneCode = VerifyCodeUtils.generateVerifyCode(6, "0123456789");
        /**          key                     value
         *    REGISTER:15008495702           9528:16985516166 **/
        //使用redisKey尝试从redis里取值
        Object redisPhoneCode = redisTemplate.opsForValue().get(redisKey);
        //不为空表示用户已经发送过验证码
        if (redisPhoneCode!=null){
            //取出上次存入的时间
            Long oldTime = Long.valueOf(redisPhoneCode.toString().split(":")[1]);
            if (System.currentTimeMillis()-oldTime<60*1000){
                //如果距离上次小于1min
                throw new BusinessException("发送短信太频繁，请1min后再试！");

            }
            //如果大于1min就把上次的验证码再发一次
            phoneCode =redisPhoneCode.toString().split(":")[0];

        }

        //将验证码存入redis
        redisTemplate.opsForValue().set(redisKey, phoneCode,5,TimeUnit.MINUTES);
        boolean b = SendCodeUtils.sendCode(regFormDto.getPhone(), phoneCode);
        System.out.println(b);



    }

}
