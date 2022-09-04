<template>
    <div  style="width: 1000px;margin: auto">
        <div >
            <div style="margin-top: 30px">
                <span>题目学科:</span><span @click="selectAll"  class="courseType" style="margin-left: 26px;cursor: pointer">全部</span>
                <span
                        class="courseType"
                        v-for="(cou,index) in courseType" :key="index"
                        style="margin-left: 26px;display: inline-block;cursor: pointer"
                        @click="searchCourse(cou.coursetype)"
                >
                    {{cou.ctname}}
                </span>
            </div>
            <div style="margin-top: 30px">
                <span>系统题型:</span><span @click="selectAll" class="course" style="margin-left: 26px;cursor: pointer">全部</span>
                <span   class="course"
                        v-for="(c,index) in course" :key="index"
                        style="margin-left: 26px;display: inline-block;cursor: pointer"
                        @click="searchCourseType(c.courseId)"
                >
                    {{c.coursename}}
                </span>
            </div>
            <div style="border-bottom: 1px dashed lightgray;margin-top: 30px;width: 1040px"></div>
            <div style="width: 1050px;height:220px;" v-for="(c,index) in allFalseCourses.slice((currentPage-1)*pageSize,currentPage*pageSize)" class="false-course">
                <div style="height: 30px;background-color: rgb(140,189,164)">
                    <span style="color: white;line-height: 30px;margin-left: 15px">题型:{{c.coursename}}</span> <span style="color: white;margin-left:50px;line-height: 30px">学科:{{c.ctname}}</span>
                    <div align="center" style="width: 100px;height: 30px;background-color: rgb(245,108,108);float: right;cursor: pointer">
                        <span class="del" @click="delCourse(c,index)">移除错题本</span>
                    </div>
                </div>
                <el-card
                >
                    <span>{{c.title}}</span><span style="margin-left: 60px">({{c.point}}分)</span><br><br>
                    <span>{{c.answerA}}</span><br>
                    <span>{{c.answerB}}</span><br>
                    <span>{{c.answerC}}</span><br>
                    <span>{{c.answerD}}</span><br>
                    <span>标准答案:</span> <span style="margin-left: 5px;color: red;font-size: 17px">{{c.answer}}</span><br>
                    <el-rate
                            v-model="c.diff"
                            show-score
                            text-color="#ff9900"
                            score-template={value}
                            style="margin-top: 6px"
                            disabled
                    >
                    </el-rate>
                </el-card>
            </div>

        </div>
        <div style="margin-top: 60px;padding-bottom: 80px">
            <el-pagination
                    :page-size="pageSize"
                    :total="total"
                    :current-page="currentPage"
                    @current-change="pageChangeHandle"
                    style="width: 560px;"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "FalseCourse",
        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },

        mounted() {
            this.loadCourse()
            this.loadCourseType()
            this.getAllFalseCourses()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },
        data(){
          return{
              course:[],      //题型
              courseType:[],   //学科
              student:{},
              allFalseCourses:[],

              pageSize: 4,
              currentPage: 1,
              total: 0,
          }
        },

        methods:{
            loadCourse(){
                this.$axios.get('course/findCourses').then(resp=>{
                    this.course =resp.data.data

                })
            },

            //查询全部
            selectAll(){
                this.getAllFalseCourses()
            },

            pageChangeHandle (page){
                this.currentPage = page
            },


            delCourse(course,index){

                this.$confirm('确定删除此错题么?',"提示",{
                    confirmButtonText:'确定',
                    cancelButtonClass:'取消',
                    type:'warning'
                }).then(()=>{
                    console.log(course)
                    this.$axios.post('course/delFalseCourse',course).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success('删除成功!')
                            this.getAllFalseCourses()
                        }
                    })
                }).catch(()=>{
                    this.$message({
                        type:'info',
                        message:'已取消删除'
                    })
                })
            },

            loadCourseType(){
                this.$axios.get('course/findCourseTypes').then(resp=>{
                    this.courseType =resp.data.data
                })
            },

            //获得所有的错题
            getAllFalseCourses(){
                this.$axios.get('student/selectStudent/'+this.$store.state.username).then(resp=>{
                    this.student = resp.data.data
                    this.$axios.get('course/findAllFalseCourses/'+this.$store.state.username).then(resp=>{
                        this.allFalseCourses = resp.data.data
                        this.currentPage = 1
                        this.total = this.allFalseCourses.length
                        for (let i = 0; i < this.allFalseCourses.length; i++) {
                            if(this.allFalseCourses[i].courseId=='1'){
                                this.allFalseCourses[i].coursename='单选题'
                            }
                            if(this.allFalseCourses[i].courseId=='2'){
                                this.allFalseCourses[i].coursename='多选题'
                            }
                            if(this.allFalseCourses[i].courseId=='3'){
                                this.allFalseCourses[i].coursename='判断题'
                            }
                            if(this.allFalseCourses[i].courseId=='4'){
                                this.allFalseCourses[i].coursename='填空题'
                            }
                            if(this.allFalseCourses[i].courseId=='5'){
                                this.allFalseCourses[i].coursename='简述题'
                            }
                            if(this.allFalseCourses[i].coursetype=='1'){
                                this.allFalseCourses[i].ctname='语文'
                            }
                            if(this.allFalseCourses[i].coursetype=='2'){
                                this.allFalseCourses[i].ctname='数学'
                            }
                            if(this.allFalseCourses[i].coursetype=='3'){
                                this.allFalseCourses[i].ctname='英语'
                            }
                            if(this.allFalseCourses[i].coursetype=='4'){
                                this.allFalseCourses[i].ctname='地理'
                            }
                            if(this.allFalseCourses[i].coursetype=='5'){
                                this.allFalseCourses[i].ctname='生物'
                            }
                            if(this.allFalseCourses[i].coursetype=='6'){
                                this.allFalseCourses[i].ctname='科学'
                            }
                        }
                    })
                })

            },

            searchCourse(courseId){
                this.$axios.get('course/findFalseByCourseId',{
                    params:{
                        courseId:courseId,
                        username:this.student.username
                    }
                }).then(resp=>{
                    this.allFalseCourses = resp.data.data
                    for (let i = 0; i < this.allFalseCourses.length; i++) {
                        if(this.allFalseCourses[i].courseId=='1'){
                            this.allFalseCourses[i].coursename='单选题'
                        }
                        if(this.allFalseCourses[i].courseId=='2'){
                            this.allFalseCourses[i].coursename='多选题'
                        }
                        if(this.allFalseCourses[i].courseId=='3'){
                            this.allFalseCourses[i].coursename='判断题'
                        }
                        if(this.allFalseCourses[i].courseId=='4'){
                            this.allFalseCourses[i].coursename='填空题'
                        }
                        if(this.allFalseCourses[i].courseId=='5'){
                            this.allFalseCourses[i].coursename='简述题'
                        }
                        if(this.allFalseCourses[i].coursetype=='1'){
                            this.allFalseCourses[i].ctname='语文'
                        }
                        if(this.allFalseCourses[i].coursetype=='2'){
                            this.allFalseCourses[i].ctname='数学'
                        }
                        if(this.allFalseCourses[i].coursetype=='3'){
                            this.allFalseCourses[i].ctname='英语'
                        }
                        if(this.allFalseCourses[i].coursetype=='4'){
                            this.allFalseCourses[i].ctname='地理'
                        }
                        if(this.allFalseCourses[i].coursetype=='5'){
                            this.allFalseCourses[i].ctname='生物'
                        }
                        if(this.allFalseCourses[i].coursetype=='6'){
                            this.allFalseCourses[i].ctname='科学'
                        }
                    }
                })
            },

            searchCourseType(courseType){
                this.$axios.get('course/findFalseByCourseType',{
                    params:{
                        coursetype:courseType,
                        username:this.student.username
                    }
                }).then(resp=>{
                    this.allFalseCourses = resp.data.data
                    for (let i = 0; i < this.allFalseCourses.length; i++) {
                        if(this.allFalseCourses[i].courseId=='1'){
                            this.allFalseCourses[i].coursename='单选题'
                        }
                        if(this.allFalseCourses[i].courseId=='2'){
                            this.allFalseCourses[i].coursename='多选题'
                        }
                        if(this.allFalseCourses[i].courseId=='3'){
                            this.allFalseCourses[i].coursename='判断题'
                        }
                        if(this.allFalseCourses[i].courseId=='4'){
                            this.allFalseCourses[i].coursename='填空题'
                        }
                        if(this.allFalseCourses[i].courseId=='5'){
                            this.allFalseCourses[i].coursename='简述题'
                        }
                        if(this.allFalseCourses[i].coursetype=='1'){
                            this.allFalseCourses[i].ctname='语文'
                        }
                        if(this.allFalseCourses[i].coursetype=='2'){
                            this.allFalseCourses[i].ctname='数学'
                        }
                        if(this.allFalseCourses[i].coursetype=='3'){
                            this.allFalseCourses[i].ctname='英语'
                        }
                        if(this.allFalseCourses[i].coursetype=='4'){
                            this.allFalseCourses[i].ctname='地理'
                        }
                        if(this.allFalseCourses[i].coursetype=='5'){
                            this.allFalseCourses[i].ctname='生物'
                        }
                        if(this.allFalseCourses[i].coursetype=='6'){
                            this.allFalseCourses[i].ctname='科学'
                        }
                    }
                })
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
    .del{
        color: white;
        font-size: 13px;
        line-height: 30px;
    }
    .false-course{
        margin-top: 40px;
    }
    span{
        margin-top: 5px;
    }
</style>