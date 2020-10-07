<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="maintab ">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-150 mx-auto">
              <div class="profile">
                <div class="avatar">
                  <input
                    type="file"
                    ref="imageInput"
                    hidden
                    @change="onChangeImages"
                  />
                  <img
                    :src="imageUrl"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid"
                    @click="onClickImageUpload"
                  />
                </div>
                <div class="name">
                  <h3 class="title">{{ this.username }}</h3>
                  <h6>{{ this.email }}</h6>
                </div>
              </div>
            </div>
          </div>
          <div class="description text-center col" style="width:450px;">
            <h4>Who am I?</h4>
            <md-field>
              <md-input class="text-center" v-model="explain"></md-input>
            </md-field>
          </div>
          <div class="profile-tabs">
            <tabs
              :tab-name="[
                '개인정보 추가/변경',
                '비밀번호 변경',
                '내 유언장 보기',
                '받은 유언장 보기'
              ]"
              :tab-icon="[
                'perm_identity',
                'vpn_key',
                'chrome_reader_mode',
                'fact_check'
              ]"
              plain
              nav-pills-icons
              color-button="success"
              style="margin-bottom:200px;"
            >
              <!-- here you can add your content for tab-content -->
              <template slot="tab-pane-1">
                <div class="text-center" style="margin-bottom:200px;">
                  <div class="col" style="margin-left:250px; width:600px;">
                    <div class="col">
                      <md-field class="md-form-group" slot="inputs">
                        <md-icon>face</md-icon>
                        <label>이름...</label>
                        <md-input v-model="username"></md-input>
                      </md-field>
                    </div>
                    <div class="col">
                      <md-field class="md-form-group" slot="inputs">
                        <md-icon>phone</md-icon>
                        <label>전화번호...</label>
                        <md-input v-model="phone" type="phone"></md-input>
                      </md-field>
                    </div>
                    <div class="col">
                      <md-field class="md-form-group">
                        <md-icon>vpn_key</md-icon>
                        <label>키 값확인</label>
                        <md-input v-model="hash" disabled></md-input>
                      </md-field>
                    </div>
                    <div class="col">
                      <md-button
                        slot="footer"
                        class="md-simple md-success md-lg"
                        @click="submitInfo"
                      >
                        추가 및 변경하기
                      </md-button>
                    </div>
                  </div>
                </div>
              </template>
              <template slot="tab-pane-2" class="text-center">
                <div class="text-center">
                  <div class="col" style="margin-left:250px; width:600px;">
                    <div class="col">
                      <md-field class="md-form-group" slot="inputs">
                        <md-icon>lock_outline</md-icon>
                        <label>현재 비밀번호를 적어주세요.</label>
                        <md-input type="password" v-model="password"></md-input>
                      </md-field>
                    </div>
                    <div class="col">
                      <md-field class="md-form-group" slot="inputs">
                        <md-icon>lock_outline</md-icon>
                        <label>변경하실 비밀번호를 적어주세요.</label>
                        <md-input
                          type="password"
                          v-model="newpassword"
                        ></md-input>
                      </md-field>
                    </div>

                    <div class="col">
                      <md-field class="md-form-group" slot="inputs">
                        <md-icon>lock_outline</md-icon>
                        <label>적으신 비밀번호를 확인해주세요.</label>
                        <md-input
                          type="password"
                          v-model="newpasswordcheck"
                        ></md-input>
                      </md-field>
                    </div>
                    <div class="col">
                      <md-button
                        slot="footer"
                        class="md-simple md-success md-lg"
                        @click="changepwd"
                      >
                        변경하기
                      </md-button>
                    </div>
                  </div>
                </div>
              </template>
              <template slot="tab-pane-3">
                <div class="text-center" style="margin-bottom:200px;">
                  <div class="col" style="width:600px;">
                    <MyRecord style="margin-bottom:100px;" />
                  </div>
                </div>
              </template>
              <template slot="tab-pane-4">
                <div
                  class="text-center"
                  style="margin-bottom:200px; width:600px;"
                >
                  <div class="col" style="width:600px;">
                    <ReceivedWill style="margin-bottom:100px;" />
                  </div>
                </div>
              </template>
            </tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Tabs } from "@/components";
