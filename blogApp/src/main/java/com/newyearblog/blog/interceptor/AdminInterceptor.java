package com.newyearblog.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newyearblog.blog.entity.User;

import org.springframework.web.servlet.HandlerInterceptor;

// 登录拦截器

public class AdminInterceptor implements HandlerInterceptor {

    //处理请求之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return true;
        }
        response.sendRedirect("/admin");
        return false;
    }
}
