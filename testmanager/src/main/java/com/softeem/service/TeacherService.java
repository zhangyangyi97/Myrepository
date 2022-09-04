package com.softeem.service;

import com.softeem.entity.Teacher;
import com.softeem.mapper.TeacherMapper;
import com.softeem.utils.JwtUtil;
import com.softeem.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:36
 * 项目名称 testmanager
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginVo teacherLogin(Teacher teacher){
        Teacher currentTea = teacherMapper.findTeaByUsername(teacher.getUsername());
        if(currentTea==null){
            //在查不到这个老师账号时
            throw new UsernameNotFoundException("老师用户名校验错误");
        }

        if(!passwordEncoder.matches(teacher.getPassword(),currentTea.getPassword())){
            throw new BadCredentialsException("密码校验错误！");
        }
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername(currentTea.getUsername());
        loginVo.setToken(JwtUtil.gengrate(teacher.getUsername()));
        loginVo.setRoleName(currentTea.getRoleName());
        return loginVo;
    }

    //修改老师信息
    public int updateTeacher(Teacher teacher){
        return teacherMapper.updateTeacher(teacher);
    }

    //判断老师原密码
    public String getPassword(){
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Teacher teacher = teacherMapper.selectPasswordByUsername(username);
        return teacher.getPassword();
    }

}
