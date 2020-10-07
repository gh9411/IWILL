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

                <md-input
                  placeholder="이름..."
                  alternative
                  type="name"
                  :valid="valid.name"
                  v-model="model.name"
                  @keydown.enter="Signup()"
                >
                </md-input>
              </md-field>
              <md-field class="md-form-group row" slot="inputs">
                <md-icon>email</md-icon>
                <label class="ml-3 text-warning text-sm" v-if="!valid.email"
                  >이메일 형식과 다릅니다.</label
                >
                <md-input
                  placeholder="Email..."
                  alternative
                  type="email"
                  :valid="valid.email"
                  v-model="model.email"
                  @keydown.enter="Signup()"
                >
                </md-input>
                <md-button
                  size="sm"
                  class=" md-just-icon md-round"
                  @click="emailcheck"
                  ><md-icon>done</md-icon></md-button
                >
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label class="ml-3 text-warning text-sm" v-if="!valid.password"
                  >8글자 이상이어야 합니다.</label
                >
                <md-input
                  placeholder="Password..."
                  alternative
                  type="password"
                  :valid="valid.password"
                  v-model="model.password"
                  @keydown.enter="Signup()"
                >
                </md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label
                  class="ml-3 text-warning text-sm"
                  v-if="!valid.passwordconfirm"
                  >비밀번호가 일치하지 않습니다.</label
                >
                <md-input
                  placeholder="Confirm..."
                  alternative
                  type="password"
                  :valid="valid.passwordconfirm"
                  v-model="model.passwordconfirm"
                  @keydown.enter="Signup()"
                ></md-input>
              </md-field>
              <md-button
                :disabled="!isPossible"
                slot="footer"
                class="md-simple md-success md-lg"
                @click="Signup()"
              >
                가입하기
              </md-button>
            </login-card>
          </div>

          <modal v-if="this.valid.duple" class="text-center">
            <template slot="header">
              <div class="py-3 text-center mb-0">
                <h4 class="text-danger">경고!</h4>
              </div>
            </template>
            <template slot="body">
              <h4 class="text-success mb-3">
                이메일 중복체크가 되지 않았습니다!
              </h4>
            </template>
            <template slot="footer">
              <div class="text-center">
                <md-button
                  size="sm"
                  class="md-simple md-default"
                  @click="valid.duple = false"
                  >닫기</md-button
                >
              </div>
            </template>
          </modal>

          <modal v-if="this.valid.emailcheck" class="text-center">
            <template slot="header">
              <div class="py-3 text-center mb-0">
                <h4 class="text-danger">성공!</h4>
              </div>
            </template>
            <template slot="body">
              <h4 class="text-success mb-3">
                중복된 이메일이 없습니다!
              </h4>
            </template>
            <template slot="footer">
              <div class="text-center">
                <md-button
                  size="sm"
                  class="md-simple md-default"
                  @click="valid.emailcheck = false"
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
      duplemail: false,
      password: "",
      passwordcheck: "",
      phone: "",
      createdate: "",
      usertype: 0,
      imageUrl: require("@/assets/img/guest.jpg"),

      isPossible: false,
      model: {
        email: "",
        name: "",
        password: "",
        passwordconfirm: ""
      },
      error: {
        email: false,
        name: false,
        password: false,
        passwordconfirm: false
      },
      valid: {
        email: false,
        emailcheck: false,
        name: false,
        duple: false,
        password: false,
        passwordconfirm: false
      }
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
  watch: {
    model: {
      deep: true,
      handler() {
        this.validCheck(this.model);
      }
    }
  },
  methods: {
    emailcheck() {
      const data = new FormData();
      data.append("email", this.model.email);
      if (this.model.email == "") {
        alert("이메일을 적어주세요");
        return;
      }
      this.$axios.post(this.$SERVER_URL + "user/detail", data).then(res => {
        if ((this.model.email = res.data.email)) {
          alert("기존에 존재하는 이메일입니다.");
          this.model.email = "";
        } else {
          this.valid.emailcheck = true;
          this.valid.duple = true;
          this.model.email = data.email;
        }
      });
    },
    Signup() {
      const today = new Date();
      const year = today.getFullYear();
      const month = today.getMonth() + 1;
      const date = today.getDate();
      let hour = today.getHours();
      let minute = today.getMinutes();
      this.createdate = `${year}.${month}.${date}-${hour}.${minute}`;
      const senddate = null;
      const SignData = new FormData();
      SignData.append("name", this.model.name);
      SignData.append("email", this.model.email);
      SignData.append("upw", this.model.password);
      SignData.append("createdate", this.createdate);
      SignData.append("profile", this.imageUrl);
      SignData.append("senddate", senddate);
      SignData.append("phone", this.phone);
      SignData.append("usertype", this.usertype);

      this.$axios
        .post(this.$SERVER_URL + "user/signup", SignData)
        .then(res => {
          this.$router.push({ name: "login" });
          location.reload();
        })
        .catch(err => {
          console.log(err);
        });
    },

    validCheck(model) {
      if (/^\w+([.-]?\w+)@\w+([.-]?\w+)*(.\w{2,3})+$/.test(model.email)) {
        this.valid.email = true;
      } else this.valid.email = false;

      if (model.password.length > 7) {
        this.valid.password = true;
      } else this.valid.password = false;

      if (model.passwordconfirm == model.password) {
        this.valid.passwordconfirm = true;
      } else this.valid.passwordconfirm = false;

      if (
        this.valid.email &&
        this.valid.password &&
        this.valid.passwordconfirm &&
        this.valid.emailcheck &&
        this.valid.duple
      ) {
        this.isPossible = true;
      } else this.valid.isPossible = false;
    }
  }
};
</script>

<style lang="css"></style>'