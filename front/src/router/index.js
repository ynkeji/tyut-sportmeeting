import {createRouter, createWebHistory} from "vue-router"

const routes = [

    {
        path:'/',
        name:'index',
        component:()=>import('../view/login/login.vue')

    },
    {
        path: '/admin',
        name: 'admin',
        meta: {
            title: '运动会管理系统'
        },
        component: () => import('../view/admin/admin.vue'),
        children: [
            {
                path: '/admin-index',
                name: 'admin-index',
                component: () => import('../view/admin/index.vue')
            },
            {
                path: '/admin-search',
                name: 'admin-search',
                component: () => import('../view/admin/SearchRegistration.vue')
            },
            {
                path: '/admin-MemberSearch',
                name: 'admin-MemberSearch',
                component: () => import('../view/admin/MemberSearch.vue')
            },
            {
                path: '/admin-GameSet',
                name: 'admin-GameSet',
                component: () => import('../view/admin/GameSet.vue')
            },
            {
                path: '/admin-ScoreFindeByOne',
                name: 'admin-ScoreFindeByOne',
                component: () => import('../view/admin/scoreByOne.vue')
            },
            {
                path: '/admin-ScoreFindBySum',
                name: 'admin-ScoreFindBySum',
                component: () => import('../view/admin/scoreSum.vue')
            },
            {
                path: '/admin-DataView',
                name: 'admin-DataView',
                component: () => import('../view/admin/DataView.vue')
            }
        ]

    },
    {
        path: '/login',
        name: 'login',
        meta: {
            title: '用户登录'
        },
        component: () => import('../view/login/login.vue')
    },


]


const router = createRouter({
    history: createWebHistory(),
    routes,
});
router.beforeEach((to, from, next) => {
    if (to.path === '/login') {
        next();
    } else {
        let token = localStorage.getItem('token');
        if (token === null || token === '') {
            next('/login');
        } else {
            next();
        }
    }
});

export default router;
