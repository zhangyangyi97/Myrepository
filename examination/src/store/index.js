import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
        //存数据 USERNAME TOKEN 菜单      
        username:undefined || window.localStorage.getItem('username'),    
        token:undefined || window.localStorage.getItem('token'),
        roleName:undefined || window.localStorage.getItem('roleName')   //角色
  },
  getters: {
  },
  mutations: {
    login(state,loginVo){
        console.log(loginVo.username) 
        state.username = loginVo.username;      
        state.token = loginVo.token
        state.roleName = loginVo.roleName
        //存入浏览器本地缓存      
        window.localStorage.setItem('username',loginVo.username);      
        window.localStorage.setItem('token',loginVo.token);  
        window.localStorage.setItem('roleName',loginVo.roleName)
      },
  },
  actions: {
  },
  modules: {
  }
})
