<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="grey">
              <h2 slot="title" class="card-title">IWILL</h2>

              <!-- SNS 로그인 버튼 -->
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-google-plus-g"></i>
              </md-button>

              <!-- 데이터 입력 -->
              <md-field
                class="md-form-group"
                slot="inputs"
                style="margin-top: 3em"
              >
                <md-icon>email</md-icon>
                <label>Email...</label>
                <md-input v-model="email" type="email"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Password...</label>
                <md-input v-model="password" type="password"></md-input>
              </md-field>
              <md-button
                slot="footer"
                class="md-simple md-lg"
                style="height: 4.5em"
                @click="goToMain()"
              >
                <span
                  style="font-size: 1.4em"
                  onMouseOver="this.style.color='#000000'"
                  onMouseOut="this.style.color='grey'"
                >
                  로그인
                </span>
              </md-button>

              <hr
                slot="hrtag"
                style="margin-right: 2em; margin-left: 2em; margin-top: 1.7em; margin-bottom: 0.5em"
              />

              <div slot="atag" style="margin-left: 2em; margin-right: 2em;">
                <span style="font-size: 0.9em; font-weight:500"
                  >아직 회원이 아니라면</span
                >
                <a
                  slot="atag"
                  href="/signup"
                  style="font-size: 0.96em; font-weight:600; color: grey; float: right"
                  >가입하기</a
                >
              </div>
              <div
                slot="atag"
                style="margin-left: 2em; margin-right: 2em; margin-bottom: .7em"
              >
                <span style="font-size: 0.9em; font-weight:500;">
                  비밀번호를 잊으셨나요?</span
                >
                <a
                  slot="atag"
                  href="#"
                  style="font-size: 0.96em; font-weight:600; color: grey; float: right"
                  >비밀번호 찾기</a
                >
              </div>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LoginCard from "../components/cards/LoginCard.vue";

export default {
  name: "Login",
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data() {
    return {
      email: "",
      password: ""
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
    setCookie(UserInfo) {
      this.$cookies.set("UserInfo", UserInfo);
    },
    goToMain() {
      const loginData = new FormData();
      loginData.append("email", this.email);
      loginData.append("upw", this.password);
      this.$axios
        .post(this.$SERVER_URL + "user/login", loginData)
        .then(res => {
          const UserData = res.data;
          this.setCookie(UserData);
          this.$router.push("/index");
        })
        .then(err => {
          console.log(err);
        });
    },
    onLogin() {
      let { email, password } = this;
      let data = {
        email,
        password
      };
      store.dispatch("login", { email: this.email, password: this.password });
      if (this.$store.state.isLogin) {
        this.$router.push("/index");
      }
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style lang="css">
</style>