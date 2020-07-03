import Vue from 'vue'
import store from './store/index.js'
import router from './router/index.js'

import App from './App.vue'
import './plugins/element.js'
import filters from './filters/index.js'

// 全局样式
import './style/index.scss'

// 项目环境
const HOST = process.env.NODE_ENV;

if (HOST === 'product') {
  Vue.config.productionTip = false;
} else {
  console.log(`===${HOST}`);
}

// 全局过滤器
for (const val in filters) {
  Vue.filter(val, filters[val]);
}
// 状态初始化
store.commit('init');

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
