<template>
	<div class="not-found-box">
		<el-link type="success" @click="goHomeHandler">返回首页</el-link>
	</div>
</template>

<script>
  /**
	 * 404,页面
   */
  export default {
    name: 'notFoundPage',
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
		background: url("../assets/images/404.jpg") center center no-repeat;
	}
</style>
