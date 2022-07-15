package com.pn.interceptor;

import com.pn.annotation.Admin;
import com.pn.annotation.LoginUser;
import com.pn.annotation.PassToken;
import com.pn.support.BusinessException;
import com.pn.utils.JwtUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class Authentication implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(PassToken.class)) {
            return true;
        }

        if (method.isAnnotationPresent(LoginUser.class)) {

            if (token == null) {
                throw new BusinessException("无token,请重新登陆");
            }

            JwtUtils.parseJWT(token);

            return true;
        }

        if (method.isAnnotationPresent(Admin.class)) {

            if (token == null) {
                throw new BusinessException("无token,请重新登陆");
            }

            if (!JwtUtils.is_admin(token)) {
                throw new BusinessException("权限不够");
            }

            JwtUtils.parseJWT(token);

            return true;

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
