package cn.changge.base.listener;

import cn.changge.auth.service.IPermissionScanService;
import cn.changge.auth.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.base.listener
 * @Author: WangXi
 * @CreateTime: 2023-10-16  15:55
 * @Description: TODO
 * @Version: 1.0
 */
@WebListener
public class PermissionListener implements ServletContextListener {
    @Autowired
    private IPermissionScanService Service;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("开始初始化");
        permissionService.deleteAll();
        Service.scan();
        System.out.println("初始化完成");
    }
}
