// pages/animation/animation.js
var _loadImagePathIndex = 0
var _animation;
var _animationIndex
const _ANIMATION_TIME = 50;
var _animationIntervalId
Page({

  data: {
    //常量或由用户控制的变量
    motto: "Page animation",
    animation: '',
    rotate: 1,
    //程序控制的变量
    _type: 'primary',
    text: '开始',
    which: 'startAnimationInterval'
  },

  rotateAni: function (n) {
    this._animation.rotate(this.data.rotate * (n)).step()
    this.setData({
      animation: this._animation.export()
    })
  },

  reset: function () {
    this.stopAnimationInterval()
    _loadImagePathIndex = 0;
    this._animation.rotate(0).step({ duration: 1 })
    this.setData({ animation: this._animation.export() })
    wx.showToast({
      title: '成功还原',
      duration: 1500,
      mask: false,
    })
  },

  /**
  * 开始旋转
  */
  startAnimationInterval: function () {
    var that = this;
    that.rotateAni(++_loadImagePathIndex); // 进行一次旋转
    _animationIntervalId = setInterval(function () {
      that.rotateAni(++_loadImagePathIndex);
    }, _ANIMATION_TIME); // 每间隔_ANIMATION_TIME进行一次旋转
    this.setData({
      _type: 'warn',
      text: '结束',
      which: 'stopAnimationInterval'
    })
  },

  /**
  * 停止旋转
  */
  stopAnimationInterval: function () {
    if (_animationIntervalId > 0) {
      clearInterval(_animationIntervalId);
      _animationIntervalId = 0;
    }
    this.setData({
      _type: 'primary',
      text: '开始',
      which: 'startAnimationInterval'
    })
  },

  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    wx.showToast({
      title: '动画页面渲染完成',
      duration: 10,
      mask: false,
    })
  },
  onReady: function () {
    // 页面渲染完成
    //实例化一个动画
    this._animation = wx.createAnimation({
      duration: 1000,
      timingFunction: 'linear',
      delay: 0,
      transformOrigin: '1 1 1',
      success: function (res) {
        console.log(res)
      }
    })
  },


  onShow: function () {
    // 页面显示
    wx.showToast({
      title: '欢迎',
      duration: 500,
      mask: false,
    })
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
    wx.showToast({
      title: '欢迎再来',
      duration: 500,
    })
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
    this.stopAnimationInterval()
    this._animation.rotate(0).step({ duration: 1 })
    this.setData({ animation: this._animation.export() })
    wx.stopPullDownRefresh()
  },

})