/**
 * @author tanxin
 * @date $
 * @Description: 路由配置信息
 */
import {lazy} from 'react'

const loginPage = lazy(() => import('../pages/login/index.js'));
const notFoundPage = lazy(() => import('../pages/notFound/index.js'));

export default [
  {
    path: '/login',
    exact: true,
    component: loginPage,
  },
  {
    path: '/register',
    exact: true,
    component: lazy(() => import('../pages/register/index.js')),
  },
  {
    component: lazy(() => import('../pages/home/index.js')),
    children: [
      // 首页
      {
        path: '/home/index',
        exact: true,
        component: lazy(() => import('../pages/home/index/index.js')),
      },
      // 个人中心
      {
        path: '/home/personCenter/personInfo',
        exact: true,
        component: lazy(() => import('../pages/home/person_center/person_info/index.js')),
      },

      /**
       * 系统管理
       * */
      // 用户管理
      {
        path: '/home/system_manager/user_manager',
        exact: true,
        component: lazy(() => import('../pages/home/system_manager/user_manager/index.js')),
      },
      // 角色管理
      {
        path: '/home/system_manager/role_manager',
        exact: true,
        component: lazy(() => import('../pages/home/system_manager/role_manager/index.js')),
      },
      // 权限管理
      {
        path: '/home/system_manager/auth_manager',
        exact: true,
        component: lazy(() => import('../pages/home/system_manager/auth_manager/index.js')),
      },
      {
        path: '/home/404',
        component: notFoundPage,
      },
    ]
  },
  {
    component: notFoundPage,
  }
]
