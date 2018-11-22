package com.jk.luckydraw.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(session.getId());
        if (attribute != null){
            return true;
        }
        try {
            response.sendRedirect("/admin/toLoginPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
