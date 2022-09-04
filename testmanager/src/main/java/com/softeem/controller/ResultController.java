package com.softeem.controller;

import com.softeem.entity.Result;
import com.softeem.http.R;
import com.softeem.mapper.ResultMapper;
import com.softeem.service.ResultService;
import com.softeem.vo.AllCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/8/3 9:05
 * 项目名称 testmanager
 */
@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;
    @Autowired
    private ResultMapper resultMapper;


    @PostMapping("/addResult")
    public R getAllFinishPapers(@RequestBody Result result){
        Date date = new Date();
        String endTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        result.setEndTime(endTime);
        if(Integer.parseInt(result.getFinishtime())/60/1000<1){
            result.setFinishtime((Integer.parseInt(result.getFinishtime())/1000)+"秒");
        }else{
            result.setFinishtime((Integer.parseInt(result.getFinishtime())/1000/60)+"分钟");
        }
        result.setEndTime(endTime);
        return R.ok(200,"添加成功！",resultService.getAllFinishPapers(result));
    }

    @PostMapping("/findResultByPaperId")
    public R findResultByPaperId(@RequestBody AllCourses allCourses){
        return R.ok(200,"查询",resultMapper.findResults(allCourses));
    }

    @GetMapping("/findMyGradeResults/{gradeId}")
    public R findMyGradeResults(@PathVariable("gradeId")Integer gradeId){
        return R.ok(200,"获取成功！",resultMapper.findMyGradeResults(gradeId));
    }


}
