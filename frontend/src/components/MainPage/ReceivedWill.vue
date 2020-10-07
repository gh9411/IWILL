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
      <div style="margin-bottom:200px;">
        <div class="col">
          <div style="margin-bottom:100px;">
            <v-expansion-panels v-if="reveal" style="width:1200px;">
              <v-expansion-panel>
                <v-expansion-panel-header
                  >{{ jgarr.uid }}님의 전달</v-expansion-panel-header
                >

                <v-expansion-panel-content>
                  <p>생성날짜 : {{ jgarr.date }}</p>
                  <h2 style="font-weight:bold">{{ jgarr.title }}</h2>
                  <hr />
                  <pre
                    class="preclass"
                    style="margin-top:40px; margin-bottom:40px; white-space: pre-wrap;"
                    >{{ jgarr.text }}</pre
                  >
                  <hr />
                  <img
                    :src="jgarr.image"
                    style="margin-bottom:30px; margin-top:30px;"
                  />
                  <hr />
                  <video
                    :src="jgarr.video"
                    controls
                    style="margin-top:30px;"
                  ></video>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </div>
        </div>
      </div>
      <modal v-if="this.modal.failcheck" class="text-center">
        <template slot="header">
          <div class="py-3 mb-0">
            <h4 class="text-warning">Fail!</h4>
          </div>
        </template>
        <template slot="body">
          <hr />
          <h5 class="title mb-3">
            유언장에 변조가 일어나 불러올 수 없습니다.
          </h5>
        </template>
        <template slot="footer">
          <div class="text-center">
            <md-button
              size="sm"
              class="md-simple md-default"
              @click="modal.failcheck = false"
              >닫기</md-button
            >
          </div>
        </template>
      </modal>
    </div>
  </div>
</template>

<script>
import { Tabs } from "@/components";
import { Modal } from "@/components";
export default {
  components: {
    Modal
  },
  name: "ReceivedWill",
  data() {
    return {
      code: "",
      willlists: [],
      imgurl: "",
      jgarr: [],
      reveal: false,
      modal: {
        failcheck: false
      }
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

    submitcode() {
      const data = new FormData();
      data.append("ruid", this.$cookies.get("UserInfo").email);
      data.append("hash", this.code);
      this.$axios
        .post(this.$SERVER_URL + "will/savetransaction", data)
        .then(res => {
          this.jgarr = res.data;
          this.reveal = true;
          if (res.data == "fail") {
            this.modal.failcheck = true;
          }
        })
        .catch(err => {
          console.log(err);
        });
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