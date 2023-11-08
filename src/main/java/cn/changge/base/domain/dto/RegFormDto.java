package cn.changge.base.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.base.domain.dto
 * @Author: WangXi
 * @CreateTime: 2023-11-08  19:21
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegFormDto {
    @NotNull(message = "手机号不能为空")
    private String phone;
    @NotNull(message = "图形验证码不能为空")
    private String imgCode;
    //@NotNull(message = "手机验证码不能为空")
    private String phoneCode;
    //@NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "参数有误,请仔细核对")
    private String uuid;

    private String codeType;
}
