<template>
  <div class="MyRecord">
    <v-expansion-panels style="margin-bottom:300px;">
      <v-expansion-panel v-for="(willlist, i) in willlists" :key="i">
        <v-expansion-panel-header
          >{{ willlist.date }}의 기록입니다.</v-expansion-panel-header
        >
        <v-expansion-panel-content>
          <p>생성날짜 : {{ willlist.date }}</p>
          <h2 style="font-weight:bold">{{ willlist.title }}</h2>
          <hr />
          <pre
            style="margin-top:40px; margin-bottom:40px;  white-space: pre-wrap; "
            >{{ willlist.text }}</pre
          >
          <hr />
          <img
            :src="willlist.image"
            style="margin-bottom:30px; margin-top:30px;"
          />
          <hr />
          <video
            controls
            :src="willlist.video"
            style="margin-top:30px;"
          ></video>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
export default {
  name: "MyRecord",
  data() {
    return {
      willlists: []
    };
  },
  created() {
    const data = new FormData();
    data.append("email", this.$cookies.get("UserInfo").email);
    this.$axios.post(this.$SERVER_URL + "will/user", data).then(res => {
      this.willlists = res.data;
    });
  }
};
</script>

<style>
</style>