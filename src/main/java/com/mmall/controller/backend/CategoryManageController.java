package com.mmall.controller.backend;

import com.alipay.api.internal.util.StringUtils;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rerdli
 * @time
 * @description
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 增加节点
     *
     * @param httpServletRequest
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping("add_category")
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest httpServletRequest, String categoryName,
                                      @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMsg("当前用户未登录，无法获取有效信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "未登录，请重新登陆");
        }
        //检查是否为管理员
        if (iUserService.checkAdmin(user).isSuccess()) {
            return iCategoryService.addCategory(categoryName, parentId);
        } else {
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
        }
    }

    /**
     * 更新name
     *
     * @param session
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpServletRequest httpServletRequest, Integer categoryId, String categoryName) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMsg("当前用户未登录，无法获取有效信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "未登录，请重新登陆");
        }
        //检查是否为管理员
        if (iUserService.checkAdmin(user).isSuccess()) {
            return iCategoryService.updateCategoryName(categoryName, categoryId);
        } else {
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpServletRequest httpServletRequest, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMsg("当前用户未登录，无法获取有效信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "未登录，请重新登陆");
        }
        //检查是否为管理员
        if (iUserService.checkAdmin(user).isSuccess()) {
            return iCategoryService.getChildrenParallerCategory(categoryId);
        } else {
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_deep_category")
    @ResponseBody
    public ServerResponse getCategoryAndDeepCategory(HttpServletRequest httpServletRequest, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMsg("当前用户未登录，无法获取有效信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要登录");
        }
        if (iUserService.checkAdmin(user).isSuccess()) {
            //查询当前节点id和递归子节点id
            return iCategoryService.getChildrenParallerCategory(categoryId);
        } else {
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
        }
    }
}
