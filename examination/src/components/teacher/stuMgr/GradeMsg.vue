<template>
    <div>
        <el-table
                :data="gradeMsgData"
                border
                style="width: 100%">
            <el-table-column
                    prop="gradeName"
                    label="年级"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="gradeNum"
                    label="年级总人数"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="manNum"
                    label="男生人数">
            </el-table-column>
            <el-table-column
                    prop="womenNum"
                    label="男生人数">
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
        name: "GradeMsg",
        data(){
            return{
                gradeMsgData:[]
            }
        },

        mounted() {
            this.getGradeMsgData()
            document.querySelector('body').setAttribute('style', 'background-color:rgb(247,247,247)')

        },


        //在结束之前，删除该样式，即可
        beforeDestroy () {
            document.querySelector('body').removeAttribute('style')
        },

        methods:{
            getGradeMsgData(){
                this.$axios.post('grade/getGradeMsg').then(resp=>{
                    this.gradeMsgData = resp.data.data
                })
            },

            handleInfo(){

            }
        }
    }
</script>

<style scoped>

</style>