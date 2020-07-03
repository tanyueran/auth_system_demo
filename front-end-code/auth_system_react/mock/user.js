module.exports = {
  // 登录
  'POST /api/login': {
    "code": 200,
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJyb2xlcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfTUFOQUdFUlwifSx7XCJhdXRob3JpdHlcIjpcImJ1dHRvbk1hbmFnZXI6QlROX0FERFwifSx7XCJhdXRob3JpdHlcIjpcImJ1dHRvbk1hbmFnZXI6QlROX0RFTFwifSx7XCJhdXRob3JpdHlcIjpcImJ1dHRvbk1hbmFnZXI6QlROX1FVRVJZXCJ9LHtcImF1dGhvcml0eVwiOlwiYnV0dG9uTWFuYWdlcjpCVE5fVVBEQVRFXCJ9LHtcImF1dGhvcml0eVwiOlwibWVudU1hbmFnZXI6QlROX0FERFwifSx7XCJhdXRob3JpdHlcIjpcIm1lbnVNYW5hZ2VyOkJUTl9ERUxcIn0se1wiYXV0aG9yaXR5XCI6XCJtZW51TWFuYWdlcjpCVE5fUVVFUllcIn0se1wiYXV0aG9yaXR5XCI6XCJtZW51TWFuYWdlcjpCVE5fVVBEQVRFXCJ9LHtcImF1dGhvcml0eVwiOlwicm9sZU1hbmFnZXI6QlROX0FERFwifSx7XCJhdXRob3JpdHlcIjpcInJvbGVNYW5hZ2VyOkJUTl9ERUxcIn0se1wiYXV0aG9yaXR5XCI6XCJyb2xlTWFuYWdlcjpCVE5fUVVFUllcIn0se1wiYXV0aG9yaXR5XCI6XCJyb2xlTWFuYWdlcjpCVE5fVVBEQVRFXCJ9LHtcImF1dGhvcml0eVwiOlwidXNlck1hbmFnZXI6QlROX0FERFwifSx7XCJhdXRob3JpdHlcIjpcInVzZXJNYW5hZ2VyOkJUTl9ERUxcIn0se1wiYXV0aG9yaXR5XCI6XCJ1c2VyTWFuYWdlcjpCVE5fUVVFUllcIn0se1wiYXV0aG9yaXR5XCI6XCJ1c2VyTWFuYWdlcjpCVE5fVVBEQVRFXCJ9XSIsImlzcyI6InN5c191c2VyIiwiZXhwIjoxNTkzODI4NzMxLCJ1c2VyQ29kZSI6Im1hbmFnZXIifQ.ezU2UIUN7-agHWbnh_PQY58vjDOqVT9lN_TLVLJ73QJqT_AKDc91IqjdrljaGAen2aACVZ-UXQaUH-4w4Sl5-E6Wvn8xzI_-y317MbExmu0bsmtyJFkm26yuAUdi7xgodoUWhIF3ShJj4BrW49WZxq-5mKx511DAHjyB-GJG9mSNwaUo2qsIdTsiFSPO6wvo3g4plT0Zyg5hSxNy41lWT1l1O2VATTFnYfByjlBGg2G3jL4vrxqejRBDV5cXG2kYomdaNg0Sc2THTuWzci8JJQLGKhJeriMP7WOgvjuLbOV2K6Vu7tv5OYx8vUJovpRJddiOywsmlgXNmCDiFXP2aX4eqpx7rkaCXqLr9nM0F7-7_ObkJe1P9Kmv2MA0xo_ogTmx0dK9wtuAhCT3qlyiyXTIuXcj5OX72QcyXlKOoRonFZ8q-201V8J-uns8hXYpa5tK_IUrNuJVmm3ymK-Idw6BX9SXQx5OezKmY7xx9eF4JapOZgEe7h-P75cKGKJ_Xo53HH-nLh3x9yMFlWZ-3APk3-6QNKiITG8gecE-ZcyBtVvc1vlOssrAiwYi66EuCmOD7jypfLTHTpoa_37xHqFnkaxKGVksvDCrtWMJNZ6jtzdRBn1OCFO8NdSTtoBRrvsfA-c9JTnxUl2ra_3ET5OboMaYrbbe6UKIgEb97Tw",
    "msg": "登录成功"
  },

  // 获取用户信息
  'GET /api/user/getUserInfo': {
    "code": 200,
    "data": {
      "active": "1",
      "createTime": "2020-06-22 16:11:55",
      "desc": "管理员",
      "fileId": "",
      "id": "2",
      "password": "",
      "roles": [{
        "createTime": "2020-06-22 16:13:02",
        "id": "2",
        "menus": [],
        "remark": "普通管理员",
        "roleCode": "",
        "roleName": "普通管理员",
        "updateTime": "2020-06-22 16:13:21"
      }],
      "sex": 2,
      "updateTime": "2020-07-02 11:30:16",
      "userCode": "manager",
      "userName": "普通管理员"
    },
    "msg": "ok"
  },

  // 获取菜单信息
  'POST /api/menu/levelMenu/roleIds': {
    "code": 200, "data": [{
      "children": [{
        "createTime": "2020-06-22 16:18:39",
        "data": "",
        "id": "2",
        "menuCode": "userManager",
        "menuIcon": "myiconzhuzhanghaoliebiao",
        "menuName": "用户管理",
        "menuType": "1",
        "menuUrl": "/home/system_manager/user_manager",
        "pid": "1",
        "remark": "",
        "sort": 4,
        "updateTime": "2020-07-02 10:24:16"
      }, {
        "createTime": "2020-06-22 16:19:36",
        "data": "",
        "id": "3",
        "menuCode": "roleManager",
        "menuIcon": "myiconziyuanjiaoseguanli",
        "menuName": "角色管理",
        "menuType": "1",
        "menuUrl": "/home/system_manager/role_manager",
        "pid": "1",
        "remark": "",
        "sort": 3,
        "updateTime": "2020-07-02 10:24:18"
      }, {
        "createTime": "2020-06-22 16:20:00",
        "data": "",
        "id": "4",
        "menuCode": "menuManager",
        "menuIcon": "myiconcaidan",
        "menuName": "菜单管理",
        "menuType": "1",
        "menuUrl": "/home/system_manager/menu_manager",
        "pid": "1",
        "remark": "",
        "sort": 2,
        "updateTime": "2020-07-02 10:24:20"
      }, {
        "createTime": "2020-06-22 16:20:28",
        "data": "",
        "id": "5",
        "menuCode": "buttonManager",
        "menuIcon": "myiconziyuanfenleiguanli",
        "menuName": "按钮管理",
        "menuType": "1",
        "menuUrl": "/home/system_manager/btn_manager",
        "pid": "1",
        "remark": "",
        "sort": 1,
        "updateTime": "2020-07-02 10:24:22"
      }],
      "createTime": "2020-06-22 16:18:31",
      "data": "",
      "id": "1",
      "menuCode": "systemManager",
      "menuIcon": "myiconpeizhiguanli",
      "menuName": "系统管理",
      "menuType": "0",
      "menuUrl": "",
      "pid": "",
      "remark": "",
      "sort": 1,
      "updateTime": "2020-07-02 10:03:43"
    }], "msg": "ok"
  }
};
