<template>
	<div class="footer_wrapper text-center">
		{{txt}}
	</div>
</template>

<script>
  export default {
    name: 'm_footer',
    data() {
      return {
        txt: '',
        textMap: {
          morning: '清晨的阳光和代码最搭了！',
          afternoon: '可以来个下午茶！',
          night: '夜深了，快休息吧！',
        },
      }
    },
    created() {
      let now = new Date().getHours();
      if (now >= 18) {
        this.txt = this.textMap.night;
      } else if (now < 12) {
        this.txt = this.textMap.morning;
      } else {
        this.txt = this.textMap.afternoon;
      }
    }
  }
</script>

<style scoped lang="scss">
	.footer_wrapper {
		height: $footer-height;
		line-height: $footer-height;
		text-align: center;
	}
</style>
