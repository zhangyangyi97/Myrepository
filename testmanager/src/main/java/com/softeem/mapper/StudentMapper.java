package com.softeem.mapper;

import com.softeem.entity.Paper;
import com.softeem.entity.Paperstudent;
import com.softeem.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/23 10:57
 * 项目名称 testmanager
 */
@Repository
public interface StudentMapper {

    //学生登录,进行校验，后面颁发凭证jwt
    Student findStuByUsername(String username);

    //学生注册
    int stuRegister(Student student);

    //查询学生总人数
    @Select("select count(*) from student")
    long gerStudentNum();

    //查找所有的学生
    @Select("select s.*,g.gradename from student s,grade g where s.gradeId = g.gradeId ")
    List<Student> findStudents();

    //模糊查询
    List<Student> findStuByKeyword(Student student);

    //根据id删除学生
    @Delete("delete from student where sid = #{sid}")
    int delStudent(Integer sid);

    //查询每个年级的人数
    @Select("SELECT count(*) from grade g,student s WHERE s.gradeId = g.gradeId and s.gradeId = #{gradeId}")
    long gradeStuNum(Integer gradeId);


    //修改学生
    int updateStudent(Student student);


    //修改密码
    @Update("update student set password = #{password} where username = #{username}")
    int updatePwd(Student student);

}
