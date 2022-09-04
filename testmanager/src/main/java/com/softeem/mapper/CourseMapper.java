package com.softeem.mapper;

import com.softeem.entity.*;
import com.softeem.vo.AllCourses;
import com.softeem.vo.Params;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:08
 * 项目名称 testmanager
 */
@Repository
public interface CourseMapper {

    //查询数据库中所有题目的数量（包括各种题型的多表的查询）
    @Select("SELECT (SELECT count(*) FROM choice) + (SELECT count(*) from trueorfalse) + (select count(*) from exercise) FROM DUAL")
    long getCourseNum();

    //查询单选题，多选的所有
    @Select("select c.*,ct.ctname,cou.coursename from choice c,coursetype ct,course cou" +
            " where c.courseId = cou.courseId and c.coursetype = ct.coursetype ")
    List<Choice> findChoiceOne();


    //查询填空简述题,的所有
    @Select("select e.*,ct.ctname,cou.coursename from exercise e,coursetype ct,course cou" +
            " where e.courseId = cou.courseId and e.coursetype = ct.coursetype ")
    List<Exercise> findExercises();

    //查找所有的填空题
    @Select("select t.*,ct.ctname,cou.coursename from trueorfalse t,coursetype ct,course cou" +
            " where t.courseId = cou.courseId and t.coursetype = ct.coursetype ")
    List<Trueorfalse> findTrueOrFalse();




    //查找所有学科
    @Select("select * from course")
    List<Course> findCourses();

    //查找所有类型
    @Select("select * from coursetype")
    List<Coursetype> findCourseTypes();

    //模糊查询
    List<Choice> selectChoice(Params param);
    List<Exercise> selectExercise(Params params);
    List<Trueorfalse> selectTrueOrFalse(Params params);

    //执行添加
    int addChoice(Choice choice);
    int addExercise(Exercise exercise);
    int addTrusorfalse(Trueorfalse trueorfalse);


    //查找ctname
    @Select("select * from coursetype where coursetype = #{coursetype}")
    Choice selectCtname(Integer courseType);

    //执行修改
    int updateChoice(Choice choice);
    int updateExercise(Exercise exercise);
    int updateTrue(Trueorfalse trueorfalse);

    //添加错题
    @Insert("insert into studentchoice (sid,cid) value(#{sid},#{cid})")
    int addFalseChoice(Studentchoice choice);

    @Insert("insert into studentexercise (sid,eid) value(#{sid},#{cid})")
    int addFalseExercise(Studentexercise exercise);

    @Insert("insert into studenttrue (sid,tid) value(#{sid},#{tid})")
    int addFalseTrueOrFalse(Studenttrue trueorfalse);


    //查找错题
    @Select("SELECT * from choice where cid in(SELECT cid from studentchoice where sid = (SELECT sid from student where username = #{username}))")
    List<AllCourses> findAllFalseChoice(String username);

    @Select("SELECT * from exercise where eid in(SELECT eid from studentexercise where sid = (SELECT sid from student where username = #{username}))")
    List<AllCourses> findAllExercise(String username);

    @Select("SELECT * from trueorfalse where tid in(SELECT tid from studenttrue where sid = (SELECT sid from student where username = #{username}))")
    List<AllCourses> findAllTrues(String username);

    @Delete("delete from studentchoice where cid = #{cid}")
    int delChoice(Integer cid);
    @Delete("delete from studentexercise where eid = #{eid}")
    int delExercise(Integer eid);
    @Delete("delete from studenttrue where tid = #{tid}")
    int delTrue(Integer tid);

    //根据courseId获取
    @Select("select c.*,ct.ctname,cou.coursename from choice c,coursetype ct,course cou" +
            " where c.courseId = cou.courseId and c.coursetype = ct.coursetype where c.courseId = #{courseId}")
    List<AllCourses> findFalseChoice(Integer courseId);

    @Select("select e.*,ct.ctname,cou.coursename from exercise e,coursetype ct,course cou" +
            " where e.courseId = cou.courseId and e.coursetype = ct.coursetype where e.courseId = #{courseId}")
    List<AllCourses> findFalseExercise(Integer courseId);


    @Select("select t.*,ct.ctname,cou.coursename from trueorfalse t,coursetype ct,course cou" +
            " where t.courseId = cou.courseId and t.coursetype = ct.coursetype where t.courseId = #{courseId}")
    List<AllCourses> findFalseTrueOrFalse(Integer courseId);



    //删除
    @Delete("delete from studentchoice where sid = #{sid}")
    int delStuChoice(Integer sid);
    @Delete("delete from studentexercise where sid = #{sid}")
    int delStuExercise(Integer sid);
    @Delete("delete from studenttrue where sid = #{sid}")
    int delStuTrue(Integer sid);


    //删除
    @Delete("delete from choice where cid = #{cid}")
    int deleteChoice(Integer cid);
    @Delete("delete from exercise where eid = #{eid}")
    int deleteExercise(Integer eid);
    @Delete("delete from trueorfalse where tid = #{tid}")
    int deleteTrue(Integer tid);

    //获取所有年级
    @Select("select * from  grade")
    List<Grade> selectAllGrades();
}
