package com.softeem.entity;


public class Teacher {

  private Integer tid;      //教师编号
  private String tname;     //教师姓名
  private String username;  //教师登录时用的昵称
  private String password;  //登录时用的密码
  private Integer gradeId;  //年级id,与后面的grade表格进行关联
  private Integer role;     //角色id 与角色表进行关联

  //额外查询年级名称
  private String gradeName;
  //添加角色
  private String roleName;

  public String getGradeName() {
    return gradeName;
  }

  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
  }


  public String getTname() {
    return tname;
  }

  public void setTname(String tname) {
    this.tname = tname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }


  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "Teacher{" +
            "tid=" + tid +
            ", tname='" + tname + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", gradeId=" + gradeId +
            ", role=" + role +
            ", gradeName='" + gradeName + '\'' +
            ", roleName='" + roleName + '\'' +
            '}';
  }
}
