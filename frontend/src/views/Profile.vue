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
                '내 유언장 보기'
              ]"
              :tab-icon="['perm_identity', 'vpn_key', 'chrome_reader_mode']"
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
                        <md-input v-model="name"></md-input>
                      </md-field>
                    </div>
                    <div class="col">
                      <md-field class="md-form-group" slot="inputs">
                        <md-icon>phone</md-icon>
                        <label>전화번호...</label>
                        <md-input v-model="email" type="phone"></md-input>
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
export default {
  components: {
    Tabs,
    MyRecord
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
      imageUrl: require("@/assets/img/guest.jpg")
    };
  },
  created() {
    const data = new FormData();
    data.append("uid", this.$cookies.get("UserInfo").uid);
    this.$axios.post(this.$SERVER_URL + "user/detail", data).then(res => {
      this.username = res.data.nickname;
      this.email = res.data.email;
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