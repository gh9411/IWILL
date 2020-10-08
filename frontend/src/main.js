import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import VueCookie from "vue-cookies";
import MaterialKit from "./plugins/material-kit";
import vuetify from "./plugins/vuetify";

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
const SERVER_URL = "http://j3a104.p.ssafy.io/api/";
Vue.prototype.$SERVER_URL = SERVER_URL;
Vue.use(MaterialKit);

const NavbarStore = {
  showNavbar: false,
};
Vue.use(VueCookie);
Vue.mixin({
  data() {
    return {
      NavbarStore,
    };
  },
});
new Vue({
  router,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
