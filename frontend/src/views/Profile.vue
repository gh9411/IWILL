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
              :tab-name="['비밀번호 변경', '내 유언장 보기']"
              :tab-icon="['vpn_key', 'chrome_reader_mode']"
              plain
              nav-pills-icons
              color-button="success"
              style="margin-bottom:200px;"
            >
              <!-- here you can add your content for tab-content -->
              <template slot="tab-pane-1" class="text-center">
                <div class="text-center">
                  <div class="col" style="width:600px;">
                    <div
                      class="col"
                      style="display:flex; justify-content:center;"
                    >
                      <md-field class="md-form-group" slot="inputs">
                        <md-icon>lock_outline</md-icon>
                        <label>현재 비밀번호를 적어주세요.</label>
                        <md-input type="password" v-model="password"></md-input>
                      </md-field>
                    </div>
                    <div
                      class="col"
                      style="display:flex; justify-content:center;"
                    >
                      <md-field class="md-form-group" slot="inputs">
                        <md-icon>lock_outline</md-icon>
                        <label>변경하실 비밀번호를 적어주세요.</label>
                        <md-input
                          type="password"
                          v-model="newpassword"
                        ></md-input>
                      </md-field>
                    </div>

                    <div
                      class="col"
                      style="display:flex; justify-content:center;"
                    >
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
              <template slot="tab-pane-2">
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
      explain: "한 줄 자기소개를 적어주세요",
      password: "",
      newpassword: "",
      newpasswordcheck: "",
      username: "",
      email: "",
      imageUrl: require("@/assets/img/guest.jpg"),
      tabPane1: [
        { image: require("@/assets/img/examples/studio-1.jpg") },
        { image: require("@/assets/img/examples/studio-2.jpg") },
        { image: require("@/assets/img/examples/studio-4.jpg") },
        { image: require("@/assets/img/examples/studio-5.jpg") }
      ],
      tabPane2: [
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/cynthia-del-rio.jpg") },
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/clem-onojegaw.jpg") }
      ],
      tabPane3: [
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/studio-3.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/studio-1.jpg") }
      ]
    };
  },
  created() {
    const data = new FormData();
    data.append("uid", this.$cookies.get("UserInfo").uid);
    this.$axios.post(this.$SERVER_URL + "user/detail2", data).then(res => {
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