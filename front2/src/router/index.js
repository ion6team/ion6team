import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../components/Login.vue';
import Join from '../views/Join.vue';
import Header from '../components/Header.vue';
import Footer from '../components/Footer.vue';

// import store from '../store'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/',
    name: 'Header',
    component: Header
  },
  {
    path: '/',
    name: 'Footer',
    component: Footer
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  // otherwise redirect to home
  { path: '*', redirect: '/' }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

// router.beforeEach((to, from, next) => {
//   if (to.matched.some(record => record.meta.requiresAuth)) {
//     if (!store.getters.isLoggedIn) {
//       next({
//         name: 'Login'
//       })
//     } else {
//       next();
//     }
//   } else {
//     next();
//   }
// });

export default router
