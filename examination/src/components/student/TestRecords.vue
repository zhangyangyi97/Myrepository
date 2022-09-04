<template>
    <div id="bigBox"  style="width: 1000px;margin: auto">
        <div >
            <div style="margin-top: 30px">
                <span>试卷类型:</span><span class="courseType" @click="selectAll" style="margin-left: 26px;cursor: pointer">全部</span>
                <span
                        class="courseType"
                        v-for="(cou,index) in courseType" :key="index"
                        style="margin-left: 26px;display: inline-block;cursor: pointer"
                        @click="searchCourse(cou.coursetype)"
                >
                    {{cou.ctname}}
                </span>

            </div>
            <div id="search">
                <el-input prefix-icon="el-icon-search" v-model="paperCenterVo.keyword" style="width: 243px" placeholder="请输入试卷名称"></el-input><el-button type="primary" @click="selectPaperByKeyword" style="margin-left: 3px">查询</el-button>
            </div>


            <div style="border-bottom: 1px dashed lightgray;margin: 30px 0;width: 1040px"></div>
            <el-card
                style="height: 230px"
                v-for="(p,index) in paperList" :key="index"


            >
                <div style="width: 1000px;height: 200px;">
                    <div style="width: 240px;height: 150px;margin: 20px 0 0 30px">
                        <span><i></i> {{p.papername}}</span><br><br>
                        <span>提交时间: <i></i> {{resultList[index].endTime}}</span><br><br>
                        <span>试卷状态:</span><el-tag type="success" style="margin-left: 5px">{{p.paperstatus==1?'完成':'未完成'}}</el-tag>
                    </div>
                    <div style="width: 260px;height: 180px"  class="card">
                        <span>学课: <i></i> {{p.ctname}}</span><br><br>
                        <span>试卷类型: <i></i>{{p.status==1?'固定试卷':'非固定试卷'}}</span><br><br>
                        <span>题目数量: <i></i>{{p.coursecount}}</span><br><br>
                        <span>试卷总分: <i></i>{{resultList[index].score}}</span><br><br>
                        <span>发布时间: <i></i>{{p.firstInit}}</span>
                    </div>
                    <div style="width: 200px;" class="card1">
                        <span>正确题数:  <i></i> {{resultList[index].truecount}}</span><br><br>
                        <span>错误题数: <i></i> {{resultList[index].falsecount}}</span><br><br>
                        <span>耗时: <i></i> {{resultList[index].finishtime}}</span><br><br>
                        <el-button type="primary" @click="checkPaper(p,index)">查看试卷</el-button>

                    </div>
                </div>

            </el-card>
        </div>
        <div style="padding-bottom: 88px"></div>
    </div>
</template>

