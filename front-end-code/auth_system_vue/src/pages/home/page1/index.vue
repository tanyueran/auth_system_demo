<template>
	<div>
		page1
		<hr>
		<p>
			您已选中：{{selList.join(",")}}
		</p>

		<div>
			<p :key="item.key" v-for="item in list">
				<input name="a" :value="item.key" v-model="selList" type="checkbox">
				主键:{{item.key}}\
				val:{{item.val}}、
				value:{{item.value}}
			</p>
		</div>


		<div style="border:1px solid #333;">
			<p>
				val:<input type="text" v-model="obj.val">
			</p>
			<p>
				value: <input type="text" v-model="obj.value">
			</p>
			<button @click="sureHandler">确定</button>
		</div>

	</div>
</template>

<script>
  export default {
    name: 'page1',
    data() {
      return {
        list: [
          {key: 'a1', name: 'A', val: '1', value: '234',},
          {key: 'b1', name: 'B', val: '2', value: '234',},
          {key: 'c1', name: 'C', val: '3', value: '234',},
        ],
        // 保存选中的项的主键
        selList: [],
        // 待修改的对象
        obj: {
          val: '',
          value: '',
        }
      }
    },
    methods: {
      sureHandler() {
        let arr = [];
        // 获取需要修改的
        this.list.forEach(item => {
          if (this.selList.indexOf(item.key) !== -1) {
            arr.push({...item});
          }
        });
        console.log("arr======需要修改的");
        console.log(JSON.stringify(arr));
        arr.map(item => {
          item.val = this.obj.val;
          item.value = this.obj.value;
          return item;
        });
        console.log("arr=======修后的");
        console.log(JSON.stringify(arr));
      }
    }
  }
</script>

<style scoped>

</style>
