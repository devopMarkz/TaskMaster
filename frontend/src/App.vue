<template>
  <v-app>
    Aplicativo
    <ul>
      <li v-for="nome in estr">{{ nome }}</li>
    </ul>
    <router-view />
  </v-app>
</template>

<script lang="ts" setup>
  const estr = ['Marcos', 'Sara'];
</script>

<style scoped>
</style>
