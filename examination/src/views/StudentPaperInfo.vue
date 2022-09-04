<template>


    <div id="aside">
        <el-card
                style="width: 270px;height: 480px;position: fixed;margin: 40px 0 0 110px"
        >
            <div>
                <span style="font-size: 17px;font-weight: 600">{{paper.papername}}</span><br><br>
                <span>总分:{{paper.mark}}</span> <span style="margin-left: 25px">时长:{{paper.time}}分钟</span>
            </div>
            <div align="center" style="width: 210px;border-bottom: 1px solid lightgray;margin: 15px 0"></div>

            <div class="spanId" align="center" v-for="(item,index) in paper.coursecount">
                <span  @click="selectCourse(index)">
                    {{index+1}}
                </span>
            </div>
            <div align="center" id="bottom" style="width: 210px;border-bottom: 1px solid lightgray;margin: 15px 0"></div>
            <div id="count-down" align="center">
                <span>剩余时间</span><br>
                <count-down style="color: red" v-on:start_callback="countDownS_cb(1)"
                            v-on:end_callback="countDownE_cb(1)"
                            :currentTime="currentTime"
                            :startTime="startTime"
                            :endTime="endTime"
                            :dayTxt="'天'"
                            :hourTxt="'小时'" :minutesTxt="'分钟'"
                            :secondsTxt="'秒'"> </count-down>
                <!--currentTime  当前时间戳,如果不传,默认获取用户本地的时间(建议传服务器的当前时间)-->
            </div>
            <div align="center" id="bottom2" style="width: 210px;border-bottom: 1px solid lightgray;margin: 15px 0"></div>
            <div id="btn" >
                <el-button type="primary" @click="submitPaper">提交试卷</el-button>
                <el-button @click="toHome" style="margin: 10px 10px 0 0 ">返回首页</el-button>
            </div>
        </el-card>

        <el-card
                class="paper"
                style="width: 720px;"
        >
            <div  v-show="one"  style="background-color: lightgray">
                <div style="border-top: 2px solid gray"></div>
                <div>
                    <span style="font-size: 22px;font-weight: 600">单选</span>
                </div>
                <div style="border-bottom: 2px solid gray;"></div>
            </div>
            <div  v-for="(c,index) in choiceOne" class="course">
                <span style="font-weight: 700;">{{index+1}}.</span><span>{{c.title}}</span><span style="margin-left: 50px">({{c.point}}分)</span><br>
                <el-radio-group v-model="choiceAnswer[index]">
                    <el-radio name="choiceOne" :label="'A'">{{c.answerA}}</el-radio>
                    <br>
                    <el-radio name="choiceOne" :label="'B'">{{c.answerB}}</el-radio>
                    <br>
                    <el-radio name="choiceOne" :label="'C'">{{c.answerC}}</el-radio>
                    <br>
                    <el-radio name="choiceOne" :label="'D'">{{c.answerD}}</el-radio>
                    <br>
                </el-radio-group>
            </div>


            <div v-show="more"  style="background-color: lightgray;margin-top: 30px">
                <div style="border-top: 2px solid gray"></div>
                <div>
                    <span style="font-size: 22px;font-weight: 600">多选</span>
                </div>
                <div style="border-bottom: 2px solid gray"></div>
            </div>

            <div v-for="(cm,index) in choiceMore" class="course">
                <span style="font-weight: 700;">{{choiceLen+index+1}}.</span><span>{{cm.title}}</span><span style="margin-left: 50px">({{cm.point}}分)</span><br>
                <el-checkbox-group v-model="choiceMoreAnswer[index]">
                    <el-checkbox :label="'A'">{{cm.answerA}}</el-checkbox>
                    <br>
                    <el-checkbox :label="'B'">{{cm.answerB}}</el-checkbox>
                    <br>
                    <el-checkbox :label="'C'">{{cm.answerC}}</el-checkbox>
                    <br>
                    <el-checkbox :label="'D'">{{cm.answerD}}</el-checkbox>
                    <br>
                </el-checkbox-group>
            </div>

            <div v-show="trueFalse" style="background-color: lightgray;margin-top: 30px">
                <div style="border-top: 2px solid gray;"></div>
                <div>
                    <span style="font-size: 22px;font-weight: 600">判断</span>
                </div>
                <div style="border-bottom: 2px solid gray"></div>
            </div>
            <div v-for="(t,index) in trueorfalse" class="course">
                <span style="font-weight: 700;">{{choiceLen+choiceMoreLen+index+1}}.</span><span>{{t.title}}</span><span style="margin-left: 50px">({{t.point}}分)</span><br>
                <el-radio-group v-model="trueFalseAnswer[index]">
                    <el-radio label="对"></el-radio>
                    <el-radio label="错"></el-radio>
                </el-radio-group>
            </div>

            <div v-show="exe" style="background-color: lightgray;margin-top: 30px">
                <div style="border-top: 2px solid gray"></div>
                <div>
                    <span style="font-size: 22px;font-weight: 600">填空</span>
                </div>
                <div style="border-bottom: 2px solid gray"></div>
            </div>
            <div v-for="(e,index) in exercise" class="course">
                <span style="font-weight: 700;">{{choiceLen+choiceMoreLen+trueFalseLen+index+1}}.</span><span>{{e.title}}</span><span style="margin-left: 50px">({{e.point}}分)</span><br>
                <el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 4}"
                        placeholder="请输入答案"
                        v-model="exerciseAnswer[index]"
                >
                </el-input>
            </div>


            <div v-show="exeMore"  style="background-color: lightgray;margin-top: 30px">
                <div style="border-top: 2px solid gray"></div>
                <div>
                    <span style="font-size: 22px;font-weight: 600">简答</span>
                </div>
                <div style="border-bottom: 2px solid gray"></div>
            </div>
            <div v-for="(em,index) in exerciseMore" class="course">
                <span style="font-weight: 700;">{{choiceLen+choiceMoreLen+trueFalseLen+exerciseLen+index+1}}.</span><span>{{em.title}}</span><span style="margin-left: 50px">({{em.point}}分)</span><br>
                <el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 4}"
                        placeholder="请输入答案"
                        v-model="exerciseMoreAnswer[index]"
                >
                </el-input>
            </div>
            <div style="padding-bottom: 88px;background-color: white"></div>
        </el-card>

    </div>



