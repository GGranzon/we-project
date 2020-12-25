package com.woniuxy.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于rememberMe 的 Vo类
 */
@Data
public class UserVo implements Serializable {
    private String username;
    private String password;
}
