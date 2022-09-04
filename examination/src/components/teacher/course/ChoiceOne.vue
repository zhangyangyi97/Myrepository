<template>
    <div id="outside"  style="box-sizing: border-box">
        <div style="float: left;margin-bottom: 20px">
            <span style="font-size: 18px;">题目:</span>
            <el-input
                    style="width: 200px;margin-left: 16px"
                    v-model="params.title">
                <i slot="suffix" class="el-input__icon el-icon-search"></i>
            </el-input>

            <span style="font-size: 18px;margin-left: 30px">学科:</span>
            <el-select v-model="params.courseType" placeholder="请选择学科"
                    style="width: 200px;margin-left: 16px"
            >
                <el-option label="语文" :value="1"></el-option>
                <el-option label="数学" :value="2"></el-option>
                <el-option label="英语" :value="3"></el-option>
                <el-option label="地理" :value="4"></el-option>
                <el-option label="生物" :value="5"></el-option>
                <el-option label="科学" :value="6"></el-option>
            </el-select>

            <span style="font-size: 18px;margin-left: 30px">题型:</span>
            <el-select v-model="params.courseId" placeholder="请选择题型"
                       style="width: 200px;margin-left: 16px"
            >
                <el-option label="单选题" :value="1"></el-option>
                <el-option label="多选题" :value="2"></el-option>
                <el-option label="判断题" :value="3"></el-option>
                <el-option label="填空题" :value="4"></el-option>
                <el-option label="简答题" :value="5"></el-option>
            </el-select>
            <el-button style="margin-left: 30px;height: 40px" @click="select" type="primary">查询</el-button>
            <el-button style="height: 40px" @click="resetAll">重置</el-button>
        </div>

        <el-table
                :data="allCourses.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                style="width: 100%"
        >
            <el-table-column
                    type="index"
                    :index="indexMethod"
                    label="编号"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="ctname"
                    label="学科"
                    width="60">
            </el-table-column>
            <el-table-column
                    prop="coursename"
                    label="题型"
                    width="100"
            >
            </el-table-column>
            <el-table-column
                    prop="title"
                    label="题干">

            </el-table-column>
            <el-table-column
                    prop="point"
                    label="分数"
                    width="65"
            >
            </el-table-column>
            <el-table-column
                    prop="diff"
                    label="难度"
                    width="65"
            >
            </el-table-column>
            <el-table-column label="操作"
                    width="220"
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
                </template>
            </el-table-column>

            <CourseOperation ref="CourseOperation" @onsubmit="this.loadCourses"></CourseOperation>
        </el-table>

        <el-row style="margin: 25px auto;">
            <el-col>
                <el-pagination
                        :page-size="pageSize"
                        :total="total"
                        :current-page="currentPage"
                        @current-change="pageChangeHandle"
                        style="width: 650px;"
                >
                </el-pagination>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import CourseOperation from "@/components/teacher/course/CourseOperation";
    export default {
        name: "ChoiceOne",
        components: {CourseOperation},
        data(){
            return{
                params:{
                    courseType:'',
                    title:'',
                    courseId:'',
                },

                course:{},
                pageSize: 4,
                currentPage: 1,
                total: 0,
                allCourses:[
                    {
                        ctname:'',
                        coursename:'',
                        title:'',
                        point:'',
                        diff:''
                    }
                ],

            }
        },

        mounted() {
            this.loadCourses()
        },

        //在生命周期中，创建之前，给body赋值让其背景色为你想设置的颜色
        beforeCreate () {
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')
        },
        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },

        methods:{
            //重置
            resetAll(){
                this.params.courseId = ''
                this.params.courseType=''
                this.params.title=''
            },

            //题目的模糊查询
            select(){
               this.$axios.post('course/select',this.params).then(resp=>{
                   this.allCourses = resp.data.data
                   this.currentPage = 1
                   this.total = this.allCourses.length
               })
            },


            //题目编号的排序
            indexMethod(index) {
                return (this.currentPage-1)*(this.pageSize)+index+1 ;
            },

            loadCourses(){
                this.$axios.post('course/findAllCourses').then(resp=>{
                    console.log(resp.data.data)
                    this.allCourses = resp.data.data
                    this.currentPage = 1
                    this.total = this.allCourses.length
                })
            },

            pageChangeHandle (page){
                this.currentPage = page
            },

            //查看详情
            handleInfo(index,row){
                this.$refs.CourseOperation.dialogVisible = true
                this.$refs.CourseOperation.canEdit = true
                this.$refs.CourseOperation.course = JSON.parse(JSON.stringify(row))
                if(row.courseId=='1'||row.courseId=='2'){
                    this.$refs.CourseOperation.choice = true
                }
            },

            //修改
            handleEdit(index, row) {
                this.$refs.CourseOperation.dialogVisible = true
                this.$refs.CourseOperation.canEdit = false
                this.$refs.CourseOperation.course = JSON.parse(JSON.stringify(row))
                if(row.courseId=='1'||row.courseId=='2'){
                    this.$refs.CourseOperation.choice = true
                }
            },

            //删除
            handleDelete(index, row) {
                    this.$confirm('确定删除此题目么?',"提示",{
                        confirmButtonText:'确定',
                        cancelButtonClass:'取消',
                        type:'warning'
                    }).then(()=>{
                        this.$axios.post('course/delCourse',row).then(resp=>{
                            if(resp.data.code==200){
                                this.$message.success('删除该题目成功!')
                                this.loadCourses()
                            }
                        })
                    }).catch(()=>{
                        this.$message({
                            type:'info',
                            message:'已取消删除'
                        })
                    })
            },
        }
    }
</script>

<style scoped>
    #outside{
        width: 1000px;
        box-sizing: border-box;
    }
    .el-card{
        margin:0 0 20px 60px;
        width: 260px;
        height: 175px;
        float: left;
    }
</style>