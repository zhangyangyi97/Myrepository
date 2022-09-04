package com.softeem.vo;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/8/4 1:27
 * 项目名称 testmanager
 */
public class CheckFinishVo {
    private Integer paperstatus;
    private String username;

    public Integer getpaperstatus() {
        return paperstatus;
    }

    public void setpaperstatus(Integer paperstatus) {
        this.paperstatus = paperstatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CheckFinishVo{" +
                "paperstatus=" + paperstatus +
                ", username='" + username + '\'' +
                '}';
    }
}
