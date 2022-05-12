//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
    logs: []
  },
  back: function () {
    wx.navigateBack()
  },
  onLoad: function () {
    this.setData({
      logs: (wx.getStorageSync('logs') || []).map(log => {
        return util.formatTime(new Date(log))
      })
    })
  },
  onShow: function () {
    wx.showToast({
      title: '启动日志界面',
      duration: 1000,
      mask: false
    })
  } 
})
