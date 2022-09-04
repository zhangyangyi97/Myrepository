package com.softeem.service;

import com.softeem.entity.*;
import com.softeem.mapper.PaperMapper;
import com.softeem.mapper.ResultMapper;
import com.softeem.vo.PaperCenterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/25 0:14
 * 项目名称 testmanager
 */
@Service
public class PaperService {

    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private ResultMapper resultMapper;

    public Long getPaperNum(){
        return paperMapper.getPaperNum();
    }


    //试卷的组成,将试卷传给前端
    public List<Object> paperInfo(String papername){
        List<Object> paperInfo = new ArrayList<>();
        List<Choice> choicesByPaperName = paperMapper.findChoicesByPaperName(papername);
        for (Choice choice : choicesByPaperName) {
            choice.setPapername(papername);
        }
        List<Exercise> exercisesByPaperName = paperMapper.findExercisesByPaperName(papername);
        for (Exercise exercise : exercisesByPaperName) {
            exercise.setPapername(papername);
        }
        List<Trueorfalse> trueByPaperName = paperMapper.findTrueByPaperName(papername);
        for (Trueorfalse trueorfalse : trueByPaperName) {
            trueorfalse.setPapername(papername);
        }
        paperInfo.addAll(choicesByPaperName);
        paperInfo.addAll(exercisesByPaperName);
        paperInfo.addAll(trueByPaperName);
        System.out.println("paperInfo = " + paperInfo);
        return paperInfo;
    }

    //获取paper表中最大的paperId
    public Integer getMaxPaperId(){
        List<Paper> paperList = paperMapper.selectPaperId();
        return paperList.stream().map(Paper::getPaperId).max(Integer::compareTo).get();
    }


    public List<Paper> getFinishPapers(PaperCenterVo paperCenterVo){
        List<Paper> allList = new ArrayList<>();
        List<Paper> paperList = paperMapper.selectFinishPapers(paperCenterVo);
        System.out.println("paperList = " + paperList);
        for (Paper paper : paperList) {
            Paperstudent paperstudent = new Paperstudent();
            paperstudent.setPaperId(paper.getPaperId());
            paperstudent.setSid(paper.getSid());
            allList.add(paperMapper.selectPaperInfo(paperstudent));
        }
        System.out.println("allList = " + allList);
        return allList;
    }

    public List<Paper> getFinishPapersByCourseId(PaperCenterVo paperCenterVo){
        List<Paper> allList = new ArrayList<>();
        List<Paper> newList = new ArrayList<>();
        List<Paper> paperList = paperMapper.selectFinishPapers(paperCenterVo);
        for (Paper paper : paperList) {
            Paperstudent paperstudent = new Paperstudent();
            paperstudent.setPaperId(paper.getPaperId());
            paperstudent.setSid(paper.getSid());
            allList.add(paperMapper.selectPaperInfo(paperstudent));
        }
        for (Paper paperInfo : allList) {
            if(paperInfo.getCoursetype().equals(paperCenterVo.getCoursetype())){
                newList.add(paperInfo);
            }
        }
        return newList;
    }

    public List<Paper> getFinishPapersByKeyword(PaperCenterVo paperCenterVo){
        List<Paper> allList = new ArrayList<>();
        List<Paper> newList = new ArrayList<>();
        List<Paper> paperList = paperMapper.selectFinishPapers(paperCenterVo);
        for (Paper paper : paperList) {
            Paperstudent paperstudent = new Paperstudent();
            paperstudent.setPaperId(paper.getPaperId());
            paperstudent.setSid(paper.getSid());
            allList.add(paperMapper.selectPaperInfo(paperstudent));
        }
        for (Paper paperInfo : allList) {
            if(paperInfo.getPapername().contains(paperCenterVo.getKeyword())){
                newList.add(paperInfo);
            }
        }
        return newList;
    }





}
