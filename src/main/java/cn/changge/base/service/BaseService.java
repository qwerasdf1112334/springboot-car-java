package cn.changge.base.service;

import cn.changge.base.query.BaseQuery;
import cn.changge.base.utils.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.base.service
 * @Author: WangXi
 * @CreateTime: 2023-10-12  11:34
 * @Description: TODO
 * @Version: 1.0
 */
public interface BaseService <T>{
    // 增
    void insert(T t);
    // 删
    void delete(Serializable id);
    // 改
    void update(T t);
    // 查
    List<T> queryAll();

    T queryById(Serializable id);



    PageInfo<T> queryPage(BaseQuery baseQuery);

    void batchDelete(@Param("ids") List<Long> ids);
}
