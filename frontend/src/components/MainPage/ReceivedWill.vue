<template>
  <div class="ReceivedWill">
    <div class="col">
      <div class="text-center">
        <h3 class="title">이메일로 받은 코드를 입력해주세요!</h3>
        <md-field class="md-form-group" slot="inputs">
          <md-icon>note</md-icon>
          <label>코드...</label>
          <md-input v-model="code"></md-input>
        </md-field>
        <md-button
          slot="footer"
          class="md-simple md-success md-lg"
          @click="submitcode"
        >
          유언장 불러오기
        </md-button>
      </div>
      <div class="text-center" style="margin-bottom:200px;">
        <div class="col">
          <div style="margin-bottom:100px;">
            <v-expansion-panels>
              <v-expansion-panel>
                <v-expansion-panel-header
                  >{{ jgarr.uid }}님의 전달</v-expansion-panel-header
                >
                <p>생성날짜 : {{ jgarr.date }}</p>
                <pre>{{ jgarr.text }}</pre>
                <hr />
                <v-expansion-panel-content>
                  <div class="col">
                    <h5 class="title">남긴 사진</h5>
                    <img
                      :src="jgarr.image"
                      style="margin-bottom:30px; margin-top:30px;"
                    />
                  </div>
                  <hr />
                  <div class="col">
                    <h5 class="title">남긴 영상</h5>
                    <video
                      :src="jgarr.video"
                      controls
                      style="margin-top:30px;"
                    ></video>
                  </div>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Tabs } from "@/components";
export default {
  components: {},
  name: "ReceivedWill",
  data() {
    return {
      code: "",
      willlists: [],
      imgurl: "",
      imgarr: [],
      texturl: "",
      textarr: [],
      videourl: "",
      videoarr: [],
      realtextarr: [],
      jgarr: []
    };
  },
  created() {},
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bonobono.jpg")
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
    urlcheck(index) {
      return this.willlist[index];
    },
    print(willlist) {
      console.log(willlist);
    },
    submitcode() {
      const data = new FormData();
      data.append("ruid", this.$cookies.get("UserInfo").email);
      data.append("hash", this.code);
      this.$axios
        .post(this.$SERVER_URL + "will/savetransaction", data)
        .then(res => {
          // console.log("json", res.data);
          // this.jgarr.push(res.data);
          console.log("res", res.data);
          this.jgarr = res.data;
          console.log(this.jgarr);
          // for (var i = 0; i < res.data.length; i++) {
          //   var array = new Array();

          //   array["uid"] = res.data[i].uid;
          //   array["title"] = res.data[i].title;

          //   var pathhash = JSON.parse(res.data[i].filepath);

          //   array["image"] =
          //     `http://j3a104.p.ssafy.io/images/` +
          //     pathhash["imagepath"].slice(19);

          //   array["video"] =
          //     `http://j3a104.p.ssafy.io/images/` +
          //     pathhash["videopath"].slice(19);

          //   array["textpath"] =
          //     `http://j3a104.p.ssafy.io/images/` +
          //     pathhash["textpath"].slice(19);

          //   this.jgarr.push(array);

          //   // this.$axios.get(array["textpath"]).then(nes => {
          //   // console.log(nes.data);
          //   // array["content"] = nes.data;
          //   // });
          // }

          // for (var i = 0; i < res.data.length; i++) {
          //   res.data[i].filepath = JSON.parse(res.data[i].filepath);
          //   console.log(
          //     "이미지 정보",
          //     res.data[i].filepath.hasOwnProperty("imagepath") // true, false
          //   );
          //   if (res.data[i].filepath.hasOwnProperty("imagepath")) {
          //     this.imgurl =
          //       "http://j3a104.p.ssafy.io/images/" +
          //       res.data[i].filepath.imagepath.slice(19);
          //     console.log("imgurl출력값", this.imgurl);
          //     if (this.imgurl.indexOf("nofile") == -1) {
          //       this.imgarr.push(this.imgurl);
          //     }
          //   }
          //   // console.log(this.imgurl);
          //   if (res.data[i].filepath.hasOwnProperty("textpath")) {
          //     this.texturl =
          //       "http://j3a104.p.ssafy.io/images/" +
          //       res.data[i].filepath.textpath.slice(19);
          //     this.textarr.push(this.texturl);
          //   }
          //   if (res.data[i].filepath.hasOwnProperty("videopath")) {
          //     // console.log(this.texturl);
          //     this.videourl =
          //       "http://j3a104.p.ssafy.io/images/" +
          //       res.data[i].filepath.videopath.slice(19);
          //     if (this.videourl.indexOf("nofile") == -1) {
          //       this.videoarr.push(this.videourl);
          //     }
          //   }

          //   this.$axios.get(this.textarr[0]).then(nes => {
          //     this.jgarr.push(nes.data);
          //   });
          // }
        })

        // .then(() => {
        //   console.log("종관리스트1", this.jgarr);
        //   for (var i = 0; i < this.jgarr.length; i++) {
        //     this.$axios.get(this.jgarr[i]["textpath"]).then(nes => {
        //       console.log(i, nes.data);
        //       console.log(i, this.jgarr[i]);
        //       this.jgarr[i].content = nes.data;
        //     });
        //   }
        //   console.log("종관리스트2", this.jgarr);
        // })

        // .then(() => {
        //   // console.log("textarr", this.textarr);
        //   for (var j = 0; j < this.textarr.length; j++) {
        //     // console.log(this.textarr[j]);
        //     this.$axios.get(this.textarr[j]).then(nes => {
        //       console.log("텍스트", nes.data);
        //       this.jgarr.push(nes.data);
        //     });
        //   }
        // })
        .catch(err => {
          console.log(err);
        });

      // for (var i = 0; i < this.jgarr.length; i++) {
      //   this.$axios.get(this.jgarr[i]["textpath"]).then(nes => {
      //     console.log(i, nes.data);
      //     console.log(i, this.jgarr[i]);
      //     this.jgarr[i].content = nes.data;
      //   });
      // }
      console.log("종관리스트2", this.jgarr);

      // console.log("imgarr", this.imgarr);
      // console.log("imgurl", this.imgurl);
      // console.log("videoarr", this.videoarr);
      // console.log("videourl", this.videourl);
      // console.log("videoarr", this.textarr);
      // console.log("videourl", this.texturl);
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