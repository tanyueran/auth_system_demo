<template>
	<el-container class="app-box">
		<m-nav class="app-nav-box"></m-nav>
		<el-container>
			<m-header/>
			<el-main>
				<transition name="fade-transform" mode="out-in">
					<keep-alive :include="include">
						<router-view/>
					</keep-alive>
				</transition>
			</el-main>
			<el-footer>
				<m-footer/>
			</el-footer>
		</el-container>
	</el-container>
</template>

<script>
  import mHeader from '../components/m_header.vue'
  import mFooter from '../components/m_footer.vue'
  import mNav from '../components/m_nav.vue'

  export default {
    name: 'home',
    components: {
      mHeader,
      mFooter,
      mNav,
    },
    computed: {
      // 需要缓存的页面
      include() {
        return this.$store.state.cacheList;
      },
      isOpen() {
        return this.$store.state.navState;
      }
    },
    data() {
      return {}
    },
  }
</script>

<style scoped lang="scss">
	.app-box {
		height: 100%;
		position: relative;
		overflow: hidden;
	}

</style>
