package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author rerdli
 * @time
 * @description
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            User user = response.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN) {
                //登陆用户为管理员
                //session.setAttribute(Const.CURRENT_USER, user);
                //新增redis共享redis,session方式
                CookieUtil.writeLoginToken(httpServletResponse, session.getId());
                RedisShardedPoolUtil.setEx(session.getId(), JsonUtil.objToString(response.getData()), Const.RedisCacheExTime.REDIS_SESSION_EXTIME);
                return response;
            } else {
                return ServerResponse.createByErrorMsg("不是管理员，无法登陆");
            }
        }
        return response;
    }
}
