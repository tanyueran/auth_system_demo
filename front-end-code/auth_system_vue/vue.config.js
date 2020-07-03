/**
 * @author tanxin
 * @date $
 * @Description vue项目的配置
 */
module.exports = {
  lintOnSave: false,
  // publicPath: './',

  productionSourceMap: true,

  css: {
    loaderOptions: {
      sass: {
        prependData: `@import "~@/style/variable.scss";`,
      }
    }
  },

  // 开发的代理
  devServer: {
    disableHostCheck: true,
    port: '3002',
    proxy: {
      '^/api': { //替换代理地址名称
        target: 'http://localhost:3000/auth_system_ssm/',
        changeOrigin: true, //可否跨域
        pathRewrite: {
          '^/api': '', //重写接口
        },
      },
    },
    // 开启mock server数据,process.argv的第四个参数mock 表明是否为mock版本的
    before: process.argv[3] && require('./mock/index.js')
  },
};
