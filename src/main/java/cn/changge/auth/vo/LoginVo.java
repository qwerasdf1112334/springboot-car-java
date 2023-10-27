package cn.changge.auth.vo;

import cn.changge.org.domain.OrgEmployee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.auth.vo
 * @Author: WangXi
 * @CreateTime: 2023-10-17  19:15
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {
    private String token;
    private OrgEmployee user;
}
