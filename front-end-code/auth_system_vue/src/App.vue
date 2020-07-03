<template>
  <div id="app">
    <keep-alive>
      <router-view/>
    </keep-alive>
  </div>
</template>

<script>
  export default {
    name: 'app',
    components: {},
  }
</script>

<style scoped lang="scss">
  #app {
    height: 100%;
  }
</style>
