import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login";
import Home from "@/views/Home";
import FirstPage from "@/components/student/FirstPage";
import StuRegister from "@/components/register/StuRegister";
import TeaRegister from "@/components/register/TeaRegister";
import TeaHome from "@/views/TeaHome";
import TeaFirstPage from "@/components/teacher/TeaFirstPage";
import FalseCourse from "@/components/student/FalseCourse";
import ChoiceOne from "@/components/teacher/course/ChoiceOne";
import PaperInfo from "@/components/teacher/paper/PaperInfo";
import StudentMgr from "@/components/teacher/stuMgr/StudentMgr";
import CreatePaper from "@/components/teacher/paper/CreatePaper";
import CreateChoice from "@/components/teacher/course/CreateChoice";
import CreateExercise from "@/components/teacher/course/CreateExercise";
import CreateTrueOrFalse from "@/components/teacher/course/CreateTrueOrFalse";
import GradeMsg from "@/components/teacher/stuMgr/GradeMsg";
import TestRecords from "@/components/student/TestRecords";
import readOverPaper from "@/components/teacher/paper/readOverPaper";
import StudentPaperInfo from "@/views/StudentPaperInfo";
import PaperCenter from "@/components/student/PaperCenter";

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.replace
// 修改 原型对象中的push方法
VueRouter.prototype.replace = function push(location) {
    return originalPush.call(this, location).catch(err=>err)
}


const routes = [
    {
        name:'studentPaperInfo',
        component: StudentPaperInfo,
        path: '/home/studentPaperInfo'
    },
    {
      name:'login',
      component:Login,
      path:'/login'
    },
    {
      name:'login',
      component:Login,
      path:'/'
    },
    {
        name:'stuRegister',
        component: StuRegister,
        path: '/stuRegister'
    },
    {
        name:'teaRegister',
        component: TeaRegister,
        path: '/teaRegister'
    },

    {
        name: 'teaHome',
        component: TeaHome,
        path: '/teaHome',
        children: [
            {
                name:'gradeMsg',
                component: GradeMsg,
                path: '/teaHome/gradeMsg'
            },

            {
                name: 'teaFirstPage',
                component: TeaFirstPage,
                path: '/teaHome/teaFirstPage'
            },
            {
                name:'choice-one',
                component: ChoiceOne,
                path: '/teaHome/choice-one'
            },
            {
                name:'paperInfo',
                component: PaperInfo,
                path: '/teaHome/paperInfo'
            },
            {
                name:'studentMgr',
                component: StudentMgr,
                path: '/teaHome/studentMgr'
            },
            {
                name:'createPaper',
                component: CreatePaper,
                path: '/teaHome/createPaper'
            },
            {
                name:'createChoice',
                component: CreateChoice,
                path: '/teaHome/createChoice'
            },
            {
                name:'createExercise',
                component: CreateExercise,
                path: '/teaHome/createExercise'
            },
            {
                name: 'createTrueOrFalse',
                component: CreateTrueOrFalse,
                path: '/teaHome/createTrueOrFalse'
            },
            {
                name:'readOverPaper',
                component: readOverPaper,
                path: '/teaHome/readOverPaper'
            }
        ]
    },

    {
        name: 'home',
        component: Home,
        path: '/home',
        children:[
            {
                name:'fistPage',
                component:FirstPage,
                path:'/home/firstPage'
            },
            {
                name:'falseCourse',
                component: FalseCourse,
                path: '/home/falseCourse'
            },
            {
                name:'testRecords',
                component: TestRecords,
                path: '/home/testRecords'
            },
            {
                name:'paperCenter',
                component: PaperCenter,
                path: '/home/paperCenter'
            }

        ]
    },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
