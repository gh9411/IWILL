<template>
  <div class="wrapper">
    <!--상단 배경 및 안내 문구 -->
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <img :src="leaf4" alt="leaf4" class="leaf4" v-show="leafShow" />
            <img :src="leaf3" alt="leaf3" class="leaf3" v-show="leafShow" />
            <img :src="leaf2" alt="leaf2" class="leaf2" v-show="leafShow" />
            <img :src="leaf1" alt="leaf1" class="leaf1" v-show="leafShow" />
            <div class="brand">
              <h1 class="title">IWILL</h1>
              <h3>남아있는 소중한 사람들에게 전하고 싶은 말</h3>
            </div>
          </div>
        </div>
      </div>
    </parallax>

    <!-- 메인 페이지 헤더 -->
    <div class="main" style="height:1300px;">
      <div class="headerblank"></div>
      <div class="header">
        <div class="header-left"></div>
        <div>
          <v-tabs class="maintap">
            <v-tab @click="toggle('MyRecord')">글 남기기</v-tab>
            <v-tab @click="toggle('WriteWill')">내 기록</v-tab>
            <v-tab @click="toggle('receivedWill')">받은 유언장</v-tab>
          </v-tabs>
        </div>
      </div>

      <!-- content 시작 -->
      <div class="content">
        <div class="content-left">
          <div class="mainImg text-center">
            <img :src="imgurl" alt="Circle Image" class="rounded-circle" />
          </div>
          <div class="underImg">
            <h4>{{ this.username }}</h4>
            <md-button class="md-block" href="/profile"
              ><span style="font-size: 1.3em; height: 1em;"
                >프로필 페이지</span
              ></md-button
            >
          </div>
        </div>

        <div class="content-right">
          <div class="menu">
            <div v-if="categories[2].display">
              <ReceivedWill />
            </div>
            <div v-if="categories[1].display">
              <MyRecord />
            </div>
            <div v-if="categories[0].display">
              <WriteWill />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../assets/scss/custom.scss";

import MyRecord from "../components/MainPage/MyRecord";
import WriteWill from "../components/MainPage/WriteWill";
import ReceivedWill from "../components/MainPage/ReceivedWill";

export default {
  components: {
    MyRecord,
    WriteWill,
    ReceivedWill
  },
  name: "mainpage",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/vue-mk-header.jpg")
    },
    leaf4: {
      type: String,
      default: require("@/assets/img/leaf4.png")
    },
    leaf3: {
      type: String,
      default: require("@/assets/img/leaf3.png")
    },
    leaf2: {
      type: String,
      default: require("@/assets/img/leaf2.png")
    },
    leaf1: {
      type: String,
      default: require("@/assets/img/leaf1.png")
    },
    signup: {
      type: String,
      default: require("@/assets/img/city.jpg")
    },
    landing: {
      type: String,
      default: require("@/assets/img/landing.jpg")
    },
    profile: {
      type: String,
      default: require("@/assets/img/profile.jpg")
    }
  },
  data() {
    return {
      firstname: null,
      email: null,
      username: "",
      password: null,
      leafShow: false,
      imgurl: "",
      categories: [
        { display: true, name: "MyRecord" },
        { display: false, name: "WriteWill" },
        { display: false, name: "receivedWill" }
      ]
    };
  },
  created() {
    if (this.$cookies.get("UserInfo") == null) {
      alert("로그인이 필요합니다.");
      this.$router.push({ name: "login" });
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
      this.imgurl = res.data.profile;
    });
  },
  methods: {
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
      }
    },
    toggle(text) {
      for (var i = 0; i < this.categories.length; i++) {
        if (text == this.categories[i].name) {
          this.categories[i].display = true;
        } else {
          this.categories[i].display = false;
        }
      }
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`
      };
    }
  },
  mounted() {
    this.leafActive();
    window.addEventListener("resize", this.leafActive);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.leafActive);
  }
};
</script>
<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
</style>