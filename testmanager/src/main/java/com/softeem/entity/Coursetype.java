package com.softeem.entity;

//题目类型关联表
public class Coursetype {

  private Integer coursetype;     //题目类型编号
  private String ctname;          //题目类型名称



  public Integer getCoursetype() {
    return coursetype;
  }

  public void setCoursetype(Integer coursetype) {
    this.coursetype = coursetype;
  }


  public String getCtname() {
    return ctname;
  }

  public void setCtname(String ctname) {
    this.ctname = ctname;
  }

}
