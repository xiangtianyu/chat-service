package com.chat.interceptor;

import com.chat.datamodel.dto.ResultDTO;
import com.chat.service.UserService;
import com.chat.util.Convert;
import com.chat.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiangtianyu on 2016/12/22.
 */
public class UserLoginInterceptor implements HandlerInterceptor{
    private RedisUtils redisUtils;

    public UserLoginInterceptor(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        String sid = Convert.getCookie(cookies, "CUSER");
        String uid = Convert.getCookie(cookies, "USERID");
        if (!sid.equals("") && !uid.equals("")) {
            if (redisUtils.exists(uid)) {
                if (redisUtils.get(uid).toString().equals(sid)) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }

        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
}
