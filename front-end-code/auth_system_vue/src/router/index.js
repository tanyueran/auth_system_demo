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
  mode: 'history',
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
            auth: false,// 不要权限
          }
        },
        /**
         * 个人中心
         */
        {
          path: '/home/personCenter/personInfo',
          name: 'personInfo',
          component: () => import('../pages/home/person_center/person_info/index.vue'),
          meta: {
            title: '个人中心',
            needLogin: true,
            auth: false,
          }
        },

        /**
         * 系统管理页面
         */
        // 用户管理
        {
          path: '/home/system_manager/user_manager',
          name: 'userManager',
          component: () => import('../pages/home/system_manager/user_manager/index.vue'),
          meta: {
            title: '用户管理',
            needLogin: true,
          }
        },
        // 角色管理
        {
          path: '/home/system_manager/role_manager',
          name: 'roleManager',
          component: () => import('../pages/home/system_manager/role_manager/index.vue'),
          meta: {
            title: '角色管理',
            needLogin: true,
          }
        },
        // 菜单管理
        {
          path: '/home/system_manager/menu_manager',
          name: 'menuManager',
          component: () => import('../pages/home/system_manager/menu_manager/index.vue'),
          meta: {
            title: '菜单管理',
            needLogin: true,
          }
        },
        // 按钮管理
        {
          path: '/home/system_manager/btn_manager',
          name: 'btnManager',
          component: () => import('../pages/home/system_manager/btn_manager/index.vue'),
          meta: {
            title: '按钮管理',
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
        // 404
        {
          path: '/home/404',
          component: () => import('../pages/notFound.vue'),
          meta: {
            needLogin: false,
            title: '404',
            auth: false,
          }
        },
        // 401
        {
          path: '/home/401',
          component: () => import('../pages/noAuth.vue'),
          meta: {
            needLogin: false,
            title: '401',
            auth: false,
          }
        },
        // 监听404页面
        {
          path: '/home/*',
          redirect: '/home/404',
          auth: false,
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

  // 权限拦截
  console.log(to.meta.needLogin, to.meta.auth !== false, store.state.userButton[to.path] === undefined);
  console.log(to.path)
  if ((to.meta.needLogin && to.meta.auth !== false) && store.state.userButton[to.path] === undefined) {
    return next({path: '/home/401',});
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
