package com.softeem.entity;


public class Paperstudent {

  private Integer paperId;
  private Integer sid;
  private Integer paperstatus;

  public Integer getPaperstatus() {
    return paperstatus;
  }

  public void setPaperstatus(Integer paperstatus) {
    this.paperstatus = paperstatus;
  }

  public Integer getPaperId() {
    return paperId;
  }

  public void setPaperId(Integer paperId) {
    this.paperId = paperId;
  }


  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }

  @Override
  public String toString() {
    return "Paperstudent{" +
            "paperId=" + paperId +
            ", sid=" + sid +
            ", paperstatus=" + paperstatus +
            '}';
  }
}
