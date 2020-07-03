<template>
	<el-aside class="nav-box" :width="isClose?'200px':'64px'">
		<div class="logo-wrapper">
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
			:collapse-transition="false"
			:router="true">
			<el-submenu index="">
				<template slot="title">
					<i class="el-icon-location"></i>
					<span>导航一</span>
				</template>
				<el-menu-item-group>
					<template slot="title">分组一</template>
					<el-menu-item index="/home/page1">页面1</el-menu-item>
					<el-menu-item index="/home/page2">页面2</el-menu-item>
					<el-menu-item index="/home/page3">页面3</el-menu-item>
				</el-menu-item-group>
			</el-submenu>
			<el-menu-item index="/home/page1">
				<i class="el-icon-menu"></i>
				<span slot="title">页面一</span>
			</el-menu-item>
			<el-menu-item index="/home/page2">
				<i class="el-icon-document"></i>
				<span slot="title">页面二</span>
			</el-menu-item>
			<el-menu-item index="/home/page3">
				<i class="el-icon-setting"></i>
				<span slot="title">页面三</span>
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
        navList: [
          {},
        ]
      }
    },
    computed: {
      isClose() {
        return this.$store.state.navState;
      }
    },
    watch: {
      $rotuer() {

      }
    },
    methods: {
      /*
      * 跳转页面
      * */
      goto(name) {
        if (name !== this.$route.name) {
          this.$router.push({name}).then(() => {
          }).catch((err) => {
            console.log("--------路由跳转错误");
            console.log(err);
          });
        }
      }
    }
  }
</script>

<style scoped lang="scss">
	.nav-box {
		height: 100%;
		display: flex;
		flex-direction: column;
		overflow: hidden;
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
</style>
