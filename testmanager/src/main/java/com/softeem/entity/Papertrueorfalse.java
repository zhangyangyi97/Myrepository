package com.softeem.entity;


public class Papertrueorfalse {

  private Integer paperId;
  private Integer tid;


  public Integer getPaperId() {
    return paperId;
  }

  public void setPaperId(Integer paperId) {
    this.paperId = paperId;
  }


  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
  }

  @Override
  public String toString() {
    return "Papertrueorfalse{" +
            "paperId=" + paperId +
            ", tid=" + tid +
            '}';
  }
}
