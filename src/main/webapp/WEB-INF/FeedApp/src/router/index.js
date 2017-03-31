import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '../view/login/index.vue'
import SignIn from '../view/login/sign_in.vue'
import SignUp from '../view/login/sign_up.vue'
import HomePage from '../view/home/index.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '',
      component: HomePage
    },
    {
      path: '/login',
      component: LoginPage,
      children: [
        {
          path: '',
          component: SignIn
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
