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

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(String categoryName, Integer categoryId);

    ServerResponse<List<Category>> getChildrenParallerCategory(Integer categoaryId);

    ServerResponse<List<Integer>> selectCategoryAndChildById(Integer categoryId);
}
