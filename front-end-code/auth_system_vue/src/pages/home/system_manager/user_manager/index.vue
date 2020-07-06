<template>
	<div class="user-manager-box">
		<m-title>用户管理</m-title>

		<div class="search-box">
			<el-input v-model="searchObj.keyword" width="120"/>
			<el-button>搜索</el-button>
		</div>

		<div class="result-box">
			<el-table
				:data="resultObj.data"
				style="width: 100%"
				height="400">
				<el-table-column
					fixed
					prop="date"
					label="日期"
					width="150">
				</el-table-column>
				<el-table-column
					prop="name"
					label="姓名"
					width="120">
				</el-table-column>
				<el-table-column
					prop="province"
					label="省份"
					width="120">
				</el-table-column>
				<el-table-column
					prop="city"
					label="市区"
					width="120">
				</el-table-column>
				<el-table-column
					prop="address"
					label="地址"
					width="300">
				</el-table-column>
				<el-table-column
					prop="zip"
					label="邮编"
					width="120">
				</el-table-column>
				<el-table-column
					fixed="right"
					label="操作"
					width="120">
					<template slot-scope="scope">
						<el-button
							type="text"
							size="small">
							移除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>

		<div class="page-box text-right">
			<el-pagination
				@size-change="handleSizeChange"
				@current-change="handleCurrentChange"
				:current-page="resultObj.currentPage"
				:page-sizes="[searchObj.size, 50, 100, 200]"
				:page-size="searchObj.size"
				layout="total, sizes, prev, pager, next, jumper"
				:total="resultObj.total">
			</el-pagination>
		</div>
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
        },
        resultObj: {
          data: [],
          total: 0,
          currentPage: 1,
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
      handleSizeChange() {

      },

      /**
       * 当前页数改变
       */
      handleCurrentChange() {

      },

      /**
       * 请求数据
       */
      getData() {
        this.loading = true;
        queryUserByPage().then(data => {

        }).catch(err => {
          console.log(err);
        }).finally(() => {
          this.loading = false;
        })
      },
    }
  }
</script>

<style scoped lang="scss">
	.page-box {
		margin-top: 1em;
	}
</style>
