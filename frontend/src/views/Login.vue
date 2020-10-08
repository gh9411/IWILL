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

              <!-- 데이터 입력 -->
              <md-field
                class="md-form-group"
                slot="inputs"
                style="margin-top: 3em"
              >
                <md-icon>email</md-icon>
                <label class="ml-3 text-warning text-sm" v-if="!valid.email"
                  >이메일 형식과 다릅니다.</label
                >
                <md-input
                  placeholder="Email"
                  alternative
                  type="email"
                  :valid="valid.email"
                  v-model="model.email"
                  @keydown.enter="goToMain"
                >
                </md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label class="ml-3 text-warning text-sm" v-if="!valid.password"
                  >8글자 이상이어야 합니다.</label
                >
                <md-input
                  placeholder="Password"
                  alternative
                  type="password"
                  :valid="valid.password"
                  v-model="model.password"
                  @keydown.enter="goToMain"
                >
                </md-input>
              </md-field>
              <md-button
                :disabled="!isPossible"
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

              <div
                slot="atag"
                style="margin-left: 2em; margin-right: 2em; margin-bottom: 1em;"
              >
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
      model: {
        email: "",
        password: ""
      },
      error: {
        email: false,
        password: false
      },
      isSubmit: false,
      isPossible: false,
      valid: {
        email: false,
        password: false
      }
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  watch: {
    model: {
      deep: true,
      handler() {
        this.validCheck(this.model);
      }
    }
  },
  created() {
    if (this.$cookies.get("UserInfo") != null) {
      alert("이미 로그인 되어있습니다.");
      this.$router.push({ name: "profile" });
    }
    if (this.$cookies.get("UserInfo") == null) {
      this.logstate = "LogIn";
    } else {
      this.logstate = "LogOut";
    }
  },
  methods: {
    setCookie(UserInfo) {
      this.$cookies.set("UserInfo", UserInfo);
    },
    goToMain() {
      const loginData = new FormData();
      loginData.append("email", this.model.email);
      loginData.append("password", this.model.password);
      this.$axios
        .post(this.$SERVER_URL + "user/login", loginData)
        .then(res => {
          const UserData = res.data;
          this.setCookie(UserData);
          this.$router.push("/index");
          location.reload();
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
    },
    validCheck(model) {
      if (/^\w+([.-]?\w+)@\w+([.-]?\w+)*(.\w{2,3})+$/.test(model.email)) {
        this.valid.email = true;
      } else this.valid.email = false;

      if (model.password.length > 7) {
        this.valid.password = true;
      } else this.valid.password = false;

      if (this.valid.email && this.valid.password) {
        this.isPossible = true;
      } else this.valid.isPossible = false;
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