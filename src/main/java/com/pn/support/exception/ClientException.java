package com.pn.support.exception;

import com.pn.enums.ResponseCode;

/**
 * @Description 客户端异常类
 * @Author duhongyu
 * @Data 2022/7/18 10:55
 **/
public class ClientException extends BaseException {

    public ClientException(ResponseCode code, String message) {
        super(code, message);
    }

}
