package com.softeem.entity;

//判断题
public class Trueorfalse {

  private Integer tid;
  private Integer courseId;       //题目编号，与后面course联表
  private String title;           //题目名称
  private String firstanw;        //第一个选项
  private String secondanw;       //第二个选项
  private String answer;          //答案
  private double diff;            //难度系数
  private double point;           //分值
  private Integer coursetype;     //题目类型，与后面coursetype进行关联  


  private String uuid;      //题目唯一不一样的标识
  //加两个属性
  private String ctname;
  private String coursename;
  private String papername;

  public String getPapername() {
    return papername;
  }

  public void setPapername(String papername) {
    this.papername = papername;
  }

  public String getCtname() {
    return ctname;
  }

  public void setCtname(String ctname) {
    this.ctname = ctname;
  }

  public String getCoursename() {
    return coursename;
  }

  public void setCoursename(String coursename) {
    this.coursename = coursename;
  }

  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
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

  public String getFirstanw() {
    return firstanw;
  }

  public void setFirstanw(String firstanw) {
    this.firstanw = firstanw;
  }

  public String getSecondanw() {
    return secondanw;
  }

  public void setSecondanw(String secondanw) {
    this.secondanw = secondanw;
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

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Override
  public String toString() {
    return "Trueorfalse{" +
            "tid=" + tid +
            ", courseId=" + courseId +
            ", title='" + title + '\'' +
            ", firstanw='" + firstanw + '\'' +
            ", secondanw='" + secondanw + '\'' +
            ", answer='" + answer + '\'' +
            ", diff=" + diff +
            ", point=" + point +
            ", coursetype=" + coursetype +
            ", uuid='" + uuid + '\'' +
            ", ctname='" + ctname + '\'' +
            ", coursename='" + coursename + '\'' +
            ", papername='" + papername + '\'' +
            '}';
  }
}
