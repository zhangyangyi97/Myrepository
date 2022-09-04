package com.softeem.vo;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/23 11:04
 * 项目名称 testmanager
 */
public class LoginVo {

    private String username;
    private String token;
    private String roleName;   //角色的认证

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
