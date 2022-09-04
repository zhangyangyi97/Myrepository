package com.softeem.entity;


//年级关联表
public class Grade {


  private Integer gradeId;    //年级编号，与前面老师学生进行区分
  private String gradename;   //年级名称




  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }


  public String getGradename() {
    return gradename;
  }

  public void setGradename(String gradename) {
    this.gradename = gradename;
  }

}
