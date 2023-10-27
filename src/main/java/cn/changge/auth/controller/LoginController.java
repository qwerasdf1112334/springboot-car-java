package cn.changge.auth.controller;

import cn.changge.auth.service.ILoginService;
import cn.changge.auth.vo.LoginVo;
import cn.changge.base.utils.AjaxResult;
import cn.changge.org.domain.Employee;
import cn.changge.org.domain.OrgEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.auth.controller
 * @Author: WangXi
 * @CreateTime: 2023-10-17  19:10
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILoginService loginServer;

    @PutMapping
    public AjaxResult login(@RequestBody OrgEmployee orgEmployee){
        try {
            LoginVo login= loginServer.login(orgEmployee);
            return AjaxResult.success(login);
        } catch (Exception e) {

            return AjaxResult.error("登录失败");
        }


    }
}
