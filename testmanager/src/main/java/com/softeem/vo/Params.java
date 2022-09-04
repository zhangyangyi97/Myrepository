package com.softeem.vo;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/28 23:09
 * 项目名称 testmanager
 */
public class Params {

    private String courseId;
    private String title;
    private String coursetype;

    private String papername;
    private Integer gradeId;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseType() {
        return coursetype;
    }

    public void setCourseType(String courseType) {
        this.coursetype = courseType;
    }

    @Override
    public String toString() {
        return "Params{" +
                "courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", coursetype='" + coursetype + '\'' +
                ", papername='" + papername + '\'' +
                ", gradeId=" + gradeId +
                '}';
    }
}
