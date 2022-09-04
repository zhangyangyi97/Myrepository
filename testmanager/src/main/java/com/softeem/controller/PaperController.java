package com.softeem.controller;

import com.softeem.entity.Paper;
import com.softeem.entity.Paperstudent;
import com.softeem.http.R;
import com.softeem.mapper.PaperMapper;
import com.softeem.service.PaperService;
import com.softeem.vo.AllCourses;
import com.softeem.vo.CheckFinishVo;
import com.softeem.vo.PaperCenterVo;
import com.softeem.vo.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:15
 * 项目名称 testmanager
 */
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;
    @Autowired
    private PaperMapper paperMapper;

    @GetMapping("/findPaperNum")
    public R getPaperNum(){
        return R.ok(paperService.getPaperNum());
    }

    //向前端传所有的试卷
    @GetMapping("/findPapersByPapername/{papername}")
    public R getAllPapers(@PathVariable("papername") String papername){
        System.out.println("papername = " + papername);
        return R.ok(paperService.paperInfo(papername));
    }

    @PostMapping("/findPapersOnly")
    public R getPapers(){
        return R.ok(paperMapper.findPapers());
    }

    //学生只能看到自己的试卷
    @GetMapping("/findMyselfPapers/{username}")
    public R getMyselfPapers(@PathVariable("username") String username){
        System.out.println("username = " + username);
        return R.ok(paperMapper.findPapersByGradeId(username));
    }


    @GetMapping("/getMaxPaperId")
    public R getMaxPaperId(){
        return R.ok(paperService.getMaxPaperId());
    }


    @PostMapping("/addPaper")
    public R addPaper(@RequestBody Paper paper){
        System.out.println("paper = " + paper);
        Date date = new Date(System.currentTimeMillis());
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        paper.setFirstInit(time);
        int i = paperMapper.addPaper(paper);
        if(i>0){
            return R.ok(200,"添加成功！",i);
        }
        return R.ok(500,"添加失败！");
    }

    @PostMapping("/selectPaperByCoursetype")
    public R selectPaperByCourseType(@RequestBody AllCourses allCourses){
        List<Paper> paperList = paperMapper.selectPaperByCoursetype(allCourses);
        return R.ok(200,"查卷子类型成功！",paperList);
    }

    //查找试卷的所有
    @PostMapping("/selectPaperByPaperId")
    public R selectPaperByPaperId(@RequestBody Paperstudent paperstudent){
        Paper paper = paperMapper.selectPaperInfo(paperstudent);
        return R.ok(paper);
    }


    @PostMapping("/select")
    public R selectAll(@RequestBody Params params){
        List<Paper> paperList = paperMapper.selectAllPapers(params);
        return R.ok(200,"试卷信息！",paperList);
    }

    @GetMapping("/getHomework/{username}")
    public R getHomework(@PathVariable("username")String username){
        List<Paper> homework = paperMapper.findHomework(username);
        System.out.println("homework = " + homework);
        return R.ok(homework);
    }

    @PostMapping("/updateStatus")
    public R updateStatus(@RequestBody Paperstudent paperstudent){
        paperstudent.setPaperstatus(1);
        return R.ok(200,"修改成功！",paperMapper.updateStatus(paperstudent));
    }

    @PostMapping("/getFinishPapers")
    public R getFinishPapers(@RequestBody PaperCenterVo paperCenterVo){
        List<Paper> finishPapers = paperService.getFinishPapers(paperCenterVo);
        System.out.println("finishPapers = " + finishPapers);
        return R.ok(finishPapers);
    }

    @PostMapping("/getFinishByCourseId")
    public R getFinishByCourseId(@RequestBody PaperCenterVo paperCenterVo){
        return R.ok(paperService.getFinishPapersByCourseId(paperCenterVo));
    }

    @PostMapping("/getFinishByKeyword")
    public R getFinishByKeyword(@RequestBody PaperCenterVo paperCenterVo){
        return R.ok(paperService.getFinishPapersByKeyword(paperCenterVo));
    }

    @PostMapping("/getCheckFinish")
    public R getCheckFinish(@RequestBody CheckFinishVo checkFinishVo){
        return R.ok(paperMapper.findCheckFinish(checkFinishVo));
    }


    //老师端根据学科查询试卷
    @GetMapping("/selectByCoursetype/{coursetype}")
    public R getPapersByCoursetype(@PathVariable("coursetype")Integer coursetype){
        return R.ok(200,"根据学科查询！",paperMapper.selectByCourseType(coursetype));
    }

    //老师端根据年级查询试卷
    @GetMapping("/selectByGradeId/{gradeId}")
    public R getPapersByGradeId(@PathVariable("gradeId")Integer gradeId){
        return R.ok(200,"根据学科查询！",paperMapper.selectByGradeId(gradeId));
    }
}
