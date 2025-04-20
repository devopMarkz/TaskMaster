<template>
  <v-app>
    <v-toolbar compact>
      <v-toolbar-title text="TaskMaster"></v-toolbar-title>

      <template v-slot:append>
        <v-btn icon="mdi-magnify"></v-btn>

        <v-btn icon="mdi-heart"></v-btn>

        <v-btn icon="mdi-dots-vertical"></v-btn>
      </template>
    </v-toolbar>
    <router-view />
  </v-app>
</template>

<script lang="ts" setup>
</script>

<style scoped>
</style>
