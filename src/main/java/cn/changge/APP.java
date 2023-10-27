package cn.changge;

import cn.changge.base.interceptor.AuthPermInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge
 * @Author: WangXi
 * @CreateTime: 2023-10-09  12:44
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("cn.changge.*.mapper")
@ServletComponentScan("cn.changge.base.listener")
public class APP implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(APP.class,args);
    }
    @Autowired
    private AuthPermInterceptor authPermInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authPermInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/loginOut");
    }
}
