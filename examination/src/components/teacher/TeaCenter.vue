<template>
    <div>
        <el-dialog
                title="个人中心"
                :visible.sync="dialogVisible"
                @close="teacher={}"
                :append-to-body="true"
        >
            <el-form :model="teacher"
                     ref="ruleForm"
                     label-width="100px"
                     class="demo-ruleForm"
            >

                <el-form-item label="姓名" prop="tname">
                    <el-input readonly v-model="teacher.tname"></el-input>
                </el-form-item>

                <el-form-item label="昵称" prop="username">
                    <el-input  v-model="teacher.username"></el-input>
                </el-form-item>

                <el-form-item label="学科" prop="ctname">
                    <el-select v-model="teacher.gradeId" placeholder="请选择年级" disabled>
                        <el-option label="一年级" :value="1"></el-option>
                        <el-option label="二年级" :value="2"></el-option>
                        <el-option label="三年级" :value="3"></el-option>
                        <el-option label="四年级" :value="4"></el-option>
                        <el-option label="五年级" :value="5"></el-option>
                        <el-option label="六年级" :value="6"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item >
                    <el-button type="primary" @click="submitHandle">确定</el-button>
                    <el-button type="danger" @click="dialogVisible=false">取消</el-button>
                </el-form-item>
                <el-input type="hidden" v-model="teacher.tid"></el-input>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "TeaCenter",
        data(){
            return{
                teacher:{
                    tname:'',
                    username:'',
                    tid:'',
                    gradeId:''
                },
                dialogVisible:false
            }
        },

        methods:{

            submitHandle(){
                this.$axios.post('teacher/update',this.teacher).then(resp=>{
                    if(resp.data.code==200){
                        this.$message.success('修改成功！')
                        this.dialogVisible = false
                        setTimeout(()=>{
                            this.$router.replace('/login')
                        },2000)
                    }else{
                        this.$message.warning('修改失败！')
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>