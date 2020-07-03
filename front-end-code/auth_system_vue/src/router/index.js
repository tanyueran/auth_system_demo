/**
 * @author tanxin
 * @date 2019/10/15
 * @desc 路由文件
 **/
import vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store/index';
import {Loading} from 'element-ui';

/*
* 解决路由跳转时出现的错误
* */
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
};

vue.use(VueRouter);

const router = new VueRouter({
  routes: [
    // 登录
    {
      path: '/login',
      name: 'login',
      component: () => import('../pages/login/index.vue'),
      meta: {
        needLogin: false,
        title: '登录',
      }
    },
    // 主页
    {
      path: '/',
      redirect: '/home',
      component: () => import('../layout/index.vue'),
      children: [
        // 欢迎页
        {
          path: 'home',
          name: 'home',
          component: () => import('../pages/home/home.vue'),
          meta: {
            title: '首页',
            needLogin: true,
          }
        },
        {
          path: '/home/page1',
          name: 'page1',
          component: () => import('../pages/home/page1/index.vue'),
          meta: {
            title: 'page1',
            needLogin: true,
          }
        },
        {
          path: '/home/page2',
          name: 'page2',
          component: () => import('../pages/home/page2/index.vue'),
          meta: {
            title: 'page2',
            needLogin: true,
          }
        },
        {
          path: '/home/page3',
          name: 'page3',
          component: () => import('../pages/home/page3/index.vue'),
          meta: {
            title: 'page3',
            needLogin: true,
          }
        },
        // 404
        {
          path: '/home/404',
          component: () => import('../pages/notFound.vue'),
          meta: {
            needLogin: false,
            title: '404',
          }
        },
        // 401
        {
          path: '/home/401',
          component: () => import('../pages/noAuth.vue'),
          meta: {
            needLogin: false,
            title: '401',
          }
        },
        // 监听404页面
        {
          path: '/home/*',
          redirect: '/home/404',
        },
      ]
    },
    //404页面
    {
      path: '/404',
      component: () => import('../pages/notFound.vue'),
      meta: {
        needLogin: false,
        title: '404',
      }
    },
    // 404
    {
      path: '/*',
      redirect: '/404',
    },
  ]
});

// 路由错误
router.onError(() => {

});

// 保存加载显示对象
let loading = null;

router.beforeEach((to, from, next) => {
  loading = Loading.service({
    text: '页面加载中...',
  });
  // 认证拦截
  if (to.meta.needLogin && !store.getters.get_isLogin) {
    return next({name: 'login',});
  }

  // 登录后不允许到登录页
  if (store.getters.get_isLogin && (to.name === 'login')) {
    return next({name: 'home'});
  }

  document.title = to.meta.title || '系统';
  return next();
});

router.afterEach(() => {
  if (loading != null) {
    vue.nextTick(() => {
      loading.close();
    });
  }
});

export default router;
