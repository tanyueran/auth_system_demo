<template>
	<div class="not-found-box">
		<el-link type="success" @click="goHomeHandler">返回首页</el-link>
	</div>
</template>

<script>
  /**
   * 401页面
   */
  export default {
    name: 'noAuthPage',
    methods: {
      goHomeHandler() {
        this.$router.replace("/");
      },
    }
  }
</script>

<style scoped lang="scss">
	.not-found-box {
		height: 100%;
		background: url("../assets/images/401.jpg") center center no-repeat;
	}
</style>
