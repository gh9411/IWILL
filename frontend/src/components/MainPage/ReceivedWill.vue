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
              <v-expansion-panel
                v-for="(willlist, i) in willlists"
                v-bind:key="i"
              >
                <v-expansion-panel-header>{{}}의 전달</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <img src="@/assets/img/gh.jpg" />
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
      pp: "gh"
    };
  },
  created() {},
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
    submitcode() {
      const data = new FormData();
      data.append("ruid", this.$cookies.get("UserInfo").email);
      data.append("hash", this.code);
      this.$axios
        .post(this.$SERVER_URL + "will/savetransaction", data)
        .then(res => {
          for (var i = 0; i < res.data.length; i++) {
            const clist = [];
            res.data[i].filepath = JSON.parse(res.data[i].filepath);
            clist.push(res.data[i].filepath.imagepath);

            this.willlists.push(clist);
          }
          console.log(this.willlists);
        })
        .then(err => {
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