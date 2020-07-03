<template>
	<div class="wrapper">
		<div class="login-box">
			<h1 class="text-center">欢迎您，登录系统</h1>
			<div class="login-form">
				<p>
					<span class="icon el-icon-user"></span>
					<input v-model="userInfo.username" type="text" placeholder="请输入账号">
				</p>
				<p>
					<span class="icon el-icon-lock"></span>
					<input v-model="userInfo.password" type="password" placeholder="请输入密码">
				</p>
				<p class="text-center">
					<el-button style="width: 100%" :loading="loading" type="primary" @click="loginHandler">登录</el-button>
				</p>
			</div>
		</div>
	</div>
</template>

<script>
  /**
   * 登录页面
   */
  import {login} from "../../api/user";

  export default {
    name: 'loginPage',
    data() {
      return {
        // 用户信息
        userInfo: {
          username: 'manager',
          password: 'password',
        },
        loading: false,
        // 验证
        form: {
          username: [
            {required: true, message: '请输入账号', trigger: 'blur'},
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
        }
      }
    },
    methods: {
      /*
      * 登录事件监听
      * */
      loginHandler() {
        if (this.userInfo.username && this.userInfo.password) {
          this.login({
            ...this.userInfo,
          })
        } else {
          this.$message.error("账号密码不可为空")
        }
      },

      /*
      * 登录
      * */
      login(obj) {
        this.loading = true;
        login(obj).then((data) => {
          this.$store.commit('set_isLogin', true);
          this.$store.commit('set_token', data);
          this.$store.dispatch("getUserInfo");
          this.$router.replace("/");
        }).catch((err) => {
          console.log(err);
        }).finally(() => {
          this.loading = false;
        });
      }
    }
  }
</script>

<style scoped lang="scss">
	.wrapper {
		height: 100%;
		overflow-y: hidden;
		position: relative;
		background-image: url("../../assets/images/login-bg.jpg");
		background-size: cover;

		> .login-box {
			position: absolute;
			width: 300px;
			top: 50%;
			left: 50%;
			border: 1px solid #efefef;
			transform: translate(-50%, -50%);

			h1 {
				margin-bottom: 10px;
			}
		}
	}

	.login-form {
		> p {
			position: relative;
			margin-bottom: 10px;

			.icon {
				font-size: 22px;
				position: absolute;
				top: 50%;
				left: 10px;
				transform: translate(0, -50%);
			}

			input {
				flex: 1;
				width: 100%;
				height: 35px;
				border: 1px solid #eee;
				padding-left: 3em;
				background: #fff;

				&:focus {
					border-color: #409EFF;
				}
			}
		}
	}
</style>
