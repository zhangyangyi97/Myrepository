package com.softeem.entity;


public class Paperexercise {

  private Integer paperId;
  private Integer eid;


  public Integer getPaperId() {
    return paperId;
  }

  public void setPaperId(Integer paperId) {
    this.paperId = paperId;
  }


  public Integer getEid() {
    return eid;
  }

  public void setEid(Integer eid) {
    this.eid = eid;
  }

  @Override
  public String toString() {
    return "Paperexercise{" +
            "paperId=" + paperId +
            ", eid=" + eid +
            '}';
  }
}
