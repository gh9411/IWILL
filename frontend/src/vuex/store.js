import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo: {},
    isLogin: false,
  },
  getters: {

  },
  mutations: {

  },
  actions: {
    login(context, { email, password }) {
      http.post(`user/login?email=${email}&password=${password}`)
        .then(res => {
          context.commit('IsLogin', true)
          context.commit('UserInfo', res)
          alert("로그인 성공");
        })
        .catch(err => {
          alert("아이디 또는 비밀번호 실패입니다.");
          router.push("/errorPage");
        })
    }
  }
})