import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SignUpView from "@/views/SignUpView.vue";
import LoginView from "@/views/loginView.vue";
import ItemReadView from '@/views/ItemReadView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/signUp',
      name: 'signUp',
      component: SignUpView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/item/:itemId',
      name: 'item',
      component: ItemReadView,
      props: true
    }
  ],
})

export default router
