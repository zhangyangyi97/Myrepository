package com.softeem.vo;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/8/1 11:09
 * 项目名称 testmanager
 */
public class AllCourses {
    private String title;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private Integer cid;
    private Integer eid;
    private Integer tid;
    private Integer courseId;
    private Integer coursetype;
    private Integer diff;
    private double point;
    private Integer paperId;
    private String coursename;
    private String ctname;
    private String firstanw;
    private String secondanw;
    private Integer sid;
    private String answer;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(Integer coursetype) {
        this.coursetype = coursetype;
    }

    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCtname() {
        return ctname;
    }

    public void setCtname(String ctname) {
        this.ctname = ctname;
    }

    public String getFirstanw() {
        return firstanw;
    }

    public void setFirstanw(String firstanw) {
        this.firstanw = firstanw;
    }

    public String getSecondanw() {
        return secondanw;
    }

    public void setSecondanw(String secondanw) {
        this.secondanw = secondanw;
    }

    @Override
    public String toString() {
        return "AllCourses{" +
                "title='" + title + '\'' +
                ", answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                ", cid=" + cid +
                ", eid=" + eid +
                ", tid=" + tid +
                ", courseId=" + courseId +
                ", coursetype=" + coursetype +
                ", diff=" + diff +
                ", point=" + point +
                ", paperId=" + paperId +
                ", coursename='" + coursename + '\'' +
                ", ctname='" + ctname + '\'' +
                ", firstanw='" + firstanw + '\'' +
                ", secondanw='" + secondanw + '\'' +
                ", sid=" + sid +
                ", answer='" + answer + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
