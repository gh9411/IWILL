<template>
  <div class="MyRecord">
    <v-expansion-panels>
      <v-expansion-panel v-for="(willlist, i) in willlists" :key="i">
        <v-expansion-panel-header
          >{{ willlist.date }}의 기록입니다.</v-expansion-panel-header
        >
        <v-expansion-panel-content>
          <h4 class="title">제목 : {{ willlist.title }}</h4>
          <hr />
          <img :src="willlist.image" />
          <video controls :src="willlist.video"></video>
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
      console.log(res.data);
      this.willlists = res.data;
    });
  }
};
</script>

<style>
</style>