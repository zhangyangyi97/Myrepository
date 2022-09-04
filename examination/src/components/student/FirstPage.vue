<template>
   <div style="box-sizing: border-box">
       <div style="box-sizing: border-box;">
           <img src="../../assets/stuFirstPage.png" width="100%" height="400px" alt="">
       </div>
       <div  align="center" style="box-sizing: border-box;width: 1200px;margin: auto" id="test-center">
           <el-button  id="test-btn" style="background-color: rgb(108,173,200);font-size: 17px;color: #ffffff;width: 130px;">
               作业中心
           </el-button>
            <el-card  style="width: 960px;padding: 20px;margin-top: 30px">
                <el-collapse v-model="activeNames" @change="handleChange" style="margin-top: 30px" accordion>
                    <el-collapse-item title="已完成作业" name="1">
                        <div  v-for="(f,index) in finishPaperList">
                            <span>{{index+1}}.{{f.papername}}</span>

                        </div>

                    </el-collapse-item>
                    <el-collapse-item title="未完成作业" name="2">
                        <div v-for="(uf,index) in unFinishPaperList">
                            <span>{{index+1}}.{{uf.papername}}</span>
                        </div>

                    </el-collapse-item>
                </el-collapse>
            </el-card>
       </div>
       <div  style="box-sizing: border-box;width: 1000px;margin-left:130px" id="paper-center">
           <el-button  id="paper-btn" style="background-color: rgb(140,189,164);font-size: 17px;color: #ffffff;width: 130px;">
               试卷中心
           </el-button>

           <el-card style="width: 960px;padding: 20px;margin-top: 30px">
               <el-card shadow="hover"
                        v-for="(p,index) in workList"
                        :key="index"
                        style="margin: 28px 0 20px 31px;width: 260px;height: 200px;float: left;border: 2px solid rgb(235,238,245);cursor: pointer;"
                        class="paper"
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
           </el-card>
       </div>
       <div style="padding-bottom: 80px"></div>
   </div>
</template>

<script>
    export default {
        name: "FirstPage",
        data(){
            return{
                activeNames: ['1'],
                workList:[],
                checkFinish:{
                    paperstatus:0,
                    username:''
                },
                finishPaperList:[],
                unFinishPaperList:[],
                paperstudent:{
                    paperId:0,
                    sid:0
                },
            }
        },
        mounted() {
            this.getHomework()
            this.finish()
            this.unfinish()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },
        methods:{
            handleChange(val) {
                console.log(val);
            },

            //在结束之前，删除该样式，即可
            beforeDestroy () {
                document.querySelector('body').removeAttribute('style')
            },

            getHomework(){
                this.$axios.get('student/selectStudent/'+this.$store.state.username).then(resp=>{
                    this.student = resp.data.data
                    this.$axios.get('paper/getHomework/'+this.$store.state.username).then(resp=>{
                        var fixedPapers = []
                        fixedPapers = resp.data.data
                        for (let i = 0; i < fixedPapers.length; i++) {
                            this.paperstudent.paperId = fixedPapers[i].paperId
                            this.paperstudent.sid = this.student.sid
                            if(fixedPapers[i].status=='1'){
                                this.$axios.post('paper/selectPaperByPaperId',this.paperstudent).then(resp=>{
                                    this.workList.push(resp.data.data)
                                })
                            }
                        }
                        console.log(this.workList)

                    })
                })
            },

            paperInfoHandle(paper,index){
                this.$router.replace({
                    path: '/home/studentPaperInfo',
                    query:{
                        paper:  JSON.stringify(paper),
                        index: index,
                        startTime:new Date().valueOf()
                    }
                })
            },

            //已完成
            finish(){
                this.checkFinish.username = this.$store.state.username
                this.checkFinish.paperstatus = 1
                this.$axios.post('paper/getCheckFinish',this.checkFinish).then(resp=>{
                    console.log(resp.data.data)
                    this.finishPaperList = resp.data.data
                })

            },

            //未完成试卷
            unfinish(){
                this.checkFinish.username = this.$store.state.username
                this.checkFinish.paperstatus = 0
                this.$axios.post('paper/getCheckFinish',this.checkFinish).then(resp=>{
                    console.log(resp.data.data)
                    this.unFinishPaperList = resp.data.data
                })
            },
        }
    }
</script>

<style scoped>
    #test-center{
        position: relative;
    }
    #test-btn{
        position: absolute;
        top: 8px;
        right: 1010px;
    }
    #paper-center{
        position: relative;
    }
    #paper-btn{
        position: absolute;
        top: 8px;
        right: 908px;
    }
    .paper:hover{
        box-shadow: 0 16px 32px 0 rgba(48,55,66,0.15);
    }
</style>