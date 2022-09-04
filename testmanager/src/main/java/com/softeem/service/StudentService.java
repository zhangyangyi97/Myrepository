package com.softeem.service;

import com.softeem.entity.Paper;
import com.softeem.entity.Paperstudent;
import com.softeem.entity.Student;
import com.softeem.mapper.CourseMapper;
import com.softeem.mapper.GradeMapper;
import com.softeem.mapper.PaperMapper;
import com.softeem.mapper.StudentMapper;
import com.softeem.utils.JwtUtil;
import com.softeem.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/23 10:59
 * 项目名称 testmanager
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    //密码的加密校验
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private PaperMapper paperMapper;


    public LoginVo stuLogin(Student student){
        Student currentStudent = studentMapper.findStuByUsername(student.getUsername());
        System.out.println("currentStudent = " + currentStudent);
        if(currentStudent==null){
            //在查不到这个学生时
            throw new UsernameNotFoundException("用户名校验错误");
        }

        if(!passwordEncoder.matches(student.getPassword(),currentStudent.getPassword())){
            throw new BadCredentialsException("密码校验错误！");
        }

        LoginVo loginVo = new LoginVo();
        loginVo.setRoleName(currentStudent.getRoleName()); //添加角色
        loginVo.setUsername(currentStudent.getUsername());
        loginVo.setToken(JwtUtil.gengrate(currentStudent.getUsername()));
        return loginVo;
    }



    public Long getStudentNum(){
        return studentMapper.gerStudentNum();
    }

    //模糊查询
    public List<Student> findStuByKeyword(Student student){
        System.out.println("student = " + student);
        return studentMapper.findStuByKeyword(student);
    }

    //查询每个年级的人数
    public List<Object> getStuNums(){
        List<Object> stuNums = new ArrayList<>();
        long i = gradeMapper.getGradeNum();
        for (int j = 0; j < i; j++) {
            long gradeStuNum = studentMapper.gradeStuNum((j + 1));
            stuNums.add(gradeStuNum);
        }
        return stuNums;
    }

    //发布作业
    public int addHomework(List<Paper> papers){
        System.out.println("papers = " + papers);
        int count = 0;
        for (Paper paper : papers) {
            Paperstudent paperstudent = new Paperstudent();
            paperstudent.setPaperId(paper.getPaperId());
            paperstudent.setSid(paper.getSid());
            paperMapper.addToPaperStudent(paper);
        }
        System.out.println("count = " + count);
        return count;
    }


    //删除学生
    public int delStudent(Integer sid) {
        int count = 0;
        int delStudent = studentMapper.delStudent(sid);
        int i = paperMapper.delPaperStu(sid);
        int e = courseMapper.delExercise(sid);
        int c = courseMapper.delChoice(sid);
        int t = courseMapper.delStuTrue(sid);
        count = i+e+c+t+delStudent;
        return count;
    }
}
