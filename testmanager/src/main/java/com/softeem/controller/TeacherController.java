package com.softeem.controller;

import com.softeem.entity.Teacher;
import com.softeem.http.R;
import com.softeem.mapper.TeacherMapper;
import com.softeem.service.TeacherService;
import com.softeem.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:41
 * 项目名称 testmanager
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //老师的登录
    @PostMapping("/login")
    public R teacherLogin(@RequestBody Teacher teacher){
        LoginVo loginVo = teacherService.teacherLogin(teacher);
        if(loginVo!=null){
            return R.ok(200,"登录成功！",loginVo);
        }
        return R.ok(500,"您不是老师！");
    }

    @PostMapping("/getTeacher")
    public R getTeacher(@RequestBody String username){
        username = username.substring(0,username.length()-1);
        System.out.println("username = " + username);
        return R.ok(teacherMapper.findTeaAllByUsername(username));
    }

    @PostMapping("/update")
    public R updateTeacher(@RequestBody Teacher teacher){
        return R.ok(200,"修改成功！",teacherService.updateTeacher(teacher));
    }


    //查找该老师的昵称是否存在
    @PostMapping("/findHasTeacher")
    public R findHasStudent(@RequestBody Map map){
        String username =(String) map.get("username");
        if(teacherMapper.findTeaByUsername(username)==null){
            return R.ok(200,"昵称可以使用哦！");
        }else{
            return R.ok(500,"该昵称不可用！");
        }
    }


    @PostMapping("/register")
    public R studentRegister(@RequestBody Teacher teacher){
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));;
        int i = teacherMapper.teaRegister(teacher);
        if(i>0){
            System.out.println("i = " + i);
            return R.ok(200,"注册成功！",i);
        }else{
            return R.ok(500,"注册失败！",i);
        }
    }

    @GetMapping("/passwordIsTrue/{password}")
    public R getPassword(@PathVariable("password") String password){
        System.out.println("password = " + password);
        String pwd = teacherService.getPassword();
        if(passwordEncoder.matches(password,pwd)){
            return R.ok(200,"校验成功！");
        }
        return R.ok(500,"校验失败！");
    }

    //修改密码
    @PostMapping("/changePwd")
    public R updatePassword(@RequestBody Teacher teacher){
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        int i = teacherMapper.updatePassword(teacher);
        if(i>0){
            return R.ok(200,"修改成功！",i);
        }
        return R.ok(500,"修改失败！");
    }
}
