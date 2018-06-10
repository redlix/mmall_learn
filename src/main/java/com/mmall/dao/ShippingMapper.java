package com.mmall.dao;

import com.mmall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    /**
     * 根据主键删除
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
    int insert(Shipping record);

    /**
     * 插入选中记录
     *
     * @param record
     * @return
     */
    int insertSelective(Shipping record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    Shipping selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新选中的记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Shipping record);

    /**
     * 根据主键更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Shipping record);

    /**
     * 更新商品
     *
     * @param record
     * @return
     */
    int updateByShipping(Shipping record);

    /**
     * 根据商品编号用户编号删除
     *
     * @param userId
     * @param shippingId
     * @return
     */
    int deleteByShippingIdUserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    /**
     * 根据商品id用户id查询
     *
     * @param userId
     * @param shippingId
     * @return
     */
    Shipping selectByShippingIdUserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);

    /**
     * 根据用户id查询
     *
     * @param userId
     * @return
     */
    List<Shipping> selectByUserId(@Param("userId") Integer userId);
}