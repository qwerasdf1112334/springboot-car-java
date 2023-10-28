package cn.changge.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2  //2.x用 @EnableSwagger2WebMvc
public class Knife4jConfig {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("系统中心接口文档")
                .description("系统中心接口文档，仅限内部使用")
                .termsOfServiceUrl("http://www.wangxi.org")
                .contact(new Contact("wangxi", "www.wangxi.org", "wangxi@qq.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                //.groupName("1.0版本")
                .select()
                //指定controller（接口）扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("cn.changge"))
                .paths(PathSelectors.any())
                .build();
    }

}