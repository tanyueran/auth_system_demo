/**
 * @author tanxin
 * @date $
 * @Description: 存储数据
 */
const s = window.sessionStorage;

export default class Storage{

  static set(key,val){
    s.setItem(key,JSON.stringify(val));
  }

  static get(key){
    let val = s.getItem(key);
    if(val === null){
      return null;
    }
    return JSON.parse(s.getItem(key));
  }

  static remove(key){
    s.removeItem(key);
  }
}
