package cn.changge.org.service.impl;

import cn.changge.base.utils.FastdfsUtil;
import cn.changge.org.service.IFastDfsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.org.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-10-31  17:41
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class IFastDfsServiceImpl implements IFastDfsService {
    @Value("${fastdfs.url}")
    private String fastDfsUrl;
    @Override
    public String upload(byte[] bytes, String extName) {
        return fastDfsUrl+ FastdfsUtil.upload(bytes,extName);
    }

    @Override
    public void delete(String path) {
        System.out.println(path);
        path=path.substring(fastDfsUrl.length());
        FastdfsUtil.delete(path);
    }
}
