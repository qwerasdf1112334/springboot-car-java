package cn.changge.auth.service.impl;

import cn.changge.auth.service.ILoginService;
import cn.changge.auth.vo.LoginVo;
import cn.changge.base.context.LoginContext;
import cn.changge.org.domain.OrgEmployee;
import cn.changge.org.mapper.OrgEmployeeMapper;
import cn.changge.org.service.IOrgEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.auth.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-10-17  19:33
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class ILoginServiceImpl implements ILoginService {
    @Autowired
    private IOrgEmployeeService orgEmployeeService;
    @Override
    public LoginVo login(OrgEmployee orgEmployee) {
        //判断数据是否为空
        if (Objects.isNull(orgEmployee.getUsername())||Objects.isNull(orgEmployee.getPassword()))throw  new RuntimeException("账号或密码不能为空");
        //根据账户名字查对象
        OrgEmployee user = orgEmployeeService.queryByName(orgEmployee.getUsername());
        //
        if (Objects.isNull(user) || !Objects.equals(user.getPassword(), orgEmployee.getPassword())) {
            throw new RuntimeException("账号或密码不正确");
        }
        String token = UUID.randomUUID().toString();
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        LoginContext.loginMap.put(token, user);
        user.setEmail(null);
        user.setPassword(null);
        loginVo.setUser(user);

        return loginVo;
    }
}
