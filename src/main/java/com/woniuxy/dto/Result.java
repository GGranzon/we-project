package com.woniuxy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    //是否成功
    private boolean flag;
    //响应状态码
    private Integer statusCode;
    //描述信息
    private String message;
    //返回数据
    private T  data;

    public Result(boolean flag, Integer statusCode, String message) {
        this.flag = flag;
        this.statusCode = statusCode;
        this.message = message;
    }

    public Result(boolean flag, Integer statusCode, String message, T data) {
        this.flag = flag;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}
