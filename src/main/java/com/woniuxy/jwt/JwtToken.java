package com.woniuxy.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义 jwtToken令牌
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
