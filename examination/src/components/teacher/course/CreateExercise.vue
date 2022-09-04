<template>
    <div id="form">
        <el-form :model="exercise"
                 :rules="rules"
                 ref="ruleForm"
                 label-width="100px"
                 class="demo-ruleForm"
        >
            <el-form-item label="学科" prop="courseType">
                <el-select v-model="exercise.coursetype" placeholder="请选择学科">
                    <el-option label="语文" :value="1"></el-option>
                    <el-option label="数学" :value="2"></el-option>
                    <el-option label="英语" :value="3"></el-option>
                    <el-option label="地理" :value="4"></el-option>
                    <el-option label="生物" :value="5"></el-option>
                    <el-option label="科学" :value="6"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="题型" prop="courseId" id="courseId">
                <el-select v-model="exercise.courseId"
                           placeholder="请选择题型">
                    <el-option label="填空题" :value="4"></el-option>
                    <el-option label="简答题" :value="5"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="题干" prop="title">
                <el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 4}"
                        placeholder="请输入内容"
                        v-model="exercise.title"
                        style="width: 570px"
                >
                </el-input>
            </el-form-item>


            <el-form-item label="标答" prop="answer">
                <el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 4}"
                        placeholder="请输入内容"
                        v-model="exercise.answer"
                        style="width: 570px"
                >
                </el-input>
            </el-form-item>

            <el-form-item label="分数" prop="point">
                <el-input-number v-model="exercise.point"
                                 :precision="1" :step="0.5" :max="20"
                                 @change="handleChange" :min="1"  label="描述文字"></el-input-number>
            </el-form-item>

            <el-form-item label="难度" prop="diff">
                <el-rate
                        v-model="exercise.diff"
                        show-score
                        text-color="#ff9900"
                        score-template={value}
                        style="margin-top: 13px"
                >
                </el-rate>
            </el-form-item>

            <el-form-item >
                <el-button type="primary" @click="submitHandle">提交</el-button>
                <el-button @click="resetAll">重置</el-button>
                <el-button type="success" @click="checkInfo">预览</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "CreateExercise",
        data(){
            return{
                exercise:{
                    coursetype:'',
                    answer:[],
                    diff:'',
                    point:'',
                    title:'',
                    courseId:''
                },


                rules:{
                    courseId:[
                        {required:true,message:'请选择题型',trigger:'blur'}
                    ],
                    courseType:[
                        {required:true,message:'请选择学科',trigger:'blur'}
                    ],
                    title:[
                        { required: true, message: '请输入题目', trigger: 'blur' },
                        { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
                    ],
                    answer:[
                        { required: true, message: '请输入标准答案', trigger: 'blur' },
                    ],
                    point:[
                        { required: true, message: '请填写该题的分数', trigger: 'change' },
                    ],
                    diff:[
                        { required: true, message: '请勾选该题的难度系数', trigger: 'blur' },
                    ],
                },
                cou:{
                    courseType:'',
                    ctname:''
                },
                dialogVisible:false
            }
        },

        methods:{
            //改变分数
            handleChange(value) {
                console.log(value);
            },

            submitHandle(){
                this.$axios.post('course/addExercise',this.exercise).then(resp=>{
                    if(resp.data.code==200){
                        this.$message.success('添加成功！')
                        this.resetAll()
                    }else{
                        this.$message.error('添加题目失败');
                    }
                })
            },
            selectCtName(){
                this.$axios.get('course/selectCtName/'+this.exercise.courseType).then(resp=>{
                    this.cou = resp.data.data
                })
            },


            //预览判断题
            checkInfo(){
                this.dialogVisible = true
                this.selectCtName()
            },

            resetAll(){
                this.exercise.answer=''
                this.exercise.courseType=''
                this.exercise.diff=''
                this.exercise.point=''
                this.exercise.title=''
            },

            //关闭弹出层
            closeDia(){
                this.dialogVisible = false
            }
        },

    }
</script>

<style scoped>
    #form{
        position: relative;
    }
    #courseId{
        position: absolute;
        top: 78px;
        left: 565px;
    }
</style>