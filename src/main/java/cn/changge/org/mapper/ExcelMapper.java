package cn.changge.org.mapper;

import cn.changge.base.mapper.BaseMapper;
import cn.changge.org.domain.ExcelEmployee;
import cn.changge.org.domain.OrgEmployee;

import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.org.mapper
 * @Author: WangXi
 * @CreateTime: 2023-10-21  10:26
 * @Description: TODO
 * @Version: 1.0
 */
public interface ExcelMapper  extends BaseMapper<ExcelEmployee> {
    ExcelEmployee quertByName(String name);

    void batchInsertEmployees(List<ExcelEmployee> employees);
}
