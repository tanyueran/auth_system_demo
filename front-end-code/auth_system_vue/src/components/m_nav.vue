<template>
	<el-aside class="nav-box" :width="isClose?'200px':'64px'">
		<div class="logo-wrapper" :style="{width:isClose?'200px':'64px'}">
			<img src="../assets/images/logo.png" alt="logo"/>
			<span :class="[!isClose?'hidden':'']">
				XXX系统
			</span>
		</div>
		<el-menu
			class="nav-box_content"
			:collapse="!isClose"
			background-color="#304156"
			text-color="#fff"
			:unique-opened="false"
			:default-active="activeUrl"
			:collapse-transition="false"
			:router="true">
			<el-menu-item index="/home">
				<i class="iconfont myiconshouye"></i>
				<span slot="title">首页</span>
			</el-menu-item>
			<el-submenu index="/home/personCenter">
				<template slot="title">
					<i class="iconfont myicongongzuotaishouye"></i>
					<span>个人中心</span>
				</template>
				<el-menu-item index="/home/personCenter/personInfo">
					<i class="iconfont myiconzichanxinxibuquancelve"></i>
					个人信息
				</el-menu-item>
			</el-submenu>
			<template v-for="item in menuList">
				<el-submenu :key="item.menuCode" :index="item.menuUrl">
					<template slot="title">
						<i class="iconfont" :class="item.menuIcon"></i>
						<span>{{item.menuName}}</span>
					</template>
					<el-menu-item :key="item2.menuCode" v-for="item2 in item.children" :index="item2.menuUrl">
						<i class="iconfont" :class="item2.menuIcon"></i>
						{{item2.menuName}}
					</el-menu-item>
				</el-submenu>
			</template>
			<el-menu-item index="/home/page1">
				<i class="el-icon-menu"></i>
				<span slot="title">页面一</span>
			</el-menu-item>
		</el-menu>
	</el-aside>
</template>

<script>
  /*
  * 导航组件
  * */
  export default {
    name: 'm_nav',
    data() {
      return {
        activeUrl: '',
      }
    },
    computed: {
      isClose() {
        return this.$store.state.navState;
      },
      menuList() {
        return this.$store.state.userMenu;
      }
    },
    watch: {
      $route: {
        handler(val) {
          this.activeUrl = val.path;
        },
        immediate: true,
      }
    },
  }
</script>

<style scoped lang="scss">
	.nav-box {
		height: 100%;
		display: flex;
		flex-direction: column;
		overflow: hidden;
		transition: width .3s;
	}

	.logo-wrapper {
		height: 60px;
		display: flex;
		align-items: center;
		padding-left: 1em;
		cursor: pointer;
		border-right: 1px solid #f0f0f0;
		background: #2B2F3A;
		color: #fff;
		font-weight: bold;

		> img {
			height: 40px;
			width: auto;
		}
	}

	.nav-box_content {
		flex: 1;
		overflow: hidden;

		&:hover {
			overflow-y: auto;
		}
	}

	.iconfont {
		margin-right: 0.5em;
	}
</style>
