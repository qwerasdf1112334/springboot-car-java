package cn.changge.org.mapper;

import cn.changge.org.domain.Shop;
import cn.changge.base.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangxi
 * @since 2023-10-28
 */
public interface ShopMapper extends BaseMapper<Shop> {

    Shop queryByEmpId(Long id);
}
