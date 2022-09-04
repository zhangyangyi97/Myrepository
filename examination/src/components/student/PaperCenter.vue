<template>
    <div id="allBox">
        <div  style="width: 1050px;margin:40px auto">
            <div style="box-sizing: border-box">
                <span>试卷类型:</span>
                <span style="margin-left: 25px;cursor: pointer" @click="selectAllPapers" class="courseType">全部</span>
                <span style="margin-left: 29px;cursor: pointer" @click="selectStatusByOne" class="courseType">固定试卷</span>
                <span style="margin-left: 28px;cursor: pointer" @click="selectStatusByTwo" class="courseType">非固定试卷</span>
                <span style="margin-left: 28px;cursor: pointer" @click="finish" class="courseType">已完成</span>
                <span style="margin-left: 28px;cursor: pointer" @click="unfinish" class="courseType">未完成</span>
            </div>
            <div >
                <div style="margin-top: 30px">
                    <span>题目学科:</span> <span @click="selectAllPapers" class="courseType" style="margin-left: 20px;cursor: pointer">全部</span>
                    <span
                            class="courseType"
                            v-for="(cou,index) in courseType"
                            :key="index"
                            style="margin-left: 26px;display: inline-block;cursor: pointer"
                            @click="searchCourse(cou.coursetype)"
                    >
                    {{cou.ctname}}
                </span>
                </div>
                <div style="border-bottom: 1px dashed lightgray;margin-top: 30px;width: 1000px"></div>
            </div>
        </div>

            <el-card
                    style="width: 255px;height: 190px;margin: 20px 0 20px 50px;cursor: pointer;float:left;box-sizing: border-box"
                    shadow="hover"
                    v-for="(p,index) in paperList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                    :key="index"
            >

                <div @click="paperInfoHandle(p,index)">
                    <span style="color: rgb(48,49,51)">{{p.papername}}</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">学科: {{p.ctname}}</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">题目数量: {{p.coursecount}}</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">试卷总分: {{p.mark}}分</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">考试时长: {{p.time}}分钟</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">创建时间: {{p.firstInit}}</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">试卷状态:</span><el-tag type="success" size="small" style="margin-left: 5px">{{p.paperstatus==1?'完成':'未完成'}}</el-tag>
                </div>
            </el-card>



        <div id="page">
            <el-pagination
                    :page-size="pageSize"
                    :total="total"
                    :current-page="currentPage"
                    @current-change="pageChangeHandle"
                    style="width: 550px;"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PaperCenter",
        data(){
            return{
                course:[],      //题型
                courseType:[],   //学科

                paperList:[],
                allCourseInPapers:[],
                pageSize: 4,
                currentPage: 1,
                total: 0,

                checkFinish:{
                    paperstatus:0,
                    username:''
                },
                student:{
                    username:'',
                    coursetype:0
                }

            }
        },

        mounted() {
            this.loadPaperList()
            this.loadCourse()
            this.loadCourseType()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },

        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },

        methods:{
            //查找固定试卷
            selectStatusByOne(){
                var fixedPapers= []
                this.paperList = []
                this.$axios.get('paper/findMyselfPapers/'+this.$store.state.username).then(resp=>{
                    fixedPapers = resp.data.data
                    console.log(fixedPapers)
                    for (let i = 0; i < fixedPapers.length; i++) {
                        if(fixedPapers[i].status=='1'){
                            this.paperList.push(fixedPapers[i])
                        }
                    }
                    this.currentPage = 1
                    this.total = this.paperList.length
                    for (let i = 0; i < this.paperList.length; i++) {
                        this.$axios.get('paper/findPapersByPapername/'+this.paperList[i].papername).then(resp=>{
                            this.allCourseInPapers.push(resp.data.data)
                        })
                    }

                })
            },

            paperInfoHandle(paper,index){
                this.$router.replace({
                    path: '/home/studentPaperInfo',
                    query:{
                        paper: JSON.stringify(paper),
                        index: index,
                        startTime:new Date().valueOf()
                    }
                })
            },

            pageChangeHandle (page){
                this.currentPage = page
            },

            //查找非固定的试卷
            selectStatusByTwo(){
                var unFixedPapers= []
                this.$axios.get('paper/findMyselfPapers/'+this.$store.state.username).then(resp=>{
                    this.paperList = []
                    unFixedPapers = resp.data.data
                    for (let i = 0; i < unFixedPapers.length; i++) {
                        if(unFixedPapers[i].status=='2'){
                            this.paperList.push(unFixedPapers[i])
                        }
                    }
                    this.currentPage = 1
                    this.total = this.paperList.length
                    for (let i = 0; i < this.paperList.length; i++) {
                        this.$axios.get('paper/findPapersByPapername/'+this.paperList[i].papername).then(resp=>{
                            this.allCourseInPapers.push(resp.data.data)
                        })
                    }
                })
            },


            loadPaperList(){
                this.$axios.get('paper/findMyselfPapers/'+this.$store.state.username).then(resp=>{
                    this.paperList = resp.data.data
                    this.currentPage = 1
                    console.log(this.paperList)
                    this.total = this.paperList.length
                    for (let i = 0; i < this.paperList.length; i++) {
                        this.$axios.get('paper/findPapersByPapername/'+this.paperList[i].papername).then(resp=>{
                            this.allCourseInPapers.push(resp.data.data)
                        })
                    }

                    console.log(this.paperList)
                })
            },

            loadCourse(){
                this.$axios.get('course/findCourses').then(resp=>{
                    this.course =resp.data.data
                    this.currentPage = 1
                    this.total = this.paperList.length
                })
            },

            loadCourseType(){
                this.$axios.get('course/findCourseTypes').then(resp=>{
                    this.courseType =resp.data.data
                    this.currentPage = 1
                    this.total = this.paperList.length
                })
            },

            //根据学科查询
            searchCourse(coursetype){
                this.student.username = this.$store.state.username
                this.student.coursetype = coursetype
                this.$axios.post('paper/selectPaperByCoursetype',this.student).then(resp=>{
                    this.paperList = resp.data.data
                    this.currentPage = 1
                    this.total = this.paperList.length
                })
            },

            //查询所有
            selectAllPapers(){
                this.loadPaperList()
                this.currentPage = 1
                this.total = this.paperList.length
            },


            //已完成
            finish(){
                this.checkFinish.username = this.$store.state.username
                this.checkFinish.paperstatus = 1
                this.$axios.post('paper/getCheckFinish',this.checkFinish).then(resp=>{
                    this.paperList = resp.data.data
                    this.currentPage = 1
                    this.total = this.paperList.length
                })

            },

            //未完成试卷
            unfinish(){
                this.checkFinish.username = this.$store.state.username
                this.checkFinish.paperstatus = 0
                this.$axios.post('paper/getCheckFinish',this.checkFinish).then(resp=>{
                    this.paperList = resp.data.data
                    this.currentPage = 1
                    this.total = this.paperList.length
                })
            },

        }
    }
</script>

<style scoped>
    span{
        color: rgba(0,0,0,0.85);
        font-size: 14px;
    }
    .courseType:hover{
        background-color: skyblue;
    }
    .el-card:hover{
        border-color:  skyblue;
    }
    #allBox{
        position: relative;
    }
    #page{
        position: absolute;
        left: 140px;
        top: 400px;
    }
</style>