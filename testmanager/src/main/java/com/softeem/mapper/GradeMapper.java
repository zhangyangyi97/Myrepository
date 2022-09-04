package com.softeem.mapper;

import com.softeem.entity.Student;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/24 23:59
 * 项目名称 testmanager
 */
@Repository
//年级表
public interface GradeMapper {

    //查询年级总人数
    @Select("select count(*) from grade")
    long getGradeNum();

    //查询所有年级状态，学生人数，男生女生人数
    @Select("select count(*) from grade g,student s where g.gradeId=s.gradeId and g.gradeId = #{gradeId}")
    long getStudentNumByGradeId(Integer gradeId);

    //查询男生女生人数
    @Select("select count(*) from grade g,student s where g.gradeId=s.gradeId and g.gradeId = #{gradeId} and sex = #{sex}")
    long getSexNumByStudent(Student student);


}
