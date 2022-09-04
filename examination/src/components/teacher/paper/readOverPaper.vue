<template>
    <div>
        <el-table
                :data="studentList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                style="width: 100%">
            <el-table-column
                    prop="username"
                    label="学生姓名"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="papername"
                    label="试卷名称"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="score"
                    label="成绩"
                    width="75"
            >
            </el-table-column>
            <el-table-column
                    prop="finishtime"
                    label="耗时"
                    width="75"
            >
            </el-table-column>
            <el-table-column
                    prop="endTime"
                    label="提交时间"
                    width="250"
            >
            </el-table-column>
            <el-table-column
                    prop="truecount"
                    label="正确题数"
                    width="100"
            >
            </el-table-column>
            <el-table-column
                    prop="falsecount"
                    label="错误题数"
                    width="100"
            >
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            type="primary"
                            size="mini"
                            @click="handleInfo(scope.$index, scope.row)">详情</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "readOverPaper",
        data(){
            return{
                studentList:[],
                pageSize: 4,
                currentPage: 1,
                total: 0,

                gradeId:0
            }
        },

        mounted() {
            this.getStudentList()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },

        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },


        methods:{
            getStudentList(){
                this.$axios.post('teacher/getTeacher',this.$store.state.username).then(resp=>{
                    this.teacher = resp.data.data
                    this.gradeId = this.teacher.gradeId
                    this.$axios.get('result/findMyGradeResults/'+this.gradeId).then(resp=>{
                        console.log(resp.data.data)
                        this.studentList = resp.data.data
                        this.currentPage = 1
                        this.total = this.studentList.length
                    })
                    console.log(this.studentList)
                })

            },
            handleInfo(row,idnex){

            }
        }
    }
</script>

<style scoped>

</style>