import MyRecord from "../components/MainPage/MyRecord";
import ReceivedWill from "../components/MainPage/ReceivedWill";
export default {
  components: {
    Tabs,
    MyRecord,
    ReceivedWill
  },
  bodyClass: "profile-page",
  data() {
    return {
      hash: "키 값 확인",
      explain: "한 줄 자기소개를 적어주세요",
      password: "",
      newpassword: "",
      newpasswordcheck: "",
      username: "",
      email: "",
      phone: "",
      imageUrl: require("@/assets/img/jg.jpg")
    };
  },
  created() {
    if (this.$cookies.get("UserInfo") == null) {
      alert("로그인이 필요합니다.");
      this.$router.push({ name: "login" });
      location.reload();
    }
    if (this.$cookies.get("UserInfo") == null) {
      this.logstate = "LogIn";
    } else {
      this.logstate = "LogOut";
    }
    const data = new FormData();
    data.append("email", this.$cookies.get("UserInfo").email);
    this.$axios.post(this.$SERVER_URL + "user/detail", data).then(res => {
      this.email = res.data.email;
      this.username = res.data.name;
      this.hash = res.data.accounthash;
      this.imgurl = res.data.profile;
    });
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg")
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
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {
      const file = e.target.files[0];
      if (!file.type.match("image/.*")) {
        alert("이미지만 업로드 가능합니다");
      } else {
        this.imageUrl = URL.createObjectURL(file);
        console.log(this.imageUrl);
      }
    },
    submitInfo() {
      const data = new FormData();
      data.append("uid", this.$cookies.get("UserInfo").uid);
      data.append("upw", this.$cookies.get("UserInfo").upw);
      data.append("accounthash", this.$cookies.get("UserInfo").accounthash);
      data.append("email", this.$cookies.get("UserInfo").email);
      data.append("name", this.username);
      data.append("phone", this.phone);
      data.append("createdate", this.$cookies.get("UserInfo").createdate);
      data.append("senddate", this.$cookies.get("UserInfo").senddate);
      data.append("profile", this.imageUrl);
      data.append("usertype", this.$cookies.get("UserInfo").usertype);
      this.$axios
        .post(this.$SERVER_URL + "user/update", data)
        .then(res => {
          this.$cookies.remove("UserInfo");
          this.$cookies.set("UserInfo", res.data);
          location.reload();
        })
        .catch(err => {
          console.log(err);
        });
    },
    changepwd() {
      if (
        this.password.length == 0 ||
        this.newpassword.length == 0 ||
        this.newpasswordcheck.length == 0
      ) {
        alert("빈 칸이 존재합니다. 채워서 다시 변경해주세요.");
        return;
      } else if (this.password != this.$cookies.get("UserInfo").upw) {
        alert("현재 비밀번호를 잘못 적으셨습니다. 확인해주세요");
        return;
      } else if (this.newpassword != this.newpasswordcheck) {
        alert("새 비밀번호와 비밀번호 확인이 다릅니다. 적어주세요");
        return;
      } else {
        const data = new FormData();
        data.append("uid", this.$cookies.get("UserInfo").uid);
        data.append("upw", this.newpassword);
        data.append("email", this.$cookies.get("UserInfo").email);
        data.append("accounthash", this.$cookies.get("UserInfo").accounthash);
        data.append("name", this.$cookies.get("UserInfo").name);
        data.append("phone", this.$cookies.get("UserInfo").phone);
        data.append("createdate", this.$cookies.get("UserInfo").createdate);
        data.append("senddate", this.$cookies.get("UserInfo").senddate);
        data.append("profile", this.$cookies.get("UserInfo").profile);
        data.append("usertype", this.$cookies.get("UserInfo").usertype);
        this.$axios
          .post(this.$SERVER_URL + "user/updatepw", data)
          .then(res => {
            console.log(res);
            this.$cookies.remove("UserInfo");
            this.$cookies.set("UserInfo", res.data);
            location.reload();
          })
          .then(err => {
            console.log(err);
          });
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>