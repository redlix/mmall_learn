package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * @author rerdli
 * @time
 * @description
 */
public interface IUserService {
    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 校验用户名、邮箱
     *
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    /**
     * 选择问题
     *
     * @param username
     * @return
     */
    ServerResponse<String> selectQuestion(String username);

    /**
     * 检查答案
     *
     * @param username
     * @param password
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String username, String password, String answer);

    /**
     * 忘记重置密码
     *
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    /**
     * 重置密码
     *
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    ServerResponse<User> updateInfo(User user);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    ServerResponse<User> getInfo(Integer userId);

    /**
     * backend
     *
     * @param user
     * @return
     */
    ServerResponse checkAdmin(User user);
}
