package com.softeem.mapper;

import com.softeem.entity.*;
import com.softeem.vo.AllCourses;
import com.softeem.vo.CheckFinishVo;
import com.softeem.vo.PaperCenterVo;
import com.softeem.vo.Params;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:13
 * 项目名称 testmanager
 */
@Repository
public interface PaperMapper {

    //查询卷子总数
    @Select("select count(*) from paper")
    long getPaperNum();

    //查询所有的卷子
    @Select("select p.*,c.ctname from paper p,coursetype c where c.coursetype = p.coursetype")
    List<Paper> findPapers();

    //查询所有的卷子
    @Select("SELECT B.*,c.ctname from " +
            " (SELECT A.* from " +
            "  (select ps.paperstatus,p.*,ps.sid from paper p,paperstudent ps where p.paperId = ps.paperId ) as A" +
            " where A.sid =( SELECT sid from student where username =#{username})) as B,coursetype c " +
            " where c.coursetype = B.coursetype")
    List<Paper> findPapersByGradeId(String username);


    //查询所有的卷子
    @Select("SELECT A.* from " +
            " (select ps.paperstatus,p.*,ps.sid from paper p,paperstudent ps where p.paperId = ps.paperId and ps.paperstatus=#{paperstatus}) as A" +
            " where A.sid =( SELECT sid from student where username =#{username})")
    List<Paper> findCheckFinish(CheckFinishVo checkFinishVo);

    //查询其卷子下选择题
    @Select("select A.*,c.ctname FROM" +
            " (SELECT * from choice  where cid in" +
            " (SELECT cid FROM paperchoice where paperId in " +
            " (SELECT paperId from paper where papername = #{papername}))) as A,coursetype c" +
            " where c.coursetype = A.coursetype")
    List<Choice> findChoicesByPaperName(String papername);

    //查询填空和简答题
    @Select("select A.*,c.ctname FROM" +
            " (SELECT * from exercise  where eid in" +
            " (SELECT eid FROM paperexercise where paperId in " +
            " (SELECT paperId from paper where papername = #{papername}))) as A,coursetype c" +
            " where c.coursetype = A.coursetype")
    List<Exercise> findExercisesByPaperName(String papername);

    //查询判断
    @Select("select A.*,c.ctname FROM\n" +
            " (SELECT * from trueorfalse  where tid in" +
            " (SELECT tid FROM papertrueorfalse where paperId in " +
            " (SELECT paperId from paper where papername = #{papername}))) as A,coursetype c" +
            " where c.coursetype = A.coursetype")
    List<Trueorfalse> findTrueByPaperName(String paperName);


    //获取卷子的编号
    @Select("select * from paper")
    List<Paper> selectPaperId();

    //添加卷子
    int addPaper(Paper paper);

    //选择题关联表添加
    @Insert("insert into paperchoice (paperId,cid) value (#{paperId},#{cid})")
    int addPaperChoice(Paperchoice paperchoice);

    @Insert("insert into papertrueorfalse (paperId,tid) value(#{paperId},#{tid})")
    int addPaperTrueOrfalse(Papertrueorfalse papertrueorfalse);

    @Insert("insert into paperexercise (paperId,eid) value(#{paperId},#{eid})")
    int addPaperExercise(Paperexercise paperexercise);

    //根据学科查询卷子
    @Select("select B.*  from " +
            " (select p.*,c.ctname from paper p,coursetype c where c.coursetype = p.coursetype and p.coursetype = #{coursetype}) as B" +
            " where B.paperId in(SELECT paperId from paperstudent where sid = (SELECT sid from student where username=#{username}))")
    List<Paper> selectPaperByCoursetype(AllCourses allCourses);

    //老师端根据学科查卷子
    @Select("select p.*,c.ctname from paper p,coursetype c where c.coursetype = p.coursetype and p.coursetype = #{coursetype}")
    List<Paper> selectByCourseType(Integer coursetype);

    @Select("select p.*,c.ctname from paper p,coursetype c where c.coursetype = p.coursetype and p.gradeId = #{gradeId}")
    List<Paper> selectByGradeId(Integer gradeId);



    //查询所有卷子
    List<Paper> selectAllPapers(Params params);


    @Select("select p.*,cou.ctname,g.gradename,ps.paperstatus from paper p,grade g,coursetype cou,paperstudent ps" +
            "            where p.coursetype = cou.coursetype and g.gradeId = p.gradeId and ps.paperId=p.paperId and p.paperId = #{paperId} and ps.sid = #{sid}")
    Paper selectPaperInfo(Paperstudent paperstudent);






    //学生端查找固定试卷
    @Select("SELECT A.* from" +
            " (select ps.paperstatus,p.*,ps.sid from paper p,paperstudent ps where p.paperId = ps.paperId) as A" +
            " where A.sid =( SELECT sid from student where username =#{username})")
    List<Paper> findHomework(String username);

    //修改卷子状态
    @Update("update paperstudent set paperstatus = #{paperstatus} where paperId = #{paperId}")
    int updateStatus(Paperstudent paperstudent);

    List<Paper> selectFinishPapers(PaperCenterVo paperCenterVo);


    //通过关联表删除
    @Select("delete from paperstudent where sid =#{sid}")
    int delPaperStu(Integer sid);

    //执行添加中间表
    @Insert("insert into paperstudent(sid,paperId,paperstatus) value(#{sid},#{paperId},#{paperstatus})")
    int addToPaperStudent(Paper paper);

    //试卷的回显
    @Insert("insert into paperanswer (paperId,answer) value(#{paperId},#{answer})")
    int addAnswerToPaper(PaperAnswer paperAnswer);
}