<script>
    export default {
        name: "TestRecords",
        data(){
            return{
                course:[],      //题型
                courseType:[],   //学科
                paperCenterVo:{
                    paperstatus:1,
                    username:'',
                    coursetype:0,
                    keyword:''
                },
                paperList:[],
                resultList:[],
                paperstudent:{
                    paperId:0,
                    sid:0
                },
                student:{},
            }
        },
        mounted() {
            this.loadCourse()
            this.loadCourseType()
            this.getFinishPapers()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },
        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },

        methods:{
            checkPaper(paper,index){

            },

            selectAll(){
                this.paperList=[]
                this.resultList=[]
                this.getFinishPapers()
            },
            loadCourse(){
                this.$axios.get('course/findCourses').then(resp=>{
                    this.course =resp.data.data
                })
            },

            loadCourseType(){
                this.$axios.get('course/findCourseTypes').then(resp=>{
                    this.courseType =resp.data.data
                })
            },

            //查找已做过的试卷
            getFinishPapers(){
                this.paperCenterVo.username = this.$store.state.username
                this.paperCenterVo.paperstatus =1
                this.$axios.get('student/selectStudent/'+this.$store.state.username).then(resp=>{
                    this.student = resp.data.data
                    this.$axios.post('paper/getFinishPapers',this.paperCenterVo).then(resp=>{
                        var list = resp.data.data
                        console.log(list)
                        for (let i = 0; i < list.length; i++) {
                            this.paperstudent.sid = this.student.sid
                            this.paperstudent.paperId = list[i].paperId
                            this.$axios.post('paper/selectPaperByPaperId',this.paperstudent).then(resp=>{
                                list[i] = resp.data.data
                                this.paperList.push(list[i])
                                this.$axios.post('result/findResultByPaperId',this.paperstudent).then(resp=>{
                                    this.resultList.push(resp.data.data)
                                    this.paperList[i].score = this.resultList[i].score
                                    this.paperList[i].truecount = this.resultList[i].truecount
                                    this.paperList[i].falsecount = this.resultList[i].falsecount
                                    this.paperList[i].finishtime = this.resultList[i].finishtime
                                })
                            })
                        }
                    })
                })
            },



            searchCourse(coursetype){
                this.paperCenterVo.username = this.$store.state.username
                this.paperCenterVo.coursetype = coursetype
                this.$axios.post('paper/getFinishByCourseId',this.paperCenterVo).then(resp=>{
                    var list = resp.data.data
                    console.log(list)
                    for (let i = 0; i < list.length; i++) {
                        this.paperstudent.sid = this.student.sid
                        this.paperstudent.paperId = list[i].paperId
                        this.$axios.post('paper/selectPaperByPaperId',this.paperstudent).then(resp=>{
                            list[i] = resp.data.data
                            this.paperList = []
                            this.paperList.push(list[i])
                            this.$axios.post('result/findResultByPaperId',this.paperstudent).then(resp=>{
                                this.resultList = []
                                this.resultList.push(resp.data.data)
                                this.paperList[i].score = this.resultList[i].score
                                this.paperList[i].truecount = this.resultList[i].truecount
                                this.paperList[i].falsecount = this.resultList[i].falsecount
                                this.paperList[i].finishtime = this.resultList[i].finishtime
                            })
                        })
                    }
                })
            },


            //根据关键字搜索试卷
            selectPaperByKeyword(){
                this.paperCenterVo.username = this.$store.state.username
                this.$axios.post('paper/getFinishByKeyword',this.paperCenterVo).then(resp=>{
                    var list = resp.data.data
                    for (let i = 0; i < list.length; i++) {
                        this.paperstudent.sid = this.student.sid
                        this.paperstudent.paperId = list[i].paperId
                        this.$axios.post('paper/selectPaperByPaperId',this.paperstudent).then(resp=>{
                            list[i] = resp.data.data
                            this.paperList = []
                            this.paperList.push(list[i])
                            this.$axios.post('result/findResultByPaperId',this.paperstudent).then(resp=>{
                                this.resultList = []
                                this.resultList.push(resp.data.data)
                                this.paperList[i].score = this.resultList[i].score
                                this.paperList[i].truecount = this.resultList[i].truecount
                                this.paperList[i].falsecount = this.resultList[i].falsecount
                                this.paperList[i].finishtime = this.resultList[i].finishtime
                            })
                        })
                    }
                })
            }
        },
    }
</script>

<style scoped>
    span{
        color: rgba(0,0,0,0.85);
        font-size: 14px;
    }
    .course:hover{
        background-color: skyblue;
    }
    .courseType:hover{
        background-color: skyblue;
    }
    #bigBox{
        position: relative;
    }
    #search{
        position: absolute;
        left: 550px;
        top: -10px;
    }
    .el-card{
        position: relative;
    }
    .card{
        position: absolute;
        left: 395px;
        top: 20px;
    }

    .card1{
        position: absolute;
        left: 720px;
        top: 20px;
    }
    i{
        margin-left: 5px;
    }
    .el-card{
        margin-top: 40px;
    }
</style>