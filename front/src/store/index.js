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
    ctindex:0,

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
    setctindex(state, ctindex){
      state.ctindex=ctindex
    },
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
      } catch (err) {
        
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
        
      },
      
    },
  modules: {
  }
})
