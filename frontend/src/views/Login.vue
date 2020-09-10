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
                <i class="fab fa-twitter"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-google-plus-g"></i>
              </md-button>

            
              <!-- 데이터 입력 -->
              <md-field class="md-form-group" slot="inputs" style="margin-top: 3em">
                <md-icon>email</md-icon>
                <label>Email...</label>
                <md-input v-model="email" type="email"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Password...</label>
                <md-input v-model="password"></md-input>
              </md-field>
              <md-button slot="footer" class="md-simple md-lg" @click="gotoMain()">
                <span style="font-size: 1.4em" onMouseOver="this.style.color='#000000'" onMouseOut="this.style.color='grey'">
                  로그인
                </span>
              </md-button>
              
              <hr slot="hr" style="margin-right: 2em; margin-left: 2em; margin-top: 1.7em">
              
              <div slot="a" style="margin-left: 2em; margin-right: 2em;">
                <span style="font-size: 0.9em; font-weight:500">아직 회원이 아니라면</span>
                <a slot="a" href="#" style="font-size: 0.96em; font-weight:600; color: grey; float: right">가입하기</a>
              </div>
              <div slot="a" style="margin-left: 2em; margin-right: 2em; margin-bottom: .7em">
                <span style="font-size: 0.9em; font-weight:500;" >
                비밀번호를 잊으셨나요?</span>
                <a slot="a" href="#" style="font-size: 0.96em; font-weight:600; color: grey; float: right">비밀번호 찾기</a>
              </div>

            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";

export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data() {
    return {
      email: null,
      password: null
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  methods: {
    gotoMain() {
      this.$router.push("/main")
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
