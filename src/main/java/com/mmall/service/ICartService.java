package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * @author redLi
 * @package com.mmall.service
 * @time 2018/1/4
 */
public interface ICartService {

    /**
     * 商品加入购物车
     *
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    /**
     * 购物车数量更新
     *
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    /**
     * 购物车删除商品
     *
     * @param userId
     * @param productIds
     * @return
     */
    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    /**
     * 查询购物车
     *
     * @param userId
     * @return
     */
    ServerResponse<CartVo> list(Integer userId);

    /**
     * 选择或者不选
     *
     * @param userId
     * @param productId
     * @param checked
     * @return
     */
    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);

    /**
     * 货物购物车商品数量
     *
     * @param userId
     * @return
     */
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
