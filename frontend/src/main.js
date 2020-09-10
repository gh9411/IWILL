import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import MaterialKit from "./plugins/material-kit";

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
const SERVER_URL = "http://localhost:8080/";
Vue.prototype.$SERVER_URL = SERVER_URL;
Vue.use(MaterialKit);

const NavbarStore = {
  showNavbar: false
};

Vue.mixin({
  data() {
    return {
      NavbarStore
    };
  }
});

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
