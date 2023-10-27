package cn.changge.auth.service.impl;

import cn.changge.auth.domain.Menu;
import cn.changge.auth.mapper.MenuMapper;
import cn.changge.auth.service.IMenuService;
import cn.changge.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> menuTree(Long id) {
        return menuMapper.menuTree(id);
    }
}
