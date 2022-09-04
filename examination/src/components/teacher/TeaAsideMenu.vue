<template>
    <div class="home">
        <div class="manage_page fillcontain">
            <el-row style="height: 100%;">
                <el-col  style="min-height: 100%;background-color: #324157;overflow: hidden;">
                    <el-menu
                            router
                            :default-active="$route.name"
                            class="el-menu-vertical-demo"
                            @open="handleOpen"
                            @close="handleClose"
                            background-color="#324157"
                            text-color="#bfcbd9"
                            :unique-opened="true"
                            active-text-color="#049eff"
                    >
                        <el-menu-item id="head" style="height: 50px;line-height: 50px">
                            <div align="center">
                                <span style="color: white;font-weight: bold">学生管理-教师端</span>
                            </div>
                        </el-menu-item>
                        <el-menu-item index="/teaHome/teaFirstPage">
                            <i class="el-icon-s-home"></i>
                            <span slot="title" style="color: #ffffff">主页</span>
                        </el-menu-item>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-s-data"></i>
                                <span slot="title" style="color: #ffffff">年级管理</span>
                            </template>
                            <el-menu-item index="/teaHome/gradeMsg">
                                <span slot="title">年级列表</span>
                            </el-menu-item>
                        </el-submenu>

                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-s-custom"></i>
                                <span slot="title" style="color: #ffffff">学生管理</span>
                            </template>
                            <el-menu-item index="/teaHome/studentMgr">
                                <span slot="title">学生列表</span>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="4">
                            <template slot="title">
                                <i class="el-icon-document-copy"></i>
                                <span slot="title" style="color: #ffffff">试卷管理</span>
                            </template>
                            <el-menu-item index="/teaHome/paperInfo">
                                <span slot="title">试卷列表</span>
                            </el-menu-item>
                            <el-menu-item index="/teaHome/createPaper">
                                <span slot="title">试卷创编</span>
                            </el-menu-item>
                            <el-menu-item index="/teaHome/readOverPaper">
                                <span slot="title">试卷批阅</span>
                            </el-menu-item>
                        </el-submenu>

                        <el-submenu index="5">
                            <template slot="title">
                                <i class="el-icon-edit-outline"></i>
                                <span slot="title" style="color: #ffffff">题库管理</span>
                            </template>
                            <el-menu-item index="/teaHome/choice-one">
                                <span slot="title">题目总览</span>
                            </el-menu-item>
                            <el-menu-item index="/teaHome/createChoice">
                                <span slot="title">选择题创编</span>
                            </el-menu-item>
                            <el-menu-item index="/teaHome/createExercise">
                                <span slot="title">填空题创编</span>
                            </el-menu-item>
                            <el-menu-item index="/teaHome/createTrueOrFalse">
                                <span slot="title">判断题创编</span>
                            </el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: "TeaAsideMenu",
        props:{
          collapse:''
        },
        data(){
            return{

            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        },
    }
</script>

<style scoped>
    .home, .fillcontain {
        height: 100vh;

    }
    .el-menu {
        border: 0;
    }
    .el-col {
        background-color: #EFF2F7;
        height: 100%;
        overflow: auto;
    }
    .el-menu-item:hover{
        background-color: #333333 !important;
    }
    #head{
        background-color: black !important;
    }
</style>