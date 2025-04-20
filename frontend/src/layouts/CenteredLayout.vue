<template>
    <v-container class="centered-container">
      <slot></slot>  <!-- O conteúdo do componente filho será renderizado aqui -->
    </v-container>
  </template>
  
  <script setup>
  </script>
  
  <style scoped>
  .centered-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 90vh;
    text-align: center;
  }
  </style>