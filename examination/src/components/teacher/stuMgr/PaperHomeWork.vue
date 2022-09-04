<template>
    <div>
        <el-dialog title="发布作业"
                   width="800px"
                   :visible.sync="dialogTableVisible"
                   :append-to-body="true"
        >

            <div style="float: left;margin-bottom: 20px">
                <span style="font-size: 18px;">试卷名称:</span>
                <el-input
                        style="width: 185px;margin-left: 16px"
                        v-model="params.papername">
                    <i slot="suffix" class="el-input__icon el-icon-search"></i>
                </el-input>

                <span style="font-size: 18px;margin-left: 25px">学科类型:</span>
                <el-select v-model="params.courseType" placeholder="请选择学科"
                           style="width: 185px;margin-left: 16px"
                >
                    <el-option label="语文" :value="1"></el-option>
                    <el-option label="数学" :value="2"></el-option>
                    <el-option label="英语" :value="3"></el-option>
                    <el-option label="地理" :value="4"></el-option>
                    <el-option label="生物" :value="5"></el-option>
                    <el-option label="科学" :value="6"></el-option>
                </el-select>
                <el-button style="margin-left: 20px;height: 40px" @click="select" type="primary">查询</el-button>
                <el-button style="height: 40px" @click="resetAllSelect">重置</el-button>
            </div>
            <el-table
                    ref="multipleTable"
                    :data="allPapers.slice((currentPage-1)*pageSize,currentPage*pageSize)"
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
                        width="60"
                >
                </el-table-column>
                <el-table-column
                        property="gradename"
                        label="年级"
                        width="70">

                </el-table-column>
                <el-table-column
                        property="papername"
                        label="试卷名称"
                >

                </el-table-column>
                <el-table-column
                        property="firstInit"
                        label="创建时间"
                        width="220"
                >

                </el-table-column>
                <el-table-column
                        property="coursecount"
                        label="题目数量"
                        width="60"
                >

                </el-table-column>
                <el-table-column
                        property="mark"
                        label="总分数"
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
                <span>已选中</span><span style="color: red">{{paperCount}}</span><span>套试卷</span>
                <el-button type="primary"  @click="addPaper" style="margin-left: 40px">提交</el-button>
                <el-button @click="resetAll" style="margin-left: 20px">重置</el-button>
                <el-button type="danger" @click="closeDia" style="margin-left: 20px">关闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "PaperHomeWork",
        data(){
            return{
                params:{
                    gradeId:'',
                    papername:''
                },
                dialogTableVisible:false,
                allPapers:[],
                paperCount: 0,


                student:{},
                selectPapers:[],
                pageSize: 0,
                currentPage: 0,
                total: 0,

            }
        },

        methods:{
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

            //重置
            resetAllSelect(){
                this.params.papername=''
                this.params.courseType=''

            },


            //是分页多选不会失效
            getRowKeys(row){
                return row.id
            },

            handleSelectionChange(val) {
                this.paperCount = val.length
                this.selectPapers = val
            },

            //模糊查询
            select(){
                this.params.gradeId = this.student.gradeId
                this.$axios.post('paper/select',this.params).then(resp=>{
                    this.allPapers = resp.data.data
                    this.currentPage = 1
                    this.total = this.allPapers.length
                })
            },

            addPaper(){
                for (let i = 0; i < this.selectPapers.length; i++) {
                    this.selectPapers[i].sid = this.student.sid
                    this.selectPapers[i].paperstatus = 0
                }
                this.$axios.post('student/addHomework',this.selectPapers).then(resp=>{
                    if(resp.data.code==200){
                        this.$message.success('发布作业成功！')
                        this.dialogTableVisible = false
                    }else{
                        this.$message.error('发布作业失败！')
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>