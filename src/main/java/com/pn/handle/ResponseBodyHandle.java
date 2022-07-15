package com.pn.handle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseBodyHandle implements ResponseBodyAdvice<Object> {

    //判断是否要执行beforeBodyWrite方法，true为执行，false不执行
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    //对response处理的执行方法
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (((ServletServerHttpResponse) response).getServletResponse().getStatus() != 200) {
            return Response.createResponse(null, "操作失败", 0);
        }
        return Response.createResponse(body);
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Response<T> {
        private Integer code;
        private String message;
        private T data;

        private Response() {
            this.code = 1;
        }

        private Response(T data) {
            this();
            this.data = data;
            this.message = "操作成功";
        }

        private Response(T data, String message, Integer code) {
            this.code = code;
            this.data = data;
            this.message = message;
        }

        public static <T> Response<T> createResponse(T data) {
            return new Response<>(data);
        }

        public static <T> Response<T> createResponse(T data, String message, Integer code) {
            return new Response<>(data, message, code);
        }

    }
}