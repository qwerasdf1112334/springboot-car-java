package cn.changge.auth.dto;

import cn.changge.auth.domain.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.auth.dto
 * @Author: WangXi
 * @CreateTime: 2023-10-17  15:56
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Long roleId;
    private List<Permission>  PermissionList;


}
