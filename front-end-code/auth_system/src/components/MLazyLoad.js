import Loadable from 'react-loadable';
import Loading from "./MLoading.js"

// 懒加载
export default (loader, loading = Loading) => Loadable({
  loader,
  loading,
})
