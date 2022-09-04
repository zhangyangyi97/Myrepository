package com.softeem.mapper;

import com.softeem.entity.Paper;
import com.softeem.entity.Result;
import com.softeem.vo.AllCourses;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/8/3 8:57
 * 项目名称 testmanager
 */
@Repository
public interface ResultMapper {

    //添加做完的试卷
    @Insert("insert into result (sid,paperId,score,finishtime,endTime,truecount,falsecount) value(#{sid},#{paperId},#{score},#{finishtime},#{endTime},#{truecount},#{falsecount})")
    int addResults(Result result);

    //根据paperId查找做的试卷
    @Select("SELECT r.* from paper p,result r where p.paperId = r.paperId and p.paperId = #{paperId} and r.sid = #{sid}")
    Result findResults(AllCourses allCourses);


    //老师查询自己年级学生的试卷
    @Select("SELECT s.username,B.* from student s," +
            " (SELECT p.papername,A.* from paper p," +
            " (select * from result where sid in (SELECT sid from student where gradeId = #{gradeId})) as A" +
            " where A.paperId = p.paperId) AS B" +
            " where s.sid = B.sid")
    List<Result> findMyGradeResults(Integer gradeId);
}
