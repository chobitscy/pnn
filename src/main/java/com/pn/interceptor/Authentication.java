package com.pn.interceptor;

import com.pn.annotation.Admin;
import com.pn.annotation.LoginUser;
import com.pn.annotation.PassToken;
import com.pn.enums.ResponseCode;
import com.pn.support.exception.BaseException;
import com.pn.support.exception.ClientException;
import com.pn.utils.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Authentication implements HandlerInterceptor {

    @Value("${env:}")
    private String env;

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
                throw new ClientException(ResponseCode.SERVICE_ERROR, "无token,请重新登陆");
            }
            JwtUtil.parseJWT(token);
        } else if (method.isAnnotationPresent(Admin.class)) {
            if (token == null) {
                throw new ClientException(ResponseCode.SERVICE_ERROR, "无token,请重新登陆");
            }
            if (!JwtUtil.is_admin(token)) {
                throw new BaseException(ResponseCode.SERVICE_ERROR, "权限不够");
            }
            JwtUtil.parseJWT(token);
        }
        String timestamp_str = request.getHeader("timestamp");
        long timestamp = timestamp_str == null ? 0 : Long.parseLong(timestamp_str);
        String sign = request.getHeader("sign");
        if (timestamp == 0 || sign == null) {
            throw new ClientException(ResponseCode.SERVICE_ERROR, "illegal request");
        }
        long current = new Date().getTime();
        int path = request.getRequestURI().length();
        if (!env.equals("dev") && current - timestamp > 20 * 1000) {
            throw new ClientException(ResponseCode.SERVICE_ERROR, "timeout");
        }
        String res = BigDecimal.valueOf(timestamp)
                .divide(BigDecimal.valueOf(path), 2, RoundingMode.HALF_UP)
                .toString();
        String value = DigestUtils.md5Hex(res).toUpperCase();
        if (!env.equals("dev") && !value.equals(sign)) {
            throw new ClientException(ResponseCode.SERVICE_ERROR, "sign error");
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
