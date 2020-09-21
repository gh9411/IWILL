<template>
  <div class="writeWILL">
    <h3>기록 남기기</h3>
    <md-field style="margin-top: 2em">
      <label>제목</label>
      <md-input v-model="Topic"></md-input>
    </md-field>

    <md-field>
      <label>내용을 적어주세요</label>
      <md-textarea v-model="textarea"></md-textarea>
    </md-field>

    <md-button class="md-primary">제출</md-button>
  </div>
</template>

<script>
export default {
  name: "writeWILL",
  data() {
    return {
      Topic: "",
      aboutWill: null
    }
  }
}
</script>

<style>

</style>