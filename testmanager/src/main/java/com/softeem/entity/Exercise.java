package com.softeem.entity;

//简答题
public class Exercise {

  private Integer eid;
  private Integer courseId;   //与course进行联表
  private String title;       //题目名称
  private String answer;      //题目答案
  private double diff;        //题目难度系数
  private double point;       //题目的分值
  private Integer coursetype; //题目类型，与course进行连表

  //加两个属性
  private String ctname;
  private String coursename;

  private String uuid;      //题目唯一不一样的标识
  private String papername;

  public String getPapername() {
    return papername;
  }

  public void setPapername(String papername) {
    this.papername = papername;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getCoursename() {
    return coursename;
  }

  public void setCoursename(String coursename) {
    this.coursename = coursename;
  }

  public String getCtname() {
    return ctname;
  }

  public void setCtname(String ctname) {
    this.ctname = ctname;
  }

  public Integer getEid() {
    return eid;
  }

  public void setEid(Integer eid) {
    this.eid = eid;
  }

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }


  public double getDiff() {
    return diff;
  }

  public void setDiff(double diff) {
    this.diff = diff;
  }


  public double getPoint() {
    return point;
  }

  public void setPoint(double point) {
    this.point = point;
  }


  public Integer getCoursetype() {
    return coursetype;
  }

  public void setCoursetype(Integer coursetype) {
    this.coursetype = coursetype;
  }


  @Override
  public String toString() {
    return "Exercise{" +
            "eid=" + eid +
            ", courseId=" + courseId +
            ", title='" + title + '\'' +
            ", answer='" + answer + '\'' +
            ", diff=" + diff +
            ", point=" + point +
            ", coursetype=" + coursetype +
            ", ctname='" + ctname + '\'' +
            ", coursename='" + coursename + '\'' +
            ", uuid='" + uuid + '\'' +
            ", papername='" + papername + '\'' +
            '}';
  }
}
