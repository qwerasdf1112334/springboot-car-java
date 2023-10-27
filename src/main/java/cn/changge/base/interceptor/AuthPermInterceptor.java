package cn.changge.base.interceptor;

import cn.changge.auth.service.IPermissionService;
import cn.changge.base.annotation.ChangGePermission;
import cn.changge.base.context.LoginContext;
import cn.changge.org.domain.OrgEmployee;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.rmi.log.LogInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.base.interceptor
 * @Author: WangXi
 * @CreateTime: 2023-10-18  15:04
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class AuthPermInterceptor implements HandlerInterceptor {
    @Autowired
   private IPermissionService permissionService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        System.out.println(token);
        //token为空
        if (StringUtils.isEmpty(token)) {
            response.getWriter().print("{\"success\":false,\"message\":\"noLogin\"}");
            return false;
        }
        //map里为空
        OrgEmployee orgEmployee =(OrgEmployee) LoginContext.loginMap.get(token);
        System.out.println(orgEmployee);
        if (!Optional.ofNullable(orgEmployee).isPresent()){
            response.getWriter().print("{\"success\":false,\"message\":\"noLogin\"}");
            return false;
        }

        HandlerMethod handler1 = (HandlerMethod)handler;
        //获取用户访问的方法
        Method method = handler1.getMethod();
        String simpleName = handler1.getBeanType().getSimpleName();
        //判断用户访问的资源是否需要权限
        ChangGePermission annotation = method.getAnnotation(ChangGePermission.class);
        if (Objects.isNull(annotation)){
            //没有权限注解直接放行
            return true;
        }
        //如果有注解则通过用户查角色，查权限
    List<String> sns= permissionService.queryPermByuserId(orgEmployee.getId());
        String sn =simpleName+":"+method.getName();
        //用户拥有的权限中是否包含这个资源的权限，包含就放行，不包含就拦截并返回错误信息
        if (!sns.contains(sn)){
            response.getWriter().print("{\"success\":false,\"message\":\"noPermission\"}");
            return false;
        }
        return true;
    }
}
