import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import DefaultLayout from '../layouts/default/index';
import PageLayout from '../layouts/page/index';
import NoneLayout from '../layouts/none/index';
import Login from '../views/Login.vue';
import Join from '../views/Join.vue';
import Board from '../views/Board.vue';
import BoardPage from '../views/BoardPage.vue';
import Sales from '../views/Sales.vue'
import admin from '../views/admin.vue';
import Write from '../views/Write.vue';
import MyPage from '../views/MyPage.vue';
import ReWrite from '../views/ReWrite.vue';
import MyInfo from '../components/MyPage/MyInfo.vue'
import Change from '../components/MyPage/Change.vue'
import Zzim from '../components/MyPage/Zzim.vue'


Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'DefaultLayout',
        component: DefaultLayout,
        children: [{
                path: '/',
                name: 'Home',
                component: Home
            },
            {
                path: '/board',
                name: 'Board',
                component: Board,
            },
            {
                path: '/write',
                name: 'Write',
                component: Write,
            },
            {
                path: '/ReWrite',
                name: 'ReWrite',
                component: ReWrite,
                props: true,
            },

            {
                path: '/board/:id',
                name: 'BoardPage',
                component: BoardPage,
                props: true,
            },
        ]
    },

    {
        path: '/',
        name: 'PageLayout',
        component: PageLayout,
        children: [
            {
                path: '/admin',
                name: 'admin',
                component: admin
            },
            {
                path: '/MyPage',
                name: 'myPage',
                component: MyPage,         
            },
            {
                path: '/Sales',
                name: 'sales',
                component: Sales
            }
        ]
    },

    {
        path: '/',
        name: 'NoneLayout',
        component: NoneLayout,
        children: [{
                path: '/login',
                name: 'login',
                component: Login
            },
            {
                path: '/join',
                name: 'join',
                component: Join
            },
            
        ]
    },



]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router