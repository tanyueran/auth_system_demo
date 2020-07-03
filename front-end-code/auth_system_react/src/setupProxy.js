/**
 * @author tanxin
 * @date $
 * @Description: 代理
 */
const {createProxyMiddleware} = require('http-proxy-middleware');

// 没有开启mock是启用代理
let app = process.argv[2] === 'mock' ? function () {
} : function (app) {
  console.log("代理开启了=====");
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://localhost:3000/auth_system_ssm',
      changeOrigin: true,
      pathRewrite: {
        '^/api': '', // rewrite path
      },
    })
  );
};

module.exports = app;
