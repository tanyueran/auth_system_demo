/**
 * @author tanxin
 * @date $
 * @Description: 路由配置信息
 */
import MLoading from "../components/MLazyLoad";

const LoginPageUrl = () => import('../pages/login/index.js');

const notFoundPageUrl = () => import('../pages/notFound/index.js');

export default [
  {
    path: '/login',
    exact: true,
    component: MLoading(LoginPageUrl),
  },
  {
    path: '/register',
    exact: true,
    component: MLoading(() => import('../pages/register/index.js')),
  },
  {
    component: MLoading(() => import('../layout/index.js')),
    children: [
      // 首页
      {
        path: '/home/index',
        exact: true,
        component: MLoading(() => import('../pages/home/index/index.js')),
      },
      // 个人中心
      {
        path: '/home/personCenter/personInfo',
        exact: true,
        component: MLoading(() => import('../pages/home/person_center/person_info/index.js')),
      },

      /**
       * 系统管理
       * */
      // 用户管理
      {
        path: '/home/system_manager/user_manager',
        exact: true,
        component: MLoading(() => import('../pages/home/system_manager/user_manager/index.js')),
      },
      // 角色管理
      {
        path: '/home/system_manager/role_manager',
        exact: true,
        component: MLoading(() => import('../pages/home/system_manager/role_manager/index.js')),
      },
      // 菜单管理
      {
        path: '/home/system_manager/menu_manager',
        exact: true,
        component: MLoading(() => import('../pages/home/system_manager/menu_manager/index.js')),
      },
      // 按钮管理
      {
        path: '/home/system_manager/btn_manager',
        exact: true,
        component: MLoading(() => import('../pages/home/system_manager/btn_manager/index.js')),
      },
      {
        path: '/home/404',
        component: MLoading(notFoundPageUrl),
      },
      {
        path: '/home/401',
        component: MLoading(() => import('../pages/noAuth/index.js')),
      },
    ]
  },
  {
    component: MLoading(notFoundPageUrl),
  }
]
