package com.softeem.vo;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/30 17:16
 * 项目名称 testmanager
 */
public class GradeVo {
    private String gradeName;
    private Integer manNum;

    private Integer womenNum;

    private Integer gradeNum;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getManNum() {
        return manNum;
    }

    public void setManNum(Integer manNum) {
        this.manNum = manNum;
    }

    public Integer getWomenNum() {
        return womenNum;
    }

    public void setWomenNum(Integer womenNum) {
        this.womenNum = womenNum;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    @Override
    public String toString() {
        return "GradeVo{" +
                "gradeName=" + gradeName +
                ", manNum=" + manNum +
                ", womenNum=" + womenNum +
                ", gradeNum=" + gradeNum +
                '}';
    }
}
