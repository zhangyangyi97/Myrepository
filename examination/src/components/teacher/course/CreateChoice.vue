<template>
    <div id="form">
        <el-form :model="choice"
                 :rules="rules"
                 ref="ruleForm"
                 label-width="100px"
                 class="demo-ruleForm"
        >
            <el-form-item label="学科" prop="courseType">
                <el-select v-model="choice.coursetype" placeholder="请选择学科">
                    <el-option label="语文" :value="1"></el-option>
                    <el-option label="数学" :value="2"></el-option>
                    <el-option label="英语" :value="3"></el-option>
                    <el-option label="地理" :value="4"></el-option>
                    <el-option label="生物" :value="5"></el-option>
                    <el-option label="科学" :value="6"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="题型" prop="courseId" id="courseId">
                <el-select v-model="choice.courseId" @change="changeHandle" placeholder="请选择题型">
                    <el-option label="单选题" :value="1"></el-option>
                    <el-option label="多选题" :value="2"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="题干" prop="title">
                <el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 4}"
                        placeholder="请输入内容"
                        v-model="choice.title"
                >
                </el-input>
            </el-form-item>

            <el-form-item label="选项" prop="answerA" id="answer">
               <div style="box-sizing: border-box">
                   <span style="float: left;color: rgb(96,98,102)">A</span><el-button style="width: 50px;float: left;margin-left: 10px">A</el-button>
                   <el-input type="text" style="width: 290px;float: left;margin-left: 10px;"
                            v-model="choice.answerA"
                   ></el-input>
                   <el-button style="background-color:rgb(255,73,73);float: left;margin-left: 10px ">
                       <i class="el-icon-delete" style="color: white"></i>
                   </el-button>
               </div>
                <div style="box-sizing: border-box">
                    <span style="float: left;color: rgb(96,98,102);margin-left: 50px">B</span><el-button style="width: 50px;float: left;margin-left: 10px">B</el-button>
                    <el-input type="text" style="width: 290px;float: left;margin-left: 10px"
                              v-model="choice.answerB"
                    >
                    </el-input>
                    <el-button style="background-color:rgb(255,73,73);margin-left: 10px ">
                        <i class="el-icon-delete" style="color: white"></i>
                    </el-button>

                </div>
                <div style="margin-top: 5px">
                    <div style="box-sizing: border-box">
                        <span style="float:left;color: rgb(96,98,102);">C</span><el-button style="width: 50px;float: left;margin-left: 10px">C</el-button>
                        <el-input type="text" style="width: 290px;float: left;margin-left: 10px;"
                                v-model="choice.answerC"
                        >
                        </el-input>
                        <el-button style="background-color:rgb(255,73,73);float: left;margin-left: 10px ">
                            <i class="el-icon-delete" style="color: white"></i>
                        </el-button>
                    </div>
                    <div style="box-sizing: border-box">
                        <span style="float: left;color: rgb(96,98,102);margin-left: 50px">D</span><el-button style="width: 50px;float: left;margin-left: 10px">D</el-button>
                        <el-input type="text" style="width: 290px;float: left;margin-left: 10px"
                                  v-model="choice.answerD"
                        ></el-input>
                        <el-button style="background-color:rgb(255,73,73);margin-left: 10px ">
                            <i class="el-icon-delete" style="color: white"></i>
                        </el-button>

                    </div>
                </div>

            </el-form-item>

            <el-form-item label="标答" prop="answer" v-show="choiceOnly">
                <el-radio-group v-model="choice.answer">
                    <el-radio label="A"></el-radio>
                    <el-radio label="B"></el-radio>
                    <el-radio label="C"></el-radio>
                    <el-radio label="D"></el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="标答" prop="answer" v-show="choiceMore">
                <el-checkbox-group v-model="choice.answer">
                    <el-checkbox label="A"></el-checkbox>
                    <el-checkbox label="B"></el-checkbox>
                    <el-checkbox label="C"></el-checkbox>
                    <el-checkbox label="D"></el-checkbox>
                </el-checkbox-group>
            </el-form-item>


            <el-form-item label="分数" prop="point">
                <el-input-number v-model="choice.point"
                                 :precision="1" :step="0.5" :max="20"
                                 @change="handleChange" :min="1"  label="描述文字"></el-input-number>
            </el-form-item>

            <el-form-item label="难度" prop="diff">
                <el-rate
                        v-model="choice.diff"
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

        <el-dialog
                :title="cou.ctname"
                :append-to-body="true"
                :visible.sync="dialogVisible"
        >
            <span>{{this.choice.title}}</span>
            <p>A.{{this.choice.answerA}}</p>
            <p>B.{{this.choice.answerB}}</p>
            <p>C.{{this.choice.answerC}}</p>
            <p>D.{{this.choice.answerD}}</p>
            <p style="color: red">标准答案:{{this.choice.answer}}</p>
            <p style="color: red">分值:{{this.choice.point}}</p>
            <span>难度系数:</span>
            <el-rate
                    v-model="choice.diff"
                    show-score
                    text-color="#ff9900"
                    score-template={value}
                    style="margin-top: 13px"
            >
            </el-rate>
            <el-button type="danger" @click="closeDia">关闭</el-button>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "CreateChoice",
        data(){
            return{
                choice:{
                    answerA:'',
                    answerB:'',
                    answerC:'',
                    answerD:'',
                    coursetype: '',
                    answer:[],
                    diff: 0,
                    point:0,
                    title:'',
                    courseId:''
                },

                choiceOnly:true,
                choiceMore:false,

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
                    answerA: [
                        {required: true, message: '请填写选项信息', trigger: 'blur'}
                    ]
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
                var answer = ''
                if(this.choice.courseId=='2'){
                    for (let i = 0; i < this.choice.answer.length; i++) {
                        answer += this.choice.answer[i]
                    }
                    this.choice.answer = answer
                }
                console.log(this.choice.answer)
                this.$axios.post('course/addChoice',this.choice).then(resp=>{
                    if(resp.data.code==200){
                        this.$message.success('添加成功！')
                        this.resetAll()
                    }else{
                        this.$message.error('添加题目失败');
                    }
                })
            },
            selectCtName(){
                this.$axios.get('course/selectCtName/'+this.choice.courseType).then(resp=>{
                    this.cou = resp.data.data
                })
            },

            changeHandle(val){
                if(val=='1'){
                    this.choiceOnly = true
                    this.choiceMore = false
                }
                if(val=='2'){
                    this.choiceOnly = false
                    this.choiceMore = true
                }
            },


            //预览选择题
            checkInfo(){
                this.dialogVisible = true
                this.selectCtName()
            },

            resetAll(){
                this.choice.answer=''
                this.choice.coursetype=''
                this.choice.diff=''
                this.choice.point=''
                this.choice.title=''
                this.choice.answerA=''
                this.choice.answerB=''
                this.choice.answerC=''
                this.choice.answerD=''
                this.choice.courseId=''
            },

            //关闭弹出层
            closeDia(){
                this.dialogVisible = false
            }
        },

    }
</script>

<style scoped>
    #answer{
        position: relative;
    }
    #form{
        position: relative;
    }
    #courseId{
        position: absolute;
        top: 78px;
        left: 565px;
    }
</style>