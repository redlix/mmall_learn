package com.mmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author redLi
 * @package com.mmall.util
 * @time 2018/03/16 10:12
 * @description:
 */
@Slf4j
public class CookieUtil {
    private final static String COOKIE_DOMAIL = ".redli.xin";
    private final static String COOKIE_NAME = "mmall_login_token";

    /**
     * 读取cookie
     *
     * @param request
     * @return
     */
    public static String readLoginToken(HttpServletRequest request) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                log.info("read CookieName:{}, CookieValue{}", ck.getName(), ck.getValue());
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    log.info("return CookieName:{}, CookieValue{}", ck.getName(), ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 写cookie
     *
     * @param response
     * @param token
     */
    public static void writeLoginToken(HttpServletResponse response, String token) {
        Cookie ck = new Cookie(COOKIE_NAME, token);
        ck.setDomain(COOKIE_DOMAIL);
        //设置在根目录
        ck.setPath("/");
        ck.setHttpOnly(true);
        //若MaxAge不设置,Cookie不会写入硬盘,而是写在内存,只在当前页面有效
        ck.setMaxAge(60 * 60 * 24 * 365);
        log.info("write CookieName:{}, CookieValue:{}", ck.getName(), ck.getValue());
        response.addCookie(ck);
    }

    /**
     * 删除cookie
     *
     * @param request
     * @param response
     */
    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    ck.setDomain(COOKIE_DOMAIL);
                    ck.setPath("/");
                    //0表示删除cookie
                    ck.setMaxAge(0);
                    log.info("del CookieName:{} CookieValue:{}", ck.getName(), ck.getValue());
                    return;
                }
            }
        }
    }
}
