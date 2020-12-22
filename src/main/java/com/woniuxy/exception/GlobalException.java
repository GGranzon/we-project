package com.woniuxy.exception;

/**
 * 全局异常
 */

import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler({NullPointerException.class})
    public Result NullPointerException(){
        return new Result(false, StatusCode.FAILED,"空指针异常");
    }


    @ExceptionHandler({Exception.class})
    public Result Exception(){
        return new Result(false,StatusCode.FAILED,"服务器异常");
    }
}
