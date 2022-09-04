package com.softeem.vo;

import com.softeem.entity.Choice;
import com.softeem.entity.Exercise;
import com.softeem.entity.Trueorfalse;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/27 0:32
 * 项目名称 testmanager
 */
public class PaperVo {

    //判断题的集合
    private List<Trueorfalse> trueOrFalseList;

    //选择题的集合
    private List<Choice> choiceList;

    //填空和简述题的集合
    private List<Exercise> exerciseList;

    private List<Object> objectList;

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public List<Trueorfalse> getTrueOrFalseList() {
        return trueOrFalseList;
    }

    public void setTrueOrFalseList(List<Trueorfalse> trueOrFalseList) {
        this.trueOrFalseList = trueOrFalseList;
    }

    public List<Choice> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Choice> choiceList) {
        this.choiceList = choiceList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @Override
    public String toString() {
        return "PaperVo{" +
                "trueOrFalseList=" + trueOrFalseList +
                ", choiceList=" + choiceList +
                ", exerciseList=" + exerciseList +
                ", objectList=" + objectList +
                '}';
    }
}
