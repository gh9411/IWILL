<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <img :src="leaf4" alt="leaf4" class="leaf4" v-show="leafShow" />
            <img :src="leaf3" alt="leaf3" class="leaf3" v-show="leafShow" />
            <img :src="leaf2" alt="leaf2" class="leaf2" v-show="leafShow" />
            <img :src="leaf1" alt="leaft1" class="leaf1" v-show="leafShow" />
            <div class="brand">
              <h1 class="title">IWILL</h1>
              <h3>남아있는 소중한 사람들에게 전하고 싶은 말</h3>
            </div>
          </div>
        </div>
      </div>
    </parallax>
    <div class="maintab" style="background-color: white;">
      <div class="section section-basic;">
        <div class="container" style="margin-bottom:500px;">
          <div>
            <p style="font-size:medium; font-weight:bold; color:green;">
              Who we are
            </p>
          </div>
          <div
            class="row"
            style="float:right; margin-top:0px; position:relative; left:-50%;"
          >
            <div
              class="title"
              style="width:50%; float:left; margin-top:0px; position:relative; left:50%;"
            >
              <h2 class="title" style="margin-top:0px;">IWILL이란?</h2>
              <h3 class="title">남겨진 이들을 위한 유언장 작성 플랫폼</h3>
              <h4>블록체인 기술을 이용해 작성한 글, 사진, 동영상을</h4>
              <h4>위변조 걱정 없이 보관해드립니다.</h4>
            </div>
            <div
              class="title"
              style="width:50%; float:left; margin-top:0px; position:relative; left:55%;"
            >
              <a href="/main" target="_blank" style="margin-top:0px;">
                <img
                  :src="service"
                  alt="Rounded Image"
                  class="img-raised img-fluid"
                />
              </a>
            </div>
          </div>
        </div>
        <div class="container">
          <div>
            <p style="font-size:medium; font-weight:bold; color:green;">
              Our Technology
            </p>
          </div>
          <div class="row" style="float:right; position:relative; left:-50%;">
            <div
              class="title"
              style="width:50%; margin-top:0px; float:left; position:relative; left:50%;"
            >
              <h2 class="title" style="margin-top:0px; width:250px;">
                왜 블록체인을 사용하나요?
              </h2>
            </div>
            <div
              class="title"
              style="width:50%; float:left; margin-top:0px; position:relative; left:55%;"
            >
              <h4 style=" margin:0px auto; width:375px;">
                블록체인은 첨단 암호 기술의 매커니즘을 통해 보호됩니다. 블록체인
                매커니즘의 3가지 특징을 소개합니다.
              </h4>
            </div>
          </div>
          <div class="container">
            <div
              class="row"
              style=" display:flex; justify-content:space-around;"
            >
              <div class="title col-4" style="padding:0px 10px 0px 0px;">
                <h1 style="color:gray;">1.</h1>
                <hr style="border:solid 0.1px gray; width:80%;" />
                <h2>해싱(암호화)</h2>
                <h4 style="width:80%;">
                  블록 해시는 해당 블록의 데이터와 이전 블록의 해시를 기반으로
                  만들어 보안을 유지합니다.
                </h4>
              </div>
              <div class="title col-4" style="padding:0px 10px 0px 0px;">
                <h1 style="color:gray;">2.</h1>
                <hr style="border:solid 0.1px gray; width:80%;" />
                <h2>기록</h2>
                <h4 style="width:80%;">
                  블록체인은 모든 변화에 대한 기록이 남습니다. 기록을 통해
                  데이터의 무결성을 유지합니다.
                </h4>
              </div>
              <div class="title col-4" style="padding:0px 10px 0px 0px;">
                <h1 style="color:gray;">3.</h1>
                <hr style="border:solid 0.1px gray; width:80%;" />
                <h2>영원한 저장</h2>
                <h4 style="width:80%;">
                  블록체인의 기록은 영원히 저장되고 위변조 될 수 없습니다.
                  데이터의 위변성을 막을 수 있습니다.
                </h4>
              </div>
            </div>
          </div>
        </div>

        <div class="container" style="margin-top:500px; column-gap:100px;">
          <div>
            <p
              class="text-left"
              style="font-size:medium; font-weight:bold; color:green; "
            >
              Our Member
            </p>
          </div>
          <div
            class="row"
            style="margin-top:0px; display:flex; justify-content:space-around;
          "
          >
            <div class="text-center">
              <img
                :src="dongock"
                alt="Circle Image"
                class="img-raised rounded-circle "
              />
              <br />
              <p style="font-weight:bold;font-size:medium; margin-top:10px;">
                이동옥
              </p>
            </div>
            <div class="text-center">
              <img
                :src="hyunmin"
                alt="Circle Image"
                class="img-raised rounded-circle "
              />
              <br />
              <p style="font-weight:bold;font-size:medium; margin-top:10px;">
                최현민
              </p>
            </div>
            <div class="text-center">
              <img
                :src="gihoon"
                alt="Circle Image"
                class="img-raised rounded-circle "
              />
              <br />
              <p style="font-weight:bold;font-size:medium; margin-top:10px;">
                박기훈
              </p>
            </div>
            <div class="text-center">
              <img
                :src="wonho"
                alt="Circle Image"
                class="img-raised rounded-circle "
              />
              <br />
              <p style="font-weight:bold;font-size:medium; margin-top:10px;">
                이원호
              </p>
            </div>
            <div class="text-center">
              <img
                :src="jonggwan"
                alt="Circle Image"
                class="img-raised rounded-circle "
              />
              <br />
              <p style="font-weight:bold;font-size:medium; margin-top:10px;">
                김종관
              </p>
            </div>
          </div>
        </div>
      </div>

      <!-- <div class="section section-navbars">
        <div class="container">
          <small-navigation></small-navigation>
        </div>
        <navigation></navigation>
      </div>
      <div class="section section-tabs">
        <div class="container">
          <tabs></tabs>
        </div>
      </div>
      <div class="section section-white">
        <div class="container">
          <nav-pills></nav-pills>
        </div>
      </div>
      <div class="section section-notifications">
        <div class="container">
          <div class="title">
            <h3>Notifications</h3>
          </div>
        </div>
        <notifications></notifications>
      </div>
      <div class="section">
        <div class="container">
          <typography-images></typography-images>
        </div>
      </div>
      <div class="section section-javascript">
        <div class="container">
          <javascript-components></javascript-components>
        </div>
      </div>
      <div class="section">
        <div class="container text-center">
          <div class="md-layout">
            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center"
            >
              <h2>Completed with examples</h2>
              <h4>
                The kit comes with three pre-built pages to help you get started
                faster. You can change the text and images and you're good to
                go. More importantly, looking at them will give you a picture of
                what you can built with this powerful kit.
              </h4>
            </div>
          </div>
        </div>
      </div>
      <div class="section section-signup page-header" :style="signupImage">
        <div class="container">
          <div class="md-layout">
            <div
              class="md-layout-item md-size-33 md-medium-size-40 md-small-size-50 md-xsmall-size-70 mx-auto text-center"
            >
              <login-card header-color="green">
                <h4 slot="title" class="card-title">Login</h4>
                <md-button
                  slot="buttons"
                  href="javascript:void(0)"
                  class="md-just-icon md-simple md-white"
                >
                  <i class="fab fa-facebook-square"></i>
                </md-button>
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
                <p slot="description" class="description">Or Be Classical</p>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>face</md-icon>
                  <label>First Name...</label>
                  <md-input v-model="firstname"></md-input>
                </md-field>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>email</md-icon>
                  <label>Email...</label>
                  <md-input v-model="email" type="email"></md-input>
                </md-field>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>lock_outline</md-icon>
                  <label>Password...</label>
                  <md-input v-model="password"></md-input>
                </md-field>
                <md-button slot="footer" class="md-simple md-success md-lg"
                  >Get Started</md-button
                >
              </login-card>
            </div>
          </div>
        </div>
      </div>
      <div class="md-layout">
        <div class="md-layout-item text-center">
          <md-button href="#/login" class="md-simple md-success md-lg"
            >View Login Page</md-button
          >
        </div>
      </div>
      <div class="section section-examples">
        <div class="container-fluid text-center">
          <div class="md-layout">
            <div class="md-layout-item">
              <a href="#/landing" target="_blank">
                <img
                  :src="landing"
                  alt="Rounded Image"
                  class="img-raised rounded img-fluid"
                />
              </a>
              <md-button href="#/landing" class="md-simple md-success md-lg"
                >View Landing Page</md-button
              >
            </div>
            <div class="md-layout-item">
              <a href="#/profile" target="_blank">
                <img
                  :src="profile"
                  alt="Rounded Image"
                  class="img-raised rounded img-fluid"
                />
              </a>
              <md-button href="#/profile" class="md-simple md-success md-lg"
                >View Profile Page</md-button
              >
            </div>
          </div>
        </div>
      </div>
      <div class="section section-download" id="downloadSection">
        <div class="container">
          <div class="md-layout text-center">
            <div class="md-layout-item md-size-66 md-small-size-100 mx-auto">
              <h2>Do you love this UI Kit?</h2>
              <h4>
                Cause if you do, it can be yours for FREE. Hit the buttons below
                to navigate to our website where you can find the kit. Our
                friends from
                <a
                  href="https://themeisle.com/?utm_campaign=mkfree-hestia&amp;utm_source=creativetim&amp;utm_medium=website"
                  target="_blank"
                  >ThemeIsle</a
                >
                created a Wordpress Theme which can be also downloaded for free.
                Start a new project or give an old Bootstrap project a new look!
              </h4>
            </div>
            <div class="md-layout-item md-size-50 md-small-size-100 mx-auto">
              <md-button
                href="https://www.creative-tim.com/product/material-kit"
                target="_blank"
                class="md-success md-lg"
              >
                <i class="fab fa-html5"></i> Free HTML Download
              </md-button>
              <md-button
                href="https://themeisle.com/themes/hestia/?utm_campaign=mkfree-hestia&amp;utm_source=creativetim&amp;utm_medium=website"
                class="md-success md-lg"
                target="_blank"
              >
                <i class="fab fa-wordpress"></i> Wordpress Theme
              </md-button>
            </div>
          </div>
          <br />
          <br />

          <div class="md-layout text-center">
            <div class="md-layout-item md-size-66 md-small-size-100 mx-auto">
              <h2>Want more?</h2>
              <h4>
                We've just launched
                <a
                  href="https://www.creative-tim.com/product/vue-material-dashboard-pro?ref=utp-mk-demos"
                  target="_blank"
                  >Vue Material Dashboard PRO</a
                >
                and
                <a
                  href="https://www.creative-tim.com/product/vue-material-kit-pro?ref=utp-vmk-demos"
                  target="_blank"
                  >Vue Material Kit PRO</a
                >. Every product has a huge number of components, sections and
                example pages. Start Your Development With A Badass Bootstrap UI
                Kit inspired by Material Design.
              </h4>
            </div>
            <div class="md-layout-item md-size-50 md-small-size-100 mx-auto">
              <div class="btn-container">
                <md-button
                  href="https://www.creative-tim.com/product/vue-material-kit-pro?ref=utp-vmk-demos"
                  class="md-button md-success md-lg md-upgrade"
                  target="_blank"
                >
                  <md-icon>unarchive</md-icon>Vue Material Kit PRO
                </md-button>
                <md-button
                  href="https://www.creative-tim.com/product/vue-material-dashboard-pro?ref=utp-vmd-demos"
                  class="md-button md-success md-lg md-upgrade"
                  target="_blank"
                >
                  <md-icon>unarchive</md-icon>Vue Material Dashboard PRO
                </md-button>
              </div>
            </div>
          </div>

          <div class="sharing-area text-center">
            <div class="md-layout">
              <div class="md-layout-item">
                <h3>Thank you for supporting us!</h3>
              </div>
            </div>

            <md-button class="md-twitter">
              <i class="fab fa-twitter"></i>Tweet
            </md-button>
            <md-button class="md-facebook">
              <i class="fab fa-facebook-square"></i> Share
            </md-button>
            <md-button class="md-google">
              <i class="fab fa-google-plus"></i> Share
            </md-button>
            <md-button class="md-github">
              <i class="fab fa-github"></i> Star
            </md-button>
          </div>
        </div>
      </div> -->
    </div>
    <div style="background-color:#D8BFD8">
      <div
        class="text-center"
        style="margin:0px; padding:0px; display:grid; justify-items:center;"
      >
        <div class="column" style="margin:150px 30px auto;">
          <p style="font-size:medium; font-weight:bold; color:gray;">
            Our Service
          </p>
          <div class="title">
            <h2 class="title" style="margin:0px 0px auto; color:white ">
              우리는 오로지
            </h2>
            <h2 class="title" style="margin-top:0px; color:white ">
              고객의 데이터에 몰입합니다.
            </h2>
            <h4 style="color:white">남겨진 이들을 위해 글을 남겨보세요.</h4>
            <h4 style="color:white">가장 안전하게 보관해드립니다.</h4>
          </div>
          <md-button
            class="md-simple md-lg md-round"
            onclick="location.href='/main'"
          >
            <p style="margin:0px; color:white;">글 남기러 가기</p>
          </md-button>
        </div>
        <marquee
          behavior="scroll"
          direction="left"
          scrollamount="15"
          style="margin:100px 0px 100px 0px;"
          ><h1 class="title" style="color:#E0FFFF">
            BLOCKCHAIN + DATA = SECURITY
          </h1></marquee
        >
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from "./components/NavigationSection";
import SmallNavigation from "./components/SmallNavigationSection";
import Tabs from "./components/TabsSection";
import NavPills from "./components/NavPillsSection";
import Notifications from "./components/NotificationsSection";
import TypographyImages from "./components/TypographyImagesSection";
import JavascriptComponents from "./components/JavascriptComponentsSection";
import { LoginCard } from "@/components";

export default {
  components: {
    // Navigation,
    // SmallNavigation,
    // Tabs,
    // NavPills,
    // Notifications,
    // TypographyImages,
    // JavascriptComponents,
    // LoginCard
  },
  name: "index",
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
    dongock: {
      type: String,
      default: require("@/assets/img/ok.jpg")
    },
    hyunmin: {
      type: String,
      default: require("@/assets/img/hm.jpg")
    },
    gihoon: {
      type: String,
      default: require("@/assets/img/gh.jpg")
    },
    wonho: {
      type: String,
      default: require("@/assets/img/wh.jpg")
    },
    jonggwan: {
      type: String,
      default: require("@/assets/img/jg.jpg")
    },
    service: {
      type: String,
      default: require("@/assets/img/service.jpg")
    }
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
      leafShow: false
    };
  },
  created() {
    if (this.$cookies.get("UserInfo") == null) {
      this.logstate = "LogIn";
    } else {
      this.logstate = "LogOut";
    }
  },
  methods: {
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
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
.btn {
  display: block;
  width: 200px;
  height: 40px;
  line-height: 40px;
  border: 1px white solid;
  text-align: center;
  background-color: white;
  cursor: pointer;
  transition: all 0.9s, color 0.3;
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
</style>
