<template>


    <div id="box" >
        <el-card shadow="always"   style="width: 288px;height: 388px;box-shadow:0 0 20px;float: right;margin: 100px 100px 0 0">
            <div align="center" style="margin-bottom: 20px" v-show="student">
                <span style="font-size: 16px">学生端考试系统登录</span>
                <span @click="teaOrStu"  style="font-size: 9px;display:inline-block;cursor:pointer;margin-left: 7px;text-decoration: none;color: green">老师端</span>
            </div>
            <div align="center" style="margin-bottom: 20px" v-show="teacher">
                <span style="font-size: 16px">教师端管理系统登录</span>
                <span @click="teaOrStu"  style="font-size: 9px;display:inline-block;cursor:pointer;margin-left: 4px;text-decoration: none;color: green">学生端</span>
            </div>

            <!--老师登录-->
            <div align="center" v-show="teacher">
                <el-input prefix-icon="el-icon-user-solid" placeholder="请输入姓名" v-model="tea.username" style="height: 15px;margin: 9px 0 40px 0"></el-input>
                <el-input  prefix-icon="el-icon-lock" placeholder="请输入密码" v-model="tea.password" show-password></el-input>
            </div>

            <!--学生登录-->
            <div align="center" v-show="student">
                <el-input prefix-icon="el-icon-user-solid"  placeholder="请输入姓名" v-model="stu.username" style="height: 15px;margin: 9px 0 40px 0"></el-input>
                <el-input  prefix-icon="el-icon-lock" placeholder="请输入密码" v-model="stu.password" show-password></el-input>
            </div>

            <div style="margin-top: 15px;">
                <div style="width: 250px;margin-bottom: 20px">
                    <el-input style="width: 250px !important;" prefix-icon="el-icon-lock" placeholder="验证码" v-model="code" ></el-input>
                </div>
                <div class="login-code" style="cursor: pointer;width: 70px;" @click="refreshCode"><SIdentity :identifyCode="identifyCode"></SIdentity></div>

            </div>
            <div>
                <el-button type="primary" @click="toHome" style="width: 242px;height: 36px;margin-top: 5px">登录</el-button>
            </div>
            <div align="center" style="margin-top: 3px">
                <span style="font-size: 8px">没有账号?</span>
                <router-link to="/stuRegister" style="font-size: 8px;text-decoration: none;margin-left: 5px;color: green" v-show="student">注册</router-link>
                <router-link to="/TeaRegister" style="font-size: 8px;text-decoration: none;margin-left: 5px;color: green" v-show="teacher">注册</router-link>
                <span @click="forgetPwd" style="font-size: 8px;margin-left: 20px;color: green;cursor: pointer">忘记密码</span>
            </div>
            <el-dialog
                title="忘记密码"
                :append-to-body="true"
                :visible.sync="dialogVisible"
                width="450px"
            >
                <el-input show-password v-model="firstPwd" placeholder="请输入密码" style="width: 323.67px"></el-input>
                <el-input show-password @blur="isPwd" v-model="secondPwd" placeholder="请再次确认新密码" style="width: 323.67px;margin-top: 20px"></el-input>
                <el-button @click="changePwd" style="margin-top: 20px" type="primary">修改密码</el-button>
            </el-dialog>

        </el-card>
    </div>
</template>

