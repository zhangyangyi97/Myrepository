package com.softeem.entity;


public class Paper {

  private Integer id;
  private Integer paperId;      //试卷编号
  private Integer gradeId;      //年级关联
  private String papername;     //试卷名称
  private Integer coursecount;  //题目数量
  private Integer mark;         //多少分
  private Integer status;       //判断是否为固定试卷
  private Integer time;
  private String firstInit;     //创建时间
  private Integer coursetype;   //试卷类型

  private String ctname;
  private String gradename;
  private Integer paperstatus;

  private Integer sid;          //为中间表添加试卷作业做准备

  public Integer getPaperstatus() {
    return paperstatus;
  }

  public void setPaperstatus(Integer paperstatus) {
    this.paperstatus = paperstatus;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }

  public String getGradename() {
    return gradename;
  }

  public void setGradename(String gradename) {
    this.gradename = gradename;
  }

  public String getFirstInit() {
    return firstInit;
  }

  public void setFirstInit(String firstInit) {
    this.firstInit = firstInit;
  }

  public String getCtname() {
    return ctname;
  }

  public void setCtname(String ctname) {
    this.ctname = ctname;
  }

  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }

  public Integer getCoursecount() {
    return coursecount;
  }

  public void setCoursecount(Integer coursecount) {
    this.coursecount = coursecount;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getPaperId() {
    return paperId;
  }

  public void setPaperId(Integer paperId) {
    this.paperId = paperId;
  }


  public String getPapername() {
    return papername;
  }

  public void setPapername(String papername) {
    this.papername = papername;
  }



  public Integer getMark() {
    return mark;
  }

  public void setMark(Integer mark) {
    this.mark = mark;
  }


  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public Integer getCoursetype() {
    return coursetype;
  }

  public void setCoursetype(Integer coursetype) {
    this.coursetype = coursetype;
  }


  @Override
  public String toString() {
    return "Paper{" +
            "id=" + id +
            ", paperId=" + paperId +
            ", gradeId=" + gradeId +
            ", papername='" + papername + '\'' +
            ", coursecount=" + coursecount +
            ", mark=" + mark +
            ", status=" + status +
            ", time=" + time +
            ", firstInit='" + firstInit + '\'' +
            ", coursetype=" + coursetype +
            ", ctname='" + ctname + '\'' +
            ", gradename='" + gradename + '\'' +
            ", paperstatus=" + paperstatus +
            ", sid=" + sid +
            '}';
  }
}
