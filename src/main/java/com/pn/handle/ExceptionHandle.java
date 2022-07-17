package com.pn.handle;

import com.pn.annotation.BaseResponse;
import com.pn.enums.ResponseCode;
import com.pn.support.BaseException;
import com.pn.support.ResponseResult;
import lombok.extern.log4j.Log4j2;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Log4j2
@RestControllerAdvice(annotations = BaseResponse.class)
@ResponseBody
public class ExceptionHandle {
    /**
     * 处理未捕获的Exception
     *
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), null);
    }

    /**
     * 处理未捕获的RuntimeException
     *
     * @param e 运行时异常
     * @return 统一响应体
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), null);
    }

    /**
     * 处理业务异常BaseException
     *
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        ResponseCode code = e.getCode();
        return new ResponseResult(code.getCode(), e.getMessage(), null);
    }

    /**
     * 处理 validation 异常
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult exceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(),
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(), null);
    }

    /**
     * 处理数据库唯一约束异常
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseResult exceptionHandler(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), "数据已经存在", null);
    }
}
