package com.softeem.entity;

public class Studentexercise {

  private Integer sid;
  private Integer eid;


  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }


  public Integer getEid() {
    return eid;
  }

  public void setEid(Integer eid) {
    this.eid = eid;
  }

  @Override
  public String toString() {
    return "Studentexercise{" +
            "sid=" + sid +
            ", eid=" + eid +
            '}';
  }
}