<script>
    import SIdentity from "@/components/code/SIdentity";
    export default {
        name: "Login",
        components: {SIdentity},
        data(){
            return{
                tea: {
                    username:'',
                    password:''
                },
                stu:{
                    username:'',
                    password:''
                },
                student: true,
                teacher: false,
                code: '',
                identifyCode: '',
                identifyCodes: '1234567890',
                value: '',
                firstPwd:'',
                secondPwd:'',
                dialogVisible:false,
                changeStu:{
                    username:'',
                    password:''
                },
                changeTea:{
                    username:'',
                    password:''
                }
            }
        },
        mounted() {
            this.student = true
            this.identifyCode = "";
            this.makeCode(this.identifyCodes, 4);
        },
        methods:{
            //登录的跳转请求
            toHome(){
                //学生登录的判断
                if(this.student==true){
                    this.$axios.post('student/login',this.stu).then(resp=>{
                        if(resp.data.code==200){
                            if(this.code==this.identifyCode && this.code!=''){
                                this.$store.commit('login',resp.data.data)
                                this.$message.success("欢迎您"+resp.data.data.username)
                                setTimeout(()=>{
                                    this.$router.replace('/home')
                                },2000)
                            }else{
                                this.$message.error('验证码输入有误！')
                                this.code=''
                            }
                        }
                    }).catch(reason => {
                        this.$message.error('您好您没有权限！请确认自己的账号是否正确！')
                    })
                    return
                }

                //老师登录的判断
                if(this.teacher==true){
                    this.$axios.post('teacher/login',this.tea).then(resp=>{
                        console.log(resp.data.data)
                        if(resp.data.code == 200){
                            if(this.code==this.identifyCode&&this.code!=''){
                                this.$store.commit('login',resp.data.data)
                                this.$message.success("欢迎您"+resp.data.data.username)
                                setTimeout(()=>{
                                    this.$router.replace('/teaHome')
                                },2000)
                            }else{
                                this.$message.error('验证码输入有误！')
                                this.code =''
                            }
                            return
                        }
                    }).catch(reason => {
                        this.$message.error('您好您没有权限！请确认自己的账号是否正确！')
                    })
                }
            },

            //忘记密码
            forgetPwd(){
                if(this.student==true){
                    if(this.stu.username==''){
                        this.$message.warning('请先填写用户名哦！')
                    }else{
                        this.$axios.post('student/findHasStudent',{
                            username: this.stu.username
                        }).then(resp=>{
                            if(resp.data.code==500){
                                this.dialogVisible = true
                            }else{
                                this.$message.error("您的用户名输入不正确！请重新输入！")
                                this.stu.username = ''
                            }
                        })
                        return
                    }

                }
                if(this.teacher==true){
                    if(this.tea.username==''){
                        this.$message.warning('请先填写用户名哦！')
                    }else{
                        this.$axios.post('teacher/findHasTeacher',{
                            username: this.tea.username
                        }).then(resp=>{
                            if(resp.data.code==500){
                                this.dialogVisible = true
                            }else{
                                this.$message.warning("您的老师账号输入不正确！请重新输入！")
                                this.tea.username = ''
                            }
                        })
                    }
                }
            },

            isPwd(){
                if(this.firstPwd!=this.secondPwd && this.secondPwd!=''&&this.firstPwd!=''){
                    this.$message.warning('您两次输入的密码不一致！请重新输入!')
                    this.secondPwd = ''
                }
                if(this.firstPwd==this.secondPwd&& this.secondPwd!=''&&this.firstPwd!=''){
                    this.$message.success('两次密码输入一致！')
                }
            },

            //修改密码
            changePwd(){
                if(this.student==true){
                    this.changeStu.username = this.stu.username
                    this.changeStu.password = this.secondPwd
                    this.$axios.post('student/changePwd',this.changeStu).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success('修改密码成功！')
                            this.dialogVisible = false
                        }else{
                            this.$message.error('修改密码失败!')
                        }
                    }).catch(reason => {
                        this.$message.error('修改密码失败！')
                    })
                    return
                }
                if(this.teacher==true){
                    this.changeTea.password = this.secondPwd
                    this.changeTea.username = this.tea.username
                    this.$axios.post('teacher/changePwd',this.changeTea).then(resp=>{
                        if(resp.data.code==200){
                            this.$message.success('修改密码成功！')
                            this.dialogVisible = false
                        }else{
                            this.$message.error('修改密码失败!')
                        }
                    }).catch(reason => {
                        this.$message.error('修改密码失败！')
                    })
                }
            },

            teaOrStu(){
                this.student = !this.student
                this.teacher = !this.teacher
                this.code = ''
                this.refreshCode()
            },


            //验证码的校验
            refreshCode() {
                this.identifyCode = '';
                this.makeCode(this.identifyCodes, 4);
            },
            makeCode(o, l) {
                for (let i = 0; i < l; i++) {
                    this.identifyCode += this.identifyCodes[
                        this.randomNum(0, this.identifyCodes.length)]
                }
                console.log(this.identifyCode);
            },
            randomNum(min, max) {
                return Math.floor(Math.random() * (max - min) + min);
            },

        }
    }
</script>

<style>
    body{
        margin: 0;
        padding: 0;
    }
    #box{
        background-repeat: no-repeat;
        background-image: url("../assets/login.jpg");
        background-size: cover;
        box-sizing: border-box;
        position: fixed;
        background-position: center;
        width: 100%;
        height: 100%;
    }
</style>