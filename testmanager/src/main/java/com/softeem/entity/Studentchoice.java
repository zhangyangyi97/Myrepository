package com.softeem.entity;


public class Studentchoice {

  private Integer sid;
  private Integer cid;


  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }


  public Integer getCid() {
    return cid;
  }

  public void setCid(Integer cid) {
    this.cid = cid;
  }

  @Override
  public String toString() {
    return "Studentchoice{" +
            "sid=" + sid +
            ", cid=" + cid +
            '}';
  }
}
