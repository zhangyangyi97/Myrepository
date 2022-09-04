<template>
    <div>
        <el-dialog
            width="700px"
            :visible.sync="dialogPaperVisible"
        >
            <el-card>
                <div slot="header" align="center" style="box-sizing: border-box">
                    <p style="font-size: 30px;font-weight: 700;color: black">{{paper.papername}}</p>
                    <span style="font-size: 18px">试卷总分: {{paper.mark}}分</span>
                    <span style="font-size: 18px;margin-left: 50px">考试时间: {{paper.time}}分钟</span>
                </div>
                <div  v-for="(c,index) in this.choiceOne">
                    <span style="font-weight: 700;">{{index+1}}.</span><span>{{c.title}}</span><span style="margin-left: 50px">({{c.point}}分)</span><br>
                    <el-radio-group>
                        <el-radio :label="'A'">{{c.answerA}}</el-radio>
                        <br>
                        <el-radio :label="'B'">{{c.answerB}}</el-radio>
                        <br>
                        <el-radio :label="'C'">{{c.answerC}}</el-radio>
                        <br>
                        <el-radio :label="'D'">{{c.answerD}}</el-radio>
                        <br>
                    </el-radio-group>
                    <p>标准答案: <span style="color: red;font-size: 18px">{{c.answer}}</span> </p>
                </div>

                <div v-for="(cm,index) in this.choiceMore">
                    <span>{{choiceOne.length+index+1}}.</span><span>{{cm.title}}</span><span style="margin-left: 50px">({{cm.point}}分)</span><br>
                    <el-checkbox-group v-model="cm.answer" disabled>
                        <el-checkbox label="A">{{cm.answerA}}</el-checkbox>
                        <br>
                        <el-checkbox label="B">{{cm.answerB}}</el-checkbox>
                        <br>
                        <el-checkbox label="C">{{cm.answerC}}</el-checkbox>
                        <br>
                        <el-checkbox label="D">{{cm.answerD}}</el-checkbox>
                        <br>
                    </el-checkbox-group>
                    <p>标准答案: <span style="color: red;font-size: 18px">{{cm.answer}}</span></p>
                </div>

                <div v-for="(t,index) in this.trueorfalse">
                    <span>{{choiceOne.length+choiceMore.length+index+1}}.</span><span>{{t.title}}</span><span style="margin-left: 50px">({{t.point}}分)</span><br>
                    <el-radio-group >
                        <el-radio label="对"></el-radio>
                        <el-radio label="错"></el-radio>
                    </el-radio-group>
                    <p>标准答案: <span style="color: red;font-size: 18px">{{t.answer}}</span></p>
                </div>

                <div v-for="(e,index) in this.exercise">
                    <span>{{choiceOne.length+choiceMore.length+trueorfalse.length+index+1}}.</span><span>{{e.title}}</span><span style="margin-left: 50px">({{e.point}}分)</span><br>
                    <el-input
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4}"
                            placeholder="请输入答案"
                    >
                    </el-input>
                    <p>标准答案: <span style="color: red;font-size: 18px">{{e.answer}}</span>   </p>
                </div>

                <div v-for="(em,index) in this.exerciseMore">
                    <span>{{choiceOne.length+choiceMore.length+trueorfalse.length+exercise.length+index+1}}.</span><span>{{em.title}}</span><span style="margin-left: 50px">({{em.point}}分)</span><br>
                    <el-input
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4}"
                            placeholder="请输入答案"
                    >
                    </el-input>
                    <p>学生自由发挥哦！！！</p>
                </div>

            </el-card>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "ShowPaperCourses",

        data(){
            return{
                dialogPaperVisible:false,
                courses:[],
                paper:{},
                choiceMore:[],

                choiceOne:[],
                trueorfalse:[],
                exercise: [],
                exerciseMore:[]
            }
        },

        methods:{

        }
    }
</script>

<style scoped>

</style>