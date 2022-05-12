// pages/test=wxss/test.js
Page({

  a: function(){
    console.log("whao")
  },
  onPullDownRefresh: function () {
    this.setData({
      first: '',
      firstcolor: '',
      second: '',
      secondcolor: '',
      third: '',
      thirdcolor: '',
      fourth: '',
      fourthcolor: ''
    })
    wx.stopPullDownRefresh()
  },
  data: {
    first: '',
    firstcolor: '',
    second: '',
    secondcolor: '',
    third: '',
    thirdcolor: '',
    fourth: '',
    fourthcolor: ''
  },
  checked: function (e) {
    // 获取到HTML页面上定义的data-app的值 
    var app = e.currentTarget.dataset.app;
    if (app == "first") {
      this.setData({
        first: '#eaeaea',
        firstcolor: 'purple',
        second: 'default',
        secondcolor: '#default',
        third: 'default',
        thirdcolor: 'default',
        fourth: 'default',
        fourthcolor: 'default'
      })
    };
    if (app == "second") {
      this.setData({
        first: 'default',
        firstcolor: 'default',
        second: '#eaeaea',
        secondcolor: 'purple',
        third: 'default',
        thirdcolor: 'default',
        fourth: 'default',
        fourthcolor: 'default'
      })
    };
    if (app == 'third') {
      this.setData({
        first: 'default',
        firstcolor: 'default',
        second: 'default',
        secondcolor: 'default',
        third: '#eaeaea',
        thirdcolor: 'purple',
        fourth: 'default',
        fourthcolor: 'default'
      })
    };
    if (app == 'fourth') {
      this.setData({
        first: 'default',
        firstcolor: 'default',
        second: 'default',
        secondcolor: 'default',
        third: 'default',
        thirdcolor: 'default',
        fourth: '#eaeaea',
        fourthcolor: 'purple'
      })
    }
  }
})