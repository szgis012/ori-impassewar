import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/LoginView.vue'),
    meta: { title: '登录' },
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/RegisterView.vue'),
    meta: { title: '注册' },
  },
  {
    path: '/game',
    name: 'Game',
    component: () => import('@/views/GameView.vue'),
    meta: { title: '游戏', requiresAuth: true },
  },
  {
    path: '/city',
    name: 'City',
    component: () => import('@/views/CityView.vue'),
    meta: { title: '城市', requiresAuth: true },
  },
  {
    path: '/world',
    name: 'World',
    component: () => import('@/views/WorldView.vue'),
    meta: { title: '世界地图', requiresAuth: true },
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // 设置页面标题
  document.title = `${to.meta.title || '绝地战争'} - 现代化重制版`

  // 检查是否需要登录
  if (to.meta.requiresAuth) {
    const token = localStorage.getItem('token')
    if (!token) {
      next('/login')
      return
    }
  }

  next()
})

export default router
