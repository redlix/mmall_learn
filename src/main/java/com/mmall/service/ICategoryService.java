package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * @author rerdli
 * @time
 * @description
 */
public interface ICategoryService {

    /**
     * 添加品类
     *
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 更新品类名
     *
     * @param categoryName
     * @param categoryId
     * @return
     */
    ServerResponse updateCategoryName(String categoryName, Integer categoryId);

    /**
     * 获取子分类
     *
     * @param categoryId
     * @return
     */
    ServerResponse<List<Category>> getChildrenParallerCategory(Integer categoryId);


    /**
     * 获取当前分类和子分类
     *
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildById(Integer categoryId);
}
