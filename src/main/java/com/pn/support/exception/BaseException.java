package com.pn.support.exception;

import com.pn.enums.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private ResponseCode code;

    public BaseException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }
}