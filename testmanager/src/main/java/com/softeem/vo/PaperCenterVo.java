package com.softeem.vo;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/8/3 11:30
 * 项目名称 testmanager
 */
public class PaperCenterVo {
    private Integer paperstatus;
    private String username;
    private Integer courseId;
    private Integer coursetype;
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(Integer coursetype) {
        this.coursetype = coursetype;
    }

    public Integer getPaperstatus() {
        return paperstatus;
    }

    public void setPaperstatus(Integer paperstatus) {
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
        return "PaperCenterVo{" +
                "paperstatus=" + paperstatus +
                ", username='" + username + '\'' +
                ", courseId=" + courseId +
                ", coursetype=" + coursetype +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
