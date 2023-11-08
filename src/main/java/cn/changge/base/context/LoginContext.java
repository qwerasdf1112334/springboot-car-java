package cn.changge.base.context;

import cn.changge.org.domain.Employee;
import cn.changge.org.domain.OrgEmployee;

import java.util.HashMap;


/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.base.context
 * @Author: WangXi
 * @CreateTime: 2023-10-17  20:06
 * @Description: TODO
 * @Version: 1.0
 */
public class LoginContext {
  public static HashMap<String,Object> loginMap =new HashMap<String,Object>();
  /**
   * 登录成功后调用
   * @param loginUser
   */
  public static void setLoginAdmin(Employee loginUser){
    //暂时不实现--要存放session,redis

  }


  /**
   * 获取登录用户
   * @return
   */
  public static OrgEmployee getLoginAdmin(){
    //从session或者redis中获取  现在模拟获取一个用户
    OrgEmployee employee = new OrgEmployee();
    employee.setId(340L);
    employee.setUsername("Admin");
    return employee;

  }
}
