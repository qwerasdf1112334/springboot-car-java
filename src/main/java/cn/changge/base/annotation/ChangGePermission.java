package cn.changge.base.annotation;

import java.lang.annotation.*;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.base.annotation
 * @Author: WangXi
 * @CreateTime: 2023-10-16  14:50
 * @Description: TODO
 * @Version: 1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Inherited // 注解是有继承性的
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangGePermission {
     String name();
     String desc() default "";

}
