package cn.changge.org.service;

import cn.changge.org.domain.Shop;
import cn.changge.base.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxi
 * @since 2023-10-28
 */
public interface IShopService extends BaseService<Shop> {

    void settlement(Shop shop);

    void shenHe(Shop shop);
}
