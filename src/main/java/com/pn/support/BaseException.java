package com.pn.support;

import com.pn.enums.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private ResponseCode code;

    public BaseException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }
}