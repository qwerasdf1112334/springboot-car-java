package cn.changge.org.domain;

import cn.changge.base.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.domain
 * @Author: WangXi
 * @CreateTime: 2023-10-09  13:02
 * @Description: TODO
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee extends BaseDomain {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String headImage;
    private Integer age;
    private Department department;

}
