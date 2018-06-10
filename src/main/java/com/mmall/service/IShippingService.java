package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * @author redLi
 * @package com.mmall.service
 * @time 2018/1/5
 */
public interface IShippingService {

    /**
     * 添加购物车
     *
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse add(Integer userId, Shipping shipping);

    /**
     * 删除购物车
     *
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<String> del(Integer userId, Integer shippingId);

    /**
     * 更新购物车
     *
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse update(Integer userId, Shipping shipping);

    /**
     * 查询产品
     *
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    /**
     * 查询购物车列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
