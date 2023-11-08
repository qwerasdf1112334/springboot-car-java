package cn.changge.base.commons;

import cn.changge.base.domain.dto.RegFormDto;
import cn.changge.base.service.IVerifyServer;
import cn.changge.base.utils.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.base.commons
 * @Author: WangXi
 * @CreateTime: 2023-11-08  17:00
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/code")
public class VerifyCodeController {
    @Autowired
    private IVerifyServer verifyServer;


    @GetMapping("/imgCode/{uuid}")
    public AxiosResult getImgCode(@PathVariable("uuid") String uuid){
        String imgCode=  verifyServer.creatImgCode(uuid);

        return AxiosResult.me().setData(imgCode);
    }
    @PostMapping("/phone")
    public AxiosResult phoneCode(@RequestBody @Valid RegFormDto regFormDto){
        System.out.println(regFormDto);
        verifyServer.phoneCode(regFormDto);

        return AxiosResult.me();
    }
}
