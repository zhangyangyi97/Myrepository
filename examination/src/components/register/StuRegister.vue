<template>
    <div id="box">


        <div align="center"  style="margin-top: 80px">
            <el-form id="myForm"     style="border: 1px solid black;width: 288px;height: 440px;">
                <div align="center">
                    <h4>学生端注册</h4>
                </div>
                <el-form-item >
                    <el-input class="input" required @blur="hasStudent" v-model="student.username" prefix-icon="el-icon-user-solid" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input class="input" v-model="student.password"  prefix-icon="el-icon-lock" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <el-form-item style="margin: auto;">
                    <el-radio-group  v-model="student.sex" >
                        <el-radio :label="'男'" style="color: #333333;font-weight: bold;"></el-radio>
                        <el-radio :label="'女'" style="color: #333333;font-weight: bold;"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <el-select v-model="student.gradeId" placeholder="请选择年级"
                               style="width: 230px;"
                    >
                        <el-option label="一年级" :value="1"></el-option>
                        <el-option label="二年级" :value="2"></el-option>
                        <el-option label="三年级" :value="3"></el-option>
                        <el-option label="四年级" :value="4"></el-option>
                        <el-option label="五年级" :value="5"></el-option>
                        <el-option label="六年级" :value="6"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-input class="input" v-model="student.birthday" type="date"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="toLogin" class="input" >学生端注册</el-button><br>
                    <span id="span" style="font-size: 12px;cursor: pointer;color: #333333;font-weight: bold" @click="goToLogin">去登录吧</span>
                </el-form-item>
                <el-input type="hidden" v-model="student.role"></el-input>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data () {
            return {
                radio: '1',
                student: {
                    role:2,
                    sex:'',
                    username:'',
                    password:'',
                    gradeId:'',
                    birthday:''
                },


            };
        },
        methods:{
            changeHandle(val){

            },

            goToLogin(){
                this.$router.replace('/login')
            },
            hasStudent(){
                if(this.student.username){
                    this.$axios.post('student/findHasStudent',{
                        username: this.student.username
                    }).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success(resp.data.message)
                        }else{
                            this.$message.error("该账户不可用！")
                            this.student.username = ''
                        }
                    })
                }else{
                    this.$message.warning('姓名不能为空！请填写姓名！')
                }
            },


            toLogin(){
                if(this.student.password==''||this.student.birthday==''||this.student.gradeId==''||this.student.username==''){
                    this.$message.warning('请将信息填写完整！')
                    return
                }
                this.$axios.post('student/register',this.student).then(resp=>{
                    console.log(resp.data.data)
                    if(resp.data.code==200){

                        this.$message.success(resp.data.message)
                        setTimeout(()=>{
                            this.$router.replace('/login')
                        },2000)
                    }else{
                        this.$message.error(resp.data.message)
                    }
                })
            }
        }
    }
</script>

<style scoped>
    body{
        margin: 0;
        padding: 0;
    }
    #box{
        background-image: url("../../assets/reg/stuReg.png");
        background-position: center;
        background-size: cover;
        width: 100%;
        height: 100%;
    }
    #myForm{
        background-color: rgba(255,255,255,0.6);
        border-radius: 15px;
        box-shadow: 0 0 20px;
    }
    .input{
        width: 230px;
    }
    #span:hover{
        background-color: skyblue;
    }
</style>