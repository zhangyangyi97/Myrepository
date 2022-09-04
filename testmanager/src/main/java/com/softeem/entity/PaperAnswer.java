package com.softeem.entity;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/8/4 22:39
 * 项目名称 testmanager
 */
public class PaperAnswer {
    private Integer paperId;
    private String answer;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "PaperAnswer{" +
                "paperId=" + paperId +
                ", answer='" + answer + '\'' +
                '}';
    }
}
