package com.softeem.entity;

//选择题  多选题或者单选题
public class Choice {

  private Integer cid;
  private Integer courseId;   //题目编号，与后面course联表
  private String title;       //题目名称
  private String answerA;     //A选项
  private String answerB;     //B选项
  private String answerC;     //C选项
  private String answerD;     //D选项
  private String answer;      //答案，与前面选项进行匹配
  private Integer gradeId;    //年级表关联发布试卷
  private double diff;        //难度系数，与星星挂钩，也与后面发布试卷相关联
  private double point;       //分数值
  private Integer coursetype; //题目类型，与后面coursetype进行关联


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

  public Integer getCid() {
    return cid;
  }

  public void setCid(Integer cid) {
    this.cid = cid;
  }

  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
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


  public String getAnswerA() {
    return answerA;
  }

  public void setAnswerA(String answerA) {
    this.answerA = answerA;
  }


  public String getAnswerB() {
    return answerB;
  }

  public void setAnswerB(String answerB) {
    this.answerB = answerB;
  }


  public String getAnswerC() {
    return answerC;
  }

  public void setAnswerC(String answerC) {
    this.answerC = answerC;
  }


  public String getAnswerD() {
    return answerD;
  }

  public void setAnswerD(String answerD) {
    this.answerD = answerD;
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
    return "Choice{" +
            "cid=" + cid +
            ", courseId=" + courseId +
            ", title='" + title + '\'' +
            ", answerA='" + answerA + '\'' +
            ", answerB='" + answerB + '\'' +
            ", answerC='" + answerC + '\'' +
            ", answerD='" + answerD + '\'' +
            ", answer='" + answer + '\'' +
            ", gradeId=" + gradeId +
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
