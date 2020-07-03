/**
 * @author tanxin
 * @date 2019/10/15
 * @desc 全局过滤器文件
 **/
export default {
  /**
   * 时间装换成 x年x月x日 x时x分x秒
   * @param date 待转换的时间
   * @returns {string} 字符串
   */
  DateTimeToString(date) {
    if (date == undefined) {
      return '';
    }
    if (!(date instanceof Date)) {
      return '';
    }
    let str = '';
    // 年
    str += `${date.getFullYear()}-`;
    // 月
    str += (date.getMonth() + 1) > 9 ? (1 + date.getMonth()) : `0${date.getMonth() + 1}`;
    str += '-';
    // 日
    str += (date.getDate() > 9) ? date.getDate() : `0${date.getDate()}`;
    str += ' ';
    // 时
    str += date.getHours() > 9 ? date.getHours() : `0${date.getHours()}`;
    str += ':';
    // 分
    str += date.getMinutes() > 9 ? date.getMinutes() : `0${date.getMinutes()}`;
    str += ':';
    // 秒
    str += date.getSeconds() > 9 ? date.getSeconds() : `0${date.getSeconds()}`;
    return str;
  },

  /**
   * 时间转成 x年x月x日
   * @param date
   * @returns {string}
   */
  DateToString(date) {
    if (date == undefined) {
      return '';
    }
    if (!(date instanceof Date)) {
      return '';
    }
    let str = '';
    // 年
    str += `${date.getFullYear()}-`;
    // 月
    str += (date.getMonth() + 1) > 9 ? (1 + date.getMonth()) : `0${date.getMonth() + 1}`;
    str += '-';
    // 日
    str += (date.getDate() > 9) ? date.getDate() : `0${date.getDate()}`;
    return str;
  },
}
