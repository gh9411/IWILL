<template>
  <div class="MyRecord">
    <v-expansion-panels>
      <v-expansion-panel v-for="(item,i) in 5" :key="i">
        <v-expansion-panel-header>2020.{{i+1}}월의 기록입니다.</v-expansion-panel-header>
        <v-expansion-panel-content>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        
        <video
          id="my-player"
          class="video-js"
          controls
          preload="auto"
          poster=""
          data-setup='{}'
        >
          <source src="../../../src/assets/video/test.mp4" type="video/mp4">
        </video>
        

        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>

export default {
  name: "MyRecord",
}
</script>

<style>
  
</style>