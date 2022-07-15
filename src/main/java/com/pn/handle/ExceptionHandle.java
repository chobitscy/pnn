package com.pn.handle;

import com.pn.support.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handle(Exception exception) {

        log.error(exception.getMessage());

        if (exception instanceof BusinessException) {
            return ResponseEntity.status(500).body(exception.getMessage());
        }

        // 唯一性约束
        if (exception instanceof DuplicateKeyException) {
            return ResponseEntity.status(500).body("数据已经存在");
        }
        return ResponseEntity.status(500).body("系统异常");
    }
}
