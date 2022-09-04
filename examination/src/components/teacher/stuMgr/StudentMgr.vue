<template>
    <div align="center">

        <div style="float: left;margin-bottom: 20px;box-sizing: border-box">
            <span style="font-size: 16px;color: rgb(96,98,102);font-weight: 700">姓名:</span>
            <el-input
                    style="width: 200px;margin-left: 17px"
                    v-model="stu.username">
                <i slot="suffix" class="el-input__icon el-icon-search"></i>
            </el-input>

            <span style="font-size: 16px;margin-left: 38px;color: rgb(96,98,102);font-weight: 700">年级:</span>
            <el-select v-model="stu.gradeId" placeholder="请选择年级"
                       style="width: 200px;margin-left: 17px"
            >
                <el-option label="一年级" :value="1"></el-option>
                <el-option label="二年级" :value="2"></el-option>
                <el-option label="三年级" :value="3"></el-option>
                <el-option label="四年级" :value="4"></el-option>
                <el-option label="五年级" :value="5"></el-option>
                <el-option label="六年级" :value="6"></el-option>
                <el-option label="我的学生" :value="teacher.gradeId+''"></el-option>
            </el-select>

            <span style="font-size: 16px;margin-left: 38px;color: rgb(96,98,102);font-weight: 700">性别:</span>
            <el-select  placeholder="请选择性别"
                        v-model="stu.sex"
                       style="width: 200px;margin-left: 17px"

            >
                <el-option label="男" :value="1"></el-option>
                <el-option label="女" :value="2"></el-option>
            </el-select>
            <el-button style="margin-left: 38px;height: 40px"
                       type="primary"
                       @click="findStuByKeyword"
            >
                查询
            </el-button>
            <el-button style="height: 40px" @click="resetAll">重置</el-button>
        </div>


        <el-table
                :data="student.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                style="width: 100%"
        >
            <el-table-column
                    prop="sid"
                    label="学生编号"
                    width="90"
            >
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="姓名"
                    width="80"
            >
            </el-table-column>

            <el-table-column
                    prop="gradeName"
                    label="年级"
                    width="120"
            >
            </el-table-column>

            <el-table-column
                    prop="sex"
                    label="性别"
                    width="80"
            >
            </el-table-column>
            <el-table-column
                    prop="birthday"
                    label="出生日期"
            >
            </el-table-column>
            <el-table-column label="操作"
                             width="300"
            >
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleInfo(scope.$index, scope.row)">详情
                    </el-button>

                    <el-button
                            type="primary"
                            size="mini"

                            @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                    <el-button
                            size="mini"
                            type="success"
                            @click="handleWork(scope.$index, scope.row)">发布作业
                    </el-button>
                </template>
                <PaperHomeWork ref="PaperHomeWork"></PaperHomeWork>
                <StudentOperation ref="StudentOperation" @onSendSuccess="successOpera"></StudentOperation>
            </el-table-column>


        </el-table>



        <el-row style="margin: 25px auto;">
            <el-col>
                <el-button type="primary" round style="margin-bottom: 15px" @click="addStudent">添加</el-button>
                <el-pagination
                        :page-size="pageSize"
                        :total="total"
                        :current-page="currentPage"
                        @current-change="pageChangeHandle"
                        style="width: 400px;"
                >
                </el-pagination>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import StudentOperation from "@/components/teacher/stuMgr/StudentOperation";
    import PaperHomeWork from "@/components/teacher/stuMgr/PaperHomeWork";
    export default {
        name: "StudentMgr",
        components: {PaperHomeWork, StudentOperation},
        data(){
            return{
                student:[
                    {
                        sid:'',
                        username:'',
                        password:'',
                        sex:'',
                        birthday:'',
                        gradeName:''
                    }
                ],
                pageSize: 4,
                currentPage: 1,
                total: 0,

                studentName:'',
                gradeName:'',
                sex:'',

                stu:{
                    sex:'',
                    username:'',
                    gradeId:'',
                    birthday: '',
                    sid:'',
                },
                teacher:{
                    gradeId:'',
                    gradeName:''
                },

                params:{
                    courseType: '',
                    gradeId:'',
                    title:'',
                    courseId:'',
                    papername:''
                }

            }
        },

        mounted() {
            this.loadStudents()
            this.hasAuth()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },

        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },

        methods:{
            successOpera(){
                this.loadStudents()
            },

            loadStudents(){
                this.$axios.get('student/findStudents').then(resp=>{
                    this.student = resp.data.data
                    this.currentPage = 1
                    this.total = this.student.length
                })
            },
            pageChangeHandle (page){
                this.currentPage = page
            },

            //重置
            resetAll(){
                this.stu.sex=''
                this.stu.gradeId=''
                this.stu.username=''
            },

            //模糊查询
            findStuByKeyword(){
                this.$axios.post('student/findStuByKeyword',this.stu).then(resp=>{
                    this.student = resp.data.data
                    this.currentPage = 1
                    this.total = this.student.length
                })
            },

            //查看详情
            handleInfo(index,row){
                this.$refs.StudentOperation.dialogVisible = true
                this.$refs.StudentOperation.canEdit = true
                this.$refs.StudentOperation.student = JSON.parse(JSON.stringify(row))

            },

            //添加学生
            addStudent(){
                this.$refs.StudentOperation.dialogVisible = true
                this.$refs.StudentOperation.canEdit = false
            },

            //修改
            handleEdit(index, row) {
                if(row.gradeName!=this.teacher.gradeName){
                    this.$message.warning('您无法对其他年级学生进行修改操作!')
                }else{
                    this.$refs.StudentOperation.dialogVisible = true
                    this.$refs.StudentOperation.canEdit = false
                    this.$refs.StudentOperation.student = JSON.parse(JSON.stringify(row))
                }
            },

            //删除
            handleDelete(index, row) {
                if(row.gradeName!=this.teacher.gradeName){
                    this.$message.warning('您无法删除其他年的学生！')
                }else{
                    this.$confirm('确定删除此学生么?',"提示",{
                        confirmButtonText:'确定',
                        cancelButtonClass:'取消',
                        type:'warning'
                    }).then(()=>{
                        this.$axios.get('student/delStudent/'+row.sid).then(resp=>{
                            if(resp.data.code==200){
                                this.$message.success('删除'+row.username+'学生成功!')
                                this.loadStudents()
                            }
                        })
                    }).catch(()=>{
                        this.$message({
                            type:'info',
                            message:'已取消删除'
                        })
                    })
                }
            },

            //发布作业
            handleWork(index,row){
                if(row.gradeName!=this.teacher.gradeName){
                    this.$message.warning('您无法向其他年级学生发布作业！')
                }else{
                    this.$axios.post('paper/select',this.params).then(resp=>{
                        console.log(resp.data.data)
                        this.$refs.PaperHomeWork.allPapers = resp.data.data
                        this.$refs.PaperHomeWork.total = resp.data.data.length
                        this.$refs.PaperHomeWork.pageSize = 4
                        this.$refs.PaperHomeWork.currentPage = 1
                        this.$refs.PaperHomeWork.student = row
                    })
                    this.$refs.PaperHomeWork.dialogTableVisible = true
                }
            },




            //只能对自己年级的学生发布试卷执行操作，其他年级智能查看详情
            hasAuth(){
                this.$axios.post('teacher/getTeacher',this.$store.state.username).then(resp=>{
                    console.log(resp.data.data)
                    this.teacher = resp.data.data
                    this.params.gradeId = this.teacher.gradeId
                })
            }


        }
    }
</script>

<style scoped>

</style>