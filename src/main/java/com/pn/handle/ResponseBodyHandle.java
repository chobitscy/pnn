package com.pn.handle;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseBodyAdvice implements ResponseAdvice<Object> {

    //判断是否要执行beforeBodyWrite方法，true为执行，false不执行
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    //对response处理的执行方法
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 这里面参数很多，一般使用如下几个：
        // body 返回的内容 request 请求 response 响应
        return Response.createResponse(body);
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    public static class Response<T> {
        private final String code;
        private String message;
        private T data;

        private Response() {
            this.code = "success";
        }

        private Response(T data) {
            this();
            this.data = data;
        }

        public static <T> Response<T> createResponse(T data) {
            return new Response<>(data);
        }

    }
}
————————————————
版权声明：本文为CSDN博主「搏·梦」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/xueyijin/article/details/122524335