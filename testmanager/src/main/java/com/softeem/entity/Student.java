package com.softeem.entity;


public class Student {

  private Integer sid;      //学生编号
  private String username;  //学生姓名
  private String password;  //密码
  private String sex;       //性别
  private String birthday;  //生日
  private Integer gradeId;  //年级id,与后面的grade表格进行关联
  private Integer role;     //角色id 与角色表进行关联


  //查询学生的年级名称
  private String gradeName;
  //加角色
  private String roleName;

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getGradeName() {
    return gradeName;
  }

  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
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
    return "Student{" +
            "sid=" + sid +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", sex='" + sex + '\'' +
            ", birthday='" + birthday + '\'' +
            ", gradeId=" + gradeId +
            ", role=" + role +
            ", gradeName='" + gradeName + '\'' +
            ", roleName='" + roleName + '\'' +
            '}';
  }
}
