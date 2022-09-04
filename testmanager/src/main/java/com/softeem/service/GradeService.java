package com.softeem.service;

import com.softeem.entity.Student;
import com.softeem.mapper.GradeMapper;
import com.softeem.vo.GradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:00
 * 项目名称 testmanager
 */
@Service
public class GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    public Long getGradeNum(){
        return gradeMapper.getGradeNum();
    }


    //获取年级的所有信息
    public List<GradeVo> giveGradeMsg(){
        List<GradeVo> voList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            GradeVo gradeVo = new GradeVo();
            Integer stuNums =(int) gradeMapper.getStudentNumByGradeId(i);
            Student s = new Student();
            gradeVo.setGradeName(i+"年级");
            s.setSex("男");
            s.setGradeId(i);
            Integer mams =(int) gradeMapper.getSexNumByStudent(s);
            gradeVo.setGradeNum(stuNums);
            gradeVo.setManNum(mams);
            s.setSex("女");
            s.setGradeId(i);
            Integer womens = (int)gradeMapper.getSexNumByStudent(s);
            gradeVo.setWomenNum(womens);
            voList.add(gradeVo);
        }
        for (GradeVo vo : voList) {
            System.out.println("vo = " + vo);
        }
        return voList;
    }

}
