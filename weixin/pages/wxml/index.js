// pages/wxml/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    time: (new Date()).toTimeString(),
    msg: '文本改变0次',
    a: 1
  },
  animation: function () {
    wx.navigateTo({
      url: '../animation/animation',
      fail: function () { wx.switchTab({ url: '../animation/animation' }) }
    })
  },

  ani: function (evt) {
    wx.showToast({
      title: '长按触发',
      icon: 'loading',
      duration: 1000,
      mask: false
    })
    wx.showModal({
      title: '提示',
      content: '您长按了前往动画实验页面的按钮，是否前往动画实验页面？',
      showCancel: true,
      cancelText: '点错了',
      cancelColor: 'gray',
      confirmText: '去看看',
      confirmColor: 'red',
      success: function (res) {
        if (res.confirm) {
          wx.navigateTo({
            url: '../animation/animation',
            fail: function (res) {
              wx.switchTab({
                url: '../animation/animation',
              })
            }
          })

        }
      },
    })
  },

  change: function () {
    this.setData({
      msg: "文本改变" + this.data.a + "次"
    })
    this.setData({
      a: this.data.a + 1
    })
  },
  log: function () {
    wx.navigateTo({
      url: '../logs/logs',
    })
  },
  g: function () {
    var a = getApp()
    console.log(a.globalData.text)
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) { },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.showToast({
      title: '成功渲染首页面',
      duration: 1000,
      mask: false
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    wx.showToast({
      title: '欢迎回到首页面',
      duration: 1000,
      mask: false
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () { },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.showToast({
      title: '正在刷新',
      icon: 'loading',
      image: '',
      duration: 500,
      mask: true
    })
    this.setData({
      time: (new Date()).toTimeString(),
      msg: '爸爸妈妈',
      a: 1
    })
    wx.stopPullDownRefresh()
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    return {
      title: '开发程序的分享卡片',
      path: '/page/user?id=123'
    }
  }
})