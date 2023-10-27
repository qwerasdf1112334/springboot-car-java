package cn.changge.org.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.changge.base.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.domain
 * @Author: WangXi
 * @CreateTime: 2023-10-09  13:03
 * @Description: TODO
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department extends BaseDomain {
    private Long id;
    @Excel(name = "部门", width = 10)
    private String name;
    private String intro;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = ("GMT+8"))
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = ("GMT+8"))
    private Date updateTime;
    private Employee manager;
    private Department parent;
    private String path;
    private Integer state;
    //查询部门树
    private List<Department> children;

    public Department(String name, String intro, Date createTime, Date updateTime, Employee manager, Department parent, String path, Integer state) {
        this.name = name;
        this.intro = intro;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.manager = manager;
        this.parent = parent;
        this.path = path;
        this.state = state;
    }
}
