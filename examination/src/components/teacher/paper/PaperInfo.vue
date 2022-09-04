<template>
    <div id="allBox">
        <div  style="width: 1000px;margin: auto">
            <div style="box-sizing: border-box">
                <span>试卷类型:</span>
                <span style="margin-left: 25px;cursor: pointer" @click="selectAllPapers" class="courseType">全部</span>
                <span style="margin-left: 20px;cursor: pointer" @click="selectStatusByOne" class="courseType">固定试卷</span>
                <span style="margin-left: 20px;cursor: pointer" @click="selectStatusByTwo" class="courseType">非固定试卷</span>
            </div>
            <div >
                <div style="margin-top: 18px">
                    <span>题目学科:</span> <span @click="selectAllPapers" class="courseType" style="margin-left: 20px;cursor: pointer">全部</span>
                    <span
                            class="courseType"
                            v-for="(cou,index) in courseType" :key="index"
                            style="margin-left: 26px;display: inline-block;cursor: pointer"
                            @click="searchCourse(cou.coursetype)"
                    >
                    {{cou.ctname}}
                </span>
                </div>
                <div style="margin-top: 18px">
                    <span>年级查询:</span> <span @click="selectAllPapers" class="courseType" style="margin-left: 20px;cursor: pointer">全部</span>
                    <span
                            class="courseType"
                            v-for="(g,index) in grade" :key="index"
                            style="margin-left: 26px;display: inline-block;cursor: pointer"
                            @click="searchByGradeId(g.gradeId)"
                    >
                    {{g.gradename}}
                    </span>
                </div>
                <div style="border-bottom: 1px dashed lightgray;margin-top: 20px;width: 930px">

                </div>
            </div>
        </div>
        <div id="card-box">
            <el-card
                    style="width: 255px;height: 190px;margin: 30px 0 20px 20px;cursor: pointer;float:left;box-sizing: border-box"
                    shadow="hover"
                    v-for="(p,index) in paperList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                    :key="index"
            >

                <div @click="paperInfoHandle(index)">
                    <span style="color: rgb(48,49,51)">{{p.papername}}</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">学科: {{p.ctname}}</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">题目数量: {{p.coursecount}}</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">试卷总分: {{p.mark}}分</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">考试时长: {{p.time}}分钟</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">创建时间: {{p.firstInit}}</span><br>
                    <span style="color: rgb(144,147,153);font-size: 13px">试卷类型:</span><el-tag type="success" size="small" style="margin-left: 5px">{{p.status==1?'固定':'非固定'}}</el-tag>
                </div>
            </el-card>
        </div>
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


        <PaperInfoAllCourses ref="PaperInfoAllCourses"></PaperInfoAllCourses>
    </div>
</template>

<script>
    import CountDown from 'vue2-countdown'
    import PaperInfoAllCourses from "@/components/teacher/paper/PaperInfoAllCourses";
    export default {
        name: "PaperInfo",
        components:{
            PaperInfoAllCourses,
            CountDown
        },
        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },

        data(){
          return{
              course:[],      //题型
              courseType:[],   //学科
              grade:[],       //年级

              paperList:[],
              allCourseInPapers:[],
              pageSize: 3,
              currentPage: 1,
              total: 0,
          }
        },

        mounted() {
            this.loadPaperList()
            this.loadCourse()
            this.loadCourseType()
            this.loadGradeId()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },

        methods:{

            //查找固定试卷
            selectStatusByOne(){
                var fixedPapers= []
                this.paperList = []
                this.$axios.post('paper/findPapersOnly').then(resp=>{
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

            pageChangeHandle (page){
                this.currentPage = page
            },

            //查找非固定的试卷
            selectStatusByTwo(){
                var unFixedPapers= []
                this.paperList = []
                this.$axios.post('paper/findPapersOnly').then(resp=>{
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
                this.$axios.post('paper/findPapersOnly').then(resp=>{
                    this.paperList = resp.data.data
                    this.currentPage = 1
                    this.total = this.paperList.length
                    for (let i = 0; i < this.paperList.length; i++) {
                        this.$axios.get('paper/findPapersByPapername/'+this.paperList[i].papername).then(resp=>{
                            this.allCourseInPapers.push(resp.data.data)
                        })
                    }
                })
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

            loadGradeId(){
                this.$axios.get('course/findGradeId').then(resp=>{
                    this.grade = resp.data.data
                })
            },

            paperInfoHandle(index){
                this.$refs.PaperInfoAllCourses.dialogVisible = true
                this.$refs.PaperInfoAllCourses.paper = this.paperList[index]
                this.$refs.PaperInfoAllCourses.courses = this.allCourseInPapers[index]
                console.log(this.allCourseInPapers[index])
                this.$refs.PaperInfoAllCourses.choiceOne=[]
                this.$refs.PaperInfoAllCourses.choiceMore=[]
                this.$refs.PaperInfoAllCourses.trueorfalse=[]
                this.$refs.PaperInfoAllCourses.exercise=[]
                this.$refs.PaperInfoAllCourses.exerciseMore=[]
                for (let i = 0; i < this.allCourseInPapers[index].length; i++) {
                    if(this.allCourseInPapers[index][i].courseId=='1'){
                        this.$refs.PaperInfoAllCourses.choiceOne.push(this.allCourseInPapers[index][i])
                    }
                    if(this.allCourseInPapers[index][i].courseId=='2'){
                        this.$refs.PaperInfoAllCourses.choiceMore.push(this.allCourseInPapers[index][i])
                    }
                    if(this.allCourseInPapers[index][i].courseId=='3'){
                        this.$refs.PaperInfoAllCourses.trueorfalse.push(this.allCourseInPapers[index][i])
                    }
                    if(this.allCourseInPapers[index][i].courseId=='4'){
                        this.$refs.PaperInfoAllCourses.exercise.push(this.allCourseInPapers[index][i])
                    }
                    if(this.allCourseInPapers[index][i].courseId=='5'){
                        this.$refs.PaperInfoAllCourses.exerciseMore.push(this.allCourseInPapers[index][i])
                    }
                }
            },

            //根据学科查询
            searchCourse(coursetype){
                this.$axios.get('paper/selectByCoursetype/'+coursetype).then(resp=>{
                    this.paperList = resp.data.data
                    this.currentPage = 1
                    this.total = this.paperList.length
                })
            },

            //根据年级查
            searchByGradeId(gradeId){
                this.$axios.get('paper/selectByGradeId/'+gradeId).then(resp=>{
                    this.paperList = resp.data.data
                    this.currentPage = 1
                    this.total = this.paperList.length
                })
            },

            //查询所有
            selectAllPapers(){
                this.loadPaperList()
            }
        }
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
    .el-card:hover{
        border-color:  skyblue;
    }
    #allBox{
        position: relative;
    }
    #page{
        position: absolute;
        left: 232px;
        top: 440px;
    }
    #card-box{
        position: absolute;
        left: 220px;
        top: 200px;
    }
</style>