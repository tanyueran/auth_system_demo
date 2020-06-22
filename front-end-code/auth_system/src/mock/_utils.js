export function ok(obj) {
  return {
    code: 200,
    data: obj,
  }
}

export function err(obj) {
  return {
    code: 0,
    data: obj,
    msg: '报错了',
  }
}
