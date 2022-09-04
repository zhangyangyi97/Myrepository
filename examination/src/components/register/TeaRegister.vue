<template>
    <div id="box">


        <div align="center"  style="margin-top: 66px;">

            <el-form id="myForm"    style="border: 1px solid black;width: 288px;height: 420px;">
                <div align="center">
                    <h4>老师端注册</h4>

                </div>
                <el-form-item >
                    <el-input required class="input"  v-model="teacher.tname" prefix-icon="el-icon-user-solid" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item >
                    <el-input required class="input" @blur="hasTeacher" v-model="teacher.username" prefix-icon="el-icon-user-solid" placeholder="请输入昵称"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input class="input" required v-model="teacher.password"  prefix-icon="el-icon-lock" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-select v-model="teacher.gradeId" placeholder="请选择年级"
                               style="width: 230px;"
                               required
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
                    <el-button type="primary" @click="teaToLogin" class="input" >老师端注册</el-button>
                    <br>
                    <span id="span" style="font-size: 12px;cursor: pointer;color: #333333;font-weight: bold" @click="goToLogin">去登录吧</span>
                </el-form-item>
                <el-input type="hidden" v-model="teacher.role"></el-input>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "TeaRegister",
        data () {
            return {
                teacher:{
                    role:1,
                    tname:'',
                    username:'',
                    password:'',
                    gradeId:''
                }
            };
        },
        methods:{
            goToLogin(){
                this.$router.replace('/login')
            },

            hasTeacher(){
                if(this.teacher.username){
                    this.$axios.post('teacher/findHasTeacher',{
                        username: this.teacher.username
                    }).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success(resp.data.message)
                        }else{
                            this.$message.warning("该昵称已被注册！")
                            this.student.username = ''
                        }
                    })
                }else{
                    this.$message.warning('昵称不能为空！请填写！')
                }
            },

            teaToLogin(){
                if(this.teacher.password==''||this.teacher.password==''||this.teacher.gradeId==''||this.teacher.username==''){
                    this.$message.warning('请将信息填写完整！')
                    return
                }
                this.$axios.post('teacher/register',this.teacher).then(resp=>{
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
        background-image: url("../../assets/reg/teaReg.png");
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