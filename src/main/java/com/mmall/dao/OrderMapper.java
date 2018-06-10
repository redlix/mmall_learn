package com.mmall.dao;

import com.mmall.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author redli
 */
public interface OrderMapper {
    /**
     * 根据主键删除记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(Order record);

    /**
     * 插入选中记录
     *
     * @param record
     * @return
     */
    int insertSelective(Order record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新选中记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 根据主键更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Order record);

    /**
     * 根据userId，orderNo查询
     *
     * @param userId
     * @param orderNo
     * @return
     */
    Order selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    /**
     * 根据订单号查询
     *
     * @param orderNo
     * @return
     */
    Order selectByOrderNo(Long orderNo);

    /**
     * 根据UserId查询
     *
     * @param userId
     * @return
     */
    List<Order> selectByUserId(Integer userId);

    /**
     * 查询是所有订单
     *
     * @return
     */
    List<Order> selectAllOrder();

    /**
     * 二期新增定时关单
     *
     * @param status
     * @param date
     * @return
     */
    List<Order> selectOrderStatusByCreateTime(@Param("status") Integer status, @Param("date") String date);

    /**
     * 根据订单id关闭订单
     *
     * @param id
     * @return
     */
    int closeOrderByOrderId(Integer id);
}