</template>

<script>
    import CountDown from 'vue2-countdown'
    export default {
        name: "StudentPaperInfo",
        components:{
            CountDown
        },
        data(){
            return{
                paper:{},
                index:'',
                allCourses:[],
                answer:[],

                choiceOne:[],
                choiceMore:[],
                exercise:[],
                exerciseMore:[],
                trueorfalse:[],

                currentTime:0,
                startTime:0,
                endTime:0,

                one:false,
                more:false,
                exe:false,
                exeMore:false,
                trueFalse:false,

                choiceAnswer:[

                ],
                choiceMoreAnswer:[
                    [],
                    [],
                    [],
                    [],
                    [],
                ],
                trueFalseAnswer:[],
                exerciseAnswer:[],
                exerciseMoreAnswer:[],

                choiceLen:0,
                choiceMoreLen:0,
                trueFalseLen:0,
                exerciseLen:0,
                exerciseMoreLen:0,

                allAnswer:[],
                falseCourse:[],

                student:{},
                result:{
                    score:0,   //分数
                    sid:0,
                    paperId:0,
                    finishtime:'',
                    endTime:'',
                    truecount:0,
                    falsecount:0
                },

                paperstudent:{
                    paperstatus:0,
                    paperId:0
                },
                paperanswer:{
                    paperId:0,
                    answer:''
                }
            }
        },
        mounted() {
            this.getStudent()
            this.getParams()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },
        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },

        methods:{
            toHome(){
                this.$confirm('确定放弃此次考试么?',"提示",{
                    confirmButtonText:'确定',
                    cancelButtonClass:'取消',
                    type:'warning'
                }).then(()=>{
                    setTimeout(()=>{
                        this.$router.replace('/home')
                    },1300)
                }).catch(()=>{
                    this.$message({
                        type:'info',
                        message:'已取消删除'
                    })
                })
            },


            submitPaper(){
                this.endTime = new Date().valueOf()
                this.allAnswer = []
                for (let i = 0; i < this.choiceOne.length; i++) {
                    if(this.choiceOne[i].answer==this.choiceAnswer[i]){
                        this.result.score += this.choiceOne[i].point
                        this.result.truecount++;
                    }
                    if(this.choiceOne[i].answer!=this.choiceAnswer[i]){
                        this.falseCourse.push(this.choiceOne[i])
                        this.result.falsecount++;
                    }

                }

                for (let i = 0; i < this.choiceMore.length; i++) {
                    var answer = ''
                    for (let j = 0; j < this.choiceMoreAnswer[i].length; j++) {
                        answer=answer+this.choiceMoreAnswer[i][j]
                    }
                    if(this.choiceMore[i].answer==answer){
                        this.result.score+= this.choiceMore[i].point
                        this.result.truecount++;
                    }
                    if(this.choiceMore[i].answer!=answer){
                        this.falseCourse.push(this.choiceMore[i])
                        this.result.falsecount++;
                    }
                }
                for (let i = 0; i < this.trueorfalse.length; i++) {
                    if(this.trueorfalse[i].answer==this.trueFalseAnswer[i]){
                        this.result.score += this.trueorfalse[i].point
                        this.result.truecount++;
                    }
                    if(this.trueorfalse[i].answer!=this.trueFalseAnswer[i]){
                        this.falseCourse.push(this.trueorfalse[i])
                        this.result.falsecount++;
                    }
                }
                for (let i = 0; i < this.exercise.length; i++) {
                    if(this.exercise[i].answer==this.exerciseAnswer[i]){
                        this.result.score += this.exercise[i].point
                        this.result.truecount++;
                    }
                    if(this.exercise[i].answer!=this.exerciseAnswer[i]){
                        this.falseCourse.push(this.exercise[i])
                        this.result.falsecount++;
                    }
                }
                for (let i = 0; i < this.exerciseMore.length; i++) {
                    if(this.exerciseMore[i].answer==this.exerciseMoreAnswer[i]){
                        this.result.score += this.exerciseMore[i].point
                        this.result.truecount++;
                    }
                    if(this.exerciseMore[i].answer!=this.exerciseMoreAnswer[i]){
                        this.falseCourse.push(this.exerciseMore[i])
                        this.result.falsecount++;
                    }
                }

                if(this.allAnswer.length<this.allCourses.length){
                    this.$message.warning("请把题目做完！")
                    return
                }
                this.result.finishtime =this.endTime - this.startTime
                this.result.paperId = this.paper.paperId
                this.result.sid = this.student.sid
                this.result.endTime = new Date().valueOf()
                //执行错题本的添加
                if(this.falseCourse.length>0){
                    for (let i = 0; i < this.falseCourse.length; i++) {
                        this.falseCourse[i].sid = this.student.sid
                    }
                    this.$axios.post('course/addFalseCourse',this.falseCourse).then(resp=>{
                        console.log(resp.data.data)
                        if(resp.data.code==200){
                            console.log("添加成功！")
                        }
                    })
                }
                this.$axios.post('result/addResult',this.result).then(resp=>{
                    if(resp.data.code==200){
                        this.$message.success('试卷提交成功！查看你做的结果吧！')
                        this.paperstudent.paperId = this.paper.paperId
                        this.paperstudent.paperstatus = 1
                        this.$axios.post('paper/updateStatus',this.paperstudent).then(resp=>{
                            if(resp.data.code==200){
                                setTimeout(()=>{
                                    this.$router.replace('/home/paperCenter')
                                },2000)
                            }
                        })
                    }
                })
            },
            selectCourse(index){
                alert(index)
            },

            getParams(){
                this.paper = JSON.parse(this.$route.query.paper)
                this.startTime = Number(this.$route.query.startTime)
                this.choiceOne=[]
                this.choiceMore = []
                this.exercise = []
                this.exerciseMore = []
                this.trueorfalse = []
                console.log(this.paper.papername)
                this.$axios.get('paper/findPapersByPapername/'+this.paper.papername).then(resp=>{
                    this.allCourses = resp.data.data
                    console.log(this.allCourses)
                    for (let i = 0; i < this.allCourses.length; i++) {
                        if(this.allCourses[i].courseId=='1'){
                            this.allCourses[i].coursename = '单选'
                            this.choiceOne.push(this.allCourses[i])
                            if(this.choiceOne.length>0){
                                this.one = true
                                this.choiceLen = this.choiceOne.length
                            }

                        }
                        if(this.allCourses[i].courseId=='2'){
                            this.allCourses[i].coursename = '多选'
                            this.choiceMore.push(this.allCourses[i])
                            if(this.choiceMore.length>0){
                                this.more = true
                                this.choiceMoreLen = this.choiceMore.length
                            }
                            console.log(this.choiceMore)
                        }
                        if(this.allCourses[i].courseId=='3'){
                            this.allCourses[i].coursename = '判断'
                            this.trueorfalse.push(this.allCourses[i])
                            if(this.trueorfalse.length>0){
                                this.trueFalse = true
                                this.trueFalseLen = this.trueorfalse.length
                            }
                        }
                        if(this.allCourses[i].courseId=='4'){
                            this.allCourses[i].coursename = '填空'
                            this.exercise.push(this.allCourses[i])
                            if(this.exercise.length>0){
                                this.exe = true
                                this.exerciseLen = this.exercise.length
                            }
                        }
                        if(this.allCourses[i].courseId=='5'){
                            this.allCourses[i].coursename = '简答'
                            this.exerciseMore.push(this.allCourses[i])
                            if(this.exerciseMore.length>0){
                                this.exeMore = true
                                this.exerciseMoreLen = this.exerciseMore.length
                            }
                        }
                    }
                })
                this.currentTime = new Date().valueOf()
                this.endTime = this.startTime + this.paper.time*60*1000
            },

            countDownS_cb: function (x) {
                const currentTime =  new Date().valueOf()
                console.log(x)
            },
            countDownE_cb: function (x) {
                console.log(x)
            },

            getStudent(){
                this.$axios.get('student/getStuMsg/'+this.$store.state.username).then(resp=>{
                    this.student = resp.data.data
                })
            }


        },
        watch:{
            endTime:function (val) {
                if(val==new Date().valueOf()){
                    this.submitPaper()
                }
            }
        }
    }
</script>

<style scoped>
    #aside{
        position: relative;
    }
    .paper{
        position: absolute;
        left: 430px;
        top: 40px;
    }
    .spanId{
        margin: 15px 10px 10px 0;
        width: 35px;
        height: 22px;
        border: 1px solid black;
        float: left;
        cursor: pointer;
        background-color: #EFF2F7;
    }
    #bottom{
        position: absolute;
        top: 245px;
    }
    #bottom2{
        position: absolute;
        top: 327px;
    }
    .spanId:hover{
        background-color: skyblue;
    }
    #count-down{
        position: absolute;
        left: 50px;
        top: 275px;
    }
    #btn{
        position: absolute;
        left: 67px;
        top: 368px;
    }

    .course{
        margin-top: 25px;
    }
</style>