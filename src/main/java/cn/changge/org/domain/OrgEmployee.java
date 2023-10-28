package cn.changge.org.domain;

import cn.changge.base.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgEmployee extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String realname;
    private String password;
    private String email;
    private String phone;
    private String salt;
    private Integer sex;
    private String logininfoid;
    private Integer state;

//    id
//            username
//    real_name
//            email
//    phone
//            salt
//    password
//            age
//    sex
//            state
//    department_id
//            logininfo_id

    //  private String headImage;

    private Integer age;
    private Department department;

}
