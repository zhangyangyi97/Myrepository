package com.softeem.entity;


public class Studenttrue {

  private Integer sid;
  private Integer tid;


  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }


  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
  }

  @Override
  public String toString() {
    return "Studenttrue{" +
            "sid=" + sid +
            ", tid=" + tid +
            '}';
  }
}
