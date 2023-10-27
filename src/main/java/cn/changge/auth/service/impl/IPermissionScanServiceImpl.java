package cn.changge.auth.service.impl;

import cn.changge.auth.domain.Permission;
import cn.changge.auth.service.IPermissionScanService;
import cn.changge.auth.service.IPermissionService;
import cn.changge.base.annotation.ChangGePermission;
import cn.changge.base.enums.RequestTypeEnum;
import cn.changge.base.utils.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.auth.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-10-16  16:17
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class IPermissionScanServiceImpl implements IPermissionScanService {
    @Value("${Changge.Permission.scan-package}")
    private String url;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public void scan() {

        List<Class> allClassName = ClassUtils.getAllClassName(url);

        allClassName.stream().filter(clazz -> clazz.isAnnotationPresent(ChangGePermission.class) && clazz.isAnnotationPresent(RequestMapping.class))
                .forEach(clazz -> {
                    ChangGePermission changge = (ChangGePermission) clazz.getAnnotation(ChangGePermission.class);
                    RequestMapping request = (RequestMapping) clazz.getAnnotation(RequestMapping.class);

                    //父权限
                    Permission parent = new Permission();
                    parent.setDescs(changge.desc());
                    parent.setName(changge.name());
                    parent.setSn(clazz.getSimpleName());//返回的是类名
                    String parentUrl = request.value()[0];//返回的是路径上的名字@RequestMapping("/dept"),则返回"/dept"
                    parent.setUrl(parentUrl);

                    permissionService.insert(parent);//先insert，才能通过useGeneratedKeys拿到id
                    //  Method[] methods = clazz.getMethods();//这个方法会返回所有的public方法，包括从父类继承而来的和当前类中声明的 public 方法
                    Arrays.stream(clazz.getMethods()).filter(method -> method.isAnnotationPresent(ChangGePermission.class))
                            .forEach(method -> {
                                ChangGePermission annotation = method.getAnnotation(ChangGePermission.class);
                                Permission permission = new Permission();
                                permission.setDescs(annotation.desc());
                                permission.setName(annotation.name());
                                permission.setParent(parent);
                                String sn = clazz.getSimpleName() + ":" + method.getName();
                                permission.setSn(sn);
                                //获取方法上url
                                String methUrl = parentUrl + getMethodUrl(method);
                                permission.setUrl(methUrl);
                                permissionService.insert(permission);

                            });


                });

    }

    public String getMethodUrl(Method method) {
        String methodUrl = "";
        // 取枚举中定义的所有的请求类型
        RequestTypeEnum[] requestTypeEnums = RequestTypeEnum.values();
        for (RequestTypeEnum typeEnum : requestTypeEnums) {
            // 根据枚举中的不同的请求类型的class获取该类型对应的注解对象
            Annotation annotation = method.getAnnotation(typeEnum.getRequestType());
            // 如果该请求类型注解不存在就跳过
            if(Objects.isNull(annotation)){
                continue;
            }
            try {
                // 如果该请求类型注解存在,就获取它里面的value方法
                Class<? extends Annotation> aClass = annotation.annotationType();
                Method annotationMethod = aClass.getMethod("value");
                // 通过invoke调用该对象的value方法,获取结果
                // 反射执行方法方法名.invoke(对象)
                String[] value = (String[]) annotationMethod.invoke(annotation);
                // 如果value不为空且长度大于0,就赋值给methodUrl,并跳出循环
                if(value != null && value.length > 0){
                    methodUrl = value[0];
                    break;
                }
            }catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        return methodUrl;

    }
}

