import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import DefaultLayout from '../layouts/default/index';
import PageLayout from '../layouts/page/index';
import Login from '../views/Login.vue';
import Join from '../views/Join.vue';
import Board from '../views/Board.vue';
import BoardPage from '../views/BoardPage.vue';
<<<<<<< HEAD
import admin from '../views/admin.vue';
=======
import Sales from '../views/Sales.vue'
>>>>>>> dbc30df29d123636da08a3c2a6ca306420508bda

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
                path: '/board/:id',
                name: 'BoardPage',
                component: BoardPage,
                props: true,
            }
        ]
    },

    {
        path: '/',
        name: 'PageLayout',
        component: PageLayout,
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
            {
<<<<<<< HEAD
                path: '/admin',
                name: 'admin',
                component: admin
=======
                path: '/Sales',
                name: 'sales',
                component: Sales
>>>>>>> dbc30df29d123636da08a3c2a6ca306420508bda
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