<template>
	<el-header class="header_box" height="84px">
		<div class="header_box-info">
			<div class="toggle-box">
				<a
					class="toggle-btn"
					@click="toggleHandler" :class="[isOpen?'el-icon-s-fold':'el-icon-s-unfold']"></a>
				<!--面包屑导航-->
				<el-breadcrumb separator="/">
					<template v-for="(item,i) in breadcrumbList">
						<el-breadcrumb-item :key="item.path + i" :to="{ path: item.path }">
							{{item.title || '首页'}}
						</el-breadcrumb-item>
					</template>
				</el-breadcrumb>
			</div>
			<div class="info-box">
				<el-avatar title="hello" :src="userImg"/>
				&nbsp;
				&nbsp;
				<el-dropdown>
					<a class="el-dropdown-link">
						{{userInfo.userName}}
						<i class="el-icon-caret-bottom"></i>
					</a>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item>个人信息</el-dropdown-item>
						<el-dropdown-item @click.native="logoutHandler">退出</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</div>
		</div>
		<div class="header_box-btn">
			<el-Scrollbar
				ref="scrollContainer"
				@wheel.native.prevent="handleScroll"
				:vertical="false">
				<!--基本不动的-->
				<a class="item-box" :class="[$route.path === '/home' ?'active':'']" @click="gotoHandler('/')">
					首页
				</a>
				<template v-for="item in navList">
					<a
						@click="gotoHandler(item.path)"
						:key="item.path"
						class="item-box"
						:class="[$route.path === item.path ?'active':'']">
						{{item.title}}
						<span @click.stop="removeHandler(item)" class="close">×</span>
					</a>
				</template>
			</el-Scrollbar>
		</div>
	</el-header>
</template>

<script>
  import userImg from '../assets/images/user.png';

  export default {
    name: 'm_header',
    computed: {
      userInfo() {
        return this.$store.state.userInfo;
      },
      isOpen() {
        return this.$store.state.navState;
      },
      scrollWrapper() {
        return this.$refs.scrollContainer.$refs.wrap
      },
      navList() {
        return this.$store.state.navList;
      }
    },
    watch: {
      $route: {
        handler(val) {
          // 处理快捷导航
          this.dealNav(val);
          // 显示面包屑导航
          this.breadcrumbDoing(val);
        },
        immediate: true,
      },
    },
    data() {
      return {
        userImg,
        // 面包屑
        breadcrumbList: [],
      }
    },
    methods: {
      // 切换导航的开关
      toggleHandler() {
        this.$store.commit("set_nav_state", !this.$store.state.navState);
      },
      // 面包导航的生成
      breadcrumbDoing(route) {
        this.breadcrumbList = [];
        let list = [];
        route.matched.forEach(item => {
          // 判断现有的
          for (let i = 0; i < list.length; i++) {
            if (list[i].path === (item.path || item.redirect)
              || list[i].path === (item.path || item.redirect)) {
              return;
            }
          }
          list.push({
            path: item.path || item.redirect,
            title: item.meta && item.meta.title,
          });
        });
        this.breadcrumbList = list;
      },
      // 页面跳转
      gotoHandler(path) {
        this.$router.push(path);
      },
      // 处理导航
      dealNav(route) {
        let currentPath = (route.path || route.redirect);
        if (currentPath === '/home' || currentPath === "/" || currentPath === "") return;
        if (route.meta && route.meta.needLogin === false) return;
        let list = this.$store.state.navList;
        let i = list.findIndex(item => item.path === route.path);
        if (i === -1) {
          list.push({
            path: route.path || route.redirect,
            title: route.meta && route.meta.title,
          });
          this.$store.commit("set_nav_list", [...list]);
        }
      },
      // 删除导航
      removeHandler(route) {
        let list = [...this.$store.state.navList];
        let i = list.findIndex(item => item.path === route.path);
        if (i !== -1) {
          list.splice(i, 1);
        }
        this.$store.commit("set_nav_list", [...list]);
        // 删除时当前的
        if (route.path === this.$route.path) {
          if (list.length > 0) {
            this.$router.replace(list[list.length - 1].path);
          } else {
            this.$router.replace("/");
          }
        }
      },
      // 滚动条左右滚动
      handleScroll(e) {
        const eventDelta = e.wheelDelta || -e.deltaY * 40;
        const $scrollWrapper = this.scrollWrapper;
        $scrollWrapper.scrollLeft = $scrollWrapper.scrollLeft + eventDelta / 4
      },
      // 退出登录
      logoutHandler() {
        this.$store.commit("destroy");
        this.$router.replace({name: 'login'});
      },
    }
  }
</script>

<style scoped lang="scss">
	.header_box {
		display: flex;
		flex-direction: column;
		border-bottom: 1px solid #d8dce5;
		box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12), 0 0 3px 0 rgba(0, 0, 0, 0.04);
	}

	.header_box-info {
		height: 50px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;

		.toggle-box {
			display: flex;
			align-items: center;
		}

		.toggle-btn {
			font-size: 24px;
			margin-right: 2em;
		}

		.info-box {
			display: flex;
			align-items: center;
		}
	}


	.header_box-btn {
		flex: 1;
		white-space: nowrap;
		position: relative;
		overflow: hidden;
		width: 100%;

		::v-deep {
			.el-scrollbar__bar {
				bottom: 0px;
			}

			.el-scrollbar__wrap {
				height: 49px;
			}
		}

		.item-box {
			border: 1px solid #D8DCE5;
			font-size: 12px;
			padding: 0 8px;
			height: 26px;
			line-height: 26px;
			position: relative;
			margin-right: 1em;
			display: inline-block;
			transition: all .3s linear;

			&.active {
				background: #42B983;
				color: #fff;
				border-color: transparent;

				&:before {
					content: "";
					background: #fff;
					display: inline-block;
					width: 8px;
					height: 8px;
					border-radius: 50%;
					position: relative;
					margin-right: 2px;
				}
			}

			.close {
				background: transparent;
				border-radius: 50%;
				display: inline-block;
				width: 16px;
				height: 16px;
				line-height: 16px;
				text-align: center;

				&:hover {
					background-color: #B4BCCC;
				}
			}
		}
	}
</style>
