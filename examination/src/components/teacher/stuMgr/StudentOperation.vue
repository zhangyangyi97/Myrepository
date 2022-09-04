<template>
    <div>
        <el-dialog
                title="修改学生/查看详情/添加学生"
                :visible.sync="dialogVisible"
                @close="student={}"
                :append-to-body="true"
                width="550px"
        >
            <el-form :model="student"
                     ref="ruleForm"
                     label-width="100px"
                     class="demo-ruleForm"
                     :disabled="canEdit"
            >
                <el-form-item label="年级" prop="gradeId">
                    <el-select v-model="student.gradeId" placeholder="请选择年级">
                        <el-option label="一年级" :value="1"></el-option>
                        <el-option label="二年级" :value="2"></el-option>
                        <el-option label="三年级" :value="3"></el-option>
                        <el-option label="四年级" :value="4"></el-option>
                        <el-option label="五年级" :value="5"></el-option>
                        <el-option label="六年级" :value="6"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="学生姓名" prop="username" style="width: 323.67px">
                    <el-input v-model="student.username"></el-input>
                </el-form-item>

                <el-form-item label="性别" style="margin-bottom: 15px;">
                   <el-radio-group v-model="student.sex">
                       <el-radio  :label="'男'" style="color: #333333;font-weight: bold;">男</el-radio>
                       <el-radio  :label="'女'" style="color: #333333;font-weight: bold;">女</el-radio>
                   </el-radio-group>
                </el-form-item>


                <el-form-item label="学生生日" prop="birthday" style="width: 323.67px">
                    <el-input class="input" v-model="student.birthday" type="date"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitHandle">确定</el-button>
                    <el-button type="danger" @click="dialogVisible=false">取消</el-button>
                </el-form-item>
                <el-input type="hidden" v-model="student.sid"></el-input>
            </el-form>
            <el-input type="hidden" v-model="student.sid"></el-input>
            <el-input type="hidden" v-model="student.password=Number(123)"></el-input>
            <el-input type="hidden" v-model="student.role=Number(2)"></el-input>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "StudentOperation",
        data(){
            return{
                student:{},
                dialogVisible:false,
                canEdit:false,
            }
        },

        methods:{
            //改变分数
            handleChange(value) {
                console.log(value);
            },

            //修改
            submitHandle(){
                if(this.student.sid){
                    this.$axios.post('student/update',this.student).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success('修改成功！')
                            this.$emit('onSendSuccess')
                            this.dialogVisible = false
                        }else{
                            this.$message.error('修改失败！')
                            this.dialogVisible = false
                        }
                    })
                }else{
                    this.$axios.post('student/register',this.student).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success('添加成功！')
                            this.$emit('onSendSuccess')
                            this.dialogVisible =false
                        }else{
                            this.$message.error('添加失败！')
                            this.dialogVisible =false
                        }
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>