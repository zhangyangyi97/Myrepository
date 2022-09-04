<template>
    <div>
        <el-form :model="paper"
                 :rules="rules"
                 ref="ruleForm"
                 label-width="100px"
                 class="demo-ruleForm"
        >
            <el-form-item label="年级:" prop="gradeId">
                <el-select v-model="teacher.gradeId"
                            disabled
                            style="width: 256.67px"
                >
                    <el-option label="一年级" :value="1"></el-option>
                    <el-option label="二年级" :value="2"></el-option>
                    <el-option label="三年级" :value="3"></el-option>
                    <el-option label="四年级" :value="4"></el-option>
                    <el-option label="五年级" :value="5"></el-option>
                    <el-option label="六年级" :value="6"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="试卷学科:" prop="gradeId"
            >
                <el-select v-model="paper.coursetype"
                           @change="selectCourseByCourseType"
                           style="width: 256.67px"
                >
                    <el-option label="语文" :value="1"></el-option>
                    <el-option label="数学" :value="2"></el-option>
                    <el-option label="英语" :value="3"></el-option>
                    <el-option label="地理" :value="4"></el-option>
                    <el-option label="生物" :value="5"></el-option>
                    <el-option label="科学" :value="6"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="试卷类型:" prop="status"
                          placeholder="请选择试卷类型"
            >
                <el-select v-model="paper.status"
                           style="width: 256.67px"
                >
                    <el-option label="固定试卷" :value="1"></el-option>
                    <el-option label="非固定试卷" :value="2"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="试卷名称:" prop="papername">
                <el-input style="width: 256.67px" v-model="paper.papername"></el-input>
            </el-form-item>

            <el-form-item label="建议时长:" prop="time">
                <el-select v-model="paper.time" style="width: 256.67px">
                    <el-option label="20分钟" :value="20"></el-option>
                    <el-option label="30分钟" :value="30"></el-option>
                    <el-option label="40分钟" :value="40"></el-option>
                    <el-option label="60分钟" :value="60"></el-option>
                    <el-option label="90分钟" :value="90"></el-option>
                    <el-option label="120分钟" :value="120"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="试卷分数:">
                <el-input-number v-model="paper.mark"
                                 disabled
                                 :precision="1" :max="100"
                                 :min="0"  label="描述文字"></el-input-number>
            </el-form-item>

            <el-form-item label="试卷题数:">
                <el-input-number v-model="paper.coursecount"
                                 disabled
                                 :max="100"
                                 :min="0"  label="描述文字"></el-input-number>
            </el-form-item>

            <el-form-item >
                <el-button type="primary" @click="submitHandle">提交</el-button>
                <el-button  @click="resetAll">重置</el-button>
                <el-button type="success" @click="addCourse" >添加题目</el-button>

                <el-button type="info" v-show="hasCourse" @click="openPaper">试卷预览</el-button>
            </el-form-item>

            <el-input type="hidden" v-model="paper.paperId"></el-input>



            <ShowPaperCourses ref="showPaperCourses"></ShowPaperCourses>

            <AddCourse ref="addCourses"
                       @sendCourses="onSendCoursesHandle"
                       @sendPoint="onSendPointHandle"
                       @sendCourseCount="onSendCourseCountHandle"
                       @sendSuccess="onSendSuccessHandle"
            ></AddCourse>
        </el-form>
    </div>
</template>

