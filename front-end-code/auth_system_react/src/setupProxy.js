/**
 * @author tanxin
 * @date $
 * @Description: 代理
 */
const {createProxyMiddleware} = require('http-proxy-middleware');
module.exports = function (app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://localhost:3000/auth_system_react',
      changeOrigin: true,
      pathRewrite: {
        '^/api': '', // rewrite path
      },
    })
  );
};
