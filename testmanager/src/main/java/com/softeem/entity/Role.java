package com.softeem.entity;


public class Role {


  private Integer role;   //角色编号，区分老师与学生身份的唯一凭证
  private String rolename;//角色名字





  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }


  public String getRolename() {
    return rolename;
  }

  public void setRolename(String rolename) {
    this.rolename = rolename;
  }

}
