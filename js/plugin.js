// ==UserScript==
// @name         初试信息融合脚本
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  显示初试信息
// @author       Apple Inc.
// @match        https://yz.chsi.com.cn/sytj/tj/qecx.html
// @icon         https://www.google.com/s2/favicons?sz=64&domain=chsi.com.cn
// @require      https://t4.chei.com.cn/common/jquery/1.9.1/jquery.min.js
// @require      http://www.yanhuangxueyuan.com/versions/threejsR92/build/three.js
// ==/UserScript==

var activated = 0 /*0 表示没有*/
var state = []
var col = 4

$('#loading').ajaxStart(() => { $(this).show() }).ajaxStop(() => { $(this).hide() })


/*获取初试信息*/
async function getLesson(url, i) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: url,
            async: false,
            type: "GET",
            data: {},
            beforeSend: common_global_loading,
            success: (response) => {
                response = response.replace(/^[\s\S]*<body.*?>|<\/body>[\s\S]*$/ig, '').replace('\r\n', '')
                let result = response

                var lessons = [] // 获取4个科目信息
                for (let i = 0; i < $(result).find('.zsml-res-items>tr>td').length; i++)
                    lessons.push($(result).find('.zsml-res-items>tr>td').eq(i).html().replace(/<.*>*<.*>|\s/g, ''))

                var commonLesson = ''
                    // 外语科目
                try {
                    if (lessons[1].substr(1, 3) == '201') commonLesson += '英一'
                    else if (lessons[1].substr(1, 3) == '204') commonLesson += '英二'
                    else if (lessons[1].substr(1, 3) == '202') commonLesson += '俄语'
                    else commonLessson += lessons[1].substr(5)
                        // 数学科目
                    if (lessons[2].substr(1, 3) == '301') commonLesson += '数一'
                    else if (lessons[2].substr(1, 3) == '302') commonLesson += '数二'
                    else commonLessson += lessons[2].substr(5)
                        // 专业课
                    result = `${commonLesson}<br>${lessons[3]}`
                } catch (e) { result = lessons.length }

                if (result == 0) {
                    if (url.substr(url.length - 3, 2) == '00') result = getLesson(url.substr(0, url.length - 3) + '01' + url.substr(url.length - 1), i)
                    else console.log(`获取初试信息出错：{$(this).find('td')}`)
                } else {
                    console.log(`${i} ${result} (${$(response).find('.zsml-summary').eq(0).text().substr(7)})`)
                    $('tbody').eq(1).find('tr').each(function() {
                        $('tbody').eq(1).find('tr').eq(i).find('td').eq(col).html(result)
                    })
                }
                resolve()
            }
        })
    })
}
/*插入列*/
async function insCol() {

    if (activated == 0) {
        /**/
        let urls = [],
            promiseAll = new Set()
            /*按行获取查询结果表格的内容*/
        $('tbody').eq(1).find('tr').each(function() {
            var collegeID = $(this).find('td').eq(0).text().substr(1, 5) /*招生单位*/
            var collegeName = $(this).find('td').eq(0).text().substr(8)
            var schoolID = $(this).find('td').eq(1).text().substr(1, 3) /*院系所*/
            var schoolName = $(this).find('td').eq(1).text().substr(6)
            var professionID = $(this).find('td').eq(2).text().substr(1, 6) /*专业*/
            var professionName = $(this).find('td').eq(2).text().substr(9)
            var directionID = $(this).find('td').eq(3).text().substr(1, 2) /*方向*/
            var directionName = $(this).find('td').eq(3).text().substr(5)

            urls.push(`https://yz.chsi.com.cn/zsml/kskm.jsp?id=${collegeID}21${schoolID}${professionID}${directionID}1`)
        })
        $('thead th').eq(col).text('初试科目')
        for (let i = 0; i < urls.length; i++) {
            promiseAll.add(getLesson(urls[i], i))
        }

        Promise.all(promiseAll).then((res) => {
            layer.alert('获取成功')
            console.log('获取成功')
            common_global_unloading()
        }).catch((err) => { console.log(err) })

        /**/
        $('.added').hide()
    } else { layer.alert('已经获取') }
    activated = 1
    common_global_unloading()
}

(function() {
    'use strict';

    // Your code here...

    $.fn.wait = function(func, times, interval) {
        var _times = times || 100, //100次
            _interval = interval || 20, //20毫秒每次
            _self = this,
            _selector = this.selector, //选择器
            _iIntervalID; //定时器ID
        if (this.length) { //如果已经获取到了，就直接执行函数
            func && func.call(this);
        } else {
            _iIntervalID = setInterval(function() {
                if (!_times) { //是0就退出
                    clearInterval(_iIntervalID);
                }
                _times <= 0 || _times--; // 如果是整数就--
                _self = $(_selector); // 再次选择
                if (_self.length) { // 判断是否取到
                    func && func.call(_self);
                    clearInterval(_iIntervalID);
                }
            }, _interval);
        }
        return this;
    }

    // 设置表格状态（包括列宽、替换处的状态）
    var setState = function() {
        console.log('设置表格状态')
    }

    setState()

    $('h2').wait(function() {
        $('h2').append('<a id="added" data-tip="获取初试科目的自定义脚本" class="tj-btn-middle tj-seach-btn tj-tip action-text tjyx-tip" style="margin-left:30pt">获取初试科目</a>')
        $('#added').hide()
        $('#xxfs').val(1) //填全日制
        $('#zyxx').val('软件工程') //填专业
            //$('#zyxx').val('电子信息') //填专业
        var _click = function() { $('.tj-seach-btn').click() } //存储按钮函数

        $('.tj-seach-btn').eq(0).click(() => {

            if ($("thead").length) {
                if ($('tbody').eq(1).children().length == 0 || $('tbody').eq(1).children().text() == ' 该条件下没有查询到计划余额信息 ') layer.alert('表格为空', { 'title': '初试科目获取' })
                else insCol().then(common_global_unloading())
            } else { layer.alert("请先执行查询", { 'title': '初试科目获取' }); }

            common_global_unloading()
        })

        $('.tj-seach-btn').eq(1).click(function() { activated = 0;
            $('#added').show();
            console.log('[初试科目获取] 回到初始状态') })
        $('.tj-table').change(function() { console.log('changed') })

    })

})();