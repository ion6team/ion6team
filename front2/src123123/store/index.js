// import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';
import axios from 'axios'
// import router from '../router';

// import router from 'vue-router'
// import modules from './modules';

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    loginSuccess: false,
    loginError: false,
    loginfailed: false,
    member: {
      id: null,
      name: null,
      email: null,
      address: null,
      address_detail: null
    },
    checkcheck: 10000,
  },
  mutations: {
    loginSuccess(state, member) {
      state.loginSuccess = true;
      state.member = member;
    },
    logout(state) {
      state.loginSuccess = false;
    },
    loginError(state) {
      state.loginError = true;
    },
    loginfailed(state){
      state.loginfailed=true;
    }
  },
  actions: {
    async login({
      commit
    }, {
      email,
      password
    }) {
      try {
        const result = await axios.post('/api/login', {
          username: email,
          password: password,

        });
        console.log(result.data)
        if (result.data.email == email) {
          commit('loginSuccess', result.data)

        }else{
          commit('loginfailed')
        }
      } catch (err) {
        commit('loginError', {
          userName: email
        });
        throw new Error(err)
      }
    }
  },
  // getters: {
  //   isLoggedIn: state => state.loginSuccess,
  //   hasLoginErrored: state => state.loginError,
  //   getUserName: state => state.userName,
  //   getUserPass: state => state.userPass
  // },
  // modules: {
  // }
})
