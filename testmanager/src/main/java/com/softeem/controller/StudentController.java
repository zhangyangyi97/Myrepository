package com.softeem.controller;

import com.softeem.entity.Paper;
import com.softeem.entity.Student;
import com.softeem.http.R;
import com.softeem.mapper.StudentMapper;
import com.softeem.service.StudentService;
import com.softeem.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:02
 * 项目名称 testmanager
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/findStudentNum")
    //获得学生总数
    public R getStudentNum(){
        return R.ok(studentService.getStudentNum());
    }

    @PostMapping("/login")
    //学生登录
    public R studentLogin(@RequestBody Student student){
        LoginVo loginVo = studentService.stuLogin(student);
        if(loginVo!=null){
            return R.ok(200,"登录成功！",loginVo);
        }
        return R.ok(500,"没有该学生！");
    }

    @GetMapping("/findStudents")
    public R findStudents(){
        return R.ok(studentMapper.findStudents());
    }

    @PostMapping("/findStuByKeyword")
    public R findStuByKeyword(@RequestBody Student student){
        if(student.getSex().equals("1")){
            student.setSex("男");
        }
        if(student.getSex().equals("2")){
            student.setSex("女");
        }
        return R.ok(studentService.findStuByKeyword(student));
    }

    //注册时失去焦点查找是否存在该学生
    @PostMapping("/findHasStudent")
    public R findHasStudent(@RequestBody Map map){
        String username =(String) map.get("username");
        if(studentMapper.findStuByUsername(username)==null){
           return R.ok(200,"该账户可用！");
        }else{
            return R.ok(500,"该账户不可用！");
        }
    }

    @GetMapping("/selectStudent/{username}")
    public R selectStudent(@PathVariable("username")String username){
        return R.ok(studentMapper.findStuByUsername(username));
    }

    @PostMapping("/register")
    public R studentRegister(@RequestBody Student student){
        student.setPassword(passwordEncoder.encode(student.getPassword()));;
        int i = studentMapper.stuRegister(student);
        if(i>0){
            System.out.println("i = " + i);
            return R.ok(200,"注册成功！",i);
        }else{
            return R.ok(500,"注册失败！",i);
        }
    }

    //修改学生信息
    @PostMapping("/update")
    public R updateStudent(@RequestBody Student student){
        int i = studentMapper.updateStudent(student);
        if(i>0){
            return R.ok(200,"修改成功！",i);
        }
        return R.ok(500,"修改失败！");
    }

    @GetMapping("/delStudent/{sid}")
    public R delStudent(@PathVariable("sid") Integer sid){
        int i = studentService.delStudent(sid);
        if(i>0){
            return R.ok(200,"删除成功！",i);
        }else{
            return R.ok(500,"删除失败！");
        }
    }

    //每个年级人数
    @GetMapping("/stuNums")
    public R getStuNums(){
        return R.ok(studentService.getStuNums());
    }


    //发布作业
    @PostMapping("/addHomework")
    public R addHomework(@RequestBody List<Paper> paperList){
        System.out.println("paperList = " + paperList);
        return R.ok(200,"发布作业成功！",studentService.addHomework(paperList));
    }


    //修改密码
    @PostMapping("/changePwd")
    public R changePwd(@RequestBody Student student){
        System.out.println("student = " + student);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        int i = studentMapper.updatePwd(student);
        if(i>0){
            return R.ok(200,"修改成功！",i);
        }
        return R.ok(500,"修改失败！");
    }

    @GetMapping("/getStuMsg/{username}")
    public R getStudent(@PathVariable("username")String username){
        return R.ok(studentMapper.findStuByUsername(username));
    }

}
