package com.softeem.controller;

import com.softeem.entity.Choice;
import com.softeem.entity.Exercise;
import com.softeem.entity.Trueorfalse;
import com.softeem.http.R;
import com.softeem.mapper.CourseMapper;
import com.softeem.service.CourseService;

import com.softeem.vo.AllCourses;
import com.softeem.vo.Params;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:11
 * 项目名称 testmanager
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    @GetMapping("/findCourseNum")
    public R getCourseNum(){
        return R.ok(courseService.getCourseNum());
    }


    //查找所有的题目
    @PostMapping("/findAllCourses")
    public R findChoiceOne(){
        List<Object> allCourses = courseService.getAllCourses();
        return R.ok(200,"得到所有的题目集合！",allCourses);
    }



    //查找所有的类型
    @GetMapping("/findCourses")
    public R findCourses(){
        return R.ok(courseMapper.findCourses());
    }


    //查找所有的学科
    @GetMapping("/findCourseTypes")
    public R findCourseTypes(){
        return R.ok(courseMapper.findCourseTypes());
    }

    //模糊查询
    @PostMapping("/select")
    public R select(@RequestBody Params params){

        return R.ok(courseService.selectCourses(params));
    }

    //选择题的添加
    @PostMapping("/addChoice")
    public R addChoice(@RequestBody Choice choice){
        System.out.println("choice = " + choice);
        int i = courseService.addChoice(choice);
        if(i>0){
            return R.ok(200,"添加成功！");
        }else{
            return R.ok(500,"添加失败！");
        }
    }

    //简述题,填空题的添加
    @PostMapping("/addExercise")
    public R addExercise(@RequestBody Exercise exercise){
        int i = courseMapper.addExercise(exercise);
        if(i>0){
            return R.ok(200,"添加成功！");
        }else{
            return R.ok(500,"添加失败！");
        }
    }
    //判断题的添加
    @PostMapping("/trueorfalse")
    public R addTrues(@RequestBody Trueorfalse trueorfalse){
        int i = courseMapper.addTrusorfalse(trueorfalse);
        if(i>0){
            return R.ok(200,"添加成功！");
        }else{
            return R.ok(500,"添加失败！");
        }
    }


    //查找ctname
    @GetMapping("/selectCtName/{courseType}")
    public R selectCtName(@PathVariable("courseType") Integer courseType){
        System.out.println("courseType = " + courseType);
        return R.ok(courseMapper.selectCtname(courseType));
    }

    //执行修改
    @PostMapping("/updateChoice")
    public R updateChoice(@RequestBody Choice choice){
        int i = courseMapper.updateChoice(choice);
        if(i>0){
            return R.ok(200,"修改成功！");
        }else {
            return R.ok(500,"修改失败!");
        }
    }

    @PostMapping("/updateExercise")
    public R updateExercise(@RequestBody Exercise exercise){
        int i = courseMapper.updateExercise(exercise);
        if(i>0){
            return R.ok(200,"修改成功！");
        }else {
            return R.ok(500,"修改失败!");
        }
    }
    @PostMapping("/updateTrue")
    public R updateTrue(@RequestBody Trueorfalse trueorfalse){
        int i = courseMapper.updateTrue(trueorfalse);
        if(i>0){
            return R.ok(200,"修改成功！");
        }else {
            return R.ok(500,"修改失败!");
        }
    }

    @PostMapping("/addCourseToPaper")
    public R addCourseToPaper(@RequestBody List<AllCourses> coursesList){
        System.out.println("coursesList = " + coursesList);
        int courses = courseService.addCoursesToPaper(coursesList);
        return R.ok(200,"添加成功！",courses);
    }


    //添加错题本
    @PostMapping("/addFalseCourse")
    public R addFalseCourses(@RequestBody List<AllCourses> allCourses){
        System.out.println("allCourses = " + allCourses);
        return R.ok(200,"添加错题成功！",courseService.addFalseCourses(allCourses));
    }

    //展示错题本
    @GetMapping("/findAllFalseCourses/{username}")
    public R findAllFalseCourses(@PathVariable("username")String username){
        return R.ok(courseService.getAllFalseCourses(username));
    }

    //删除错题
    @PostMapping("/delFalseCourse")
    public R delFalseCourse(@RequestBody AllCourses allCourses){
        System.out.println("allCourses = " + allCourses);
        return R.ok(200,"删除成功！",courseService.delCourse(allCourses));
    }
    //删除题库的题
    //删除错题
    @PostMapping("/delCourse")
    public R deleteCourse(@RequestBody AllCourses allCourses){
        System.out.println("allCourses = " + allCourses);
        return R.ok(200,"删除成功！",courseService.delCourses(allCourses));
    }

    //根据courseId查询
    @GetMapping("/findFalseByCourseId")
    public R findFalseByCourseId(@Param("username")String username,@Param("courseId")Integer courseId){
        return R.ok(courseService.getFalseCoursesByCourseId(username,courseId));
    }

    //根据courseType获取
    @GetMapping("/findFalseByCourseType")
    public R findFalseByCourseType(@Param("username")String username,@Param("coursetype")Integer coursetype){
        return R.ok(courseService.getFalseCoursesByCourseId(username,coursetype));
    }

    //获取所有年级
    @GetMapping("/findGradeId")
    public R getAllGrades(){
        return R.ok(courseMapper.selectAllGrades());
    }
}
