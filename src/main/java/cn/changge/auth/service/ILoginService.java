package cn.changge.auth.service;

import cn.changge.auth.vo.LoginVo;
import cn.changge.org.domain.OrgEmployee;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.auth.service
 * @Author: WangXi
 * @CreateTime: 2023-10-17  19:32
 * @Description: TODO
 * @Version: 1.0
 */
public interface ILoginService  {
    LoginVo login(OrgEmployee orgEmployee);
}
