package cn.changge.auth.service;

import cn.changge.auth.domain.Menu;
import cn.changge.base.service.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface IMenuService extends BaseService<Menu> {

    List<Menu> menuTree(Long id);
}
