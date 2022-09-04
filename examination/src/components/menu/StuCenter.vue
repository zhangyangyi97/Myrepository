<template>
    <div>
        <el-dialog
                title="个人中心"
                :visible.sync="dialogVisible"
                @close="student={}"
                :append-to-body="true"
        >
            <el-form :model="student"
                     ref="ruleForm"
                     label-width="100px"
                     class="demo-ruleForm"
            >

                <el-form-item label="姓名" prop="username">
                    <el-input readonly style="width: 323px" v-model="student.username"></el-input>
                </el-form-item>

                <el-form-item label="生日" prop="birthday">
                    <el-input type="date" style="width: 323px" readonly  v-model="student.birthday"></el-input>
                </el-form-item>

                <el-form-item label="年级" prop="ctname">
                    <el-select v-model="student.gradeId" disabled placeholder="请选择年级">
                        <el-option label="一年级" :value="1"></el-option>
                        <el-option label="二年级" :value="2"></el-option>
                        <el-option label="三年级" :value="3"></el-option>
                        <el-option label="四年级" :value="4"></el-option>
                        <el-option label="五年级" :value="5"></el-option>
                        <el-option label="六年级" :value="6"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item >
                    <el-button type="danger" @click="dialogVisible=false">关闭</el-button>
                </el-form-item>
                <el-input type="hidden" v-model="student.sid"></el-input>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "StuCenter",
        data(){
            return{
                student:{
                    username:'',
                    birthday:'',
                    sid:'',
                    gradeId:''
                },
                dialogVisible:false
            }
        },

        methods:{

        }
    }
</script>

<style scoped>

</style>