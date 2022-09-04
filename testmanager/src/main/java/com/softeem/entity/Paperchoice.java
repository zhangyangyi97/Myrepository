package com.softeem.entity;


public class Paperchoice {

  private Integer paperId;
  private Integer cid;


  public Integer getPaperId() {
    return paperId;
  }

  public void setPaperId(Integer paperId) {
    this.paperId = paperId;
  }


  public Integer getCid() {
    return cid;
  }

  public void setCid(Integer cid) {
    this.cid = cid;
  }

  @Override
  public String toString() {
    return "Paperchoice{" +
            "paperId=" + paperId +
            ", cid=" + cid +
            '}';
  }
}
