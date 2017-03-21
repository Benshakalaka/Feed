import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '../view/login/index.vue'
import SignIn from '../view/login/sign_in.vue'
import SignUp from '../view/login/sign_up.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: LoginPage,
      children: [
        {
          path: 'sign-in',
          component: SignIn
        },
        {
          path: 'sign-up',
          component: SignUp
        }
      ]
    }
  ]
})
