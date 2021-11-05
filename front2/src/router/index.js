import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import DefaultLayout from '../layouts/default/index'
import PageLayout from '../layouts/page/index'
import Login from '../views/Login.vue'
import Join from '../views/Join.vue'
import Board from '../views/Board.vue'

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name:'DefaultLayout',
    component: DefaultLayout,
    children:[
      {
        path: '/',
        name: 'Home',
        component: Home
      },
      {
        path: '/board',
        name: 'Board',
        component: Board
      },
    ]
  },

  {
    path:'/',
    name:'PageLayout',
    component: PageLayout,
    children:[
      {
        path:'/login',
        name:'login',
        component: Login
      },
      {
        path:'/join',
        name:'join',
        component: Join
      }
    ]
  }
 
 
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
