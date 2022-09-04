package com.softeem.mapper;

import com.softeem.entity.Teacher;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/23 11:27
 * 项目名称 testmanager
 */
@Repository
public interface TeacherMapper {

    //老师登录，校验登录昵称
    Teacher findTeaByUsername(String username);

    //通过老师昵称找到老师名字
    Teacher findTeaAllByUsername(String username);

    //老师注册
    int teaRegister(Teacher teacher);

    //修改老师信息
    int updateTeacher(Teacher teacher);

    //根据姓名查找老师密码
    @Select("select password from teacher where username = #{username}")
    Teacher selectPasswordByUsername(String username);

    //修改密码
    @Update("update teacher set password = #{password} where username = #{username}")
    int updatePassword(Teacher teacher);

}
