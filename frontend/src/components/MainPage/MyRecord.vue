<template>
  <div class="MyRecord">
    <v-expansion-panels>
      <v-expansion-panel
        v-for="(item,i) in 10"
        :key="i"
      >
        <v-expansion-panel-header>Item</v-expansion-panel-header>
        <v-expansion-panel-content>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
export default {
  name: "MyRecord"
}
</script>

<style></style>
