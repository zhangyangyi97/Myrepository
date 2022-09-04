import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import VueAxios from "vue-axios";
import ElementUI from 'element-ui';
import ECharts from 'vue-echarts';
import 'echarts';
import 'element-ui/lib/theme-chalk/index.css';

axios.defaults.baseURL="http://localhost:8080/"



//配置前端拦截器（发送请求时需要携带token）
axios.interceptors.request.use(config=>{


  //不拦截登录请求，是不需要携带token的  登录注册都是不需要携带凭证的
  if(config.url == '/teacher/login' || config.url=='/teacher/register'||config.url=='/student/login'||config.url=='/student/register'){
      return config
  }
  //在请求的头部配置在属性Authorization  以及值token
  config.headers.Authorization = store.state.token
  return config
})

//配置全局的守卫拦截  一般用来做一些进入页面的限制
router.beforeEach(((to, from, next) => {
  if(to.fullPath=='/login' || to.fullPath=='/' || to.fullPath=='/stuRegister' || to.fullPath=='/teaRegister'){
    next()
  }else{
    if(store.state.username){

      if(store.state.roleName=='学生'){
        axios.get('auth/authentication').then(resp=>{
          if(resp.data.code==200){
            if(to.fullPath.includes('tea')){

              ElementUI.Message({
                message: '您无权访问!请重新登录！',
                type: 'error'
              },1000);
              setTimeout(()=>{
                router.replace('/login')
              },2000)

            }else{
              next()
            }
          }else {
            next('/login')
          }
        })
      }else{
        //防止token过期，重新请求获得一次token
        axios.get('auth/authentication').then(resp=>{
          if(resp.data.code==200){
            next()
          }else {
            next('/login')
          }
        })
      }
    }else{
      next('/login')
    }
  }
}))

Vue.component('ECharts',ECharts);
Vue.use(VueAxios,axios)
Vue.use(ElementUI)
Vue.prototype.$axios=axios
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
