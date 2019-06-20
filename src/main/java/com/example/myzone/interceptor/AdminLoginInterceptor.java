package com.example.myzone.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    //    在请求处理之前调用,只有返回true才会执行请求
//    @Override

    public boolean preHandle(javax.servlet.http.HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        //得到session
        HttpSession session = httpServletRequest.getSession(true);
//        得到对象
        Object admin = session.getAttribute("username");
//        判断对象是否存在
        if (admin != null) {
            return true;
        } else {
//            不存在则跳转到登录页
            try {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
            } catch (IOException io) {
            }
            return false;
        }
    }


    //    在请求处理之后,视图渲染之前执行
//    @Override
//    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }

    //    @Override
//    试图渲染之后执行
    public void postHandle(javax.servlet.http.HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }
}