<template>
  <div class="ReceivedWill">
    <v-expansion-panels>
      <v-expansion-panel v-for="(item, i) in 3" :key="i">
        <v-expansion-panel-header>아무개님의 전달</v-expansion-panel-header>
        <v-expansion-panel-content>
          화장실이 급해요.
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
export default {
  name: "ReceivedWill"
};
</script>

<style>
</style>