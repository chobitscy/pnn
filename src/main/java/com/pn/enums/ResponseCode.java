package com.pn.enums;

/**
 * 返回状态码
 */
public enum ResponseCode {
    /**
     * 成功返回的状态码
     */
    SUCCESS(1, "success"),
    /**
     * 资源不存在的状态码
     */
    RESOURCES_NOT_EXIST(-1, "not found"),
    /**
     * 所有无法识别的异常默认的返回状态码
     */
    SERVICE_ERROR(0, "fail");
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}