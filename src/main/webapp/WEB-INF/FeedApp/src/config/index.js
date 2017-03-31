/**
 * Created by Ben on 2017/3/26.
 */

// 登录背景配置
const normal = {
  backgroundImage: 'url(../../../static/img/love.jpeg)',
  // 初始positin
  backgroundPositionX: '-300px',
  backgroundPositionY: '-350px',
  // 允许的两个方向的最大偏移量
  offsetMax: 15,
  // 鼠标偏移量相对于图片位置偏移量的比例
  offsetRate: 0.08,
  // 触发mousemove最终事件的时间间隔
  mousemoveInternal: 80
}

export default {
  normal
}
