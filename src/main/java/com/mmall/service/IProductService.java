package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * @author rerdli
 * @time
 * @description
 */
public interface IProductService {

    /**
     * 修改、更新产品
     *
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 设置产品状态
     *
     * @param productId
     * @param status
     * @return
     */
    ServerResponse<String> setStatus(Integer productId, Integer status);

    /**
     * 管理产品详情
     *
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    /**
     * 获取产品列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    /**
     * 查询产品
     *
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    /**
     * 根据id查询产品详情
     *
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    /**
     * 根据关键字查询产品
     *
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}