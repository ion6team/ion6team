import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: { //data
    boards:[],
    token:'',
    islogin: '',
    loginfailed: false,
    member:[],
    closeModal:false,
  },
  mutations: { 
    boardData(state,boards){
      state.boards=boards
    },
    getToken(state, token){
      state.token=token,
      state.islogin=true
    },
    rmToken(state){
      state.token='',
      state.islogin=false
    },
    getMemberInfo(state, member){
      state.member=member
    },
    showModal(state){
      return (state.closeModal=true)
    }
  },
  actions: { //method
    async login({
      commit
    }, {
      email,
      password
    }) {
      try {
        const result = await axios.post('/api/login', {
          email: email,
          password: password,

        });
        console.log(result.data)
        commit('getToken', result.data.token)
        axios.get('/api/member',{
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ result.data.token
          }
        })
        .then(response=>{
          commit('getMemberInfo', response.data)
        })
        alert("로그인 성공")
      } catch (err) {
        alert("로그인 실패")
      }
    },
    logout({commit}){
      commit('rmToken')
    },

    getBoardData({commit})
      {
        axios.get('http://localhost:8080/api/board')
        .then(res=>res.data)
        
        
        //.then('set_boards',boards)
        
      }
    },
  modules: {
  }
})
