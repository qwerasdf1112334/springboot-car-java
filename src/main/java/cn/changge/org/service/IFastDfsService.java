package cn.changge.org.service;

/**
 * @BelongsProject: springboot-car-java
 * @BelongsPackage: cn.changge.org.service
 * @Author: WangXi
 * @CreateTime: 2023-10-31  17:40
 * @Description: TODO
 * @Version: 1.0
 */
public interface IFastDfsService {
   String upload(byte[] bytes, String extName) ;

   void delete(String path);
}
