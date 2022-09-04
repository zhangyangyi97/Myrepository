<template>
    <div>
        <el-dialog title="添加题目"
                   width="800px"
                   :visible.sync="dialogTableVisible">

            <div style="float: left;margin-bottom: 20px">
                <span style="font-size: 18px;">题目:</span>
                <el-input
                        style="width: 200px;margin-left: 16px"
                        v-model="params.title">
                    <i slot="suffix" class="el-input__icon el-icon-search"></i>
                </el-input>

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
                <el-button style="margin-left: 63px;height: 40px" @click="select" type="primary">查询</el-button>
                <el-button style="height: 40px" @click="resetAllSelect">重置</el-button>
            </div>
            <el-table
                    ref="multipleTable"
                    :data="allCourses.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                    tooltip-effect="dark"
                    style="width: 100%"
                    :row-key="getRowKeys"
                    border
                    @selection-change="handleSelectionChange"
            >
                <el-table-column
                        type="selection"
                        width="55"
                        :reserve-selection="true"
                >
                </el-table-column>

                <el-table-column
                        property="ctname"
                        label="学科"
                        width="60">
                </el-table-column>
                <el-table-column
                        property="coursename"
                        label="题型"
                        width="70">

                </el-table-column>
                <el-table-column
                        property="title"
                        label="题干"
                >

                </el-table-column>
                <el-table-column
                        property="point"
                        label="分值"
                        width="60"
                >

                </el-table-column>
            </el-table>
            <el-row style="margin: 25px auto;">
                <el-col>
                    <el-pagination
                            :page-size="pageSize"
                            :total="total"
                            :current-page="currentPage"
                            @current-change="pageChangeHandle"
                            style="width: 550px;"
                    >
                    </el-pagination>
                </el-col>
            </el-row>
            <div align="center">
                <span>已选中</span><span style="color: red">{{courseCount}}</span><span>题</span>
                <el-button type="primary"  @click="addCourses" style="margin-left: 40px">提交</el-button>
                <el-button @click="resetAll" style="margin-left: 20px">重置</el-button>
                <el-button type="danger" @click="closeDia" style="margin-left: 20px">关闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AddCourse",
        data(){
            return{
                params:{
                    courseId:"",
                    title:'',
                    courseType:0
                },

                dialogTableVisible:false,
                allCourses:[],
                pageSize: 4,
                currentPage: 1,
                total: 0,
                selectCourses: [],
                courseCount:0,
                point:0,

                courseType:0
            }
        },
        mounted() {
            this.total = this.allCourses.length
        },

        methods:{
            //添加题目
            addCourses(){
                if(this.selectCourses==''||this.selectCourses.length<4){
                    this.$message.warning('选择的题目少于四道！请重新挑选哦！')
                    return
                }
                this.$emit('sendCourseCount',this.courseCount)
                this.$emit('sendPoint',this.point)
                this.$emit('sendCourses',this.selectCourses)
                this.$emit('sendSuccess','true')
                this.$message.success('添加以上题目成功!')
                this.dialogTableVisible = false
            },
            pageChangeHandle (page){
                this.currentPage = page
            },


            //重置题目
            resetAll(){
                this.$refs.multipleTable.clearSelection();
            },

            //关闭dialog
            closeDia(){
                this.dialogTableVisible = false
            },

            handleSelectionChange(val) {
                this.selectCourses = val;
                this.courseCount = val.length
                for (let i = 0; i < val.length; i++) {
                    for (let j = i+1; j < val.length; j++) {
                        if(this.selectCourses[i]==this.selectCourses[j]){
                            this.selectCourses.splice(j,1)
                            val.length--;
                            j--;
                        }
                    }
                }
                this.point = 0
                for (let i = 0; i < this.selectCourses.length; i++) {
                    this.point += this.selectCourses[i].point
                }
            },




            //模糊查询
            select(){
                this.$axios.post('course/select',this.params).then(resp=>{
                    this.allCourses = resp.data.data
                    this.currentPage = 1
                    this.total = this.allCourses.length
                })
            },


            //重置
            resetAllSelect(){
                this.params.title=''
                this.params.courseId=''

            },


            //是分页多选不会失效
            getRowKeys(row){
                return row.uuid
            }
        }
    }
</script>

<style scoped>

</style>