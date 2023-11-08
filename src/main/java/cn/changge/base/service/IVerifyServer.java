package cn.changge.base.service;

import cn.changge.base.domain.dto.RegFormDto;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.base.service
 * @Author: WangXi
 * @CreateTime: 2023-11-08  17:08
 * @Description: TODO
 * @Version: 1.0
 */
public interface IVerifyServer {
    String creatImgCode(String uuid);

    void phoneCode(RegFormDto regFormDto);
}
