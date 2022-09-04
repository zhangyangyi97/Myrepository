package com.softeem.service;

import com.softeem.entity.*;
import com.softeem.mapper.CourseMapper;
import com.softeem.mapper.PaperMapper;
import com.softeem.vo.AllCourses;
import com.softeem.vo.PaperVo;
import com.softeem.vo.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:10
 * 项目名称 testmanager
 */
@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private PaperMapper paperMapper;

    public Long  getCourseNum(){
        return courseMapper.getCourseNum();
    }


    //查找所有的题目
    public List<Object> getAllCourses(){
        List<Object> courses = new ArrayList<>();
        List<Choice> choice = courseMapper.findChoiceOne();
        List<Trueorfalse> trueOrFalse = courseMapper.findTrueOrFalse();
        List<Exercise> exercises = courseMapper.findExercises();
        for (Exercise exercise : exercises) {
            String uuid = UUID.randomUUID().toString();
            exercise.setUuid(uuid);
        }
        for (Trueorfalse trueorfalse : trueOrFalse) {
            String uuid = UUID.randomUUID().toString();
            trueorfalse.setUuid(uuid);
        }
        for (Choice choices : choice) {
            String uuid = UUID.randomUUID().toString();
            choices.setUuid(uuid);
        }
        courses.addAll(choice);
        courses.addAll(exercises);
        courses.addAll(trueOrFalse);
        return courses;
    }

    //模糊查询
    public List<Object> selectCourses(Params params){
        List<Object> courses = new ArrayList<>();
        List<Choice> choices = courseMapper.selectChoice(params);
        List<Exercise> exercises = courseMapper.selectExercise(params);
        List<Trueorfalse> trueorfalses = courseMapper.selectTrueOrFalse(params);
        for (Trueorfalse trueorfals : trueorfalses) {
            trueorfals.setUuid(UUID.randomUUID().toString());
        }
        for (Exercise exercise : exercises) {
            exercise.setUuid(UUID.randomUUID().toString());
        }
        for (Choice choice : choices) {
            choice.setUuid(UUID.randomUUID().toString());
        }
        courses.addAll(choices);
        courses.addAll(exercises);
        courses.addAll(trueorfalses);
        return courses;
    }

    //执行选择题添加
    public int addChoice(Choice choice){
        int add = courseMapper.addChoice(choice);
        if(add>0){
            return add;
        }
        return 0;
    }

    public int  addCoursesToPaper(List<AllCourses> coursesList){
        System.out.println("coursesList = " + coursesList);
        int count = 0;
        for (AllCourses course : coursesList) {
            if(course.getCourseId()==1 || course.getCourseId()==2){
                Paperchoice paperchoice = new Paperchoice();
                paperchoice.setPaperId(course.getPaperId());
                paperchoice.setCid(course.getCid());
                int choices = paperMapper.addPaperChoice(paperchoice);
                count+=choices;
            }
            if(course.getCourseId()==3){
                Papertrueorfalse papertrueorfalse = new Papertrueorfalse();
                papertrueorfalse.setPaperId(course.getPaperId());
                papertrueorfalse.setTid(course.getTid());
                int trueOrfalse = paperMapper.addPaperTrueOrfalse(papertrueorfalse);
                count+=trueOrfalse;
            }
            if(course.getCourseId()==4||course.getCourseId()==5){
                Paperexercise paperexercise = new Paperexercise();
                paperexercise.setEid(course.getEid());
                paperexercise.setPaperId(course.getPaperId());
                int exercises = paperMapper.addPaperExercise(paperexercise);
                count+=exercises;
            }
        }
        System.out.println("count = " + count);
        return count;
    }


    //错题本的添加
    public int addFalseCourses(List<AllCourses> allCourses){
        System.out.println("allCourses = " + allCourses);
        int count = 0;
        for (AllCourses allCourse : allCourses) {
            if(allCourse.getCid()!=null){
                Studentchoice sc = new Studentchoice();
                sc.setCid(allCourse.getCid());
                sc.setSid(allCourse.getSid());
                int i = courseMapper.addFalseChoice(sc);
                count+=i;
            }
            if(allCourse.getEid()!=null){
                Studentexercise se = new Studentexercise();
                se.setEid(allCourse.getEid());
                se.setSid(allCourse.getSid());
                int i = courseMapper.addFalseExercise(se);
                count+=i;
            }
            if(allCourse.getTid()!=null){
                Studenttrue st = new Studenttrue();
                st.setSid(allCourse.getSid());
                st.setTid(allCourse.getTid());
                int i = courseMapper.addFalseTrueOrFalse(st);
                count+=i;
            }
        }
        return count;
    }


    //展示错题本
    public List<AllCourses> getAllFalseCourses(String username){
        List<AllCourses> allCoursesList = new ArrayList<>();
        List<AllCourses> choices = courseMapper.findAllFalseChoice(username);
        List<AllCourses> allExercise = courseMapper.findAllExercise(username);
        List<AllCourses> allTrues = courseMapper.findAllTrues(username);
        allCoursesList.addAll(choices);
        allCoursesList.addAll(allExercise);
        allCoursesList.addAll(allTrues);
        return allCoursesList;
    }

    //删除错题本
    public String delCourse(AllCourses allCourses){
        if(allCourses.getTid()!=null){
            courseMapper.delTrue(allCourses.getTid());
        }
        if(allCourses.getCid()!=null){
            courseMapper.delChoice(allCourses.getCid());
        }
        if(allCourses.getEid()!=null){
            courseMapper.delExercise(allCourses.getEid());
        }
        return "删除成功！";
    }

    //根据courseId查询
    public List<AllCourses> getFalseCoursesByCourseId(String username,Integer courseId){
        List<AllCourses> allCoursesList = new ArrayList<>();
        List<AllCourses> choices = courseMapper.findAllFalseChoice(username);
        List<AllCourses> allExercise = courseMapper.findAllExercise(username);
        List<AllCourses> allTrues = courseMapper.findAllTrues(username);
        allCoursesList.addAll(choices);
        allCoursesList.addAll(allExercise);
        allCoursesList.addAll(allTrues);
        List<AllCourses> courseList = new ArrayList<>();
        for (AllCourses allCourses : allCoursesList) {
            if(allCourses.getCourseId().equals(courseId)){
                courseList.add(allCourses);
            }
        }
        return courseList;
    }

    //根据courseId查询
    public List<AllCourses> getFalseCoursesByType(String username,Integer coursetype){
        List<AllCourses> allCoursesList = new ArrayList<>();
        List<AllCourses> choices = courseMapper.findAllFalseChoice(username);
        List<AllCourses> allExercise = courseMapper.findAllExercise(username);
        List<AllCourses> allTrues = courseMapper.findAllTrues(username);
        allCoursesList.addAll(choices);
        allCoursesList.addAll(allExercise);
        allCoursesList.addAll(allTrues);
        List<AllCourses> courseList = new ArrayList<>();
        for (AllCourses allCourses : allCoursesList) {
            if(allCourses.getCourseId().equals(coursetype)){
                courseList.add(allCourses);
            }
        }
        return courseList;
    }


    //删除题目
    public int delCourses(AllCourses allCourses){
        int count = 0;
        if(allCourses.getCid()!=null){
            count = courseMapper.deleteChoice(allCourses.getCid());
        }
        if(allCourses.getEid()!=null){
            count = courseMapper.deleteExercise(allCourses.getEid());
        }
        if(allCourses.getTid()!=null){
            count = courseMapper.deleteTrue(allCourses.getTid());
        }
        return count;
    }
}