<script>
    import AddCourse from "@/components/teacher/paper/AddCourse";
    import ShowPaperCourses from "@/components/teacher/paper/ShowPaperCourses";
    export default {
        name: "CreatePaper",
        components: {ShowPaperCourses, AddCourse},
        data(){
            return{
                paper:{
                    status:'',
                    gradeId:'',
                    time:'',
                    coursetype:'',
                    papername:'',
                    paperId: '',
                    mark: '',
                    firstInit:'',
                    coursecount: ''
                },
                params:{
                    courseType:0
                },

                rules:{
                    status: [
                        { required: true, message: '请选择试卷类型', trigger: 'blur' },
                    ],

                    coursetype:[
                        {required:true,message:'请选择学科',trigger:'change'}
                    ],
                    gradeId:[
                        { required: true, message: '请选择年级', trigger: 'blur' },
                    ],
                    papername:[
                        { required: true, message: '请输入试卷名称', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    time:[
                        { required: true, message: '请填写该题的分数', trigger: 'change' },
                    ]
                },
                teacher:{
                    gradeId: ''
                },
                courseType:[],

                hasCourse:false,
                selectCourses: [],
                courses:[]
            }
        },

        mounted() {
            this.loadCourseType()
            this.getGrade()
            this.getMaxPaperId()
        },

        methods:{
            //外面选什么学科的试卷里面也是什么学科的题目
            selectCourseByCourseType(val){
                this.params.courseType = val
                this.$refs.addCourses.params.courseType = val
            },

            resetAll(){
                this.paper.gradeId = ''
                this.paper.coursecount=''
                this.paper.coursetype=''
                this.paper.mark=0
                this.paper.time=''
                this.paper.papername=''
                this.paper.paperId=''
                this.paper.status=''
                this.$refs.showPaperCourses.courses = []
                this.$refs.showPaperCourses.choice = []
                this.$refs.showPaperCourses.exercise = []
                this.$refs.showPaperCourses.trueorfalse = []
                this.$refs.addCourses.point = 0
                this.hasCourse = false
                this.getMaxPaperId()
                this.getGrade()
            },

            openPaper(){
                console.log(this.selectCourses)
                this.$refs.showPaperCourses.dialogPaperVisible = true
                if(this.paper.papername!=''){
                    this.$refs.showPaperCourses.paper = this.paper
                    console.log(this.paper)
                    for (let i = 0; i < this.selectCourses.length; i++) {
                        if(this.selectCourses[i].courseId=='1'){
                            this.$refs.showPaperCourses.choiceOne.push(this.selectCourses[i])
                            for (let j = 0; j < this.$refs.showPaperCourses.choiceOne.length; j++) {
                                for (let k = j+1; k < this.$refs.showPaperCourses.choiceOne.length; k++) {
                                    if(this.$refs.showPaperCourses.choiceOne[j]==this.$refs.showPaperCourses.choiceOne[k]){
                                        this.$refs.showPaperCourses.choiceOne.splice(k,1);
                                        this.$refs.showPaperCourses.choiceOne.length--;
                                        k--;
                                    }
                                }
                            }
                        }
                        if(this.selectCourses[i].courseId=='2'){
                            this.$refs.showPaperCourses.choiceMore.push(this.selectCourses[i])
                            var length = this.$refs.showPaperCourses.choiceMore.length
                            for (let j = 0; j < length; j++) {
                                for (let k = j+1; k < length; k++) {
                                    if(this.$refs.showPaperCourses.choiceMore[j]==this.$refs.showPaperCourses.choiceMore[k]){
                                        this.$refs.showPaperCourses.choiceMore.splice(k,1)
                                        length--;
                                        k--;
                                    }
                                }
                            }
                        }
                        if(this.selectCourses[i].courseId=='3'){
                            this.$refs.showPaperCourses.trueorfalse.push(this.selectCourses[i])
                            var tLength =  this.$refs.showPaperCourses.trueorfalse.length
                            for (let j = 0; j < tLength; j++) {
                                for (let k = j+1; k < tLength; k++) {
                                    if( this.$refs.showPaperCourses.trueorfalse[j]== this.$refs.showPaperCourses.trueorfalse[k]){
                                        this.$refs.showPaperCourses.trueorfalse.splice(k,1)
                                        tLength--;
                                        k--;
                                    }
                                }
                            }
                        }

                        if(this.selectCourses[i].courseId=='4'){
                            this.$refs.showPaperCourses.exercise.push(this.selectCourses[i])
                            var eLength =   this.$refs.showPaperCourses.exercise.length
                            for (let j = 0; j < eLength; j++) {
                                for (let k = j+1; k < eLength; k++) {
                                    if( this.$refs.showPaperCourses.exercise[j]==this.$refs.showPaperCourses.exercise[k]){
                                        this.$refs.showPaperCourses.exercise.splice(k,1)
                                        eLength--;
                                        k--;
                                    }
                                }
                            }
                        }
                        if(this.selectCourses[i].courseId=='5'){
                            this.$refs.showPaperCourses.exerciseMore.push(this.selectCourses[i])
                            var eMLength =  this.$refs.showPaperCourses.exerciseMore.length
                            for (let j = 0; j < eMLength; j++) {
                                for (let k = j+1; k < eMLength; k++) {
                                    if( this.$refs.showPaperCourses.exerciseMore[j]== this.$refs.showPaperCourses.exerciseMore[k]){
                                        this.$refs.showPaperCourses.exerciseMore.splice(k,1)
                                        eMLength--;
                                        k--;
                                    }
                                }
                            }
                        }
                    }
                    this.selectCourses = []
                }
            },

            //传过来选中的值
            onSendCoursesHandle(courses){
                this.selectCourses = courses
            },
            onSendPointHandle(mark){
                this.paper.mark = mark
            },
            onSendCourseCountHandle(courseCount){
                this.paper.coursecount =courseCount
            },
            onSendSuccessHandle(success){
                this.hasCourse = success
            },

            loadCourseType(){
                this.$axios.get('course/findCourseTypes').then(resp=>{
                    this.courseType = resp.data.data
                })
            },

            submitHandle(){
                this.getMaxPaperId()
                var courses = this.$refs.addCourses.selectCourses
                console.log(courses)
                for (let i = 0; i < this.selectCourses.length; i++) {
                    delete courses[i].uuid
                    courses[i].paperId = this.paper.paperId
                    courses[i].status = Number(courses[i].status)
                }
                console.log(courses)
                this.courses = courses
                this.$confirm('确定以上题目为试卷的试题么?',"提示",{
                    confirmButtonText:'确定',
                    cancelButtonClass:'取消',
                    type:'warning'
                }).then(()=>{
                    this.$axios.post('paper/addPaper',this.paper).then(resp=>{
                        if(resp.data.code==200){
                            this.$axios.post('course/addCourseToPaper',courses).then(resp=>{
                                if(resp.data.code==200){
                                    this.$message.success('添加试卷成功！')
                                    this.resetAll()
                                    this.getGrade()
                                    this.getMaxPaperId()
                                }else{
                                    this.$message.error('添加试卷失败！')
                                }
                            })
                        }
                    })
                }).catch(()=>{
                    this.$message({
                        type:'info',
                        message:'已取消删除'
                    })
                })
            },

            addCourse(){
                if(this.paper.courseType!=''&&this.paper.papername!=''&&this.paper.time!=''){
                    //添加题目
                    this.$axios.post('course/select',this.params).then(resp=>{
                        this.$refs.addCourses.allCourses = resp.data.data
                        this.$refs.addCourses.total = resp.data.data.length
                        this.$refs.addCourses.pageSize = 4
                        this.$refs.addCourses.currentPage = 1
                    })
                    this.$refs.addCourses.dialogTableVisible = true
                }else{
                    this.$message.warning('请将试卷信息填写完整！')
                }

            },

            getGrade(){
                this.$axios.post('teacher/getTeacher',this.$store.state.username).then(resp=>{
                    console.log(resp.data.data)
                    this.teacher = resp.data.data
                    this.paper.gradeId = this.teacher.gradeId
                })
            },


            //获取卷子的最大编号
            getMaxPaperId(){
                this.$axios.get('paper/getMaxPaperId').then(resp=>{
                    this.paper.paperId =(resp.data.data+1)
                })
            },
        }
    }
</script>

<style scoped>

</style>