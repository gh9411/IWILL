<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
    style="padding: 0;"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <h3
          class="md-title"
          style="font-size:1.4em; font-weight: 600; margin-top:20px;"
        >
          <h1 style="color: white; " @click="move">IWILL</h1>
        </h3>
      </div>
      <div class="md-toolbar-section-end">
        <h3 class="md-title" style="font-size:1.4em; font-weight: 600; ">
          <h3 style="color: white; " @click="movemypage">Mypage</h3>
        </h3>
        <h3 class="md-title" style="font-size:1.4em; font-weight: 600; ">
          <h3 style="color: white;" @click="log">{{ this.logstate }}</h3>
        </h3>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  // ignore resize events as long as an actualResizeHandler execution is in the queue
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();

      // The actualResizeHandler will execute at a rate of 15fps
    }, 66);
  }
}

export default {
  name: "MainNavbar",
  components: {},
  props: {
    type: {
      type: String,
      default: "white",
      validator(value) {
        return [
          "white",
          "default",
          "primary",
          "danger",
          "success",
          "warning",
          "info"
        ].includes(value);
      }
    },
    colorOnScroll: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      extraNavClasses: "",
      toggledClass: false,
      logstate: "LogIn"
    };
  },
  computed: {
    showDownload() {
      const excludedRoutes = ["login", "landing", "profile"];
      return excludedRoutes.every(r => r !== this.$route.name);
    }
  },
  created() {
    if (this.$cookies.get("UserInfo") == null) {
      this.logstate = "LogIn";
    } else {
      this.logstate = "LogOut";
    }
  },
  methods: {
    log() {
      if (this.$cookies.get("UserInfo") == null) {
        this.$router.push("/").catch(error => {
          if (error.name === "NavigationDuplicated") {
            location.reload();
          }
          location.reload();
        });
      } else {
        this.$cookies.remove("UserInfo");
        location.reload();
      }
    },
    bodyClick() {
      let bodyClick = document.getElementById("bodyClick");

      if (bodyClick === null) {
        let body = document.querySelector("body");
        let elem = document.createElement("div");
        elem.setAttribute("id", "bodyClick");
        body.appendChild(elem);

        let bodyClick = document.getElementById("bodyClick");
        bodyClick.addEventListener("click", this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    move() {
      if (this.$cookies.get("UserInfo") == null) {
        this.$router.push("/").catch(error => {
          if (error.name === "NavigationDuplicated") {
            location.reload();
          }
        });
      } else {
        this.$router.push("/index").catch(error => {
          if (error.name === "NavigationDuplicated") {
            location.reload();
          }
        });
      }
    },
    movemypage() {
      if (this.$cookies.get("UserInfo") == null) {
        alert("로그인이 필요합니다.");
        this.$router.push("/").catch(error => {
          if (error.name === "NavigationDuplicated") {
            location.reload();
          }
        });
      } else {
        this.$router.push("/main").catch(error => {
          if (error.name === "NavigationDuplicated") {
            location.reload();
          }
        });
      }
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById("toolbar");
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove("md-transparent");
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = "";
          navbarColor.classList.add("md-transparent");
        }
      }
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById("downloadSection");
      if (element_id) {
        element_id.scrollIntoView({ block: "end", behavior: "smooth" });
      }
    }
  },
  mounted() {
    document.addEventListener("scroll", this.scrollListener);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  }
};
</script>
