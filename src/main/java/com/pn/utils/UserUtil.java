package com.pn.utils;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    /**
     * 从请求 token 里获取用户 id
     *
     * @param request 请求
     * @return 用户 id
     */
    public static Long getUserIdByRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return JwtUtil.getUserId(token);
    }
}
