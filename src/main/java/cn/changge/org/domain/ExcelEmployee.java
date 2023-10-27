package cn.changge.org.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.domain
 * @Author: WangXi
 * @CreateTime: 2023-10-21  09:51
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_org_employee")
public class ExcelEmployee {
    @Excel(name = "ID", width = 15)
    private Long id;

    @Excel(name = "用户名", width = 15)
    private String username;

    @Excel(name = "密码", width = 15)
    private String password;

    @Excel(name = "邮箱", width = 20)
    private String email;

  //  @Excel(name = "头像", width = 20, type = 2, savePath = "/upload/")
    @Excel(name = "头像", width = 20)
    private String headImage;

    @Excel(name = "年龄", width = 10)
    private Integer age;


    @ExcelEntity(name = "部门")
    private Department department;


    @Excel(name = "电话", width = 15)
    private String phone;

    public ExcelEmployee(String username, String password, String email, String headImage, Integer age, Department department, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.headImage = headImage;
        this.age = age;
        this.department = department;
        this.phone = phone;
    }

}
