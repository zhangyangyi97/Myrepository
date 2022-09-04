package com.softeem.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/20 17:02
 * 项目名称 spring-security-jwt
 */
public class MyGrantedAuthority implements GrantedAuthority {

    private String getAuthority;

    public MyGrantedAuthority(String getAuthority) {
        this.getAuthority = getAuthority;
    }

    public void setGetAuthority(String getAuthority) {
        this.getAuthority = getAuthority;
    }

    @Override
    public String getAuthority() {
        return this.getAuthority;
    }
}
