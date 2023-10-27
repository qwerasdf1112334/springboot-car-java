package cn.changge.base.mapper;

import cn.changge.base.query.BaseQuery;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {
    // 增
    void insert(T t);
    // 删
    void delete(Serializable id);
    // 改
    void update(T t);
    // 查
    List<T> queryAll();

    T queryById(Serializable id);

    Long queryTotal(BaseQuery baseQuery);

    List<T> queryPage(BaseQuery baseQuery);

    void batchDelete(@Param("ids") List<Long> ids);
}
