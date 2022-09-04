<template>
    <div>
        <el-menu
            mode="horizontal"
            background-color="#ffffff"
            text-color="#ccc"
        >
            <el-menu-item>
                <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
                    <i class="el-icon-s-fold"
                       style="font-size: 30px;color: #333333;margin-top: 17px"
                       v-show="!isCollapse"
                    ></i>
                    <i class="el-icon-s-unfold" style="font-size: 30px;color: #333333;margin-top: 10px" v-show="isCollapse"></i>
                </el-radio-group>
            </el-menu-item>

            <el-menu-item>
                <span style="font-size: 17px;font-weight: 600;color: black">教师后端管理系统</span><span></span>
            </el-menu-item>

            <el-menu-item style="float: right">
                <el-dropdown trigger="click">
                    <span class="el-dropdown-link">
                        <span>{{username}}</span>
                         <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                            <span @click="myselfInfo">个人信息</span>
                        </el-dropdown-item>
                        <el-dropdown-item>
                            <span @click="changePassword">修改密码</span>
                        </el-dropdown-item>
                        <el-dropdown-item>
                            <span @click="toLogin">返回登录</span>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>

            <TeaCenter ref="TeaCenter"></TeaCenter>
            <el-dialog
                    title="修改密码"
                    :append-to-body="true"
                    :visible.sync="dialogVisible"
                    width="490px"
            >
                <el-input show-password v-model="password" placeholder="请输入原始密码" style="width: 350px"></el-input><el-button type="success" v-show="ok" @click="passwordIsTrue" style="margin-left: 20px">校验</el-button>
                <el-input show-password v-model="firstPwd" v-show="isPwd" placeholder="请输入新密码" style="width: 350px;margin-top: 30px"></el-input>
                <el-input show-password v-model="secondPwd" @blur="isFirstPwd" v-show="isPwd" placeholder="请再次输入新密码" style="width: 350px;margin-top: 30px"></el-input>
                <div>
                    <el-button type="primary" v-show="isPwd" @click="change" style="margin-top: 20px">提交</el-button>
                </div>

            </el-dialog>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    import TeaCenter from "@/components/teacher/TeaCenter";
    export default {
        name: "TeaHeadMenu",
        components: {TeaCenter},
        data(){
            return{
                isCollapse:false,
                username:this.$store.state.username,
                teacher:{},

                ok:true,
                password:'',
                firstPwd:'',
                secondPwd:'',
                isPwd:false,
                dialogVisible:false,
                tea:{
                    username:this.$store.state.username,
                    password:''
                }
            }
        },
        mounted() {

        },
        methods:{
            toLogin(){
                this.$router.push('/login')
            },

            myselfInfo(){
                this.$axios.post('teacher/getTeacher',this.username).then(resp=>{
                    console.log(resp.data.data)
                    this.teacher = resp.data.data
                    this.$refs.TeaCenter.dialogVisible = true
                    this.$refs.TeaCenter.teacher = JSON.parse(JSON.stringify(this.teacher))
                })
            },


            //修改密码
            changePassword(){
                this.dialogVisible = true
            },

            passwordIsTrue(){
                this.$axios.get('teacher/passwordIsTrue/'+this.password).then(resp=>{
                    if(resp.data.code==200){
                        this.$message.success('密码校验成功！')
                        this.isPwd = true
                        this.ok = false
                    }else{
                        this.$message.error('密码校验失败！请重新输入')
                        this.password = ''
                    }
                })
            },

            //判断两次密码是否一致
            isFirstPwd(){
                if(this.firstPwd!=this.secondPwd && this.secondPwd!=''&&this.firstPwd!=''){
                    this.$message.warning('您两次输入的密码不一致！请重新输入!')
                    this.secondPwd = ''
                }
                if(this.firstPwd==this.secondPwd&& this.secondPwd!=''&&this.firstPwd!=''){
                    this.$message.success('密码输入正确！')
                }
            },

            change(){
                this.tea.password = this.secondPwd
                this.$axios.post('teacher/changePwd',this.tea).then(resp=>{
                    if(resp.data.code==200){
                        this.$message.success('修改密码成功！即将返回登录界面！')
                        setTimeout(()=>{
                            this.$router.replace('/login')
                        },2000)
                    }else{
                        this.$message.error('修改密码失败！')
                    }
                })
            }
        }
    }
</script>

<style scoped>
  .el-menu-item:hover{
        background-color: #EFF2F7 !important;
  }
</style>