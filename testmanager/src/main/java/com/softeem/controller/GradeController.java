package com.softeem.controller;


import com.softeem.http.R;
import com.softeem.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:05
 * 项目名称 testmanager
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/findGradeNum")
    public R getGradeNum(){
        return R.ok(gradeService.getGradeNum());
    }

    @PostMapping("/getGradeMsg")
    public R getGradeMsg(){
        return R.ok(gradeService.giveGradeMsg());
    }
}
