package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.vo.OrderVo;

import java.util.Map;

/**
 * @author  redLi
 * @package com.mmall.service
 * @time 2018/1/5
 */
public interface IOrderService {

    /**
     * pay
     * @param orderNo
     * @param userId
     * @param path
     * @return
     */
    ServerResponse pay(Long orderNo, Integer userId, String path);

    /**
     * aliCallback
     * @param params
     * @return
     */
    ServerResponse aliCallback(Map<String, String> params);

    /**
     * queryOrderPayStatus
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

    /**
     * createOrder
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse createOrder(Integer userId, Integer shippingId);

    /**
     * cancel
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse<String> cancel(Integer userId, Long orderNo);

    /**
     * getOrderCartProduct
     * @param userId
     * @return
     */
    ServerResponse getOrderCartProduct(Integer userId);

    /**
     * getOrderDetail
     * @param userId
     * @param orderNo
     * @return
     */
    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    /**
     * getOrderList
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

    /**
     * backend
     * manageList
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    /**
     * manageDetail
     * @param orderNo
     * @return
     */
    ServerResponse<OrderVo> manageDetail(Long orderNo);

    /**
     * manageSearch
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);

    /**
     * manageSendGoods
     * @param orderNo
     * @return
     */
    ServerResponse<String> manageSendGoods(Long orderNo);
}
