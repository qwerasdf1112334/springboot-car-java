package cn.changge.auth.mapper;

import cn.changge.auth.domain.Menu;
import cn.changge.base.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangxi
 * @since 2023-10-13
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> menuTree(Long id);
}
