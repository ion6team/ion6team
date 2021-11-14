/* eslint-disable no-unused-vars */
import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: { //data
    boards:[]
  },
  mutations: { 
    boardData(state,boards){
      state.boards=boards
    }
  },
  actions: { //method
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
