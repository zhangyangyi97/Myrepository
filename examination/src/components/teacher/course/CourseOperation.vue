<template>
    <div>
        <el-dialog
               title="修改题目/查看详情/添加题目"
               :visible.sync="dialogVisible"
               @close="course={}"
               :append-to-body="true"
        >
            <el-form :model="course"
                     :rules="rules"
                     ref="ruleForm"
                     label-width="100px"
                     class="demo-ruleForm"
                     :disabled="canEdit"
            >
                <el-form-item label="学科" prop="coursetype">
                    <el-select v-model="course.coursetype" placeholder="请选择学科">
                        <el-option label="语文" :value="1"></el-option>
                        <el-option label="数学" :value="2"></el-option>
                        <el-option label="英语" :value="3"></el-option>
                        <el-option label="地理" :value="4"></el-option>
                        <el-option label="生物" :value="5"></el-option>
                        <el-option label="科学" :value="6"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="题目名称" prop="title">
                    <el-input v-model="course.title"></el-input>
                </el-form-item>

                <el-form-item label="选项" v-show="choice">
                    <el-input v-model="course.answerA" style="width: 300px"></el-input>
                    <el-input v-model="course.answerB" style="width: 300px;margin-top: 5px"></el-input>
                    <el-input v-model="course.answerC" style="width: 300px;margin-top: 5px"></el-input>
                    <el-input v-model="course.answerD" style="width: 300px;margin-top: 5px"></el-input>
                </el-form-item>


                <el-form-item label="标答" prop="answer">
                    <el-input v-model="course.answer"></el-input>
                </el-form-item>

                <el-form-item label="分数" prop="point">
                    <el-input-number v-model="course.point"
                                     :precision="1" :step="0.5" :max="20"
                                     @change="handleChange" :min="1"  label="描述文字"></el-input-number>
                </el-form-item>

                <el-form-item label="难度" prop="diff">
                    <el-rate
                            v-model="course.diff"
                            show-score
                            text-color="#ff9900"
                            score-template={value}
                            style="margin-top: 13px"
                    >
                    </el-rate>
                </el-form-item>

                <el-form-item >
                    <el-button type="primary" @click="submitHandle">确定</el-button>
                    <el-button type="danger" @click="dialogVisible=false">取消</el-button>
                </el-form-item>
                <el-input type="hidden" v-model="course.courseId"></el-input>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "CourseOperation",
        data(){
            return{
                choice:false,

                dialogVisible:false,
                canEdit:false,
                course:{

                },
                rules:{
                    coursetype:[
                        {required:true,message:'请选择学科',trigger:'change'}
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
                    ]
                }
            }
        },


        methods:{
            //改变分数
            handleChange(value) {
                console.log(value);
            },

            submitHandle(){
                if(this.course.courseId=='1'||this.course.courseId=='2'){
                    this.$axios.post('course/updateChoice',this.course).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success('修改成功！')
                            this.dialogVisible = false
                        }else{
                            this.$message.error('修改失败!')
                        }
                    })
                }
                if(this.course.courseId=='3'){
                    this.$axios.post('course/updateExercise',this.course).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success('修改成功！')
                            this.dialogVisible = false
                        }else{
                            this.$message.error('修改失败!')
                        }
                    })
                }
                if(this.course.courseId=='4'||this.course.courseId=='5'){
                    this.$axios.post('course/updateTrue',this.course).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success('修改成功！')
                            this.dialogVisible = false
                        }else{
                            this.$message.error('修改失败!')
                        }
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>