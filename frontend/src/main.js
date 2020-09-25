import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import VueCookie from "vue-cookies";
import MaterialKit from "./plugins/material-kit";
import vuetify from "./plugins/vuetify";

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
const SERVER_URL = "http://localhost:8080/";
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
<<<<<<< HEAD
  render: h => h(App)
=======
  render: (h) => h(App),
>>>>>>> 0ff7d8c68c64d66c7ef4f79a1e66535b5f5ca0c8
}).$mount("#app");
