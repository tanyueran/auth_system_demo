<template>
	<div class="user-manager-box">
		<m-title>用户管理</m-title>

		<div class="search-box">
			<el-row>
				<el-col :span="12" style="display: flex;">
					<el-input placeholder="请输入关键字" v-model="searchObj.keyword"/>
					<el-button @click="searchHandler" type="primary" icon="el-icon-search">搜索</el-button>
				</el-col>
				<el-col class="text-right" :span="12">
					<el-button @click="addUserHandler" type="primary" icon="el-icon-plus">添加用户</el-button>
				</el-col>
			</el-row>
		</div>

		<div class="result-box">
			<el-table
				:data="resultObj.data"
				v-loading="loading"
				style="width: 100%"
				height="400">
				<el-table-column
					fixed
					prop="userName"
					label="用户姓名"
					width="150">
				</el-table-column>
				<el-table-column
					prop="userCode"
					label="账号"
					width="120">
				</el-table-column>
				<el-table-column
					prop="active"
					label="激活"
					width="120">
					<template slot-scope="scope">
						{{scope.row.active === '1' ? '启用' : '锁定'}}
					</template>
				</el-table-column>
				<el-table-column
					prop="sex"
					label="性别"
					width="120">
					<template slot-scope="scope">
						{{scope.row.sex === 1 ? '男' : scope.row.sex === 2 ? '女' : '-'}}
					</template>
				</el-table-column>
				<el-table-column
					prop="desc"
					label="描述">
				</el-table-column>
				<el-table-column
					prop="createTime"
					label="创建时间"
					width="160">
				</el-table-column>
				<el-table-column
					fixed="right"
					label="操作"
					width="160">
					<template slot-scope="scope">
						<el-button
							type="text"
							size="small">
							配置角色
						</el-button>
						<el-button
							type="text"
							size="small">
							编辑
						</el-button>
						<el-button
							type="text"
							size="small">
							删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>

		<div class="page-box text-right">
			<el-pagination
				@size-change="handleSizeChange"
				@current-change="handleCurrentChange"
				:current-page="searchObj.current"
				:page-sizes="[searchObj.size, 50, 100, 200]"
				:page-size="searchObj.size"
				layout="total, sizes, prev, pager, next, jumper"
				:total="resultObj.total">
			</el-pagination>
		</div>

		<!--添加用户、编辑用户-->
		<el-dialog
			:title="userObj.isEdit ? '编辑用户' : '添加用户'"
			:visible.sync="userObj.show"
			width="30%"
			:before-close="closeHandler">
			<el-form ref="form" :model="userObj.obj" label-width="80px">
				<el-form-item label="用户姓名" prop="userName" :rules="[{required:true,message:'请输入'}]">
					<el-input v-model="userObj.obj.userName" placeholder="请输入"/>
				</el-form-item>
				<el-form-item label="账号" prop="userCode" :rules="[{required:true,message:'请输入'}]">
					<el-input v-model="userObj.obj.userCode" placeholder="请输入"/>
				</el-form-item>
				<el-form-item label="激活" prop="active" :rules="[{required:true,message:'请选择'}]">
					<el-radio-group v-model="userObj.obj.active">
						<el-radio label="启用" value="1"/>
						<el-radio label="锁定" value="0"/>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="性别" prop="sex">
					<el-radio-group v-model="userObj.obj.sex">
						<el-radio label="男" :value="1"/>
						<el-radio label="女" :value="2"/>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="描述" prop="desc">
					<el-input type="textarea" v-model="userObj.obj.desc"/>
				</el-form-item>
				<el-form-item class="text-right">
					<el-button @click="saveHandler" type="primary">保存</el-button>
					<el-button @click="closeHandler">取消</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
  /**
   * 用户管理页面
   */
  import mTitle from '../../../../components/m_title.vue';
  import {queryUserByPage} from "../../../../api/user_manager.js";

  export default {
    name: "userManagerPage",
    components: {
      mTitle,
    },
    data() {
      return {
        loading: false,
        searchObj: {
          size: 20,
          keyword: '',
          current: 1,
        },
        resultObj: {
          data: [],
          total: 0,
        },
        userObj: {
          show: false,
          isEdit: false,
          obj: {},
          init: {
            userName: '',
            userCode: '',
            sex: null,
            active: null,
            desc: '',
          },
        }
      }
    },
    created() {
      this.getData();
    },
    methods: {
      /**
       * 页容量改变
       */
      handleSizeChange(size) {
        this.searchObj.size = size;
        this.getData();
      },

      /**
       * 当前页数改变
       */
      handleCurrentChange(current) {
        this.searchObj.current = current;
        this.getData();
      },

      /**
       * 关键字搜搜
       */
      searchHandler() {
        this.searchObj.current = 1;
        this.getData();
      },

      /**
       * 请求数据
       */
      getData() {
        this.loading = true;
        queryUserByPage(this.searchObj).then(data => {
          this.resultObj.total = data.total;
          this.resultObj.data = data.data;
        }).catch(err => {
          console.log(err);
        }).finally(() => {
          this.loading = false;
        })
      },

      /**
       * 关闭对话框的回调
       */
      closeHandler() {
        this.userObj.show = false;
        this.userObj.isEdit = false;
        this.userObj.obj = {...this.userObj.init};
        this.$nextTick(() => {
          this.$refs.form.clearValidate();
        });
      },

      /**
       * 保存的回调
       */
      saveHandler() {
        this.$refs.form.validate().then((result) => {
          if (result) {
            console.log(this.userObj.obj);
          }
        }).catch(err => {
          this.$message.error("请按照提示填写");
        })
      },

      /**
       * 添加用户的回调
       */
      addUserHandler() {
        this.userObj.obj = {...this.userObj.init};
        this.userObj.show = true;
      },
    }
  }
</script>

<style scoped lang="scss">
	.page-box {
		margin-top: 1em;
	}
</style>
