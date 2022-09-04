<template>
    <div >
        <div id="box">
            <div align="center" style="box-sizing: border-box">
                <el-card background-color="#fff" style="width: 210px;height: 130px">
                    <div id="gradeNum" style="width: 70px;height: 70px;border-radius: 6px;margin: 10px 72px 0 0;cursor: pointer">
                        <i class="el-icon-s-data"  style="font-size: 64px;color: rgb(205,241,240)"></i>
                    </div>
                    <div style="float: right;width:80px;margin-top: -80px">
                        <p>年级总数</p>
                        <span>{{gradeNum}}</span>
                    </div>
                </el-card>
                <el-card background-color="#fff" style="width: 210px;height: 130px">
                    <div id="studentNum" style="width: 70px;height: 70px;border-radius: 6px;margin: 10px 72px 0 0;cursor: pointer">
                        <i class="el-icon-s-custom"  style="font-size: 64px;color: rgb(54,163,247)"></i>
                    </div>
                    <div style="float: right;width:80px;margin-top: -80px">
                        <p>学生总数</p>
                        <span>{{studentNum}}</span>
                    </div>
                </el-card>
                <el-card background-color="#fff" style="width: 210px;height: 130px">
                    <div id="paperNum" style="width: 70px;height: 70px;border-radius: 6px;margin: 10px 72px 0 0;cursor: pointer">
                        <i class="el-icon-document-copy"  style="font-size: 64px;color: rgb(52,191,163)"></i>
                    </div>
                    <div style="float: right;width:80px;margin-top: -80px">
                        <p>试卷总数</p>
                        <span>{{paperNum}}</span>
                    </div>
                </el-card>
                <el-card background-color="#fff" style="width: 210px;height: 130px">
                    <div id="courseNum" style="width: 70px;height: 70px;border-radius: 6px;margin: 10px 72px 0 0;cursor: pointer">
                        <i class="el-icon-edit-outline"  style="font-size: 64px;color: rgb(184, 51, 73)"></i>
                    </div>
                    <div style="float: right;width:80px;margin-top: -80px">
                        <p>题目总数</p>
                        <span>{{courseNum}}</span>
                    </div>
                </el-card>
            </div>
            <e-charts class="chart"  :option="option"/>

        </div>
    </div>

</template>

<script>
    export default {
        name: "TeaFirstPage",
        data(){
            return{
                gradeNum:'',        //年纪总数
                studentNum:'',      //学生总人数
                paperNum:'',        //试卷总数
                courseNum:'',       //题目总数
                stuNums:[]
            }
        },
        computed:{
            option(){
                return {
                    title: {
                        text: '年级人数分布',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    series: [
                        {
                            name: '总人数',
                            type: 'pie',
                            radius: '50%',
                            data: [
                                { value: this.stuNums[0], name: '1年级' },
                                { value: this.stuNums[1], name: '2年级' },
                                { value: this.stuNums[2], name: '3年级' },
                                { value: this.stuNums[3], name: '4年级' },
                                { value: this.stuNums[4], name: '5年级' },
                                { value: this.stuNums[5], name: '6年级' }
                            ],
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                }
            }
        },

        mounted() {
            this.getGradeNum()
            this.getCourseNum()
            this.getPaperNum()
            this.getStudentNum()
            this.getStuNums()
        },

        //方法
        methods:{
            getGradeNum(){
                this.$axios.get('grade/findGradeNum').then(resp=>{
                    console.log(resp.data.data)
                    this.gradeNum = resp.data.data
                })
            },

            getStudentNum(){
                this.$axios.get('student/findStudentNum').then(resp=>{

                    this.studentNum = resp.data.data
                })
            },

            getPaperNum(){
                this.$axios.get('paper/findPaperNum').then(resp=>{
                    this.paperNum = resp.data.data
                })
            },

            getCourseNum(){
                this.$axios.get('course/findCourseNum').then(resp=>{
                    this.courseNum = resp.data.data
                })
            },

            getStuNums(){
                this.$axios.get('student/stuNums').then(resp=>{
                    console.log(resp.data.data)
                    this.stuNums = resp.data.data
                })
            },







        },
    }
</script>

<style scoped>
    .chart{
        width: 400px;
        height: 300px;
        position: absolute;
        left: 300px;
        top: 200px;
    }
    .el-card{
        margin-left: 40px;
        float: left;
    }
    #box{
        position: relative;
    }

    #studentNum:hover{
        background-color: rgb(54,163,247);
    }
    .el-icon-s-custom:hover{
        color: white !important;
    }

    #paperNum:hover{
        background-color: rgb(52,191,163);
    }
    .el-icon-document-copy:hover{
        color: white !important;
    }

    #courseNum:hover{
        background-color: rgb(184, 51, 73);
    }
    .el-icon-edit-outline:hover{
        color: white !important;
    }

    #gradeNum:hover{
        background-color: rgb(205,241,240);
    }
    .el-icon-s-data:hover{
        color: white !important;
    }

</style>