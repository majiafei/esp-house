package com.ruanmou.web.espweb.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: house
 * @Package: com.ruanmou.web.espweb.util
 * @ClassName: CookieUtils
 * @Author: majiafei
 * @Description: cookie工具类
 * @Date: 2018/12/25 11:14
 */
public class CookieUtils {

    /**
     * 添加cookie
     * @param key
     * @param value
     * @param response
     */
    public static void setCookie(String key, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取cookie值
     * @param key
     * @param request
     * @return
     */
    public static String getCookieValue(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 删除cookie
     * @param name
     * @param response
     */
    public static void deleteCookie(String name, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, "");
        response.addCookie(cookie);
    }

}
