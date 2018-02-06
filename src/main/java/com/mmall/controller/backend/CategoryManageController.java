package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by redLi on 2018/1/2.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    //增加节点
    @RequestMapping("add_category")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName,
                                            @RequestParam(value = "parentId", defaultValue = "0") int parentId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "未登录，请重新登陆");
        }
        //检查是否为管理员
        if(iUserService.checkAdmin(user).isSuccess()){
            return iCategoryService.addCategory(categoryName, parentId);
        } else{
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
        }
    }

    //更新name
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "未登录，请重新登陆");
        }
        //检查是否为管理员
        if(iUserService.checkAdmin(user).isSuccess()){
            return iCategoryService.updateCategoryName(categoryName, categoryId);
        } else{
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value="categoryId", defaultValue="0") Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "未登录，请重新登陆");
        }
        //检查是否为管理员
        if(iUserService.checkAdmin(user).isSuccess()){
            return iCategoryService.getChildrenParallerCategory(categoryId);
        } else{
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_deep_category")
    @ResponseBody
    public ServerResponse getCategoryAndDeepCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要登录");
        }
        if(iUserService.checkAdmin(user).isSuccess()){
            //查询当前节点id和递归子节点id
            return iCategoryService.getChildrenParallerCategory(categoryId);
        } else{
            return ServerResponse.createByErrorMsg("无权限操作，需要管理员权限");
        }
    }
}
