package cn.changge.base.service.impl;

import cn.changge.base.mapper.BaseMapper;
import cn.changge.base.query.BaseQuery;
import cn.changge.base.service.BaseService;
import cn.changge.base.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: springboot-system
 * @BelongsPackage: cn.changge.base.service.impl
 * @Author: WangXi
 * @CreateTime: 2023-10-12  11:35
 * @Description: TODO
 * @Version: 1.0
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseMapper<T> baseMapper;
    @Override
    public void insert(T t) {
        baseMapper.insert(t);
    }

    @Override
    public void delete(Serializable id) {
        baseMapper.delete(id);
    }

    @Override
    public void update(T t) {
        baseMapper.update(t);
    }

    @Override
    public List<T> queryAll() {
        return baseMapper.queryAll();
    }

    @Override
    public T queryById(Serializable id) {
        return baseMapper.queryById(id);
    }



    @Override
    public PageInfo<T> queryPage(BaseQuery baseQuery) {
        // 查询总条数
        Long total = baseMapper.queryTotal(baseQuery);
        // 查询分页数据
        if (total != 0){
            List<T> list = baseMapper.queryPage(baseQuery);
            return new PageInfo<T>(total,list);
        }
        return new PageInfo<T>();
    }

    @Override
    public void batchDelete(List<Long> ids) {
        baseMapper.batchDelete(ids);
    }
}
