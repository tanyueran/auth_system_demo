# vue-cli3_element_pc
> 写在前面：用于平时快速开发的模板项目，主要封装了笔者熟悉的开发目录层级

> 使用yarn打包

## 项目安装依赖-初始化
```
yarn install
```
---

### 项目开发
```
yarn run serve 没有mock数据版本
yarn run serve-mock 有mock数据版本
```
---

### 项目打包
```
yarn run build
```
---

### 后台返回的json格式强约定
```text
// 如下：
{
  "code":100,
  "data":{
    
  },
  "msg": "接口的基本说明"
}
最外层有且仅有code、data、msg、三个属性，可以前后端约定code的值及含义。
数据内容都在data中值
```

### 依赖库
- vue
- vuex 
- vue-router
- axios
- element-ui
- scss

---

### 修改思考
```text
2020/1/7:
原本打算将ts加入项目中，但是在本来约束的东西，却将项目的复杂度翻了好几倍,
所以 【一般中小项目】 并不适合使用ts，如果是组件、函数库等确实很适合。
```

