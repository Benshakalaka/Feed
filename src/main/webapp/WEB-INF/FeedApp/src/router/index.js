import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '../view/login/index.vue'
import SignIn from '../view/login/sign_in.vue'
import SignUp from '../view/login/sign_up.vue'
import IndexPage from '../view/index/index.vue'
import MainContent from '../view/index/main_content.vue'
import UserPage from '../view/user/index.vue'
import DicoverPage from '../view/discover/index.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: IndexPage,
      children: [
        {
          path: '',
          redirect: 'index'
        },
        {
          path: 'index',
          name: 'main',
          component: MainContent
        },
        {
          path: 'user/:username/',
          component: UserPage,
          name: 'userhome',
          children: [
            {
              path: '',
              redirect: 'profile'
            },
            {
              path: 'profile',
              name: 'userprofiles'
            },
            {
              path: 'fans',
              name: 'userfans'
            },
            {
              path: 'follows',
              name: 'userfollows'
            }
          ]
        },
        {
          path: 'discover',
          name: 'discover',
          component: DicoverPage
        }
      ]
    },
    {
      path: '/login',
      component: LoginPage,
      children: [
        {
          path: '',
          redirect: 'sign-in'
        },
        {
          path: 'sign-in',
          component: SignIn,
          name: 'signin'
        },
        {
          path: 'sign-up',
          component: SignUp,
          name: 'signup'
        }
      ]
    }
  ]
})
