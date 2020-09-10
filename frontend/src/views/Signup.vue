<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="grey">
              <h3 slot="title" class="card-title">Sign Up</h3>
              <p slot="description" class="description">정보를 입력해주세요</p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>이름...</label>
                <md-input v-model="name"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>이메일...</label>
                <md-input v-model="email" type="email"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>비밀번호...</label>
                <md-input v-model="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>비밀번호확인...</label>
                <md-input
                  v-model="passwordcheck"
                  @keydown.enter="Signup()"
                ></md-input>
              </md-field>
              <md-button
                slot="footer"
                class="md-simple md-success md-lg"
                @click="Signup()"
              >
                가입하기
              </md-button>
              
            </login-card>
          </div>
          <modal
              v-if="this.modals.equalpassword"
              gradient="danger"
              class="text-center"
            >
          <template slot="header">
              <div class="py-3 text-center mb-0">
                <h4 class="text-danger">경고!</h4>
              </div>
            </template>
            <template slot="body">
              <h4 class="text-success mb-3">
                  비밀번호가 일치하지 않습니다! 다시 적어주세요!
                </h4>
            </template>
            <template slot="footer">
              <div class="text-center">
              <md-button
                  size="sm"
                  type="secondary"
                  @click="modals.equalpassword = false"
                  >닫기</md-button
                >
              </div>
            </template>
          </modal>

          <modal v-if="this.modals.empty" class="text-center">
            <template slot="header">
              <div class="py-3 text-center mb-0">
                <h4 class="text-danger">경고!</h4>
              </div>
            </template>
            <template slot="body">
              <h4 class="text-success mb-3">
                  빈 칸이 존재합니다. 모두 채워주세요!
                </h4>
            </template>
            <template slot="footer">
              <div class="text-center">
              <md-button
                  size="sm"
                  class="md-simple md-default"
                  @click="modals.empty=false"
                  >닫기</md-button
                >
              </div>
            </template>
          </modal>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { Modal } from "@/components";

export default {
  components: {
    LoginCard,
    Modal
  },
  bodyClass: "login-page",
  data() {
    return {
      name: "",
      email: "",
      password: "",
      passwordcheck: "", 
      isPossible: false,
      modals: {
        empty: false,
        equalpassword: false,
      },
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  methods: {
    Signup() {
      const SignData = new FormData();
      SignData.append("name", this.name);
      SignData.append("email", this.email);
      SignData.append("password", this.password);
      if (
        this.password == this.passwordcheck &&
        this.password != "" &&
        this.passwordcheck != ""
      ) {
        this.$axios
          .post(this.$SERVER_URL + "user/signUp", SignData)
          .then(res => {
            this.$router.push({ name: "login" });
          })
          .catch(err => {
            console.log(err)
          })
      } else if (
        this.name == "" ||
        this.email == "" ||
        this.password == "" ||
        this.passwordcheck == ""
      ) {
        this.modals.empty = true;
      } else {
        (this.modals.equalpassword = true),
          (this.password = ""),
          (this.passwordcheck = "");
      }
    }
  }
};
</script>

<style lang="css"></style>
