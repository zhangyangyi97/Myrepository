<template>
    <div>
        <el-menu
                mode="horizontal"
                background-color="#fff"
                text-color="black"
                active-text-color="#409EFF"
                default-active="/home/firstPage"
                router
        >
            <div style="float:left;margin-top: 4px">
                <img src="../../assets/logo.jpg" width="180px" height="50px"  alt="">
            </div>

            <el-menu-item index="/home/firstPage" style="margin-left: 140px">
                            首页
            </el-menu-item>

            <el-menu-item index="/home/paperCenter">
                试卷中心
            </el-menu-item>

            <el-menu-item index="/home/testRecords">
                考试记录
            </el-menu-item>

            <el-menu-item index="/home/falseCourse">
                错题本
            </el-menu-item>

            <div style="float: right;cursor: pointer;margin: 17px 55px 0 0">
                <el-dropdown>
                    <span class="el-dropdown-link">
                        <i class="el-icon-user" style="margin-right: 5px"></i>
                        <span>{{username}}</span>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                            <span @click="centerInfo">个人中心</span>
                        </el-dropdown-item>
                        <el-dropdown-item>
                            <span @click="toLogin">退出登录</span>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>

            <StuCenter ref="StuCenter"></StuCenter>

        </el-menu>
    </div>
</template>

<script>
    import StuCenter from "@/components/menu/StuCenter";
    export default {
        name: "TopMenu",
        components: {StuCenter},
        data(){
            return{
                username:this.$store.state.username,
            }
        },

        methods:{
            toLogin(){
                this.$router.replace('/login')
            },

            centerInfo(){
                this.$axios.get('student/selectStudent/'+this.username).then(resp=>{
                    this.$refs.StuCenter.student = resp.data.data
                })
                this.$refs.StuCenter.dialogVisible = true
            }
        }

    }
</script>

<style scoped>
    /deep/.el-menu-item:hover{
        background-color: #EFF2F7 !important;
        color: skyblue !important;
    }
</style>