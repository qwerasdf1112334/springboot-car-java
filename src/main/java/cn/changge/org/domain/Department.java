package cn.changge.org.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.changge.base.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "部门model")
public class Department extends BaseDomain {
    private Long id;
    private String sn;
    @Excel(name = "部门", width = 10)
    private String name;
    private String intro;

    private Employee manager;
    private Department parent;
    @ApiModelProperty(value = "表示层级")
    private String path;
    private Integer state;
    //查询部门树
    private List<Department> children;

    private Shop shop;


}
