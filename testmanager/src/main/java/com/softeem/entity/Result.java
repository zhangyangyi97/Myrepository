package com.softeem.entity;


public class Result {

  private Integer id;
  private String sid;
  private Integer paperId;
  private Integer score;
  private String finishtime;
  private String endTime;
  private Integer truecount;
  private Integer falsecount;

  private String username;
  private String papername;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPapername() {
    return papername;
  }

  public void setPapername(String papername) {
    this.papername = papername;
  }

  public Integer getTruecount() {
    return truecount;
  }

  public void setTruecount(Integer truecount) {
    this.truecount = truecount;
  }

  public Integer getFalsecount() {
    return falsecount;
  }

  public void setFalsecount(Integer falsecount) {
    this.falsecount = falsecount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getSid() {
    return sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }


  public Integer getPaperId() {
    return paperId;
  }

  public void setPaperId(Integer paperId) {
    this.paperId = paperId;
  }


  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }


  public String getFinishtime() {
    return finishtime;
  }

  public void setFinishtime(String finishtime) {
    this.finishtime = finishtime;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return "Result{" +
            "id=" + id +
            ", sid='" + sid + '\'' +
            ", paperId=" + paperId +
            ", score=" + score +
            ", finishtime='" + finishtime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", truecount=" + truecount +
            ", falsecount=" + falsecount +
            ", username='" + username + '\'' +
            ", papername='" + papername + '\'' +
            '}';
  }
}
