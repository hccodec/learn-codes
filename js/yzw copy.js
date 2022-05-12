function AjaxPolling(taskId, key, serverUrl, options) { this.taskId = taskId, this.key = key, this.serverUrl = serverUrl, this.useProgressBar = !1, this.onError = function(xhr, retryCount) {}, this.onSuccess = function(data) {}, this.everyPolling = function(data) { this.useProgressBar && this.changeProgressBar(data) }, this.onTimeOut = function() {}, this.pollingCount = 0, this.pollingLimit = options.pollingLimit || 10, this.pollingDelay = options.delay || 1e3, this.delayGaps = options.delayGaps || 0, this.retryCount = 0, this.retryLimit = options.retryLimit || 10, this.retryAfter = options.retryAfter || 1e3, this.retryGaps = options.retryGaps || 300 }

function return_html(flag, data, code, codeType) { return flag || (code = ""), template("common/tbtjzy-item", { seachList: data, code: code, agent_from: mark.agent_from, codeType: codeType }) }

function seach_ssdm(f) {
    var ss_code = $("#ssdm").val(),
        _html1 = template("common/qecx-seach-item", { seachList: AreaList[0].sub, selectitem: ss_code }),
        _html2 = template("common/qecx-seach-item", { seachList: AreaList[1].sub, selectitem: ss_code });
    $("#ssdm").html('<option value="">请选择' + ("wap" == mark.agent_from ? "省市" : "") + '</option><optgroup label="' + AreaList[0].name + '">' + _html1 + '</optgroup><optgroup label="' + AreaList[1].name + '">' + _html2 + "</optgroup>"), null != getQueryString("id") ? seach_dwdm(!0) : ($("#dwxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "单位" : "") + "</option>"), $("#yxxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "院系所" : "") + "</option>"), $("#zyxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "专业" : "") + "</option>"), $("#fxxxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "研究方向" : "") + "</option>"), $("#xxfs").html('<option value="">请选择' + ("wap" == mark.agent_from ? "学习方式" : "") + "</option>"), $(".tj-tsyq-warp").not("tj-hide").addClass("tj-hide"), $("#tsyq").text("招生单位对该专业暂无特殊要求"))
}

function seach_dwdm(f) {
    $(".tj-tsyq-warp").not("tj-hide").addClass("tj-hide"), $("#tsyq").text("招生单位对该专业暂无特殊要求");
    var ss_code = $("#ssdm").val(),
        dw_code = $("#dwxx").val();
    init_ajax(Mainfest["tbtjzy-dwxx"].interface_url + "?code=" + ss_code, function(o) { $("#dwxx").html(return_html(f, o, dw_code, "1")), f ? seach_yxxx(!0) : ($(".dwxx-web").removeClass("mar-b0"), $(".tb-tip-time").hide(), $("#adjust_id").val(""), $("#yxxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "院系所" : "") + "</option>"), $("#zyxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "专业" : "") + "</option>"), $("#fxxxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "研究方向" : "") + "</option>"), $("#xxfs").html('<option value="">请选择' + ("wap" == mark.agent_from ? "学习方式" : "") + "</option>")) })
}

function seach_yxxx(f) {
    $(".tj-tsyq-warp").not("tj-hide").addClass("tj-hide"), $("#tsyq").text("招生单位对该专业暂无特殊要求");
    var dw_code = $("#dwxx").val(),
        yx_code = $("#yxxx").val();
    ksbh.substring(0, 5) == dw_code || 1 == _sxbz || 1 != sxbz2 && 1 != sxbz3 ? ($("#js-sxbz").html(""), $(".submit-tr .submit-btn").attr("disabled", !1), $(".submit-tr .submit-btn").css({ cursor: "pointer" })) : ($("#js-sxbz").html("您未达到国家线要求，只能按规定申请校内调剂。"), $(".submit-tr .submit-btn").attr("disabled", !0), $(".submit-tr .submit-btn").css({ cursor: "not-allowed" })), init_ajax(Mainfest["tbtjzy-yxxx"].interface_url + "?code=" + dw_code, function(o) { $("#yxxx").html(return_html(f, o, yx_code, "2")), o.length > 0 && "" != o[0].bz ? ($("#edit-time").text(o[0].bz), $(".dwxx-web").addClass("mar-b0"), $(".tb-tip-time").show()) : ($(".dwxx-web").removeClass("mar-b0"), $(".tb-tip-time").hide()), f ? seach_zyxx(!0) : ($("#adjust_id").val(""), $("#zyxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "专业" : "") + "</option>"), $("#fxxxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "研究方向" : "") + "</option>"), $("#xxfs").html('<option value="">请选择' + ("wap" == mark.agent_from ? "学习方式" : "") + "</option>")) })
}

function seach_zyxx(f) {
    $(".tj-tsyq-warp").not("tj-hide").addClass("tj-hide"), $("#tsyq").text("招生单位对该专业暂无特殊要求");
    var dw_code = $("#dwxx").val(),
        yx_code = $("#yxxx").val(),
        zy_code = $("#zyxx").val();
    init_ajax(Mainfest["tbtjzy-zyxx"].interface_url + "?code=" + dw_code + "&code1=" + yx_code, function(o) { $("#zyxx").html(return_html(f, o, zy_code, "3")), f && "" != $("#zyxx").val() ? seach_fxxxx(!0) : ($("#adjust_id").val(""), $("#fxxxx").html('<option value="">请选择' + ("wap" == mark.agent_from ? "研究方向" : "") + "</option>"), $("#xxfs").html('<option value="">请选择' + ("wap" == mark.agent_from ? "学习方式" : "") + "</option>")) })
}

function seach_fxxxx(f) {
    $(".tj-tsyq-warp").not("tj-hide").addClass("tj-hide"), $("#tsyq").text("招生单位对该专业暂无特殊要求");
    var dw_code = $("#dwxx").val(),
        yx_code = $("#yxxx").val(),
        zy_code = $("#zyxx").val(),
        fx_code = $("#fxxxx").val();
    init_ajax(Mainfest["tbtjzy-fxxxx"].interface_url + "?code=" + dw_code + "&code1=" + yx_code + "&code2=" + zy_code, function(o) { $("#fxxxx").html(return_html(f, o, fx_code, "4")), f && "" != $("#fxxxx").val() ? seach_xxfs(!0) : ($("#adjust_id").val(""), $("#xxfs").val('<option value="">请选择' + ("wap" == mark.agent_from ? "学习方式" : "") + "</option>")) })
}

function seach_xxfs(f) {
    $(".tj-tsyq-warp").not("tj-hide").addClass("tj-hide"), $("#tsyq").text("招生单位对该专业暂无特殊要求");
    var dw_code = $("#dwxx").val(),
        yx_code = $("#yxxx").val(),
        zy_code = $("#zyxx").val(),
        fx_code = $("#fxxxx").val(),
        fs_code = $("#xxfs").val();
    init_ajax(Mainfest["tbtjzy-xffs"].interface_url + "?code=" + dw_code + "&code1=" + yx_code + "&code2=" + zy_code + "&code3=" + fx_code, function(o) { bz_Array = o, $("#xxfs").html(return_html(f, o, fs_code, "5")), "" == bz_Array && "" != fx_code ? $("#fxxxx").siblings(".tj-error").html('<i class="iconfont">&#xe67c;</i>该研究方向无计划余额') : ($("#fxxxx").siblings(".tj-error").html(""), seach_bz()) })
}

function seach_bz() {
    $(".tj-tsyq-warp").not("tj-hide").addClass("tj-hide");
    var fs_code = $("#xxfs").val();
    if ("" == fs_code) return void $("#tsyq").html("");
    $(".tj-tsyq-warp").removeClass("tj-hide");
    for (var _html = "", i = 0; i < bz_Array.length; i++)
        if (fs_code == bz_Array[i].code) { var zy_id = bz_Array[i].id; return "" != zy_id && (_html = "<a onclick=\"template_zytb_tj('" + zy_id + "')\">查看申请条件</a>"), $("#tsyq").html(_html), void $("#adjust_id").val(bz_Array[i].id) }
}

function init_select_ss(agent_from) {
    var init_html = "web" == agent_from ? '<option value="">不限</option>' : '<option value="">（省市）不限</option>',
        _html1 = template("common/qecx-seach-item", { seachList: AreaList[0].sub }),
        _html2 = template("common/qecx-seach-item", { seachList: AreaList[1].sub });
    $("#ss").html(init_html + '<optgroup label="' + AreaList[0].name + '">' + _html1 + '</optgroup><optgroup label="' + AreaList[1].name + '">' + _html2 + "</optgroup>")
}

function change_seach(sysType, seachType, e) {
    preventDefaultEvent(e);
    var str = "tjyx" == sysType ? "-" : "",
        templateName = mark.agent_from + "/" + sysType + str + "qecx-seach";
    $("#tj_seach_change").replaceWith(template(templateName, { seachType: seachType })), init_select_ss(mark.agent_from)
}

function menuHighlight() {
    var _href = window.location.href,
        _str = _href.substring(_href.lastIndexOf("/") + 1),
        _url = _str.substring(0, _str.indexOf("."));
    "" != _url && "gosytj" != _url || (_url = "index");
    var curL = 0;
    if (navfest[_url]) {
        var _nav = navfest[_url].navhighlight,
            _obj = $(".tj-menu li[data-nav=" + _nav + "]");
        _obj.hasClass("act") || _obj.addClass("act"), _obj.siblings("li").removeClass("act");
        var _wapObj = $(".tj-menu-wap a[data-nav=" + _nav + "]");
        _wapObj.hasClass("act") || _wapObj.addClass("act"), _wapObj.siblings().removeClass("act"), $.each($(".tj-menu-wap a"), function(i, it) { i < _wapObj.index() && (curL += $(it).width()) }), $(".tj-menu-wap").animate({ scrollLeft: curL }, 100)
    }
}

function other_change(data) {
    var params = $.param(data);
    mark.flash && delete mark.flash;
    for (var key in mark) params += "&" + key + "=" + mark[key];
    return params
}

function modifyHeight(className, parentClassName) {
    var _array = [];
    $("." + className).each(function() { _array.push($(this).height()) }), $("." + className).css("height", _array.max()), $("." + parentClassName).removeClass(parentClassName)
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"),
        r = window.location.search.substr(1).match(reg);
    return null != r ? unescape(r[2]) : null
}

function formserialize(element) { return $(element).parents("form").serializeArray() }

function preventDefaultEvent(e) { e && e.preventDefault ? e.preventDefault() : window.event.returnValue = !1 }

function template_zytb_tj(id) {
    init_ajax(Mainfest["tj-sqtj"].interface_url + "?id=" + id, function(data) {
        var _html = template(Mainfest["tj-sqtj"].template, data),
            _area = (data.other.agent_from, ["auto", "auto"]);
        layer.alert(_html, { id: "info-alert", title: '<span class="color-blue" style="font-size: 16px;margin-right: 10px;">申请条件</span>', area: _area, scrollbar: !1 })
    })
}

function randerTemplate(interfaceName, data) { return template(Mainfest[interfaceName].template, data) }

function common_global_loading() { $("#loading").show() }

function common_global_unloading() { $("#loading").hide() }

function init_layout(agent_from, interfaceName) { $("body").prepend(template("common/layout", { agent_from: agent_from })), init_notice(agent_from, interfaceName) }

function init_layout_tjyx(agent_from, interfaceName) { $("body").prepend(template("common/tjyx-layout", { agent_from: agent_from })), init_notice(agent_from, interfaceName) }

function init_interval(agent_from, interfaceName, sysType) { window.setInterval(function() { init_ajax(Mainfest["notice-init"].interface_url + "?systype=" + sysType, function(data) { init_fs_dlq(sysType, data) }) }, 3e5) }

function init_notice(agent_from, interfaceName) {
    var sysType = "tjyx" == interfaceName.substring(0, 4) ? "TJYX" : "TJ",
        isshow = !1;
    if ("cjxx" == interfaceName || "yzytz" == interfaceName) { var _agent = "?agent_from=" + agent_from; return void init_ajax(Mainfest[interfaceName].interface_url + _agent, function(yzydata) { $("#yz_userid").html(yzydata.uname), yzydata.yzy_usable ? yzydata.flag ? ($("#content").html(randerTemplate(interfaceName, yzydata)), menuHighlight()) : $("#content").html(template("common/unuse", { page_error: yzydata.message.page_error })) : $("#content").append(template("common/sys", { sysData: yzydata.yzy_usable_des })) }) }
    "tjyx-index" != interfaceName && "index" != interfaceName || ($("#content").append(template(Mainfest[interfaceName].template, { agent_from: agent_from })), isshow = !0), init_ajax(Mainfest["notice-init"].interface_url + "?systype=" + sysType, function(data) {
        if (mark = data.other, $("#yz_userid").html(data_cookie(data)), isshow) $("#content").prepend(template("common/notice", { basedata: data, sysType: sysType })), menuHighlight(), init_fs_dlq(sysType, data);
        else if (init_sys_open(sysType, data), sysIsOpen) { if (init_isuse(sysType, data), isUse) return init_layout_interface(agent_from, interfaceName), void init_fs_dlq(sysType, data); return }
    })
}

function init_fs_dlq(sysType, o) {
    if ("TJ" == sysType) {
        var tz_array = o.data.notice.news,
            flag = "wap" == o.other.agent_from;
        if ("" != tz_array)
            for (var i = 0; i < tz_array.length; i++) "fs" == tz_array[i].type && (fs_tz = !0), "lq" == tz_array[i].type && (dlq_tz = !0);
        fs_tz ? flag ? $('.tj-menu-wap a[data-nav="fstz"] span').show() : $('.tj-menu li[data-nav="fstz"] span').show() : flag ? $('.tj-menu-wap a[data-nav="fstz"] span').hide() : $('.tj-menu li[data-nav="fstz"] span').hide(), dlq_tz ? flag ? $('.tj-menu-wap a[data-nav="dlqtz"] span').show() : $('.tj-menu li[data-nav="dlqtz"] span').show() : flag ? $('.tj-menu-wap a[data-nav="dlqtz"] span').hide() : $('.tj-menu li[data-nav="dlqtz"] span').hide()
    }
}

function init_sys_open(sysType, o) { "TJYX" == sysType ? (sysIsOpen = o.tjyx_usable, o.tjyx_usable || $("#content").append(template("common/sys", { sysData: o.tjyx_usable_des }))) : (sysIsOpen = o.tj_usable, o.tj_usable || $("#content").append(template("common/sys", { sysData: o.tj_usable_des }))) }

function init_isuse(sysType, o) {
    var flag = "TJYX" == sysType;
    flag ? _sxbz = o.tjyxsxbz : (_sxbz = o.sxbz, sxbz2 = o.sxbz2, sxbz3 = o.sxbz3, ksbh = o.ksbh), 1 != _sxbz && ("1" == o.sxbz2 || "1" == o.sxbz3 || (isUse = !1, flag ? $("#content").html(template("common/tjyx-unuse", { _sxbz: _sxbz, userid: o.userid, _agent_from: o.other.agent_from, page_error: o.message.page_error })) : $("#content").html(template("common/unuse", { _sxbz: _sxbz, userid: o.userid, _agent_from: o.other.agent_from, page_error: o.message.page_error }))))
}

function init_layout_interface(agent_from, interfaceName) {
    if (menuHighlight(), "tjyx-qecx" == interfaceName || "qecx" == interfaceName) return void $("#content").append(template(Mainfest[interfaceName].template, { agent_from: agent_from, seachType: "accurate", sxbz2: sxbz2, sxbz3: sxbz3 }));
    var _hasId = "",
        _agent = "?agent_from=" + agent_from;
    if ("tbtjzy" == interfaceName || "tjyx-tbtjzy" == interfaceName || "tj-tjjcap" == interfaceName) {
        _hasId = "&id=" + getQueryString("id");
        2 == getQueryString("zy_type") && (interfaceName += "-edit")
    }
    init_ajax(host + Mainfest[interfaceName].interface_url + _agent + _hasId, function(o) { init_page(interfaceName, "content", o), "mytjzy" != interfaceName && "tjyx-mytjyx" != interfaceName || (modifyHeight("zy-main", "zy-main-postion"), zy_status = o.data.notice.status, zy_status.cur_num < zy_status.max_num && $(function() { $(".ewm-wap").addClass("ewm-wap-top") })) })
}

function init_page(interfaceName, element, data) { if (data.flag) { if ("tjyx-qecx" == interfaceName) return void $("#content").html(template("common/tjyx-qecx", { agent_from: agent_from })); if ("qecx" == interfaceName) return void $("#content").html(template("common/qecx", { agent_from: agent_from, sxbz2: sxbz2, sxbz3: sxbz3 })); "qecx-list" == interfaceName || "tjyx-qecx-list" == interfaceName || "qecx-list-wap" == interfaceName || "tjyx-qecx-list-wap" == interfaceName ? $("#" + element).replaceWith(randerTemplate(interfaceName, data)) : ($("#" + element).html(randerTemplate(interfaceName, data)), "tbtjzy-edit" == interfaceName && seach_ssdm(!0)) } else { if ("tbtjzy" == interfaceName || "tjyx-tbtjzy" == interfaceName || "unuse" == interfaceName || "tjyx-unuse" == interfaceName) return void $("#" + element).html(randerTemplate(interfaceName, data)); "" != data.message.page_error ? layer.alert(data.message.page_error, { title: "提示" }, function(index) { "qecx-list" == interfaceName || "tjyx-qecx-list" == interfaceName || "qecx-list-wap" == interfaceName || "tjyx-qecx-list-wap" == interfaceName ? layer.close(index) : window.location.reload() }) : "" != data.message.error && layer.alert(data.message.error[0].mc, { title: "提示" }, function(index) { "qecx-list" == interfaceName || "tjyx-qecx-list" == interfaceName || "qecx-list-wap" == interfaceName || "tjyx-qecx-list-wap" == interfaceName ? layer.close(index) : "tbtjzy-edit" == interfaceName ? window.location.href = "tjzy.html" : window.location.reload() }) } }

function data_cookie(o) { return null != $.cookie("userName") ? $.cookie("userName") : ($.cookie("userName", o.uname), $.cookie("userId", o.userid), o.uname) }

function init_ajax(url, callbackfun) {
    $.ajax({
        type: "get",
        url: url,
        cache: !1,
        beforeSend: function() {
            common_global_loading()
        },
        success: function(callbackdata) {
            chose_ajax("get", url, callbackdata, callbackfun, "")
        },
        complete: function(XMLHttpRequest, textStatus) {
            "400" == XMLHttpRequest.status && (
                    common_global_unloading(),
                    layer.alert("访问过于频繁，请稍后再试。", { closeBtn: 0, title: "提示" }, function(index) { layer.close(index) })
                ),
                "403" == XMLHttpRequest.status && (
                    common_global_unloading(),
                    layer.alert("操作失败，请检查输入的字符或操作是否合法。", { closeBtn: 0, title: "提示" }, function(index) { layer.close(index) })
                )
        },
        timeout: 6e3,
        error: function(XMLHttpRequest, textStatus, errorThrown) { error_ajax(XMLHttpRequest, textStatus, errorThrown) }
    })
}

function post_ajax(url, postdata, callbackfun) { $.ajax({ type: "post", url: url, traditional: !1, cache: !1, beforeSend: function() { common_global_loading() }, data: other_change(postdata), success: function(callbackdata) { chose_ajax("post", url, callbackdata, callbackfun, postdata) }, complete: function(XMLHttpRequest, textStatus) { "400" == XMLHttpRequest.status && (common_global_unloading(), layer.alert("访问过于频繁，请稍后再试。", { closeBtn: 0, title: "提示" }, function(index) { layer.close(index) })), "403" == XMLHttpRequest.status && (common_global_unloading(), layer.alert("操作失败，请检查输入的字符或操作是否合法。", { closeBtn: 0, title: "提示" }, function(index) { layer.close(index) })) }, timeout: 6e3, error: function(XMLHttpRequest, textStatus, errorThrown) { error_ajax(XMLHttpRequest, textStatus, errorThrown) } }) }

function chose_ajax(type, url, callbackdata, callbackfun, postdata) {
    if ("object" != typeof callbackdata) { if (16 == $.trim(callbackdata).length) { var polling = new AjaxPolling(callbackdata, "", "/sytj/asynprogress.do", { retryLimit: 4, delayGaps: 300 }); return polling.onSuccess = function(pollingData) { return "get" == type ? void init_ajax(url + "&taskId=" + callbackdata, callbackfun) : void post_ajax(url + "?taskId=" + callbackdata, postdata, callbackfun) }, polling.onTimeOut = function() { common_global_unloading(), layer.alert("请求超时，请稍后再试。", { title: "提示" }) }, polling.onError = function(xhr, retryCount) { layer.alert("请求人数过多，请稍后再试。", { title: "提示" }, function(index) { layer.close(index) }) }, void polling.start() } return common_global_unloading(), void layer.alert("请求人数过多，请稍后再试。", { title: "提示" }, function(index) { layer.close(index) }) }
    common_global_unloading(), callbackfun(callbackdata)
}

function error_ajax(XMLHttpRequest, textStatus, errorThrown) { common_global_unloading(), "timeout" == textStatus ? layer.alert("请求超时，请稍后再试。", { title: "提示" }, function(index) { layer.close(index) }) : "error" == textStatus && layer.alert("操作已过时，请 <a href='https://yz.chsi.com.cn/user/'>重新登录</a>。", { closeBtn: 0, title: "提示" }, function(index) { layer.close(index) }) }

function template_bdzkzh(interfaceName, element, e) {
    if (preventDefaultEvent(e), MyValidator.init(), !$(".fromSubmit").validate().form()) return !1;
    var form_data = formserialize(element);
    post_ajax(Mainfest[interfaceName].interface_url, form_data, function(o) {
        if (o.flag) layer.alert("准考证绑定成功", { closeBtn: 0, title: "提示" }, function() { window.location.href = "cjxx.html" });
        else {
            var _obj = o.message.page_error,
                _html = '<div class="tj-error-global"><i class="iconfont">&#xe67c;</i>' + _obj + "</div>";
            $(".tj-error").html(_html)
        }
    })
}

function template_fstz(interfaceName, data, element) { var warn_text = "确实要接受吗？确定后不能更改。"; "fstz-post-reject" == interfaceName && (warn_text = "确实要拒绝吗？请慎重，确定后不能更改。"), layer.confirm(warn_text, { btn: ["确定", "取消"], title: "提示" }, function(index) { layer.close(index), post_ajax(Mainfest[interfaceName].interface_url, { id: data }, function(o) { init_page(interfaceName, element, o) }) }, function(index) { return layer.close(index), !1 }) }

function template_dlqtz(interfaceName, data, element) { var warn_text = "考生一经确认，非经招生单位同意不得调剂录取至其他招生单位，确定要接受？"; "dlqtz-post-reject" == interfaceName && (warn_text = "确实要拒绝吗？请慎重，确定后不能更改。"), layer.confirm(warn_text, { btn: ["确定", "取消"], title: "提示" }, function(index) { layer.close(index), post_ajax(Mainfest[interfaceName].interface_url, { id: data }, function(o) { init_page(interfaceName, element, o) }) }) }

function template_dlqtz_cancel(interfaceName, data, element) { var warn_text = "确实要接受吗？确定后不能更改。"; "dlqtz-post-rejectcancel" == interfaceName && (warn_text = "确实要拒绝吗？"), layer.confirm(warn_text, { btn: ["确定", "取消"], title: "提示" }, function(index) { layer.close(index), post_ajax(Mainfest[interfaceName].interface_url, { id: data }, function(o) { init_page(interfaceName, element, o) }) }) }

function template_dlqtz_cancel_yzy(interfaceName, data, element) {
    var _sysType = "tjyx" == interfaceName.substring(0, 4) ? "TJYX" : "TJ",
        warn_text = "确实要接受吗？确定后不能更改。";
    "yzy-dlqtz-cancel-reject" == interfaceName && (warn_text = "确实要拒绝吗？"), layer.confirm(warn_text, { btn: ["确定", "取消"], title: "提示" }, function(index) { layer.close(index), post_ajax(Mainfest[interfaceName].interface_url, { id: data, systype: _sysType }, function(o) { init_page(interfaceName, element, o) }) })
}

function template_zytb_add(id) {
    if (zy_status.cur_num >= zy_status.max_num) {
        var sysName = mark.pageid,
            msg = "tj_qe_list" == sysName ? "调剂志愿" : "调剂意向";
        layer.alert("您填报的" + msg + '已达上限 <strong class="color-blue">' + zy_status.max_num + "</strong> 个，不可继续填报！", { title: "提示" })
    } else window.location.href = "tbtjzy.html?zy_type=1&id=" + id
}

function template_zytb_edit(id) { window.location.href = "tbtjzy.html?zy_type=2&id=" + id }

function template_zytb_submit(interfaceName, element, e) {
    var sysName = interfaceName.substring(0, 4),
        confirmTime = "";
    if ("tjyx" != sysName ? ("填报调剂志愿", confirmTime = "在<span class='color-blue'>" + $("#edit-time").text() + "</span>小时内不可修改（该时间由招生单位设置），") : confirmTime = "填报的调剂意向不可修改，", MyValidator.init(), !$(".fromSubmit").validate().form()) return !1;
    preventDefaultEvent(e);
    var sysmsg = "tjyx" != sysName ? "调剂志愿" : "调剂意向";
    layer.confirm("请确认您填报的" + sysmsg + "是否符合教育部招生工作管理规定和招生单位报考条件及要求，提交之后" + confirmTime + "确定要提交吗？", { btn: ["确定", "取消"], title: "提示" }, function(index) {
        layer.close(index);
        var form_data = formserialize(element);
        post_ajax(Mainfest[interfaceName].interface_url, form_data, function(o) { template_zytb_callback(o, interfaceName) })
    })
}

function template_zytb_callback(data, interfaceName) {
    var sysType = "tjyx" == interfaceName.substring(0, 4) ? "TJYX" : "TJ",
        agent_from = data.other.agent_from;
    if (data.flag) "TJYX" == sysType ? $("#content").html(template("common/tjyx-success", { agent_from: agent_from })) : $("#content").html(template("common/success", { agent_from: agent_from, zy_type: interfaceName }));
    else {
        for (var _errorMessage = data.message.error, _html = "", i = 0; i < _errorMessage.length; i++) { var _obj = _errorMessage[i]; "yddh" == _obj.dm ? $("#yddh").focus().siblings(".tj-error").html('<i class="iconfont">&#xe67c;</i>' + _obj.mc) : "bz" == _obj.dm ? $("#bz").focus().siblings(".tj-error").html('<i class="iconfont">&#xe67c;</i>' + _obj.mc) : ($("#yddh").siblings(".tj-error").html(""), $("#bz").siblings(".tj-error").html(""), "jybtjzz" == _obj.dm && (_obj.mc = '<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202103/20210305/2035189760.html">' + _obj.mc + "</a>"), "wap" == data.other.agent_from ? _html += '<tr class="tj-error-tr"><td><div class="tj-error-global"><i class="iconfont">&#xe67c;</i>' + _obj.mc + "</div></td></tr>" : _html += '<tr class="tj-error-tr"><td></td><td colspan="2"><div class="tj-error-global"><i class="iconfont">&#xe67c;</i>' + _obj.mc + "</div></td></tr>") }
        $(".tj-error-tr").length > 0 ? $(".tj-error-tr").replaceWith(_html) : $(".submit-tr").before(_html)
    }
}

function pageclick(page) {
    var page_start = page_size * (page - 1);
    $("#start").val(page_start), $("#page_size").val(page_size), qecx_post($("#sysname").val(), $("#tj_seach_form").serializeArray())
}

function test_seach() {
    var dwxxValue = $("#dwxx").val(),
        zyxxValue = $("#zyxx").val();
    return "" != dwxxValue || "" != zyxxValue || (layer.alert("招生单位和专业至少填写其中一项", { title: "提示" }, function(index) { layer.close(index) }), !1)
}

function test_seach_fuzzy() { return !($.trim($("#dwxx").val()).length < 2 && (layer.alert("招生单位或专业名称的关键词长度至少为2个字符", { title: "提示" }, function(index) { layer.close(index) }), 1)) }

function template_qecx_seach(interfaceName, element, e, isTest) {
    if (preventDefaultEvent(e), $("#order_by").val(""), $("#start").val(""), 1 == isTest) {
        if (test_seach()) {
            $("#page_size").val(page_size);
            var form_data = formserialize(element);
            qecx_post(interfaceName, form_data)
        }
    } else if (test_seach_fuzzy()) {
        $("#page_size").val(page_size);
        var form_data = formserialize(element);
        qecx_post(interfaceName, form_data)
    }
}

function qecx_post(interfaceName, form_data) { post_ajax(Mainfest[interfaceName].interface_url, form_data, function(o) { mark = o.other, o.flag ? (zy_status = o.data.notice.status, init_page(interfaceName, "content-qecxList", o), "qecx-list-wap" != interfaceName && "tjyx-qecx-list-wap" != interfaceName || (init_scoll(), pagenation = o.data.vo_list.pagenation), rander_order()) : layer.alert(o.message.page_error, { title: "提示" }, function(index) { layer.close(index) }) }) }

function order_qelist() {
    $("body").on("click", ".sort-icon", function() {
        var _obj = $(this);
        order_change(_obj);
        var _name = _obj.attr("data-name"),
            _value = _obj.attr("data-value");
        "" != _value ? $("#order_by").val(_name + "-" + _value) : $("#order_by").val(""), template_qecx_sort()
    })
}

function template_qecx_sort() { $("#start").val(""), $("#page_size").val(page_size), qecx_post($("#sysname").val(), $("#tj_seach_form").serializeArray()) }

function order_change(o) {
    switch (o.attr("data-value")) {
        case "desc":
            o.attr("data-value", "");
            break;
        case "asc":
            o.attr("data-value", "desc");
            break;
        case "":
            o.attr("data-value", "asc")
    }
}

function rander_order() {
    $(".sort-icon").attr("data-value", "");
    var str = $("#order_by").val();
    if ("" != str) {
        var strArry = str.split("-");
        $('.sort-icon[data-name="' + strArry[0] + '"]').removeClass("asc").removeClass("desc").addClass(strArry[1]).attr("data-value", strArry[1]), $('.sort-icon[data-name!="' + strArry[0] + '"]').removeClass("asc").removeClass("desc").attr("data-value", "")
    } else $(".sort-icon").removeClass("asc").removeClass("desc").attr("data-value", "")
}

function template_totjzy(interfaceName, id, e) { preventDefaultEvent(e), layer.confirm("确定要将调剂意向转为调剂志愿吗？", { btn: ["确定", "取消"], title: "提示" }, function(index) { layer.close(index), post_ajax(Mainfest[interfaceName].interface_url, { id: id }, function(o) { o.flag ? (init_page("tjyx-mytjyx", "content", o), modifyHeight("zy-main", "zy-main-postion")) : "" != o.message.page_error ? layer.alert(o.message.page_error, { title: "提示" }, function(index) { layer.close(index) }) : layer.alert(o.message.error[0].mc, { title: "提示" }, function(index) { layer.close(index) }) }) }) }

function fuzzy_query(interfaceName, sysType) {
    var flag = "zyxx-init" == interfaceName || "tjyx-zyxx-init" == interfaceName,
        _obj = flag ? $("#zyxx") : $("#dwxx");
    _obj.autocomplete({
        minLength: 2,
        max: 5,
        delay: 400,
        source: function(request, response) {
            var mc = $.trim(_obj.val()),
                ssdm = "" == $("#ss").val() ? "00" : $("#ss").val(),
                postdata = flag ? $.md5(mc) + ".json" : ssdm + "/" + $.md5(mc) + ".json";
            $.ajax({
                type: "get",
                dataType: "json",
                url: Mainfest[interfaceName].interface_url + postdata,
                success: function(data) {
                    if (null == data) return void response();
                    response($.map(data, function(item) { return flag ? { value: item.name, label: item.name } : { value: "(" + item.code + ")" + item.name, label: "(" + item.code + ")" + item.name } }))
                },
                error: function(data) {}
            })
        }
    })
}

function init_scoll() {
    window.wapLoaded = !0, window.onscroll = function() {
        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop,
            viewH = $("body").height(),
            contentH = $("body").get(0).scrollHeight;
        window.wapLoaded && scrollTop / (contentH - viewH) >= .95 && (window.wapLoaded = !1, Load())
    }
}

function Load() {
    var _nextpage = pagenation.curPage + 1,
        page_start = page_size * pagenation.curPage;
    $("#start").val(page_start);
    var form_data = $("#tj_seach_form").serializeArray();
    if (_nextpage <= Math.ceil(pagenation.totalCount / pagenation.pageCount)) {
        var interfaceName, el = $("#wrapper ul"),
            sysName = mark.pageid;
        interfaceName = "tjyx_qe_list" == sysName ? "tjyx-qecx-list" : "qecx-list", post_ajax(Mainfest[interfaceName].interface_url, form_data, function(o) { window.wapLoaded = !0, "tjyx-qecx-list" == interfaceName ? (el.append(template("wap/tjyx-qecx-list-item", o)), pagenation = o.data.vo_list.pagenation) : (el.append(template("wap/qecx-list-item", o)), pagenation = o.data.vo_list.pagenation) })
    }
}

function ckyy(text) { layer.alert(text, { title: "详细", btn: ["关闭"] }, function(index) { layer.close(index) }) }

function acceptJs(id, jsyy) { layer.confirm("该调剂志愿将无效，可继续填报其他志愿。", { btn: ["确定", "取消"], title: "提示" }, function(index) { layer.close(index), post_ajax("/sytj/stu/agreeapplyjs.action", { id: id }, function(o) { init_page("mytjzy", "content", o), modifyHeight("zy-main", "zy-main-postion") }) }, function(index) { return layer.close(index), !1 }) }

function rejectJs(id, jsyy) { layer.confirm("该调剂志愿锁定时间到达后方可继续填报其他志愿。", { btn: ["确定", "取消"], title: "提示" }, function(index) { layer.close(index), post_ajax("/sytj/stu/disagreeapplyjs.action", { id: id }, function(o) { init_page("mytjzy", "content", o), modifyHeight("zy-main", "zy-main-postion") }) }, function(index) { return layer.close(index), !1 }) }

function html2Escape(sHtml) { return sHtml.replace(/[<>&"]/g, function(c) { return { "<": "&lt;", ">": "&gt;", "&": "&amp;", '"': "&quot;" }[c] }) }! function() {
    function template(filename, content) { return (/string|function/.test(typeof content) ? compile : renderFile)(filename, content) }

    function toString(value, type) { return "string" != typeof value && (type = typeof value, "number" === type ? value += "" : value = "function" === type ? toString(value.call(value)) : ""), value }

    function escapeFn(s) { return escapeMap[s] }

    function escapeHTML(content) { return toString(content).replace(/&(?![\w#]+;)|[<>"']/g, escapeFn) }

    function each(data, callback) {
        if (isArray(data))
            for (var i = 0, len = data.length; i < len; i++) callback.call(data, data[i], i, data);
        else
            for (i in data) callback.call(data, data[i], i)
    }

    function resolve(from, to) {
        var DOUBLE_DOT_RE = /(\/)[^/]+\1\.\.\1/,
            dirname = ("./" + from).replace(/[^/]+$/, ""),
            filename = dirname + to;
        for (filename = filename.replace(/\/\.\//g, "/"); filename.match(DOUBLE_DOT_RE);) filename = filename.replace(DOUBLE_DOT_RE, "/");
        return filename
    }

    function renderFile(filename, data) { var fn = template.get(filename) || showDebugInfo({ filename: filename, name: "Render Error", message: "Template not found" }); return data ? fn(data) : fn }

    function compile(filename, fn) {
        if ("string" == typeof fn) {
            var string = fn;
            fn = function() { return new String(string) }
        }
        var render = cache[filename] = function(data) { try { return new fn(data, filename) + "" } catch (e) { return showDebugInfo(e)() } };
        return render.prototype = fn.prototype = utils, render.toString = function() { return fn + "" }, render
    }

    function showDebugInfo(e) {
        var type = "{Template Error}",
            message = e.stack || "";
        if (message) message = message.split("\n").slice(0, 2).join("\n");
        else
            for (var name in e) message += "<" + name + ">\n" + e[name] + "\n\n";
        return function() { return "object" == typeof console && console.error(type + "\n\n" + message), type }
    }
    var cache = template.cache = {},
        String = this.String,
        escapeMap = { "<": "&#60;", ">": "&#62;", '"': "&#34;", "'": "&#39;", "&": "&#38;" },
        isArray = Array.isArray || function(obj) { return "[object Array]" === {}.toString.call(obj) },
        utils = template.utils = { $helpers: {}, $include: function(filename, data, from) { return filename = resolve(from, filename), renderFile(filename, data) }, $string: toString, $escape: escapeHTML, $each: each },
        helpers = template.helpers = utils.$helpers;
    template.get = function(filename) { return cache[filename.replace(/^\.\//, "")] },
        template.helper = function(name, helper) { helpers[name] = helper },
        "function" == typeof define ?
        define(function() { return template }) : "undefined" != typeof exports ? module.exports = template : this.template = template,
        template.helper("changeLetter", function(data) { return String.fromCharCode(data + 65) }),
        template.helper("defValue", function(data, v) { return "" != data && data ? data : v }),
        template.helper("gxsjHelper", function(data) {
            return data <= 1 ? "刚刚" : data <= 60 ? data + "分钟前" : data <= 1440 ? Math.floor(data / 60) + "小时" + (data - 60 * Math.floor(data / 60)) + "分钟前" : parseInt(data / 1440) + "天前"
        }),
        template.helper("changeNum", function(data, v) {
            var cur_num = parseInt(data),
                max_num = parseInt(v);
            return cur_num > max_num ? max_num : cur_num < 0 ? 0 : data
        }), template.helper("pagehelp", function(data) { return Math.ceil(data) }), template.helper("pagerander", function(data) {
            var totalPage = Math.ceil(data.totalCount / data.pageCount),
                curPage = data.curPage;
            return function(count, pageindex) {
                function setPageList() { a[a.length] = pageindex == i ? '<li class="tj-paging-item tj-paging-check"><a href="###" class="on" onclick="pageclick(' + i + ')">' + i + "</a></li>" : '<li class="tj-paging-item"><a href="###" onclick="pageclick(' + i + ')">' + i + "</a></li>" }
                var a = [];
                if (a[a.length] = 1 == pageindex ? '<li class="tj-paging-item tj-paging-disabled"><a href="###" class="prev unclick">上一页</a></li>' : '<li class="tj-paging-item"><a href="###" class="prev" onclick="pageclick(' + (curPage - 1) + ')">上一页</a></li>', count <= 10)
                    for (var i = 1; i <= count; i++) setPageList();
                else if (pageindex <= 4) {
                    for (var i = 1; i <= 5; i++) setPageList();
                    a[a.length] = '<li class="tj-paging-item"><a href="###">...</a></li><li class="tj-paging-item"><a href="###" onclick="pageclick(' + count + ')">' + count + "</a></li>"
                } else if (pageindex >= count - 3) { a[a.length] = '<li class="tj-paging-item"><a href="###" onclick="pageclick(1)">1</a></li><li class="tj-paging-item"><a href="###">...</a></li>'; for (var i = count - 4; i <= count; i++) setPageList() } else {
                    a[a.length] = '<li class="tj-paging-item"><a href="###" onclick="pageclick(1)">1</a></li><li class="tj-paging-item"><a href="###">...</a></li>';
                    for (var i = pageindex - 2; i <= pageindex + 2; i++) setPageList();
                    a[a.length] = '<li class="tj-paging-item"><a href="###">...</a></li><li class="tj-paging-item"><a href="###" onclick="pageclick(' + count + ')">' + count + "</a></li>"
                }
                return a[a.length] = pageindex == count ? '<li class="tj-paging-item tj-paging-disabled"><a href="###" class="next unclick">下一页</a></li>' : '<li class="tj-paging-item"><a href="###" class="next" onclick="pageclick(' + (curPage + 1) + ')">下一页</a></li>', a.join("")
            }(totalPage, curPage)
        }), template("common/cjxx", function($data, $filename) {
            "use strict";
            var $utils = this,
                $helpers = $utils.$helpers,
                flag = $data.flag,
                other = $data.other,
                $each = $utils.$each,
                $escape = ($data.value, $data.index, $utils.$escape),
                data = $data.data,
                $out = "";
            return $out += " ", flag && ($out += " ", other.flash && ($out += " ", "" != other.flash.warnings && ($out += ' <div class="info-prompt mar-t20"> <ul> ', $each(other.flash.warnings, function(value, index) { $out += " <li>", $out += $escape(value.title), $out += "：", $out += $escape(value.des), $out += "</li> " }), $out += " </ul> </div> "), $out += " "), $out += " ", "" != data.vo && ($out += " ", "wap" == other.agent_from && ($out += ' <h5 class="tj-cjxx-title">初试成绩</h5> '), $out += ' <div class="tj-cjxx-warp mar-t20"> ', "web" == other.agent_from ? ($out += ' <h5 class="tj-cjxx-title">初试成绩</h5> <table class="tj-cjxx"> <tr> <td class="line1">姓名：</td> <td class="line2">', $out += $escape(data.vo.xm), $out += '</td> <td class="line3">（', $out += $escape(data.vo.zzllm), $out += "）", $out += $escape(data.vo.zzllmc), $out += '：</td> <td class="line4">', $out += $escape($helpers.defValue(data.vo.zzllcj, "暂无")), $out += '</td> <td rowspan="4"><div class="total"><h6>总分</h6><span>',
                $out += $escape($helpers.defValue(data.vo.zfcj, "暂无")), $out += '</span></div></td> </tr> <tr> <td class="line1">准考证号：</td> <td class="line2">', $out += $escape(data.vo.ksbh), $out += '</td> <td class="line3">（', $out += $escape(data.vo.wgym), $out += "）", $out += $escape(data.vo.wgymc), $out += '：</td> <td class="line4">', $out += $escape($helpers.defValue(data.vo.wgycj, "暂无")), $out += '</td> </tr> <tr> <td class="line1">报名号：</td> <td class="line2">', $out += $escape($helpers.defValue(data.vo.bmh, "暂无")), $out += '</td> <td class="line3">（', $out += $escape(data.vo.ywk1m), $out += "）", $out += $escape(data.vo.ywk1mc), $out += '：</td> <td class="line4">', $out += $escape($helpers.defValue(data.vo.ywk1cj, "暂无")), $out += '</td> </tr> <tr> <td class="line1 text-t">初试专业：</td> <td class="line2 text-t">（', $out += $escape(data.vo.bkzydm), $out += "）", $out += $escape($helpers.defValue(data.vo.bkzymc, "")), $out += '</td> <td class="line3 text-t">（', $out += $escape(data.vo.ywk2m), $out += "）", $out += $escape(data.vo.ywk2mc), $out += '：</td> <td class="line4 text-t">', $out += $escape($helpers.defValue(data.vo.ywk2cj, "暂无")), $out += "</td> </tr> </table> ") : ($out += ' <div class="tj-cjxx-wap"> <table class="tj-cjxx"> <tr> <td class="tj-base">姓名：</td> <td class="tj-cjxx-val">', $out += $escape(data.vo.xm), $out += '</td> </tr> <tr> <td class="tj-base">准考证号：</td> <td class="tj-cjxx-val">', $out += $escape(data.vo.ksbh), $out += '</td> </tr> <tr> <td class="tj-base">报名号：</td> <td class="tj-cjxx-val">', $out += $escape($helpers.defValue(data.vo.bmh, "暂无")), $out += '</td> </tr> <tr> <td class="tj-base text-t">初试专业：</td> <td class="tj-cjxx-val text-t">（', $out += $escape(data.vo.bkzydm), $out += "）", $out += $escape($helpers.defValue(data.vo.bkzymc, "")), $out += '</td> </tr> </table> <table class="tj-cjxx"> <tr class="cut"> <td class="tj-base tj-base-long">（', $out += $escape(data.vo.zzllm), $out += "）", $out += $escape(data.vo.zzllmc), $out += '</td> <td class="tj-cjxx-val tj-cjxx-num tj-base-longval">', $out += $escape($helpers.defValue(data.vo.zzllcj, "暂无")), $out += '</td> </tr> <tr> <td class="tj-base tj-base-long">（', $out += $escape(data.vo.wgym), $out += "）", $out += $escape(data.vo.wgymc), $out += '</td> <td class="tj-cjxx-val tj-cjxx-num tj-base-longval">', $out += $escape($helpers.defValue(data.vo.wgycj, "暂无")), $out += '</td> </tr> <tr> <td class="tj-base tj-base-long">（', $out += $escape(data.vo.ywk1m), $out += "）", $out += $escape(data.vo.ywk1mc), $out += '</td> <td class="tj-cjxx-val tj-cjxx-num tj-base-longval">', $out += $escape($helpers.defValue(data.vo.ywk1cj, "暂无")), $out += '</td> </tr> <tr> <td class="tj-base cut-td tj-base-long">（', $out += $escape(data.vo.ywk2m), $out += "）", $out += $escape(data.vo.ywk2mc), $out += '</td> <td class="tj-cjxx-val tj-cjxx-num cut-td tj-base-longval">', $out += $escape($helpers.defValue(data.vo.ywk2cj, "暂无")), $out += '</td> </tr> <tr class="cut"> <td class="tj-base tj-base-count">总分</td> <td class="tj-cjxx-val score-sum">', $out += $escape($helpers.defValue(data.vo.zfcj, "暂无")), $out += "</td> </tr> </table> </div> "), $out += ' <div class="tip-content-detail">如果对成绩有疑问，请咨询招生单位。</div> </div> '), $out += " "), new String($out)
        }), template("common/dlqtz", function($data, $filename) {
            "use strict";
            var $utils = this,
                flag = ($utils.$helpers, $data.flag),
                data = $data.data,
                $each = $utils.$each,
                other = ($data.value, $data.index, $data.other),
                $escape = $utils.$escape,
                $string = $utils.$string,
                $out = "";
            return $out += ' <div class="info-prompt mar-t20"> <ul> <li class="mar-b6">提示：考生只能接受一个调剂志愿的待录取，一旦接受待录取通知，表示调剂完成，将不能再填报调剂志愿、接受复试或待录取通知，请慎重选择！</li> <li>由于各方面情况的不断调整与变化，考生实际录取院系所专业，以招生单位实际录取情况为准。</li> </ul> </div> ', flag && ($out += " ", "" != data.vo_list.vos ? ($out += ' <div class="clearfix mar-t20 tz-content"> ', $each(data.vo_list.vos, function(value, index) { $out += " ", "web" == other.agent_from ? ($out += ' <div class="fs-warp mar-t20 clearfix"> <div class="fs-warp-left"> <h5>(', $out += $escape(value.dwdm), $out += ")", $out += $escape(value.dwmc), $out += " - (", $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += " - (", $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += " - (", $out += $escape(value.yjfxdm), $out += ")", $out += $escape(value.yjfxmc), "1" == value.xxfs ? $out += " - 全日制" : "2" == value.xxfs && ($out += " - 非全日制"), $out += '</h5> <div class="timer">发送时间：', $out += $escape(value.dlqtz_fssj), $out += "</div> <h6>", $out += $escape(value.dlqtz_bt), $out += '</h6> <div class="content">', $out += $string(value.dlqtz_nr), $out += " ", "" != value.dlqtz_fj_lobid && ($out += ' <br>附件：<a href="', $out += $escape("/sytj/stu/dlqtzfj.action?id=" + value.dlqtz_fj_lobid + "&c1=" + value.c1 + "&c2=" + value.c2), $out += '" download="', $out += $escape(value.dlqtz_fj_name), $out += '" download="', $out += $escape(value.dlqtz_fj_name), $out += '">', $out += $escape(value.dlqtz_fj_name), $out += "</a> "), $out += ' </div> </div> <div class="fs-warp-right"> ', 1 == value.dlqtz_zt ? ($out += ' <div class="fs-opt clearfix"> <a href="javascript:;" class="refuse" onclick="template_dlqtz(\'dlqtz-post-reject\',\'', $out += $escape(value.id), $out += "','content')\">拒绝待录取</a> <a href=\"javascript:;\" onclick=\"template_dlqtz('dlqtz-post-accept','", $out += $escape(value.id), $out += "','content')\">接受待录取</a> </div> ") : 2 == value.dlqtz_zt ? ($out += ' <div class="reply reply-accept"> <i class="iconfont">&#xe67e;</i> <div class="reply-result">您已接受该待录取通知</div> <div class="reply-time">', $out += $escape(value.dlqtz_hfsj), $out += "</div> </div> ") : 3 == value.dlqtz_zt ? ($out += ' <div class="reply reply-refuse"> <i class="iconfont">&#xe67f;</i> <div class="reply-result">您已拒绝该待录取通知</div> <div class="reply-time">', $out += $escape(value.dlqtz_hfsj), $out += "</div> </div> ") : 4 == value.dlqtz_zt && ($out += ' <div class="fs-opt clearfix"> <div class="cancel-refuse"><i class="iconfont">&#xe67d;</i>招生单位取消待录取</div> <a href="javascript:;" class="clear" onclick="template_dlqtz_cancel(\'dlqtz-post-acceptcancel\',\'', $out += $escape(value.id), $out += "','content')\">接受取消待录取</a> \x3c!-- <a href=\"javascript:;\" class=\"refuse\" onclick=\"template_dlqtz_cancel('dlqtz-post-rejectcancel','", $out += $escape(value.id), $out += "','content')\">拒绝取消待录取</a> --\x3e </div> "), $out += " </div> </div> ") : ($out += ' <div class="fs-main tjyx"> <div class="fs-cxt"> <div class="fs-cxt-title"> <h5>(', $out += $escape(value.dwdm), $out += ")", $out += $escape(value.dwmc), $out += " - (", $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += " - (", $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += " - (", $out += $escape(value.yjfxdm), $out += ")", $out += $escape(value.yjfxmc), "1" == value.xxfs ? $out += " - 全日制" : "2" == value.xxfs && ($out += " - 非全日制"), $out += '</h5> <div class="timer">发送时间：', $out += $escape(value.dlqtz_fssj), $out += '</div> </div> <div class="fs-cxt-content"> <h6>', $out += $escape(value.dlqtz_bt), $out += '</h6> <div class="content">', $out += $string(value.dlqtz_nr), $out += " ", "" != value.dlqtz_fj_lobid && ($out += ' <br>附件：<a href="', $out += $escape("/sytj/stu/dlqtzfj.action?id=" + value.dlqtz_fj_lobid + "&c1=" + value.c1 + "&c2=" + value.c2), $out += '" download="', $out += $escape(value.dlqtz_fj_name), $out += '">', $out += $escape(value.dlqtz_fj_name), $out += "</a> "), $out += " </div> ", 1 == value.dlqtz_zt ? ($out += ' <div class="fs-opt"> <a href="javascript:;" onclick="template_dlqtz(\'dlqtz-post-accept\',\'', $out += $escape(value.id), $out += "','content')\">接受待录取</a> <a href=\"javascript:;\" class=\"refuse\" onclick=\"template_dlqtz('dlqtz-post-reject','", $out += $escape(value.id), $out += "','content')\">拒绝待录取</a> </div> ") : 2 == value.dlqtz_zt ? ($out += ' <div class="reply-warp"> <div class="reply reply-accept"> <i class="iconfont">&#xe67e;</i> <div class="reply-result">您已接受该待录取通知</div> <div class="reply-time">', $out += $escape(value.dlqtz_hfsj), $out += "</div> </div> </div> ") : 3 == value.dlqtz_zt ? ($out += ' <div class="reply-warp"> <div class="reply reply-refuse"> <i class="iconfont">&#xe67f;</i> <div class="reply-result">您已拒绝该待录取通知</div> <div class="reply-time">', $out += $escape(value.dlqtz_hfsj), $out += "</div> </div> </div> ") : 4 == value.dlqtz_zt && ($out += ' <div class="reply-warp"> <div class="reply reply-refuse"> <div class="cancel-refuse"><i class="iconfont">&#xe67d;</i>招生单位取消待录取</div> <a href="javascript:;" class="clear" onclick="template_dlqtz_cancel(\'dlqtz-post-acceptcancel\',\'', $out += $escape(value.id), $out += "','content')\">接受取消待录取</a> </div> </div> "), $out += " </div> </div> </div> "), $out += " " }), $out += " </div> ") : $out += ' <div class="sys-tip no-data mar-t20"> <div class="tj-bg-zyempty"></div> <div class="tj-empty-message">您暂无待录取通知</div> </div> ', $out += " "), $out += " ", new String($out)
        }), template("common/footer", function($data, $filename) {
            "use strict";
            var $utils = this,
                agent_from = ($utils.$helpers, $data.agent_from),
                $out = "";
            return $out += " ", $out += "web" == agent_from ? ' <div class="tj-footer"> <div class="footer-content clearfix"> <div class="footer-left"> <p>主办单位：<a href="https://chesicc.chsi.com.cn/" target="_blank">教育部学生服务与素质发展中心（原全国高等学校学生信息咨询与就业指导中心）</a></p> <p>Copyright © 2003-2022 <a href="http://www.chsi.com.cn/" target="_blank">学信网</a> All Rights Reserved</p> <p> <a href="https://beian.miit.gov.cn" target="_blank">京ICP备19004913号-1</a> </p> </div> <div class="footer-right"> <p>客服电话：010-67410388</p> <p>客服邮箱：kefu#chsi.com.cn（将#替换为@）</p> </div> </div> </div> ' : ' <div class="yz-wap-footer"> <a style="margin-right: 20px;" href="http://yz.chsi.com.cn/">中国研究生招生信息网</a>客服电话：010-67410388<br> </div> ', $out += " ", new String($out)
        }), template("common/fstz", function($data, $filename) {
            "use strict";
            var $utils = this,
                flag = ($utils.$helpers, $data.flag),
                data = $data.data,
                $each = $utils.$each,
                other = ($data.value, $data.index, $data.other),
                $escape = $utils.$escape,
                $string = $utils.$string,
                $out = "";
            return $out += ' <div class="info-prompt mar-t20"> <ul> <li>提示：每个复试通知考生只有一次机会接受或拒绝，请慎重选择。已被待录取考生不能操作复试通知。</li> </ul> </div> ', flag && ($out += " ", "" != data.vo_list.vos ? ($out += ' <div class="tz-content">  ', $each(data.vo_list.vos, function(value, index) { $out += " ", "web" == other.agent_from ? ($out += ' <div class="fs-warp mar-t20 clearfix"> <div class="fs-warp-left"> <h5>(', $out += $escape(value.dwdm), $out += ")", $out += $escape(value.dwmc), $out += " - (", $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += " - (", $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += " - (", $out += $escape(value.yjfxdm), $out += ")", $out += $escape(value.yjfxmc), "1" == value.xxfs ? $out += " - 全日制" : "2" == value.xxfs && ($out += " - 非全日制"), $out += '</h5> <div class="timer">发送时间：', $out += $escape(value.fstz_fssj), $out += "</div> <h6>", $out += $escape(value.fstz_bt), $out += '</h6> <div class="content">', $out += $string(value.fstz_nr), $out += " ", "" != value.fstz_fj_lobid && ($out += ' <br>附件：<a href="', $out += $escape("/sytj/stu/fstzfj.action?id=" + value.fstz_fj_lobid + "&c1=" + value.c1 + "&c2=" + value.c2), $out += '" download="', $out += $escape(value.fstz_fj_name), $out += '">', $out += $escape(value.fstz_fj_name), $out += "</a> "), $out += ' </div> </div> <div class="fs-warp-right"> ', 1 == value.fstz_zt ? ($out += ' <div class="fs-opt clearfix"> <a href="javascript:;" class="refuse" onclick="template_fstz(\'fstz-post-reject\',\'', $out += $escape(value.fstz_id), $out += "','content')\">拒绝此通知</a> <a href=\"javascript:;\" onclick=\"template_fstz('fstz-post-accept','", $out += $escape(value.fstz_id), $out += "','content')\">接受此通知</a> </div> ") : 2 == value.fstz_zt ? ($out += ' <div class="reply reply-accept"> <i class="iconfont">&#xe67e;</i> <div class="reply-result">您已接受该复试通知</div> <div class="reply-time">', $out += $escape(value.fstz_hfsj), $out += "</div> </div> ") : 3 == value.fstz_zt && ($out += ' <div class="reply reply-refuse"> <i class="iconfont">&#xe67f;</i> <div class="reply-result">您已拒绝该复试通知</div> <div class="reply-time">', $out += $escape(value.fstz_hfsj), $out += "</div> </div> "), $out += " </div> </div> ") : ($out += ' <div class="fs-main tjyx"> <div class="fs-cxt"> <div class="fs-cxt-title"> <h5>(', $out += $escape(value.dwdm), $out += ")", $out += $escape(value.dwmc), $out += " - (", $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += " - (", $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += " - (", $out += $escape(value.yjfxdm), $out += ")", $out += $escape(value.yjfxmc), "1" == value.xxfs ? $out += " - 全日制" : "2" == value.xxfs && ($out += " - 非全日制"), $out += '</h5> <div class="timer">发送时间：', $out += $escape(value.fstz_fssj), $out += '</div> </div> <div class="fs-cxt-content"> <h6>', $out += $escape(value.fstz_bt), $out += '</h6> <div class="content">', $out += $string(value.fstz_nr), $out += " ", "" != value.fstz_fj_lobid && ($out += ' <br>附件：<a href="', $out += $escape("/sytj/stu/fstzfj.action?id=" + value.fstz_fj_lobid + "&c1=" + value.c1 + "&c2=" + value.c2), $out += '" download="', $out += $escape(value.fstz_fj_name), $out += '">', $out += $escape(value.fstz_fj_name), $out += "</a> "), $out += " </div> ", 1 == value.fstz_zt ? ($out += ' <div class="fs-opt"> <a href="javascript:;" onclick="template_fstz(\'fstz-post-accept\',\'', $out += $escape(value.fstz_id), $out += "','content')\">接受此通知</a> <a href=\"javascript:;\" class=\"refuse\" onclick=\"template_fstz('fstz-post-reject','", $out += $escape(value.fstz_id), $out += "','content')\">拒绝此通知</a> </div> ") : 2 == value.fstz_zt ? ($out += ' <div class="reply-warp"> <div class="reply reply-accept"> <i class="iconfont">&#xe67e;</i> <div class="reply-result">您已接受该复试通知</div> <div class="reply-time">', $out += $escape(value.fstz_hfsj), $out += "</div> </div> </div> ") : 3 == value.fstz_zt && ($out += ' <div class="reply-warp"> <div class="reply reply-refuse"> <i class="iconfont">&#xe67f;</i> <div class="reply-result">您已拒绝该复试通知</div> <div class="reply-time">', $out += $escape(value.fstz_hfsj), $out += "</div> </div> </div> "), $out += " </div> </div> </div> "), $out += " " }), $out += " </div> ") : $out += ' <div class="sys-tip no-data mar-t20 "> <div class="tj-bg-zyempty"></div> <div class="tj-empty-message">您暂无复试通知</div> </div> ', $out += " "), $out += " ", new String($out)
        }), template("common/header-include", '<div class=\'tj-header-right\'> <span id="yz_userid"></span><a href="/user/center.jsp?entrytype=yzgr" target="_blank">用户中心</a><a href="https://account.chsi.com.cn/account/account!show" target="_blank">账号信息</a><a href="/logout/">退出</a><br/> <a href="/" target="_blank">研招网首页</a>|<a href="/help/" target="_blank">帮助中心</a> </div>'), template("common/header", function($data, $filename) {
            "use strict";
            var $utils = this,
                agent_from = ($utils.$helpers, $data.agent_from),
                $out = "";
            return $out += " <div class='tj-header width1180'> <div class='tj-header-left'> <h1>全国硕士生招生调剂服务系统</h1> </div> ",
                function(filename, data) {
                    data = data || $data;
                    var text = $utils.$include(filename, data, $filename);
                    $out += text
                }("../common/header-include"), $out += ' </div> <div class="wap-nav-top"> <span class="logo"></span> <span class="logo-text">调剂服务系统</span> <div class="nav-right"> <a href="http://yz.chsi.com.cn/user/center.jsp" class="grzx"> <i class="iconfont" title="个人中心">&#xe645;</i> </a> <span class="ch-icon-wap-menu wap-menu"><i class="ch-icon ch-icon-wap-nav"></i></span> <div class="menu-list-warp-bg"></div> <div class="wap-menu-list"> <div class="menu-list-close"> <i class="iconfont iconfont-close">&#xe65c;</i> </div> <a class="menu-list-1" href="index.html">首页</a> <a class="menu-list-1" href="yzytz.html">一志愿通知</a> <div class="menu-list-warp"> <span class="menu-list-cut">调剂</span> <a class="menu-list-2 menu-list-22" href="qecx.html">计划余额查询</a> <a class="menu-list-2" href="tjzy.html">申请调剂</a> <a class="menu-list-2" href="fstz.html">复试通知</a> <a class="menu-list-2" href="dlqtz.html">待录取通知</a> </div> <a class="menu-list-1" href="cjxx.html">初试成绩</a> </div> </div> </div> ', $out += "web" == agent_from ? ' <div class=\'menu-bg\'> <ul class=\'tj-menu\'> <li data-nav = "index"><a href="index.html">首页</a></li> <li class="cut"></li> <li data-nav = "yzytz"><a href="yzytz.html">一志愿通知</a></li> <li class="cut"></li> <li class="cut-text">调剂</li> <li data-nav = "qecx"><a href="qecx.html">计划余额查询</a></li> <li data-nav = "tjzy"><a href="tjzy.html">申请调剂</a></li> <li data-nav = "fstz"><a href="fstz.html">复试通知</a><span></span></li> <li data-nav = "dlqtz"><a href="dlqtz.html">待录取通知</a><span></span></li> <li class="cut"></li> <li data-nav = "cjxx"><a href="cjxx.html">初试成绩</a></li> </ul> </div> <a class="jqr" href="//kl.chsi.com.cn/robot/index.action?system=yz_wb" target="_blank"></a> <div class="ewm-fix"> <div class="icon-con"> <i class="iconfont" title="二维码">&#xe65b;</i> <div class="ewm-detail tj-hide"> <div class="item clearfix"> <div class="left"> <img src="//t1.chei.com.cn/common/ewm/images/chsi.jpg" /> </div> <div class="right"> <div class="ewm-title">学信网公众号</div> <div class="ewm-con">关注学信网公众号并绑定微信账号，及时接收重要消息提醒</div> </div> </div> <div class="item clearfix"> <div class="left"> <img src="//t1.chei.com.cn/common/ewm/images/yz.jpg" /> </div> <div class="right"> <div class="ewm-title">研招网</div> <div class="ewm-con">研招关键节点提醒及相关信息查询</div> </div> </div> </div> </div> </div> ' : "  ", new String($out)
        }), template("common/index", function($data, $filename) {
            "use strict";
            var $utils = this,
                agent_from = ($utils.$helpers, $data.agent_from),
                $out = "";
            return "wap" == agent_from && ($out += ' <div class="enter-wap"> <div class="enter-wap-item"> <div class="enter-wap-item-left">一志愿</div> <div class="enter-wap-item-right"> <a href="yzytz.html">一志愿复试待录取通知</a> </div> </div> <div class="enter-wap-item"> <div class="enter-wap-item-left">调剂</div> <div class="enter-wap-item-right flex"> <a href="qecx.html">计划余<br>额查询</a> <a href="tjzy.html">申请<br>调剂</a> <a href="fstz.html">复试<br>通知</a> <a href="dlqtz.html">待录取<br>通知</a> </div> </div> <div class="enter-wap-item"> <div class="enter-wap-item-left">初试</div> <div class="enter-wap-item-right"> <a href="cjxx.html">初试成绩</a> </div> </div> </div> <div class="index-ewm-wap"> <div class="item"> <img src="//t1.chei.com.cn/common/ewm/images/chsi.jpg" /> <div class="ewm-title">学信网公众号</div> <div class="ewm-con">关注学信网公众号并绑定微信账号，及时接收重要消息提醒</div> </div> <div class="item"> <img src="//t1.chei.com.cn/common/ewm/images/yz.jpg" /> <div class="ewm-title">研招网</div> <div class="ewm-con">研招关键节点提醒及相关信息查询</div> </div> </div> '), $out += ' <div class="index-tjxz mar-t20"> ', "web" == agent_from && ($out += ' <div class="index-ewm clearfix"> <div class="item"> <img src="//t1.chei.com.cn/common/ewm/images/chsi.jpg" /> <div class="ewm-title">学信网公众号</div> <div class="ewm-con">关注学信网公众号并绑定微信账号，<br>及时接收重要消息提醒</div> </div> <div class="item"> <img src="//t1.chei.com.cn/common/ewm/images/yz.jpg" /> <div class="ewm-title">研招网</div> <div class="ewm-con">研招关键节点提醒及相关信息查询</div> </div> </div> '), $out += " ", "wap" == agent_from && ($out += ' <h3 class="index-h2" style="margin-top: 0;">调剂须知</h3> '), $out += ' <div class="sys-tip-ktsj"> <div class="sys-tip-warp ', "web" == agent_from && ($out += "sys-tip-bg"), $out += '"> <div class="sys-tip-title">调剂系统开通时间</div> <div class="sys-tip-time">4月6日</div> </div> <p>各招生单位调剂工作开展日期、相关调剂要求和计划余额信息由招生单位根据教育部相关规定自行确定并公布，详细情况请考生咨询有关招生单位。</p> </div> <div class="tip ', "web" == agent_from && ($out += "tip-web"), $out += '"> <h4 class="tip-title">重要提示</h4> <div class="tip-content"> <a href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html" target="_blank">基本要求</a> ', "wap" == agent_from && ($out += '<span class="cut-l"></span>'), $out += ' \x3c!-- <a href="https://yz.chsi.com.cn/kyzx/kp/202103/20210319/1773474721.html" target="_blank">操作流程</a> ', "wap" == agent_from && ($out += '<span class="cut-l"></span>'), $out += ' --\x3e <a href="https://yz.chsi.com.cn/help/index.jsp" target="_blank">常见问题</a> ', "wap" == agent_from && ($out += '<span class="cut-l"></span>'), $out += ' <a href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220329/2176565576.html" target="_blank" style="margin-right:0">注意事项</a> </div> </div> </div> ', $out += "web" == agent_from ? ' <div class="vote-part mar-t20"> <span class="vote-part-tip">请进行网上调查</span><span class="vote-part-time">（大约1分钟）</span> <a class="vote-part-dc" href="https://vote.chsi.com.cn/vote/real/showWholeVote.action?groupId=5rwvp9jq83kvl650" target="_blank">网上调查</a> <a class="vote-part-cp" href="https://xz.chsi.com.cn/cp/sph/cover.do?sphSurveyId=f4ediwycn6qj8nm4" target="_blank">专业认同度测评</a> </div> ' : ' <div class="cp-warp"> <a href="https://vote.chsi.com.cn/vote/real/showWholeVote.action?groupId=5rwvp9jq83kvl650"> <img src="https://t2.chei.com.cn/yz/sytj/assets/images/common/index-wsdc.png" alt=""> </a> <a href="https://xz.chsi.com.cn/cp/sph/cover.do?sphSurveyId=f4ediwycn6qj8nm4"> <img src="https://t2.chei.com.cn/yz/sytj/assets/images/common/index-cp.png" alt=""> </a> </div> ', $out += ' <div class="wap-index-h2"> <h2 class="index-h2 mar-t40">操作流程</h2> </div> ', "web" == agent_from && ($out += ' <div class="tj-step-icon"> <span class="num first">1</span> <span class="cutline"></span> <span class="num">2</span> <span class="cutline"></span> <span class="num last">3</span> </div> '), $out += ' <div class="wap-expend-warp" id="wap-expend-warp"> <ul class="tj-step"> <li> <h4>', "wap" == agent_from && ($out += '<span class="step-num">1</span>'), $out += '报名</h4> <p><span class="step-strong">登录：</span>考生凭网报时注册的用户名和密码登录“中国研究生招生信息网”的网上调剂系统。如果忘记了用户名或密码，请使用“忘记密码”功能，输入相关信息来找回用户名或密码。</p> <p><span class="step-strong">查询计划余额：</span>考生填报调剂志愿前，请务必充分了解教育部调剂相关政策及招生单位（含各院、系、所）的调剂办法、计划余额、接收考生调剂时间、基本要求、工作程序、调剂复试办法等信息，登录调剂系统查询各招生单位的计划余额信息、调剂办法、接收考生调剂的申请条件。特别注意：申请调剂的考生应符合招生管理规定确定的调剂条件。不符合调入专业学科门类要求、初试科目要求，或不满足招生单位调剂条件的考生，将无法申请填报相应志愿。</p> <p><span class="step-strong">填报调剂志愿：</span>通过调剂服务系统选择已发布计划余额的招生单位和专业研究方向，如满足教育部调剂政策且符合招生单位设置的调剂报考条件，可填报调剂志愿（可一次填报三个平行调剂志愿），每个调剂志愿提交后即由招生单位锁定，锁定时间由各招生单位自主设定，最长不超过36小时。招生单位根据本单位调剂工作要求和生源情况确定是否接收考生参加复试。如考生调剂申请未获招生单位通过，调剂志愿解锁，考生可继续填报其他志愿。锁定时间到达后，系统自动解锁，考生可继续等待或改填报其他志愿。</p> </li> <li> <h4>', "wap" == agent_from && ($out += '<span class="step-num">2</span>'), $out += "复试通知</h4> <p>考生提交调剂志愿后，如招生单位同意其参加复试，将通过调剂系统发送复试通知。请及时登录系统查看志愿状态及是否接到复试通知。如接到复试通知，请考生及时通过调剂系统确认回复，并按招生单位要求办理相关手续。复试没有通过的考生仍可继续填报其他加调剂志愿。</p> </li> <li> <h4>", "wap" == agent_from && ($out += '<span class="step-num">3</span>'), $out += "待录取通知</h4> <p>复试通过后，招生单位将通过调剂系统给考生发送待录取通知，接到待录取通知的考生应在招生单位规定时间内登录调剂系统进行确认，否则招生单位可取消待录取通知。考生一旦确认接受待录取通知，表示调剂完成，将不能再填报调剂志愿、接受复试或待录取通知。考生如取消已确认的待录取通知，必须征得招生单位同意，由招生单位取消待录取通知，且考生登录调剂系统进行确认后，方可继续填报调剂志愿知。</p> </li> </ul> ", "wap" == agent_from && ($out += ' <div class="wap-expend"> <div class="wap-expend-bg"></div> <div class="wap-expend-text text-expend"> <span>展开</span> <i class="iconfont">&#xe60f;</i> </div> <div class="wap-expend-text text-unexpend"> <span>收起</span> <i class="iconfont">&#xe608;</i> </div> </div> '), $out += " </div> ", "wap" == agent_from && ($out += ' <div class="ewm-wap"> <i class="iconfont" title="二维码">&#xe65b;</i> </div> <div class="wap-overlay tj-hide"></div> <div class="ewm-detail-wap tj-hide"> <div class="item-con"> <div class="item"> <div class="left"> <img src="//t1.chei.com.cn/common/ewm/images/chsi.jpg" /> </div> <div class="right"> <div class="ewm-title">学信网公众号</div> <div class="ewm-con">关注学信网公众号并绑定微信账号，及时接收重要消息提醒</div> </div> </div> <div class="item"> <div class="left"> <img src="//t1.chei.com.cn/common/ewm/images/yz.jpg" /> </div> <div class="right"> <div class="ewm-title">研招网</div> <div class="ewm-con">研招关键节点提醒及相关信息查询</div> </div> </div> </div> <i class="iconfont close">&#xe65c;</i> </div> '), new String($out)
        }), template("common/layout", function($data, $filename) {
            "use strict";
            var $utils = this,
                include = ($utils.$helpers, function(filename, data) { data = data || $data; var text = $utils.$include(filename, data, $filename); return $out += text }),
                $out = "";
            return $out += " <div class='tj-main'> <div class='tj-content'> ", include("../common/header"), $out += ' <div class="width1180" id="content"> </div> </div> </div> ', include("../common/footer"), new String($out)
        }), template("common/notice", function($data, $filename) {
            "use strict";
            var $utils = this,
                basedata = ($utils.$helpers, $data.basedata),
                $each = $utils.$each,
                $string = ($data.value, $data.inde, $utils.$string),
                $escape = $utils.$escape,
                sysType = $data.sysType,
                $out = ($data.index, "");
            return $out += " ", "" != basedata.other.flash.tips && ($out += '  \x3c!-- <div class="info-prompt mar-t30"><span class="tj-icon-warn"><i class="iconfont">&#xe674;</i></span> <ul> ', $each(basedata.other.flash.tips, function(value, inde) { $out += " <li>", value.escape ? $out += $string(value.title) : $out += $escape(value.title), $out += "</li> " }), $out += " </ul> </div> --\x3e "), $out += " ", "TJ" == sysType ? ($out += "  ", "" != basedata.data.notice.news && ($out += ' \x3c!-- <div class="sys-tip index-sys-tip mar-t20"> <h2><i class="iconfont" title="通知">&#xe6a7;</i>通知</h2> <ul class="sys-list"> ', $each(basedata.data.notice.news, function(value, index) { $out += " <li><span></span>", $out += $string(value.title), "fs" == value.type ? $out += '&ensp;<a href="fstz.html">去看看</a>' : "lq" == value.type && ($out += '&ensp;<a href="dlqtz.html">去看看</a>'), $out += "</li> " }), $out += " </ul> </div> --\x3e "), $out += " ") : ($out += " ", "wap" == basedata.other.agent_from ? ($out += ' <div class="enter-wap"> <div class="enter-wap-item"> <div class="enter-wap-item-left">意向</div> <div class="enter-wap-item-right flex"> <a href="qecx.html" class="flex-1">调剂意向余额查询</a> <a href="tjzy.html" class="flex-2">调剂意向</a> </div> </div> <div class="enter-wap-item"> <div class="enter-wap-item-left">初试</div> <div class="enter-wap-item-right"> <a href="cjxx.html">初试成绩</a> </div> </div> </div> <div class="index-ewm-wap"> <div class="item"> <img src="//t1.chei.com.cn/common/ewm/images/chsi.jpg" /> <div class="ewm-title">学信网公众号</div> <div class="ewm-con">关注学信网公众号并绑定微信账号，及时接收重要消息提醒</div> </div> <div class="item"> <img src="//t1.chei.com.cn/common/ewm/images/yz.jpg" /> <div class="ewm-title">研招网</div> <div class="ewm-con">研招关键节点提醒及相关信息查询</div> </div> </div> ', basedata.tj_usable && ($out += ' <div class="index-entry text-c"> <a href="http://yz.chsi.com.cn/sytj/tj/" class="entry-btn" >网上调剂服务系统</a> </div> '), $out += " ") : ($out += ' <div class="index-tjxz mar-t20"> <div class="index-ewm clearfix"> <div class="item"> <img src="//t1.chei.com.cn/common/ewm/images/chsi.jpg" /> <div class="ewm-title">学信网公众号</div> <div class="ewm-con">关注学信网公众号并绑定微信账号，<br>及时接收重要消息提醒</div> </div> <div class="item"> <img src="//t1.chei.com.cn/common/ewm/images/yz.jpg" /> <div class="ewm-title">研招网</div> <div class="ewm-con">研招关键节点提醒及相关信息查询</div> </div> </div> ', basedata.tj_usable && ($out += ' <div class="index-entry text-c"> <a href="http://yz.chsi.com.cn/sytj/tj/" class="entry-btn" >网上调剂服务系统</a> </div> '), $out += " </div> "), $out += " "), $out += " ", new String($out)
        }), template("common/qecx-quto", ' <div class="tj-elem-quto mar-t10"> <div class="tj-elem-bg"></div> <div class="tj-elem-content">请选择或填写条件进行计划余额查询</div> </div> '), template("common/qecx-seach-item", function($data, $filename) {
            "use strict";
            var $utils = this,
                $each = ($utils.$helpers, $utils.$each),
                seachList = $data.seachList,
                $escape = ($data.value, $data.index, $utils.$escape),
                selectitem = $data.selectitem,
                $out = "";
            return $out += " ", $each(seachList, function(value, index) { $out += ' <option value="', $out += $escape(value.code), $out += '" ', selectitem && selectitem == value.code && ($out += 'selected="selected"'), $out += ">(", $out += $escape(value.code), $out += ") ", $out += $escape(value.name), $out += "</option> " }), new String($out)
        }), template("common/qecx", function($data, $filename) {
            "use strict";
            var $utils = this,
                sxbz2 = ($utils.$helpers, $data.sxbz2),
                sxbz3 = $data.sxbz3,
                sxbz = $data.sxbz,
                include = function(filename, data) { data = data || $data; var text = $utils.$include(filename, data, $filename); return $out += text },
                agent_from = $data.agent_from,
                $escape = $utils.$escape,
                $out = "";
            return $out += " ", "1" != sxbz2 && "1" != sxbz3 || "1" == sxbz || ($out += " ", include("../common/sxbz-include"), $out += " "), $out += " ", "web" == agent_from ? ($out += " ", include("../web/qecx-seach"), $out += '  <div id="content-qecxList"> ', include("../common/qecx-quto"), $out += " </div> ", include("../common/tips"), $out += "  ") : ($out += " ", include("../wap/qecx-seach"), $out += '  <div id="content-qecxList"> ', include("../common/tips"), $out += "  </div> "), $out += " <script> init_select_ss('", $out += $escape(agent_from), $out += "'); <\/script> ", new String($out)
        }), template("common/sqtj", function($data, $filename) {
            "use strict";
            var $utils = this,
                data = ($utils.$helpers, $data.data),
                $escape = $utils.$escape,
                other = $data.other,
                $out = "";
            return $out += " ", "" != data.vo.sfmzjybyq ? ($out += ' <h4 class="info-title">（', $out += $escape(data.vo.qexxvo.dwdm), $out += "）", $out += $escape(data.vo.qexxvo.dwmc), $out += " - （", $out += $escape(data.vo.qexxvo.yxsdm), $out += "）", $out += $escape(data.vo.qexxvo.yxsmc), $out += " - （", $out += $escape(data.vo.qexxvo.zydm), $out += "）", $out += $escape(data.vo.qexxvo.zymc), $out += " - （", $out += $escape(data.vo.qexxvo.yjfxdm), $out += "）", $out += $escape(data.vo.qexxvo.yjfxmc), $out += " - （", $out += $escape(data.vo.qexxvo.xxfs), $out += "）", "1" == data.vo.qexxvo.xxfs ? $out += "全日制" : $out += "非全日制", $out += '</h4> <div class="tj-process-message warning" style="border: 1px solid #f7cbc6;margin-left: 0;margin-right: 0;height: auto;"> <a data-tip="具体请参见教育部调剂政策" class="tjyx-tip" target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">', $out += $escape(data.vo.sfmzjybyq), $out += "</a> </div> ") : ($out += ' <div class="info-tip"> 申请条件由招生单位设置，若有疑问，请联系招生单位；考生申请调剂志愿需同时满足国家调剂政策以及招生单位的要求。 </div> <h4 class="info-title">（', $out += $escape(data.vo.qexxvo.dwdm), $out += "）", $out += $escape(data.vo.qexxvo.dwmc), $out += " - （", $out += $escape(data.vo.qexxvo.yxsdm), $out += "）", $out += $escape(data.vo.qexxvo.yxsmc), $out += " - （", $out += $escape(data.vo.qexxvo.zydm), $out += "）", $out += $escape(data.vo.qexxvo.zymc), $out += " - （", $out += $escape(data.vo.qexxvo.yjfxdm), $out += "）", $out += $escape(data.vo.qexxvo.yjfxmc), $out += " - （", $out += $escape(data.vo.qexxvo.xxfs), $out += "）", "1" == data.vo.qexxvo.xxfs ? $out += "全日制" : $out += "非全日制", $out += "</h4> ", "" != data.vo.sfmzyq && ($out += ' <div class="tj-process-message warning" style="border: 1px solid #f7cbc6;margin-left: 0;margin-right: 0;height: auto;">', $out += $escape(data.vo.sfmzyq), $out += "</div> "), $out += ' <table class="info-table"> <tbody> <tr> <th ', "wap" == other.agent_from && ($out += 'class="text-t"'), $out += '>调剂办法：</th> <td class="info-table-b"><a href="/sch/tjzc--method-listTjPub,yxdm-', $out += $escape(data.vo.qexxvo.dwdm), $out += '.dhtml" target="_blank" style="text-decoration:underline">查看详细</a></td> </tr> <tr> <th ', "wap" == other.agent_from && ($out += 'class="text-t" '), $out += '>初试学位类型：</th> <td class="info-table-a">', $out += $escape(data.vo.xwlxyq), $out += "</td> </tr> <tr> <th ", "wap" == other.agent_from && ($out += 'class="text-t" '), $out += '>专项计划：</th> <td class="info-table-a">', $out += $escape(data.vo.zxjhyq), $out += "</td> </tr> <tr> <th ", "wap" == other.agent_from && ($out += 'class="text-t" '), $out += "> 初试门类\\一级", "wap" == other.agent_from && ($out += "&emsp;<br>"), $out += '学科\\专业： </th> <td class="info-table-a">', $out += $escape(data.vo.kmlyq), $out += "</td> </tr> <tr> <th ", "wap" == other.agent_from && ($out += 'class="text-t"'), $out += '>初试科目：</th> <td>数学：<span class="info-table-a">', $out += $escape(data.vo.sxyq), $out += '</span>；外语：<span class="info-table-a">', $out += $escape(data.vo.wyyq), $out += '</span></td> </tr> <tr> <th class="text-t">初试成绩：</th> <td class="text-t"> <table class="info-table-detail"> <tbody> <tr> <td class="title">总分</td> <td>', $out += $escape(data.vo.zfyq), $out += '</td> </tr> <tr> <td class="title">第一门</td> <td>', $out += $escape(data.vo.zzllcjyq), $out += '</td> </tr> <tr> <td class="title">第二门</td> <td>', $out += $escape(data.vo.wgycjyq), $out += '</td> </tr> <tr> <td class="title">第三门</td> <td>', $out += $escape(data.vo.ywk1cjyq), $out += '</td> </tr> <tr> <td class="title">第四门</td> <td>', $out += $escape(data.vo.ywk2cjyq), $out += '</td> </tr> </tbody> </table> </td> </tr> <tr> <th class="text-t">调剂说明：</th> <td class="info-table-g">', "" != data.vo.qexxvo.bz ? $out += $escape(data.vo.qexxvo.bz) : $out += "无", $out += "</td> </tr> </tbody> </table> "), $out += " ", new String($out)
        }), template("common/success", function($data, $filename) {
            "use strict";
            var $utils = this,
                zy_type = ($utils.$helpers, $data.zy_type),
                $out = "";
            return $out += '<div class="tj-nopermission"> <div class="tj-icon-success"></div> <h2>', $out += "tbtjzy-edit-post" == zy_type ? "志愿修改已完成!" : "志愿填报已完成！", $out += '</h2> <p class="tj-success-p"> ', $out += "tbtjzy-edit-post" == zy_type ? " " : ' <a href="qecx.html">继续填报</a> | ', $out += ' <a href="tjzy.html">查看已填报志愿</a> </p> </div>', new String($out)
        }), template("common/sxbz-include", ""), template("common/sys", function($data, $filename) {
            "use strict";
            var $utils = this,
                sysData = ($utils.$helpers, $data.sysData),
                $escape = $utils.$escape,
                $out = "";
            return $out += " ", null != sysData && ($out += ' <div class="width700 tj-nopermission"> <div class="tj-icon-nopermission"></div> <h2>', $out += $escape(sysData.title), $out += "</h2> <p>", $out += $escape(sysData.content), $out += "</p> </div> "), $out += " ", new String($out)
        }), template("common/tbtjzy-edit", function($data, $filename) {
            "use strict";
            var $utils = this,
                sxbz2 = ($utils.$helpers, $data.sxbz2),
                sxbz3 = $data.sxbz3,
                sxbz = $data.sxbz,
                include = function(filename, data) { data = data || $data; var text = $utils.$include(filename, data, $filename); return $out += text },
                other = $data.other,
                flag = $data.flag,
                $escape = $utils.$escape,
                data = $data.data,
                yddh = $data.yddh,
                message = $data.message,
                $out = "";
            return "1" != sxbz2 && "1" != sxbz3 || "1" == sxbz || ($out += " ", "1" != sxbz2 && "1" != sxbz3 || "1" == sxbz || ($out += " ", include("../common/sxbz-include"), $out += " "), $out += " "), $out += " ", "web" == other.agent_from ? ($out += ' <div class="tbzy mar-t20"> <div class="clearfix"> <div class="width700 tj-float-left"> <h2 class="text-c">修改调剂志愿</h2> ', flag ? ($out += ' <form class="fromSubmit"> <input type="hidden" name="id" value="', $out += $escape(data.vo.id), $out += '"/> <input type="hidden" name="adjust_id" value="', $out += $escape(data.vo.adjust_id), $out += '" id="adjust_id"/> <table> <tr> <td class="text-r color-6" width="210"><span class="tj-require">*</span>省市：</td> <td> <select name="ssdm" class="tj-select width252" id="ssdm" onchange="seach_dwdm(false)"> <option value="', $out += $escape(data.vo.ssdm), $out += '" selected="selected">请选择</option> </select> <span class="tj-error"></span> </td> </tr> <tr> <td class="text-r color-6" width="210"><span class="tj-require">*</span>招生单位：</td> <td> <select name="dwxx" class="tj-select width252 mar-b0 dwxx-web" id="dwxx" onchange="seach_yxxx(false)"> <option value="">请选择</option> <option value="', $out += $escape(data.vo.dwdm), $out += '" selected="selected">(', $out += $escape(data.vo.dwdm), $out += ") ", $out += $escape(data.vo.dwmc), $out += '</option> </select> <span class="tj-error"></span> </td> </tr> <tr> <td></td> <td> <p class="tb-tip tb-tip-time">注意：该招生单位设定的志愿锁定时间为 <strong id="edit-time" class="color-blue">36</strong> 小时。</p> <p class="tb-tip" id="js-sxbz" style="display: block;color: #fd6d4a"></p> </td> </tr> <tr> <td class="text-r color-6"><div class="mar-t15"><span class="tj-require">*</span>院系所：</div></td> <td> <select name="yxxx" class="tj-select width252 mar-b0" id="yxxx" onchange="seach_zyxx(false)"> <option value="">请选择</option> <option value="', $out += $escape(data.vo.yxsdm), $out += '" selected="selected">(', $out += $escape(data.vo.yxsdm), $out += ") ", $out += $escape(data.vo.yxsmc), $out += '</option> </select> <span class="tj-error tj-error-style"></span> </td> </tr> <tr> <td></td> <td> <p class="tb-tip">注意：如果招生单位未发布计划余额信息，则该项无内容。</p> </td> </tr> <tr> <td class="text-r color-6"><div class="mar-t15"><span class="tj-require">*</span>专业：</div></td> <td> <select name="zyxx" class="tj-select width252 mar-b0" id="zyxx" onchange="seach_fxxxx(false)"> <option value="', $out += $escape(data.vo.zydm), $out += '" selected="selected">(', $out += $escape(data.vo.zydm), $out += ") ", $out += $escape(data.vo.zymc), $out += '</option> </select> <span class="tj-error tj-error-style"></span> </td> </tr> <tr> <td></td> <td> <p class="tb-tip">注意：如果招生单位某专业未发布计划余额信息或考生<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">不符合调入专业学科门类要求</a>，则该专业不显示。</p> </td> </tr> <tr> <td class="text-r color-6"><span class="tj-require">*</span>研究方向：</td> <td> <select name="fxxxx" class="tj-select width252" id="fxxxx" onchange="seach_xxfs()"> <option value="">请选择</option> <option value="', $out += $escape(data.vo.yjfxdm), $out += '" selected="selected">(', $out += $escape(data.vo.yjfxdm), $out += ") ", $out += $escape(data.vo.yjfxmc), $out += '</option> </select> <span class="tj-error"></span> </td> </tr> <tr> <td class="text-r color-6"><span class="tj-require">*</span>学习方式：</td> <td> <select name="xxfs" class="tj-select width252" id="xxfs" onchange="seach_bz()"> <option value="">请选择</option> <option value="1" ', 1 == data.vo.xxfs && ($out += 'selected="selected"'), $out += '>全日制</option> <option value="2" ', 2 == data.vo.xxfs && ($out += 'selected="selected"'), $out += '>非全日制</option> </select> <span class="tj-error"></span> </td> </tr> <tr class="tj-tsyq-warp tj-hide"> <td></td> <td><span class="tj-tsyq" id="tsyq"></span></td> </tr> <tr> <td class="text-r color-6"><div class="mar-t15">备注：</div></td> <td class="form-group"><textarea name="bz" class="tj-input zytb-textarea width240 tj-float-left mar-b0" id="bz" maxlength="50">', $out += $escape(data.vo.bz), $out += '</textarea><span class="tj-error tj-error-style tj-float-left mar-t25"></span></td> </tr> <tr> <td></td> <td> <p class="tb-tip">请按招生单位要求填写，最多50个字符。</p> </td> </tr> <tr> <td class="text-r color-6"><div class="mar-t15"><span class="tj-require">*</span>考生手机号码：</div></td> <td> <div class="mar-t15"> <input type="hidden" value="', $out += $escape(yddh), $out += '" name="yddh" id="yddh" maxlength="50"/> ', $out += $escape(yddh), $out += ' <a href="//account.chsi.com.cn/account/account!show" target="_blank">修改</a> </div> </td> </tr> <tr> <td></td> <td> <p class="tb-tip">注意：此信息来源于实名注册，如有误请登录实名注册进行修改，修改后退出系统且关闭浏览器重新登录并同步信息后方可生效。</p> </td> </tr> <tr class="submit-tr"> <td></td> <td> <div class="mar-t15 mar-b55"> <button class="tj-btn-middle submit-btn" onclick="template_zytb_submit(\'tbtjzy-edit-post\',this,event)">提 交</button><a href="javascript:history.go(-1)" class="tj-btn-middle-grey mar-l25">返 回</a> </div> </td> </tr> </table> </form> ') : ($out += ' <div class="tj-info-warp"> <div class="tj-info"> <div class="tj-info-icon"><i class="iconfont">&#xe66c;</i></div> <div class="tj-info-content"> <h3 class="tj-info-title">', $out += $escape(message.error[0].mc), $out += '</h3> <p class="tj-info-explain"> <a href="qecx.html">查询计划余额信息</a> | <a href="tjzy.html">查看已填报志愿</a> </p> </div> </div> </div> '), $out += " </div> ", include("../common/tbzy-web-tips"), $out += " </div> </div> ") : ($out += ' <h2 class="tj-h2 mar-t10">修改调剂志愿</h2> <form class="fromSubmit"> <input type="hidden" name="id" value="', $out += $escape(data.vo.id), $out += '"/> <input type="hidden" name="adjust_id" value="', $out += $escape(data.vo.adjust_id), $out += '" id="adjust_id"/> <table class="tj-tbtjzy-warp"> <tr> <td> <select name="ssdm" id="ssdm" onchange="seach_dwdm(false)"> <option value="', $out += $escape(data.vo.ssdm), $out += '" selected="selected">请选择省市</option> </select> <div class="tj-error"></div> </td> </tr> <tr> <td> <select name="dwxx" id="dwxx" onchange="seach_yxxx(false)"> <option value="">请选择招生单位</option> <option value="', $out += $escape(data.vo.dwdm), $out += '" selected="selected">(', $out += $escape(data.vo.dwdm), $out += ")", $out += $escape(data.vo.dwmc), $out += '</option> </select> <p class="tb-tip tb-tip-time">注意：该招生单位设定的志愿锁定时间为 <strong id="edit-time" class="color-blue">36</strong> 小时。</p> <p class="tb-tip" id="js-sxbz" style="display: block;color: #fd6d4a"></p> <div class="tj-error"></div> </td> </tr> <tr> <td> <select name="yxxx" id="yxxx" onchange="seach_zyxx(false)"> <option value="">请选择院系所</option> <option value="', $out += $escape(data.vo.yxsdm), $out += '" selected="selected">(', $out += $escape(data.vo.yxsdm), $out += ")", $out += $escape(data.vo.yxsmc), $out += '</option> </select> <p class="tb-tip">注意：如果招生单位未发布计划余额信息，则该项无内容。</p> <div class="tj-error"></div> </td> </tr> <tr> <td> <select name="zyxx" id="zyxx" onchange="seach_fxxxx(false)"> <option value="">请选择专业</option> <option value="', $out += $escape(data.vo.zydm), $out += '" selected="selected">(', $out += $escape(data.vo.zydm), $out += ")", $out += $escape(data.vo.zymc), $out += '</option> </select> <p class="tb-tip">注意：如果招生单位某专业未发布计划余额信息或考生<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">不符合调入专业学科门类要求</a>，则该专业不显示。</p> <div class="tj-error"></div> </td> </tr> <tr> <td> <select name="fxxxx" id="fxxxx" onchange="seach_xxfs()"> <option value="">请选择研究方向</option> <option value="', $out += $escape(data.vo.yjfxdm), $out += '" selected="selected">(', $out += $escape(data.vo.yjfxmc), $out += ")", $out += $escape(data.vo.yjfxmc), $out += '</option> </select> <div class="tj-error"></div> </td> </tr> <tr> <td> <select name="xxfs" id="xxfs" onchange="seach_bz()"> <option value="">请选择学习方式</option> <option value="1" ', 1 == data.vo.xxfs && ($out += 'selected="selected"'), $out += '>全日制</option> <option value="2" ', 2 == data.vo.xxfs && ($out += 'selected="selected"'), $out += '>非全日制</option> </select> <div class="tj-error"></div> </td> </tr> <tr class="tj-tsyq-warp tj-hide"> <td><div class="tj-tsyq"><span id="tsyq"><span></div></td> </tr> <tr> <td class="form-group"><div class="tj-error-warp"><textarea value="" name="bz" id="bz" maxlength="50" placeholder="请填写备注" autocomplete="off">', $out += $escape(data.vo.bz), $out += '</textarea><p class="tb-tip">请按招生单位要求填写,最多50个字符。</p><div class="tj-error"></div></div></td> </tr> <tr> <td class="form-group"> <div class="tj-error-warp"> <input type="hidden" value="', $out += $escape(yddh), $out += '" name="yddh" id="yddh" maxlength="50" /> <div style="font-size: .346667rem; text-align: left;"> 考生手机号码：', $out += $escape(yddh), $out += ' <a href="//account.chsi.com.cn/account/account!show" target="_blank">修改</a> </div> <p class="tb-tip">注意：此信息来源于实名注册，如有误请登录实名注册进行修改，修改后退出系统且关闭浏览器重新登录并同步信息后方可生效。</p> </div> </td> </tr> <tr class="submit-tr"> <td><button class="tj-btn-middle tbtjzy-btn submit-btn" onclick="template_zytb_submit(\'tbtjzy-edit-post\',this,event)">提 交</button></td> </tr> </table> </form> ', include("../common/tbzy-wap-tips"), $out += " "), $out += " ", new String($out)
        }), template("common/tbtjzy-item", function($data, $filename) {
            "use strict";
            var $utils = this,
                agent_from = ($utils.$helpers, $data.agent_from),
                codeType = $data.codeType,
                $each = $utils.$each,
                seachList = $data.seachList,
                $escape = ($data.value, $data.index, $utils.$escape),
                code = $data.code,
                $out = "";
            return $out += " ", "wap" == agent_from ? ($out += ' <option value="">请选择', "1" == codeType ? $out += "招生单位" : "2" == codeType ? $out += "院系所" : "3" == codeType ? $out += "专业" : "4" == codeType ? $out += "研究方向" : "5" == codeType && ($out += "学习方式"), $out += "</option> ") : $out += ' <option value="">请选择</option> ', $out += " ", $each(seachList, function(value, index) { $out += ' <option value="', $out += $escape(value.code), $out += '" ', value.code == code && ($out += 'selected="selected"'), $out += ">(", $out += $escape(value.code), $out += ") ", $out += $escape(value.name), $out += "</option> " }), $out += " ", new String($out)
        }), template("common/tbtjzy", function($data, $filename) {
            "use strict";
            var $utils = this,
                sxbz2 = ($utils.$helpers, $data.sxbz2),
                sxbz3 = $data.sxbz3,
                sxbz = $data.sxbz,
                include = function(filename, data) { data = data || $data; var text = $utils.$include(filename, data, $filename); return $out += text },
                other = $data.other,
                flag = $data.flag,
                data = $data.data,
                $escape = $utils.$escape,
                ksbh = $data.ksbh,
                yddh = $data.yddh,
                message = $data.message,
                $out = "";
            return $out += " ", "1" != sxbz2 && "1" != sxbz3 || "1" == sxbz || ($out += " ", include("../common/sxbz-include"), $out += " "), $out += " ", "web" == other.agent_from ? ($out += ' <div class="tbzy mar-t20"> <div class="clearfix"> <div class="width700 tj-float-left"> <h2 class="text-c">填报调剂志愿</h2> ', flag ? ($out += " ", data.notice.status.max_num > data.notice.status.cur_num ? ($out += ' <div class="mar-t10 mar-l80"> <div class="info-prompt">  <ul> <li style="color: #666;">', data.notice.status && ($out += ' 可一次填报 <span class="color-blue">', $out += $escape(data.notice.status.max_num), $out += '</span> 个平行调剂志愿，您已填报 <span class="color-blue">', $out += $escape(data.notice.status.cur_num), $out += '</span> 个，还可以填报 <span class="color-blue">', $out += $escape(data.notice.status.max_num - data.notice.status.cur_num), $out += "</span> 个"), $out += '</li> </ul> </div> </div> <form class="fromSubmit"> <input type="hidden" name="adjust_id" value="', $out += $escape(data.vo.id), $out += '" id="adjust_id"/> <table> <tr> <td class="text-r" width="210"><label class="zy-title">省市：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.ssdm), $out += ") ", $out += $escape(data.vo.ssmc), $out += '</div></td> </tr> <tr> <td class="text-r" width="210"><label class="zy-title">招生单位：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.dwdm), $out += ") ", $out += $escape(data.vo.dwmc), $out += '</div></td> </tr> <tr> <td></td> <td> <p class="tb-tip tb-tip-time" style="display: block;"> 注意：该招生单位设定的志愿锁定时间为 <strong id="edit-time" class="color-blue">', $out += $escape(data.vo.bz1), $out += "</strong> 小时。</p> ", ksbh.substring(0, 5) == data.dwdm || 1 == sxbz || 1 != sxbz2 && 1 != sxbz3 || ($out += ' <p class="tb-tip" id="js-sxbz" style="display: block;color: #fd6d4a">您未达到国家线要求，只能按规定申请校内调剂。</p> '), $out += ' </td> </tr> <tr> <td class="text-r"><label class="zy-title">院系所：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.yxsdm), $out += ") ", $out += $escape(data.vo.yxsmc), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title">专业：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.zydm), $out += ") ", $out += $escape(data.vo.zymc), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title">研究方向：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.yjfxdm), $out += ") ", $out += $escape(data.vo.yjfxmc), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title">学习方式：</label></td> <td><div class="zy-content">', "1" == data.vo.xxfs ? $out += "(1) 全日制" : "2" == data.vo.xxfs && ($out += "(2) 非全日制"), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title"></label></td> <td> <div class="zy-content tj-tsyq"> <a onclick="template_zytb_tj(\'', $out += $escape(data.vo.id), $out += '\')">查看申请条件</a> </div> </td> </tr> <tr> <td class="text-r"><div class="mar-t15">备注：</div></td> <td class="form-group"><textarea value="" name="bz" class="tj-input zytb-textarea width240 tj-float-left mar-b0" id="bz" maxlength="50" autocomplete="off"></textarea><span class="tj-error tj-error-style tj-float-left mar-t25"></span></td> </tr> <tr> <td></td> <td> <p class="tb-tip">请按招生单位要求填写,最多50个字符。</p> </td> </tr> <tr> <td class="text-r"><div class="mar-t15"><span class="tj-require">*</span>考生手机号码：</div></td> <td> <div class="mar-t15"> <input type="hidden" value="', $out += $escape(yddh), $out += '" name="yddh" id="yddh" maxlength="50" /> ', $out += $escape(yddh), $out += ' <a href="//account.chsi.com.cn/account/account!show" target="_blank">修改</a> </div> </td> </tr> <tr> <td></td> <td> <p class="tb-tip">注意：此信息来源于实名注册，如有误请登录实名注册进行修改，修改后退出系统且关闭浏览器重新登录并同步信息后方可生效。</p> </td> </tr> <tr class="submit-tr"> <td></td> <td> <div class="mar-t15 mar-b55"> <button class="tj-btn-middle submit-btn" onclick="template_zytb_submit(\'tbtjzy-post\',this,event)">提 交</button> <a href="javascript:history.go(-1)" class="tj-btn-middle-grey mar-l25">返 回</a> </div> </td> </tr> </table> </form> ') : ($out += ' <div class="tj-info-warp"> <div class="tj-info"> <div class="tj-info-icon"><i class="iconfont">&#xe66c;</i></div> <div class="tj-info-content"> <h3 class="tj-info-title">最多可填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num), $out += '</span> 个平行调剂志愿，您已填写 <span class="color-blue">', $out += $escape(data.notice.status.cur_num), $out += '</span> 个，还可以填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num - data.notice.status.cur_num), $out += '</span> 个!</h3> <p class="tj-info-explain"> <a href="qecx.html">查询计划余额信息</a> | <a href="tjzy.html">查看已填报志愿</a> </p> </div> </div> </div> '), $out += " ") : ($out += ' <div class="tj-info-warp"> <div class="tj-info"> <div class="tj-info-icon"><i class="iconfont">&#xe66c;</i></div> <div class="tj-info-content"> <h3 class="tj-info-title">', $out += $escape(message.error[0].mc), $out += '</h3> <p class="tj-info-explain"> <a href="qecx.html">查询计划余额信息</a> | <a href="tjzy.html">查看已填报志愿</a> </p> </div> </div> </div> '), $out += " </div> ", include("../common/tbzy-web-tips"), $out += " </div> </div> ") : ($out += ' <h2 class="tj-h2 mar-t10">填报调剂志愿</h2> <div class="tj-notice">', data.notice.status && ($out += ' 可一次填报 <span class="color-blue">', $out += $escape(data.notice.status.max_num), $out += '</span> 个平行调剂志愿，您已填写 <span class="color-blue">', $out += $escape(data.notice.status.cur_num), $out += '</span> 个，还可以填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num - data.notice.status.cur_num), $out += "</span> 个"), $out += '</div> <form class="fromSubmit"> <input type="hidden" name="adjust_id" value="', $out += $escape(data.vo.id), $out += '" id="adjust_id"/> <table class="tj-tbtjzy-warp no-edit"> <tr> <td> <div class="tj-message-item">省市：(', $out += $escape(data.vo.ssdm), $out += ") ", $out += $escape(data.vo.ssmc), $out += '</div> </td> </tr> <tr> <td> <div class="tj-message-item">招生单位：(', $out += $escape(data.vo.dwdm), $out += ") ", $out += $escape(data.vo.dwmc), $out += '</div> </td> </tr> <tr> <td> <p class="tb-tip">注意：该招生单位设定的志愿锁定时间为 <strong id="edit-time" class="color-blue">', $out += $escape(data.vo.bz1), $out += "</strong> 小时。</p> ", ksbh.substring(0, 5) == data.dwdm || 1 == sxbz || 1 != sxbz2 && 1 != sxbz3 || ($out += ' <p class="tb-tip" id="js-sxbz" style="display: block;color: #fd6d4a">您未达到国家线要求，只能按规定申请校内调剂。</p> '), $out += ' </td> </tr> <tr> <td> <div class="tj-message-item">院系所：(', $out += $escape(data.vo.yxsdm), $out += ") ", $out += $escape(data.vo.yxsmc), $out += '</div> </td> </tr> <tr> <td> <div class="tj-message-item">专业：(', $out += $escape(data.vo.zydm), $out += ") ", $out += $escape(data.vo.zymc), $out += '</div> </td> </tr> <tr> <td> <div class="tj-message-item">研究方向：(', $out += $escape(data.vo.yjfxdm), $out += ") ", $out += $escape(data.vo.yjfxmc), $out += '</div> </td> </tr> <tr> <td> <div class="tj-message-item">学习方式：', "1" == data.vo.xxfs ? $out += "(1) 全日制" : "2" == data.vo.xxfs && ($out += "(2) 非全日制"), $out += '</div> </td> </tr> <tr class="tj-tsyq-warp"> <td> <div class="tj-tsyq"> <a onclick="template_zytb_tj(\'', $out += $escape(data.vo.id), $out += '\')">查看申请条件</a> </div> </td> </tr> <tr> <td class="form-group"><div class="tj-error-warp"><textarea value="" name="bz" id="bz" maxlength="50" placeholder="请填写备注" autocomplete="off"></textarea><p class="tb-tip">请按招生单位要求填写,最多50个字符。</p><div class="tj-error"></div></div></td> </tr> <tr> <td class="form-group"> <div class="tj-error-warp"> <input type="hidden" value="', $out += $escape(yddh), $out += '" name="yddh" id="yddh" maxlength="50" /> <div style="font-size: .346667rem; text-align: left;"> 考生手机号码：', $out += $escape(yddh), $out += ' <a href="//account.chsi.com.cn/account/account!show" target="_blank">修改</a> </div> <p class="tb-tip">注意：此信息来源于实名注册，如有误请登录实名注册进行修改，修改后退出系统且关闭浏览器重新登录并同步信息后方可生效。</p> </div> </td> </tr> <tr class="submit-tr"> <td> <button class="tj-btn-middle tbtjzy-btn submit-btn" onclick="template_zytb_submit(\'tbtjzy-post\',this,event)">提 交</button> </td> </tr> </table> </form> ', include("../common/tbzy-wap-tips"), $out += " "), $out += " ", new String($out)
        }), template("common/tbzy-wap-tips", ' <div class="mar-t20 tj-warn" style="margin-bottom: 0;"> <div class="tj-warn-title"><i class="iconfont">&#xe657;</i>注意：</div> <div class="tj-warn-content"> <ul> <li>1、申请调剂考生应符合招生管理规定确定的<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">调剂条件</a>。</li> <li>2、不符合调入专业学科门类要求、初试科目要求或招生单位调剂申请条件的考生，将无法填报相关志愿。</li> <li>3、每次提交志愿申请后，如招生单位未明确受理意见，锁定时间（由各招生单位自主设定，最长不超过36小时）到达后，考生可继续填报其他志愿。</li> <li>4、提交调剂志愿后，<span style="color: #fd6d4a;">请及时登录调剂系统查看</span>，如接到复试通知，应在招生单位规定时间内通过调剂系统<span style="color: #fd6d4a;">确认</span>回复，并按招生单位要求办理相关手续。</li> </ul> </div> </div>'), template("common/tbzy-web-tips", ' <div class="zytb-tip tj-float-right"> <h4>注意：</h4> <p>1、申请调剂考生应符合招生管理规定确定的<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">调剂条件</a>。</p> <p>2、不符合调入专业学科门类要求、初试科目要求或招生单位调剂申请条件的考生，将无法填报相关志愿。</p> <p>3、每次提交志愿申请后，如招生单位未明确受理意见，锁定时间（由各招生单位自主设定，最长不超过36小时）到达后，考生可继续填报其他志愿。</p> <p>4、提交调剂志愿后，<span style="color: #fd6d4a;">请及时登录调剂系统查看</span>，如接到复试通知，应在招生单位规定时间内通过调剂系统<span style="color: #fd6d4a;">确认</span>回复，并按招生单位要求办理相关手续。</p> </div> '), template("common/tips", ' <div class="mar-t20 tj-warn" style="margin-bottom: 0;"> <div class="tj-warn-title"><i class="iconfont">&#xe657;</i>注意：</div> <div class="tj-warn-content"> <ul> <li>1、申请调剂考生应符合招生管理规定确定的<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">调剂条件</a>。</li> <li>2、不符合调入专业学科门类要求、初试科目要求或招生单位调剂申请条件的考生，将无法填报相关志愿。</li> <li>3、招生单位、专业至少填写一项。</li> <li>4、考生成绩要符合教育部按照一区、二区制定并公布参加全国统一考试考生进入复试的初试成绩基本要求。</li> <li>一区包括北京、天津、河北、山西、辽宁、吉林、黑龙江、上海、江苏、浙江、安徽、福建、江西、山东、河南、湖北、湖南、广东、重庆、四川、陕西等21省（市）；</li> <li>二区包括内蒙古、广西、海南、贵州、云南、西藏、甘肃、青海、宁夏、新疆等10省（区）。</li> <li>5、计划余额无具体数字的，可向有关招生单位、院系所咨询。</li> </ul> </div> </div>'), template("common/tjjcap", function($data, $filename) {
            "use strict";
            var $utils = this,
                flag = ($utils.$helpers, $data.flag),
                other = $data.other,
                $escape = $utils.$escape,
                data = $data.data,
                $string = $utils.$string,
                $out = "";
            return flag && ($out += " ", "web" == other.agent_from ? ($out += ' <div class="zxly-main-title mar-t15"> <span><label class="color-blue">', $out += $escape(data.vo.dwmc), $out += "</label> 调剂进程安排</span> ", "" != data.vo.xgsj && ($out += ' <label style="color: #999;">（更新时间：', $out += $escape(data.vo.xgsj), $out += "）</label> "), $out += ' </div> <div class="tjjc-content"> ', "" == data.vo.ggnr ? $out += ' <div style="color: #999;"> 该招生单位暂未发布！</div> ' : ($out += " ", $out += $string(data.vo.ggnr), $out += " "), $out += " </div> ") : ($out += ' <div class="tjap-title"> <h4 class="tjap-title-main"><span class="color-blue">', $out += $escape(data.vo.dwmc), $out += "</span>调剂进程安排<br> ", "" != data.vo.xgsj && ($out += ' <span class="tjap-title-time">更新时间：', $out += $escape(data.vo.xgsj), $out += "</span> "), $out += ' </h4> </div> <div class="tjap-content"> ', "" == data.vo.ggnr ? $out += ' <div style="color: #999;"> 该招生单位暂未发布！</div> ' : ($out += " ", $out += $string(data.vo.ggnr), $out += " "), $out += " </div> "), $out += " "), new String($out)
        }), template("common/tjyx-header", function($data, $filename) {
            "use strict";
            var $utils = this,
                agent_from = ($utils.$helpers, $data.agent_from),
                $out = "";
            return $out += " <div class='tj-header width1180'> <div class='tj-header-left tjyx-header-left'> <h1>全国硕士研究生网上调剂意向采集系统</h1> </div> ",
                function(filename, data) {
                    data = data || $data;
                    var text = $utils.$include(filename, data, $filename);
                    $out += text
                }("../common/header-include"), $out += ' </div> <div class="wap-nav-top"> <span class="logo"></span> <span class="logo-text">调剂意向采集系统</span> <div class="nav-right"> <a href="http://yz.chsi.com.cn/user/center.jsp" class="grzx"> <i class="iconfont" title="个人中心">&#xe645;</i> </a> <span class="ch-icon-wap-menu wap-menu"><i class="ch-icon ch-icon-wap-nav"></i></span> <div class="menu-list-warp-bg"></div> <div class="wap-menu-list"> <div class="menu-list-close"> <i class="iconfont iconfont-close">&#xe65c;</i> </div> <a class="menu-list-1" href="index.html">首页</a> <div class="menu-list-warp"> <span class="menu-list-cut">意向</span> <a class="menu-list-2 menu-list-22" href="qecx.html">调剂意向余额查询</a> <a class="menu-list-2" href="tjzy.html">调剂意向</a> </div> <a class="menu-list-1" href="cjxx.html">初试成绩</a> </div> </div> </div> ', $out += "web" == agent_from ? ' <div class=\'menu-bg\'> <ul class=\'tj-menu\'> <li data-nav = "index"><a href="index.html">首页</a></li> <li data-nav = "qecx"><a href="qecx.html">调剂意向余额查询</a></li> <li data-nav = "tjzy"><a href="tjzy.html">调剂意向</a></li> <li data-nav = "cjxx"><a href="cjxx.html">初试成绩</a></li> </ul> </div> <a class="jqr" href="//kl.chsi.com.cn/robot/index.action?system=yz_wb" target="_blank"></a> <div class="ewm-fix"> <div class="icon-con"> <i class="iconfont" title="二维码">&#xe65b;</i> <div class="ewm-detail tj-hide"> <div class="item clearfix"> <div class="left"> <img src="//t1.chei.com.cn/common/ewm/images/chsi.jpg" /> </div> <div class="right"> <div class="ewm-title">学信网公众号</div> <div class="ewm-con">关注学信网公众号并绑定微信账号，及时接收重要消息提醒</div> </div> </div> <div class="item clearfix"> <div class="left"> <img src="//t1.chei.com.cn/common/ewm/images/yz.jpg" /> </div> <div class="right"> <div class="ewm-title">研招网</div> <div class="ewm-con">研招关键节点提醒及相关信息查询</div> </div> </div> </div> </div> </div> ' : "  ", new String($out)
        }), template("common/tjyx-index", function($data, $filename) {
            "use strict";
            var $utils = this,
                agent_from = ($utils.$helpers, $data.agent_from),
                $out = "";
            return $out += "web" == agent_from ? ' <h2 class="sys-compare-h2">系统介绍</h2> <table class="sys-compare mar-t30"> <tr> <td class="text-r sys-compare-content" width="260"><h4 class="sys-compare-title">获知余额</h4><p>考生可在调剂服务系统开通前，<br/>了解招生单位发布的调剂意向余额情况。</p></td> <td width="46" class="sys-compare-num"><div class="index-num tj-float-right">1</div></td> <td rowspan="3" width="156" class="index-bg">调剂意向<br>采集服务系统</td> <td width="46" class="sys-compare-num"><div class="index-num">2</div></td> <td class="text-l sys-compare-content" width="260"><h4 class="sys-compare-title">一键转入</h4><p>考生可在调剂服务系统开通24小时内，<br/>在满足条件的前提下，将调剂意向一键转为调剂志愿。</p></td> </tr> <tr> <td colspan="2"> <div class="index-cutline tj-float-right"></div> </td> <td colspan="2"> <div class="index-cutline"></div> </td> </tr> <tr> <td class="text-r sys-compare-content" width="260"><h4 class="sys-compare-title">沟通保障</h4><p>考生和招生单位可就调剂意向提前预选、<br/>双向沟通。</p></td> <td width="46" class="sys-compare-num"><div class="index-num tj-float-right">3</div></td> <td width="46" class="sys-compare-num"><div class="index-num">4</div></td> <td class="text-l sys-compare-content" width="260"><h4 class="sys-compare-title">范围更广</h4><p>考生适用的范围比调剂服务系统更广，<br/>最多可填写10个平行调剂意向。</p></td> </tr> </table> ' : ' <h2 class="index-h2 mar-t1"><i class="iconfont" title="通知">&#xe670;</i>系统介绍</h2> <ul class="tj-step"> <li> <span class="first">1</span> <h4><i>1、</i>获知余额</h4><div class="arrow-right"></div> <p>考生可在调剂服务系统开通前，了解招生单位发布的调剂意向余额情况。</p> </li> <li> <span class="second">2</span> <h4><i>2、</i>一键转入</h4><div class="arrow-right"></div> <p>调剂服务系统开通24小时内，在满足条件的前提下，考生可将调剂意向一键转为调剂志愿。</p> </li> <li> <span class="third">3</span> <h4><i>3、</i>沟通保障</h4><div class="arrow-right"></div> <p>考生和招生单位可就调剂意向提前预选、双向沟通。</p> </li> <li> <span class="third">4</span> <h4><i>4、</i>范围更广</h4><div class="arrow-right"></div> <p>考生适用的范围比调剂服务系统更广，最多可填写10个平行调剂意向。</p> </li> </ul> ', $out += ' <div class="mar-t55 tj-warn" style="margin-bottom: 0;"> <h4 class="tj-warn-title"><i class="iconfont">&#xe657;</i>注意：</h4> <div class="tj-warn-content"> <ul> <li>1、本调剂意向采集系统与调剂服务系统有本质的区别，不等同于调剂服务系统。</li> <li>2、申请调剂考生应符合招生管理规定确定的<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">调剂条件</a>。</li> <li>3、不符合调入专业学科门类要求、初试科目要求的考生，将无法填写相关志愿。</li> <li>4、招生单位、专业至少填写一项。</li> <li>5、在允许填写调剂意向的时间内，一位考生最多可填写10个平行调剂意向，并且提交成功后不可取消或更改。</li> <li>6、调剂服务系统开通后，调剂意向采集服务系统的查询调剂意向余额信息和填写调剂意向功能将关闭。考生可以在调剂服务系统查询余额信息。</li> <li>7、调剂服务系统开通24小时内，在满足条件的前提下，考生可将调剂意向转为调剂服务系统中的调剂志愿，转成功后不可取消或更改。</li> <li>8、调剂服务系统开通24小时后，转为调剂志愿的功能将关闭。</li> </ul> </div> </div> ', "wap" == agent_from && ($out += ' <div class="ewm-wap"> <i class="iconfont" title="二维码">&#xe65b;</i> </div> <div class="wap-overlay tj-hide"></div> <div class="ewm-detail-wap tj-hide"> <div class="item-con"> <div class="item"> <div class="left"> <img src="//t1.chei.com.cn/common/ewm/images/chsi.jpg" /> </div> <div class="right"> <div class="ewm-title">学信网公众号</div> <div class="ewm-con">关注学信网公众号并绑定微信账号，及时接收重要消息提醒</div> </div> </div> <div class="item"> <div class="left"> <img src="//t1.chei.com.cn/common/ewm/images/yz.jpg" /> </div> <div class="right"> <div class="ewm-title">研招网</div> <div class="ewm-con">研招关键节点提醒及相关信息查询</div> </div> </div> </div> <i class="iconfont close">&#xe65c;</i> </div> '), new String($out)
        }), template("common/tjyx-layout", function($data, $filename) {
            "use strict";
            var $utils = this,
                include = ($utils.$helpers, function(filename, data) { data = data || $data; var text = $utils.$include(filename, data, $filename); return $out += text }),
                $out = "";
            return $out += " <div class='tj-main'> <div class='tj-content'> ", include("../common/tjyx-header"), $out += ' <div class="width1180" id="content"> </div> </div> </div> ', include("../common/footer"), new String($out)
        }), template("common/tjyx-qecx", function($data, $filename) {
            "use strict";
            var $utils = this,
                agent_from = ($utils.$helpers, $data.agent_from),
                include = function(filename, data) { data = data || $data; var text = $utils.$include(filename, data, $filename); return $out += text },
                $escape = $utils.$escape,
                $out = "";
            return $out += " ", "web" == agent_from ? ($out += " ", include("../web/tjyx-qecx-seach"), $out += '  <div id="content-qecxList"> ', include("../common/qecx-quto"), $out += " </div> ", include("../common/tjyx-tips"), $out += "  ") : ($out += " ", include("../wap/tjyx-qecx-seach"), $out += '  <div id="content-qecxList"> ', include("../common/tjyx-tips"), $out += "  </div> "), $out += " <script> init_select_ss('", $out += $escape(agent_from), $out += "'); //初始化区域 <\/script> ", new String($out)
        }), template("common/tjyx-success", '<div class="tj-nopermission"> <div class="tj-icon-success"></div> <h2>调剂意向填写已完成！</h2> <p class="tj-success-p"><a href="qecx.html">继续填写</a>&ensp;&ensp;|&ensp;&ensp;<a href="tjzy.html">查看已填写调剂意向</a></p> </div> '), template("common/tjyx-tbtjzy", function($data, $filename) {
            "use strict";
            var $utils = this,
                other = ($utils.$helpers, $data.other),
                data = $data.data,
                $escape = $utils.$escape,
                yddh = $data.yddh,
                $out = "";
            return $out += " ", "web" == other.agent_from ? ($out += ' <div class="tbzy mar-t20"> <div class="clearfix"> <div class="width700 tj-float-left"> <h2 class="text-c">填写调剂意向</h2> ', data.notice.status.max_num > data.notice.status.cur_num ? ($out += ' <div class="mar-t10 mar-l80"> <div class="info-prompt">  <ul> <li style="color: #666;">', data.notice.status && ($out += ' 最多可填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num), $out += '</span> 个平行调剂意向，您已填写 <span class="color-blue">', $out += $escape(data.notice.status.cur_num), $out += '</span> 个，还可以填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num - data.notice.status.cur_num), $out += "</span> 个"), $out += '</li> </ul> </div> </div> <form class="fromSubmit"> <input type="hidden" name="adjust_id" value="', $out += $escape(data.vo.id), $out += '"/> <table> <tr> <td class="text-r" width="210"><label class="zy-title">招生单位：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.dwdm), $out += ") ", $out += $escape(data.vo.dwmc), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title">院系所：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.yxsdm), $out += ") ", $out += $escape(data.vo.yxsmc), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title">专业：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.zydm), $out += ") ", $out += $escape(data.vo.zymc), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title">研究方向：</label></td> <td><div class="zy-content">(', $out += $escape(data.vo.yjfxdm), $out += ") ", $out += $escape(data.vo.yjfxmc), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title">学习方式：</label></td> <td><div class="zy-content">', "1" == data.vo.xxfs ? $out += "(1) 全日制" : "2" == data.vo.xxfs && ($out += "(2) 非全日制"), $out += '</div></td> </tr> <tr> <td class="text-r"><label class="zy-title"></label></td> <td><div class="zy-content tj-tsyq">', "" != data.vo.bz ? $out += $escape(data.vo.bz) : $out += "招生单位对该专业暂无特殊要求", $out += '</div></td> </tr> <tr> <td class="text-r"><div class="mar-t15">备注：</div></td> <td class="form-group"><textarea value="" name="bz" class="tj-input zytb-textarea width240 tj-float-left mar-b0" id="bz" maxlength="50" autocomplete="off"></textarea><span class="tj-error tj-error-style tj-float-left mar-t25"></span></td> </tr> <tr> <td></td> <td> <p class="tb-tip">请按招生单位要求填写,最多50个字符。</p> </td> </tr> <tr> <td class="text-r"><div class="mar-t15"><span class="tj-require">*</span>考生手机号码：</div></td> <td> <div class="mar-t15"> <input type="hidden" value="', $out += $escape(yddh), $out += '" name="yddh" id="yddh" maxlength="50" /> ', $out += $escape(yddh), $out += ' <a href="//account.chsi.com.cn/account/account!show" target="_blank">修改</a> </div> </td> </tr> <tr> <td></td> <td> <p class="tb-tip">注意：此信息来源于实名注册，如有误请登录实名注册进行修改，修改后退出系统且关闭浏览器重新登录并同步信息后方可生效。</p> </td> </tr> <tr class="submit-tr"> <td></td> <td> <div class="mar-t15 mar-b55"> <a href="###" class="tj-btn-middle submit-btn" onclick="template_zytb_submit(\'tjyx-tbtjzy-post\',this,event)">提 交</a><a href="javascript:history.go(-1)" class="tj-btn-middle-grey mar-l25">返 回</a> </div> </td> </tr> </table> </form> ') : ($out += ' <div class="tj-info-warp"> <div class="tj-info"> <div class="tj-info-icon"><i class="iconfont">&#xe66c;</i></div> <div class="tj-info-content"> <h3 class="tj-info-title">最多可填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num), $out += '</span> 个平行调剂意向，您已填写 <span class="color-blue">', $out += $escape(data.notice.status.cur_num), $out += '</span> 个，还可以填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num - data.notice.status.cur_num), $out += '</span> 个!</h3> <p class="tj-info-explain"> <a href="qecx.html">查询调剂意向余额信息</a> | <a href="tjzy.html">查看已填写意向</a> </p> </div> </div> </div> '),
                $out += ' </div>  <div class="zytb-tip tj-float-right"> <h4>注意：</h4> <p>1、申请调剂考生应符合招生管理规定确定的<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">调剂条件</a>。</p> <p>2、不符合调入专业学科门类要求、初试科目要求的考生，将无法填写相关志愿。</p> <p>3、调剂服务系统开通后，查询调剂意向余额信息和填写调剂意向功能将关闭。</p> <p>4、调剂服务系统开通24小时内，在满足条件的前提下，考生可将调剂意向转为调剂服务系统中的调剂志愿，转移成功后不可取消或更改。</p> <p>5、调剂服务系统开通24小时后，转移为调剂志愿的功能将关闭。</p> </div> </div> </div> ') : ($out += ' <h2 class="tj-h2 mar-t10">填写调剂意向</h2> <div class="tj-notice">', data.notice.status && ($out += ' 最多可填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num), $out += '</span> 个平行调剂意向，您已填写 <span class="color-blue">', $out += $escape(data.notice.status.cur_num), $out += '</span> 个，还可以填写 <span class="color-blue">', $out += $escape(data.notice.status.max_num - data.notice.status.cur_num), $out += "</span> 个"), $out += '</div> <form class="fromSubmit"> <input type="hidden" name="adjust_id" value="', $out += $escape(data.vo.id), $out += '"/> <table class="tj-tbtjzy-warp"> <tr> <td> <div class="tj-message-item">招生单位：(', $out += $escape(data.vo.dwdm), $out += ") ", $out += $escape(data.vo.dwmc), $out += '</div> </td> </tr> <tr> <td> <div class="tj-message-item">院系所：(', $out += $escape(data.vo.yxsdm), $out += ") ", $out += $escape(data.vo.yxsmc), $out += '</div> </td> </tr> <tr> <td> <div class="tj-message-item">专业：(', $out += $escape(data.vo.zydm), $out += ") ", $out += $escape(data.vo.zymc), $out += '</div> </td> </tr> <tr> <td> <div class="tj-message-item">研究方向：(', $out += $escape(data.vo.yjfxdm), $out += ") ", $out += $escape(data.vo.yjfxmc), $out += '</div> </td> </tr> <tr> <td> <div class="tj-message-item">学习方式：', "1" == data.vo.xxfs ? $out += "(1) 全日制" : "2" == data.vo.xxfs && ($out += "(2) 非全日制"), $out += '</div> </td> </tr> <tr> <td> <div class="tj-tsyq"> ', "" != data.vo.bz ? ($out += " ", $out += $escape(data.vo.bz), $out += " ") : $out += " 招生单位对该专业暂无特殊要求 ", $out += ' </div> </td> </tr> <tr> <td class="form-group"><div class="tj-error-warp"><textarea value="" name="bz" id="bz" maxlength="50" placeholder="请填写备注" autocomplete="off"></textarea><p class="tb-tip">请按招生单位要求填写,最多50个字符。</p><div class="tj-error"></div></div></td> </tr> <tr> <td class="form-group"> <div class="tj-error-warp"> <input type="hidden" value="', $out += $escape(yddh), $out += '" name="yddh" id="yddh" maxlength="50" /> <div style="font-size: .346667rem; text-align: left;"> 考生手机号码：', $out += $escape(yddh), $out += ' <a href="//account.chsi.com.cn/account/account!show" target="_blank">修改</a> </div> <p class="tb-tip">注意：此信息来源于实名注册，如有误请登录实名注册进行修改，修改后退出系统且关闭浏览器重新登录并同步信息后方可生效。</p> </div> </td> </tr> <tr class="submit-tr"> <td><a href="###" class="tj-btn-middle tbtjzy-btn" onclick="template_zytb_submit(\'tjyx-tbtjzy-post\',this,event)">提 交</a></td> </tr> </table> </form>  <div class="mar-t20 tj-warn" style="margin-bottom: 0;"> <div class="tj-warn-title"><i class="iconfont">&#xe657;</i>注意：</div> <div class="tj-warn-content"> <ul> <li>1、参加网上报名并准考的部分考生。</li> <li>2、不属于第一条范围内的其它用户无法使用此系统。</li> <li>3、调剂服务系统开通后，查询调剂意向余额信息和填写调剂意向功能将关闭。</li> <li>4、调剂服务系统开通24小时内，在满足条件的前提下，考生可将调剂意向转为调剂服务系统中的调剂志愿，转移成功后不可取消或更改。</li> <li>5、调剂服务系统开通24小时后，转移为调剂志愿的功能将关闭。</li> </ul> </div> </div> '), $out += " ", new String($out)
        }), template("common/tjyx-tips", ' <div class="mar-t20 tj-warn" style="margin-bottom: 0;"> <div class="tj-warn-title"><i class="iconfont">&#xe657;</i>注意：</div> <div class="tj-warn-content"> <ul> <li>1、本调剂意向采集系统与调剂服务系统有本质的区别，不等同于调剂服务系统。</li> <li>2、申请调剂考生应符合招生管理规定确定的<a target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">调剂条件</a>。</li> <li>3、不符合调入专业学科门类要求、初试科目要求的考生，将无法填写相关志愿。</li> <li>4、招生单位、专业至少填写一项。</li> <li>5、在允许填写调剂意向的时间内，一位考生最多可填写10个平行调剂意向，并且提交成功后不可取消或更改。</li> <li>6、调剂服务系统开通后，调剂意向采集服务系统的查询调剂意向余额信息和填写调剂意向功能将关闭。考生可以在调剂服务系统查询余额信息。</li> <li>7、调剂服务系统开通24小时内，在满足条件的前提下，考生可将调剂意向转为调剂服务系统中的调剂志愿，转移成功后不可取消或更改。</li> <li>8、调剂服务系统开通24小时后，转移为调剂志愿的功能将关闭。</li> </ul> </div> </div>'), template("common/tjyx-tjzy", function($data, $filename) {
            "use strict";
            var $utils = this,
                $helpers = $utils.$helpers,
                data = $data.data,
                flag = $data.flag,
                other = $data.other,
                $each = $utils.$each,
                $escape = ($data.value, $data.index, $utils.$escape),
                tj_usable = $data.tj_usable,
                $out = "";
            return $out += " <div ", data.notice.status.cur_num < data.notice.status.max_num && ($out += 'class="tbtjzy-add-fixed"'), $out += "> ", flag && ($out += " ", "" != data.vo_list.vos ? ($out += " ", data.notice.status.cur_num < data.notice.status.max_num && ($out += " ", "wap" == other.agent_from && ($out += ' <div class="text-c tbtjzy-add"> <a href="qecx.html" class="tj-btn-big">新增调剂意向</a> </div> '), $out += " "), $out += ' <div class="zy-main-div clearfix mar-t20 zy-main-postion"> ', $each(data.vo_list.vos, function(value, index) { $out += ' <div class="zy-main"> ', "wap" == other.agent_from && ($out += ' <div class="tjzy-top"> <div class="left-line"> ', $out += $escape($helpers.changeLetter(index)), $out += ' </div> <div class="left"> <h6>平行调剂意向</h6> <div class="timer">', $out += $escape(value.bmsjStr), $out += '</div> </div> </div> <div class="tjzy-top-line"></div> '), $out += ' <div class="zy-cxt"> ', "web" == other.agent_from && ($out += ' <div class="tjzy-top"> <div class="left-line"> ', $out += $escape($helpers.changeLetter(index)), $out += ' </div> <div class="left"> <h6>平行调剂意向</h6> <div class="timer">', $out += $escape(value.bmsjStr), $out += '</div> </div> </div> <div class="tjzy-top-line"></div> '), $out += ' <div class="zy-cxt-dl"> <div class="clearfix"><div class="list-title">招生单位：</div><div class="list-content">(', $out += $escape(value.dwdm), $out += ") ", $out += $escape(value.dwmc), $out += '</div></div> <div class="clearfix"><div class="list-title">院系所：</div><div class="list-content">(', $out += $escape(value.yxsdm), $out += ") ", $out += $escape(value.yxsmc), $out += '</div></div> <div class="clearfix"><div class="list-title">专业：</div><div class="list-content">(', $out += $escape(value.zydm), $out += ") ", $out += $escape(value.zymc), $out += '</div></div> <div class="clearfix"><div class="list-title">研究方向：</div><div class="list-content">(', $out += $escape(value.yjfxdm), $out += ") ", $out += $escape(value.yjfxmc), $out += '</div></div> <div class="clearfix"><div class="list-title">学习方式：</div><div class="list-content">(', $out += $escape(value.xxfs), $out += ") ", "1" == value.xxfs ? $out += "全日制" : "2" == value.xxfs && ($out += "非全日制"), $out += '</div></div> <div class="clearfix"><div class="list-title">备注：</div><div class="list-content">', "" == value.bz ? $out += "无" : $out += $escape(value.bz), $out += '</div></div> <div class="clearfix"><div class="list-title">考生手机号码：</div><div class="list-content">', $out += $escape(value.lxdh), $out += '</div></div> \x3c!-- <div class="clearfix list-message mar-t30"> <a href="/yjszs/tjzx/tjyxly.html?dwdm=', $out += $escape(value.dwdm), $out += "&yxsdm=", $out += $escape(value.yxsdm), $out += "&token=", $out += $escape(value.zxtoken), $out += "&zyId=", $out += $escape(value.id), $out += "&ksbh=", $out += $escape(value.ksbh), $out += '&lx=2" title="" class="list-message-ly" ', "web" == other.agent_from && ($out += 'target="_blank"'), $out += '>向招生单位留言</a> </div> --\x3e </div> </div> <div class="tj-fixed"> ', tj_usable ? ($out += " ", "" == value.tj_id ? ($out += " ", value.istjzyym ? $out += ' <p class="grey-status"><i class="iconfont">&#xe62a;</i>调剂志愿已达三个，不可继续转为调剂志愿</p> ' : ($out += ' <a class="tj-btn-big" href="javascript:;" onclick="template_totjzy(\'tjyx-mytjyx-post\',\'', $out += $escape(value.id), $out += "',event)\">转为调剂志愿</a> "), $out += " ") : $out += ' <p class="grey-status"><i class="iconfont">&#xe68d;</i>已转为调剂志愿</p> ', $out += " ") : $out += ' <p class="grey-status"><i class="iconfont">&#xe668;</i>调剂服务系统尚未开通，不可转为调剂志愿</p> ', $out += " </div> </div> " }), $out += " ", data.notice.status.cur_num < data.notice.status.max_num && ($out += " ", "web" == other.agent_from && ($out += ' <div class="zy-main"> <a class="zy-add" href="qecx.html"> <div class="zy-add-icon">+</div> <div class="zy-add-content">填写调剂意向</div> </a> </div> '), $out += " "), $out += " </div> ") : ($out += ' <div class="sys-tip no-data mar-t20"> ', "web" == other.agent_from && ($out += ' <div class="text-c"> <a href="qecx.html" class="tj-btn-big">填写调剂意向</a> </div> '), $out += ' <div class="tj-bg-zyempty"></div> <div class="tj-empty-message">您暂无调剂意向</div> </div> ', "wap" == other.agent_from && ($out += ' <div class="text-c tbtjzy-add"> <a href="qecx.html" class="tj-btn-big">填写调剂意向</a> </div> '), $out += " "), $out += " "), $out += " </div>", new String($out)
        }), template("common/tjyx-unuse", function($data, $filename) {
            "use strict";
            var $utils = this,
                page_error = ($utils.$helpers, $data.page_error),
                $string = $utils.$string,
                $out = "";
            return $out += '<div class="width700 tj-nopermission"> <div class="tj-icon-nopermission"></div> <h2>无权使用</h2> ', "" == page_error ? $out += " <p>本调剂系统适用考生范围为参加网上报名，第一志愿没有被招生单位录取并且达到全国硕士研究生招生考试考生进入复试的初试成绩基本要求的统考、管理类联考、法硕联考、少数民族骨干计划以及网上报名时选择退役大学生士兵计划考生。</p> " : ($out += ' <p class="tj-success-p">', $out += $string(page_error), $out += "</p> "), $out += " </div> ", new String($out)
        }), template("common/tjzy", function($data, $filename) {
            "use strict";
            var $utils = this,
                $helpers = $utils.$helpers,
                data = $data.data,
                flag = $data.flag,
                other = $data.other,
                $each = $utils.$each,
                $escape = ($data.value, $data.index, $utils.$escape),
                $out = "";
            return $out += ' <div class="info-prompt mar-t20"> <ul> <li>参加调剂的考生可一次填报三个平行调剂志愿，提交后的调剂志愿在招生单位设置的时间内（不超过36小时）不允许修改（每个志愿单独计时），以供招生单位下载志愿信息和决定是否通知考生参加复试。锁定时间到达后，考生可继续填报其他志愿。</li> </ul> </div> <div ', data.notice.status.cur_num < data.notice.status.max_num && ($out += 'class="tbtjzy-add-fixed"'), $out += "> ", flag && ($out += " ", "" != data.vo_list.vos ? ($out += " ", data.notice.status.cur_num < data.notice.status.max_num && ($out += " ", "wap" == other.agent_from && ($out += ' <div class="text-c tbtjzy-add"> <a href="qecx.html" class="tj-btn-big">新增调剂志愿</a> </div> '), $out += " "), $out += ' <div class="zy-main-div clearfix mar-t20 zy-main-postion"> ', $each(data.vo_list.vos, function(value, index) { $out += ' <div class="zy-main"> ', "wap" == other.agent_from && ($out += ' <div class="tjzy-top"> <div class="left-line"> ', $out += $escape($helpers.changeLetter(index)), $out += ' </div> <div class="left"> <h6>平行调剂志愿</h6> <div class="timer">', $out += $escape(value.bmsjStr), $out += '</div> </div> </div> <div class="tjzy-top-line"></div> '), $out += ' <div class="zy-cxt"> ', "web" == other.agent_from && ($out += ' <div class="tjzy-top"> <div class="left-line"> ', $out += $escape($helpers.changeLetter(index)), $out += ' </div> <div class="left"> <h6>平行调剂志愿</h6> <div class="timer">', $out += $escape(value.bmsjStr), $out += '</div> </div> </div> <div class="tjzy-top-line"></div> '), $out += ' <div class="zy-cxt-dl ', "web" == other.agent_from && ($out += "mar-t10"), $out += '"> <div class="clearfix"><div class="list-title">招生单位：</div><div class="list-content">(', $out += $escape(value.dwdm), $out += ") ", $out += $escape(value.dwmc), $out += '</div></div> <div class="clearfix"><div class="list-title">院系所：</div><div class="list-content">(', $out += $escape(value.yxsdm), $out += ") ", $out += $escape(value.yxsmc), $out += '</div></div> <div class="clearfix"><div class="list-title">专业：</div><div class="list-content">(', $out += $escape(value.zydm), $out += ") ", $out += $escape(value.zymc), $out += '</div></div> <div class="clearfix"><div class="list-title">研究方向：</div><div class="list-content">(', $out += $escape(value.yjfxdm), $out += ") ", $out += $escape(value.yjfxmc), $out += '</div></div> <div class="clearfix"><div class="list-title">学习方式：</div><div class="list-content">(', $out += $escape(value.xxfs), $out += ") ", "1" == value.xxfs ? $out += "全日制" : "2" == value.xxfs && ($out += "非全日制"), $out += '</div></div> <div class="clearfix"><div class="list-title">备注：</div><div class="list-content">', "" == value.bz ? $out += "无" : $out += $escape(value.bz), $out += '</div></div> <div class="clearfix"><div class="list-title">考生手机号码：</div><div class="list-content">', $out += $escape(value.lxdh), $out += '</div></div> </div> </div> <div class="tj-fixed"> <div class="clearfix tj-process"> ', "2" == value.applyzt ? $out += ' <div class="tj-process-item tj-process-item-long"> <div class="ch-steps-tail warning"></div> <div class="ch-steps-point warning"></div> </div> <div class="tj-process-item tj-process-item-last"> <div class="ch-steps-point warning"></div> </div> ' : ($out += ' <div class="tj-process-item"> <div class="ch-steps-tail ', "1" != value.applyzt && ($out += "active"), $out += '"></div> <div class="ch-steps-point ', "" != value.applyzt && ($out += "active"), $out += '"></div> </div> <div class="tj-process-item"> <div class="ch-steps-tail ', "4" == value.applyzt ? $out += "active" : "5" == value.applyzt && ($out += "active"), $out += '"></div> <div class="ch-steps-point ', "1" != value.applyzt && ($out += "active"), $out += '"></div> </div> <div class="tj-process-item"> <div class="ch-steps-tail ', "5" == value.applyzt && ($out += "active"), $out += '"></div> <div class="ch-steps-point ', "4" == value.applyzt ? $out += "active" : "5" == value.applyzt && ($out += "active"), $out += '"></div> </div> <div class="tj-process-item tj-process-item-last"> <div class="ch-steps-point ', "5" == value.applyzt && ($out += "active"), $out += '"></div> </div> '), $out += " </div> ", "1" == value.applyzt ? ($out += " ", "1" == value.jszt ? ($out += ' <div class="tj-process-message warning clearfix"> <div> 你是否主动申请放弃该调剂志愿？', "" != value.jsyy && ($out += '<a href="javascript:;" onclick="ckyy(\'', $out += $escape(value.jsyy), $out += "')\">详细</a>"), $out += ' <div> <a class="tj-process-message-btn" href="javascript:;" onclick="acceptJs(\'', $out += $escape(value.id), $out += "','", $out += $escape(value.jsyy), $out += '\')">是</a> <a class="tj-process-message-btn" href="javascript:;" onclick="rejectJs(\'', $out += $escape(value.id), $out += "','", $out += $escape(value.jsyy), $out += "')\">否</a> </div> </div> </div> ") : $out += ' <div class="tj-process-message"> <div>调剂申请已提交至招生单位。</div> </div> ', $out += " ") : "2" == value.applyzt ? ($out += ' <div class="tj-process-message warning"> ', "01" == value.jslx ? ($out += " <div>该调剂志愿已无效，可继续填报其他志愿。", "" != value.jsyy && ($out += '<a href="javascript:;" onclick="ckyy(\'', $out += $escape(value.jsyy), $out += '\')" style="white-space: nowrap;">详细</a>'), $out += "</div> ") : "02" == value.jslx ? ($out += " <div>不符合招生单位调剂报考条件，该志愿已被拒绝。", "" != value.jsyy && ($out += '<a href="javascript:;" onclick="ckyy(\'', $out += $escape(value.jsyy), $out += '\')" style="white-space: nowrap;">详细</a>'), $out += "</div> ") : "03" == value.jslx ? ($out += " <div>未达到进入招生单位复试条件，该志愿已被拒绝。", "" != value.jsyy && ($out += '<a href="javascript:;" onclick="ckyy(\'', $out += $escape(value.jsyy), $out += '\')" style="white-space: nowrap;">详细</a>'), $out += "</div> ") : ($out += " <div> 调剂申请已被招生单位拒绝。", "" != value.jsyy && ($out += '<a href="javascript:;" onclick="ckyy(\'', $out += $escape(value.jsyy), $out += '\')" style="white-space: nowrap;">详细</a>'), $out += " </div> "), $out += " </div> ") : "3" == value.applyzt ? ($out += " ", "1" == value.jszt ? ($out += ' <div class="tj-process-message warning clearfix"> <div> 你是否主动申请放弃该调剂志愿？', "" != value.jsyy && ($out += '<a href="javascript:;" onclick="ckyy(\'', $out += $escape(value.jsyy), $out += '\')" style="white-space: nowrap;">详细</a>'), $out += ' <div> <a class="tj-process-message-btn" href="javascript:;" onclick="acceptJs(\'', $out += $escape(value.id), $out += "','", $out += $escape(value.jsyy), $out += '\')">是</a> <a class="tj-process-message-btn" href="javascript:;" onclick="rejectJs(\'', $out += $escape(value.id), $out += "','", $out += $escape(value.jsyy), $out += "')\">否</a> </div> </div> </div> ") : $out += ' <div class="tj-process-message"><div>调剂申请已被招生单位查看。</div></div> ', $out += " ") : "4" == value.applyzt ? ($out += " ", "3" == value.fstz_zt ? $out += ' <div class="tj-process-message warning"><div>考生已拒绝复试通知。</div></div> ' : $out += ' <div class="tj-process-message"><div>招生单位已发复试通知。<a href="fstz.html">查看</a></div></div> ', $out += " ") : "5" == value.applyzt && ($out += " ", "3" == value.dlqtz_zt ? $out += ' <div class="tj-process-message warning"><div>考生已拒绝待录取。</div></div> ' : $out += ' <div class="tj-process-message"><div>招生单位已发待录取通知。<a href="dlqtz.html">查看</a></div></div> ', $out += " "), $out += ' <div class="tj-ly"> <a href="/yjszs/tjzx/tjly.html?dwdm=', $out += $escape(value.dwdm), $out += "&yxsdm=", $out += $escape(value.yxsdm), $out += "&token=", $out += $escape(value.zxtoken), $out += "&zyId=", $out += $escape(value.id), $out += "&ksbh=", $out += $escape(value.ksbh), $out += '&lx=1" title="" class="list-message-ly" ', "web" == other.agent_from && ($out += 'target="_blank"'), $out += ">给招生单位留言</a> </div> ", value.canModify ? ($out += ' <div class="tj-fixed-text tj-fixed-text-no"></div> <a class="tj-btn-middle" onclick="template_zytb_edit(\'', $out += $escape(value.id), $out += '\')" href="javascript:;">修 改</a> ') : ($out += ' <div class="tj-fixed-text"> 志愿锁定时间为<strong class="color-blue">', $out += $escape(value.lockhours), $out += '</strong>小时，由招生单位设定。 </div> <a class="tj-btn-big-grey" href="javascript:;">', $out += $escape(value.modtime), $out += "后可修改</a> "), $out += " </div> </div> " }), $out += " ", data.notice.status.cur_num < data.notice.status.max_num && ($out += " ", "web" == other.agent_from && ($out += ' <div class="zy-main"> <a class="zy-add" href="qecx.html"> <div class="zy-add-icon">+</div> <div class="zy-add-content">填报调剂志愿</div> </a> </div> '), $out += " "), $out += " </div> ") : ($out += ' <div class="sys-tip no-data mar-t20"> ', "web" == other.agent_from && ($out += ' <div class="text-c"> <a href="qecx.html" class="tj-btn-big">填报调剂志愿</a> </div> '), $out += ' <div class="tj-bg-zyempty paddingT40"></div> <div class="tj-empty-message">您暂无调剂志愿</div> </div> ', "wap" == other.agent_from && ($out += ' <div class="text-c tbtjzy-add"> <a href="qecx.html" class="tj-btn-big">填报调剂志愿</a> </div> '), $out += " "), $out += " "), $out += " </div> ", new String($out)
        }), template("common/unuse", function($data, $filename) {
            "use strict";
            var $utils = this,
                page_error = ($utils.$helpers, $data.page_error),
                $string = $utils.$string,
                $out = "";
            return $out += '<div class="width700 tj-nopermission"> <div class="tj-icon-nopermission"></div> <h2>无权使用</h2> ', "" == page_error ? $out += " <p>本调剂系统适用考生范围为参加网上报名，第一志愿没有被招生单位录取并且达到全国硕士研究生招生考试考生进入复试的初试成绩基本要求的统考、管理类联考、法硕联考、少数民族骨干计划以及网上报名时选择退役大学生士兵计划考生。</p> " : ($out += ' <p class="tj-success-p">', $out += $string(page_error), $out += "</p> "), $out += " </div>", new String($out)
        }), template("common/yzytz", function($data, $filename) {
            "use strict";
            var $utils = this,
                data = ($utils.$helpers, $data.data),
                $each = $utils.$each,
                other = ($data.value, $data.index, $data.other),
                $escape = $utils.$escape,
                $string = $utils.$string,
                $out = "";
            return $out += " ", data.vo_list.vos.length > 0 && ($out += ' <div class="tz-content">  ', $each(data.vo_list.vos, function(value, index) { $out += " ", "web" == other.agent_from ? ($out += ' <div class="fs-warp mar-t20 clearfix"> <div class="fs-warp-left" style="width: auto;"> <h5><span class="tz-tag">复试通知</span>(', $out += $escape(value.dwdm), $out += ")", $out += $escape(value.dwmc), $out += " - (", $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += " - (", $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += " - (", $out += $escape(value.yjfxdm), $out += ")", $out += $escape(value.yjfxmc), "1" == value.xxfs ? $out += " - 全日制" : "2" == value.xxfs && ($out += " - 非全日制"), $out += '</h5> <div class="timer">发送时间：', $out += $escape(value.fstz_fssj), $out += "</div> <h6>", $out += $escape(value.fstz_bt), $out += '</h6> <div class="content">', $out += $string(value.fstz_nr), $out += " ", "" != value.fstz_fj_lobid && ($out += ' <br>附件：<a href="', $out += $escape("/sytj/stu/fstzfj.action?id=" + value.fstz_fj_lobid + "&c1=" + value.c1 + "&c2=" + value.c2), $out += '" download="', $out += $escape(value.fstz_fj_name), $out += '">', $out += $escape(value.fstz_fj_name), $out += "</a> "), $out += " </div> </div> </div> ") : ($out += ' <div class="fs-main tjyx"> <div class="fs-cxt"> <div class="fs-cxt-title"> <h5><span class="tz-tag">复试通知</span>(', $out += $escape(value.dwdm), $out += ")", $out += $escape(value.dwmc), $out += " - (", $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += " - (", $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += " - (", $out += $escape(value.yjfxdm), $out += ")", $out += $escape(value.yjfxmc), "1" == value.xxfs ? $out += " - 全日制" : "2" == value.xxfs && ($out += " - 非全日制"), $out += '</h5> <div class="timer">发送时间：', $out += $escape(value.fstz_fssj), $out += '</div> </div> <div class="fs-cxt-content"> <h6>', $out += $escape(value.fstz_bt), $out += '</h6> <div class="content">', $out += $string(value.fstz_nr), $out += " ", "" != value.fstz_fj_lobid && ($out += ' <br>附件：<a href="', $out += $escape("/sytj/stu/fstzfj.action?id=" + value.fstz_fj_lobid + "&c1=" + value.c1 + "&c2=" + value.c2), $out += '" download="', $out += $escape(value.fstz_fj_name), $out += '">', $out += $escape(value.fstz_fj_name), $out += "</a> "), $out += " </div> </div> </div> </div> "), $out += " " }), $out += " </div> "), $out += " ", data.vo_list.vos2.length > 0 && ($out += ' <div class="clearfix mar-t20 tz-content"> ', $each(data.vo_list.vos2, function(value, index) { $out += " ", "web" == other.agent_from ? ($out += ' <div class="fs-warp mar-t20 clearfix"> <div class="fs-warp-left" ', 4 == value.dlqtz_zt && ($out += 'style="width: auto;"'), $out += '> <h5><span class="tz-tag">待录取通知</span>(', $out += $escape(value.dwdm), $out += ")", $out += $escape(value.dwmc), $out += " - (", $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += " - (", $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += " - (", $out += $escape(value.yjfxdm), $out += ")", $out += $escape(value.yjfxmc), "1" == value.xxfs ? $out += " - 全日制" : "2" == value.xxfs && ($out += " - 非全日制"), $out += '</h5> <div class="timer">发送时间：', $out += $escape(value.dlqtz_fssj), $out += "</div> <h6>", $out += $escape(value.dlqtz_bt), $out += '</h6> <div class="content">', $out += $string(value.dlqtz_nr), $out += " ", "" != value.dlqtz_fj_lobid && ($out += ' <br>附件：<a href="', $out += $escape("/sytj/stu/dlqtzfj.action?id=" + value.dlqtz_fj_lobid + "&c1=" + value.c1 + "&c2=" + value.c2), $out += '" download="', $out += $escape(value.dlqtz_fj_name), $out += '" download="', $out += $escape(value.dlqtz_fj_name), $out += '">', $out += $escape(value.dlqtz_fj_name), $out += "</a> "), $out += " </div> </div> ", 4 == value.dlqtz_zt && ($out += ' <div class="fs-warp-right"> <div class="fs-opt clearfix"> <div class="cancel-refuse"><i class="iconfont">&#xe67d;</i>招生单位取消待录取</div> <a href="javascript:;" class="clear" onclick="template_dlqtz_cancel_yzy(\'yzy-dlqtz-cancel-accept\',\'', $out += $escape(value.id), $out += "','content')\">接受取消待录取</a> </div> </div> "), $out += " </div> ") : ($out += ' <div class="fs-main tjyx"> <div class="fs-cxt"> <div class="fs-cxt-title"> <h5><span class="tz-tag">待录取通知</span>(', $out += $escape(value.dwdm), $out += ")", $out += $escape(value.dwmc), $out += " - (", $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += " - (", $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += " - (", $out += $escape(value.yjfxdm), $out += ")", $out += $escape(value.yjfxmc), "1" == value.xxfs ? $out += " - 全日制" : "2" == value.xxfs && ($out += " - 非全日制"), $out += '</h5> <div class="timer">发送时间：', $out += $escape(value.dlqtz_fssj), $out += '</div> </div> <div class="fs-cxt-content"> <h6>', $out += $escape(value.dlqtz_bt), $out += '</h6> <div class="content">', $out += $string(value.dlqtz_nr), $out += " ", "" != value.dlqtz_fj_lobid && ($out += ' <br>附件：<a href="', $out += $escape("/sytj/stu/dlqtzfj.action?id=" + value.dlqtz_fj_lobid + "&c1=" + value.c1 + "&c2=" + value.c2), $out += '" download="', $out += $escape(value.dlqtz_fj_name), $out += '">', $out += $escape(value.dlqtz_fj_name), $out += "</a> "), $out += " </div> ", 4 == value.dlqtz_zt && ($out += ' <div class="reply-warp"> <div class="reply reply-refuse"> <div class="cancel-refuse"><i class="iconfont">&#xe67d;</i>招生单位取消待录取</div> <a href="javascript:;" class="clear" onclick="template_dlqtz_cancel_yzy(\'yzy-dlqtz-cancel-accept\',\'', $out += $escape(value.id), $out += "','content')\">接受取消待录取</a> </div> </div> "), $out += " </div> </div> </div> "), $out += " " }), $out += " </div> "), $out += " ", 0 == data.vo_list.vos.length && 0 == data.vo_list.vos2.length && ($out += ' <div class="sys-tip no-data mar-t20"> <div class="tj-bg-zyempty"></div> <div class="tj-empty-message">您暂无一志愿通知</div> </div> '), $out += " ", new String($out)
        }), template("wap/qecx-list-item", function($data, $filename) {
            "use strict";
            var $utils = this,
                $helpers = $utils.$helpers,
                data = $data.data,
                $each = $utils.$each,
                $escape = ($data.value, $data.index, $utils.$escape),
                $out = "";
            return $out += " ", "" == data.vo_list.vos ? $out += ' <li class="tj-list-item tj-list-item-noresult">该条件下没有查询到计划余额信息</li> ' : ($out += " ", $each(data.vo_list.vos, function(value, index) { $out += ' <li class="tj-list-item"> <table id="qe_', $out += $escape(value.id), $out += '"> <tr> <td colspan="2" class="tj-list-item-dw"><a href="/sch/tjzc--method-listTjPub,yxdm-', $out += $escape(value.dwdm), $out += '.dhtml" target="_blank">(', $out += $escape(value.dwdm), $out += ") ", $out += $escape(value.dwmc), $out += '</a></td> <td rowspan="5" class="tj-list-item-sq"> ', "" == value.sfmzjybyq && ($out += ' <span class="tj-tip tsyq-has-title tj-sqtj-text" onclick="template_zytb_tj(\'', $out += $escape(value.id), $out += "')\">申请条件</span> "), $out += " ", value.hasit ? $out += '  <button class="tj-sqtj-btn" style="background: #ccc;">已申请</button> ' : ($out += " ", "" == value.sfmzjybyq ? ($out += ' <button class="tj-sqtj-btn" ', "" == value.sfmzyq ? ($out += "onclick=\"template_zytb_add('", $out += $escape(value.id), $out += "')\"") : ($out += "onclick=\"layer.alert('", $out += $escape(value.sfmzyq), $out += "',{title: '提示'})\""), $out += ">申请</button> ") : ($out += ' <a class="action-text" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">', $out += $escape(value.sfmzjybyq), $out += "</a> "), $out += " "), $out += ' <span class="tj-qe">计划余额：<br><span class="tj-qe-y">', 0 != value.qers ? ($out += $escape(value.qers), $out += "人") : $out += "有", $out += '</span></span> </td> </tr> <tr> <td class="tj-list-item-title">院系所：</td> <td class="tj-list-item-content yxxx">(', $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += '</td> </tr> <tr> <td class="tj-list-item-title">专业：</td> <td class="tj-list-item-content zyxx">(', $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += '</td> </tr> <tr> <td class="tj-list-item-title">研究方向：</td> <td class="tj-list-item-content zyxx">(', $out += $escape(value.yjfxdm), $out += ") ", $out += $escape(value.yjfxmc), $out += '</td> </tr> <tr> <td class="tj-list-item-title">学习方式：</td> <td class="tj-list-item-content xxfs">', "1" == value.xxfs ? $out += "全日制" : "2" == value.xxfs && ($out += "非全日制"), $out += '</td> </tr> <tr> <td colspan="3"> <div class="tj-list-item-tjsm"><span style="color: #f57716">调剂说明：</span>', "" == value.bz ? $out += "招生单位对该专业暂无特殊要求" : $out += $escape(value.bz), $out += '</div> <div class="tj-list-item-time"><span style="color: #f57716">最后更新：</span>', $out += $escape($helpers.gxsjHelper(value.gxsj)), $out += '</div> </td> </tr> \x3c!-- <tr> <td colspan="2" class="tj-list-tsyq"> <span class="tsyq tsyq-has"> <i class="iconfont">&#xe652;</i> </span> <span class="time"><i class="iconfont">&#xe659;</i>', $out += $escape(value.fbsjStr), $out += "</span> </td> </tr> --\x3e </table> </li> " }), $out += " "), $out += " ", new String($out)
        }), template("wap/qecx-list", function($data, $filename) {
            "use strict";
            var $utils = this,
                data = ($utils.$helpers, $data.data),
                include = function(filename, data) { data = data || $data; var text = $utils.$include(filename, data, $filename); return $out += text },
                $out = "";
            return $out += '<div id="content-qecxList">  <div class="info-prompt mar-t20"> <ul> <li>提醒：招生单位发布的调剂计划余额每次更新后将至少持续12小时，请各位考生结合各招生单位设定的志愿锁定时间综合考虑，合理申请，错峰报名。</li> </ul> </div> ', "" != data.vo_list.vos && ($out += '  <div class="tj-seach-sort clearfix"> <input type="hidden" id="sysname" value="qecx-list"> <ul> <li class="sort-icon" data-name="dwmc" data-value="">单位<span class="sort-icon-i"></span></li> <li class="sort-icon" data-name="yxsmc" data-value="">院系<span class="sort-icon-i"></span></li> <li class="sort-icon" data-name="zymc" data-value="">专业<span class="sort-icon-i"></span></li> <li class="sort-icon" data-name="qers" data-value="">人数<span class="sort-icon-i"></span></li> <li class="sort-icon sort-icon-more" data-name="xxfs" data-value="">学习方式<span class="sort-icon-i"></span></li> </ul> </div> '), $out += ' <div class="tj-list" id="wrapper"> <ul> ', include("../wap/qecx-list-item"), $out += " </ul> </div> ", include("../common/tips"), $out += "  </div> ", new String($out)
        }), template("wap/qecx-seach", function($data, $filename) {
            "use strict";
            var $utils = this,
                seachType = ($utils.$helpers, $data.seachType),
                $out = "";
            return $out += ' <div class="tj-seach-conditions mar-t10" id="tj_seach_change"> <div class="conditions-warp clearfix"> <div class="conditions-tabs"> <ul> <li ', "accurate" == seachType && ($out += 'class="tabs-check"'), $out += " onclick=\"change_seach('','accurate',event)\">精确查询</li> <li ", "fuzzy" == seachType && ($out += 'class="tabs-check"'), $out += ' onclick="change_seach(\'\',\'fuzzy\',event)">模糊查询</li> </ul> </div> <div class="conditions_show"> <input type="hidden" id="sysname" value="qecx-list-wap"> <form id="tj_seach_form"> <input type="hidden" name="pageSize" id="page_size"> <input type="hidden" name="start" id="start"> <input type="hidden" name="orderBy" id="order_by"> ', $out += "fuzzy" == seachType ? ' <input type="text" name="mhcx" value="1" style="display: none;"> <select id="ss" name="ssdm2"> <option value="">（省市）不限</option> </select> <select id="xxfs" name="xxfs2"> <option value="">（学习方式）不限</option> <option value="1">全日制</option> <option value="2">非全日制</option> </select> <input type="text" placeholder="请输入招生单位或专业名称的关键词" name="dwmc2" id="dwxx" maxlength="100" autocomplete="off" /> <a class="tj-btn-middle conditions-btn tj-seach-btn" href="javascript:;" onclick="template_qecx_seach(\'qecx-list-wap\',this,event,\'0\')">查 询</a> ' : ' <select id="ss" name="ssdm"> <option value="">（省市）不限</option> </select> <input type="text" placeholder="请输入完整的单位名称" name="dwmc" id="dwxx" maxlength="100" autocomplete="off" /> <select id="xxfs" name="xxfs"> <option value="">（学习方式）不限</option> <option value="1">全日制</option> <option value="2">非全日制</option> </select> <input type="text" placeholder="请输入完整的专业名称" name="zymc" id="zyxx" maxlength="100" autocomplete="off" /> <div class="qers-content"> <input type="text" placeholder="请输入计划余额人数" name="qers" id="qexx" maxlength="4" onkeyup="this.value=this.value.replace(/[^\\d]/g,\'\')" autocomplete="off" /><span>人以上</span> </div> <a class="tj-btn-middle conditions-btn tj-seach-btn" href="javascript:;" onclick="template_qecx_seach(\'qecx-list-wap\',this,event,\'1\')">查 询</a> ', $out += " </form> </div> </div> ", "fuzzy" != seachType && ($out += ' <script> $(function () { fuzzy_query("dwxx-init","tj"); fuzzy_query("zyxx-init","tj"); }); <\/script> '), $out += " </div>", new String($out)
        }), template("wap/tjyx-qecx-list-item", function($data, $filename) {
            "use strict";
            var $utils = this,
                data = ($utils.$helpers, $data.data),
                $each = $utils.$each,
                $escape = ($data.value, $data.index, $utils.$escape),
                $out = "";
            return $out += " ", "" == data.vo_list.vos ? $out += ' <li class="tj-list-item tj-list-item-noresult">该条件下没有查询到余额信息</li> ' : ($out += " ", $each(data.vo_list.vos, function(value, index) { $out += ' <li class="tj-list-item"> <table id="qe_', $out += $escape(value.id), $out += '"> <tr> <td colspan="2" class="tj-list-item-dw"><a href="/sch/tjzc--method-listTjPub,yxdm-', $out += $escape(value.dwdm), $out += '.dhtml" target="_blank">(', $out += $escape(value.dwdm), $out += ") ", $out += $escape(value.dwmc), $out += '</a></td> <td rowspan="5" class="tj-list-item-sq"> \x3c!-- <span class="tj-tip tsyq-has-title" onclick="template_zytb_tj(\'', $out += $escape(value.id), $out += "')\">申请条件</span> --\x3e ", value.hasit ? $out += '  <button class="tj-sqtj-btn" style="background: #ccc;">已填写</button> ' : ($out += " ", "" == value.sfmzjybyq ? ($out += ' <button class="tj-sqtj-btn" onclick="template_zytb_add(\'', $out += $escape(value.id), $out += "')\">填写</button> ") : ($out += ' <a class="action-text" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">', $out += $escape(value.sfmzjybyq), $out += "</a> "), $out += " "), $out += ' \x3c!-- <span class="tj-qe"> 余额', 0 != value.qers ? ($out += $escape(value.qers), $out += "人") : $out += "有", $out += '</span> --\x3e </td> </tr> <tr> <td class="tj-list-item-title">院系所：</td> <td class="tj-list-item-content yxxx">(', $out += $escape(value.yxsdm), $out += ")", $out += $escape(value.yxsmc), $out += '</td> </tr> <tr> <td class="tj-list-item-title">专业：</td> <td class="tj-list-item-content zyxx">(', $out += $escape(value.zydm), $out += ")", $out += $escape(value.zymc), $out += '</td> </tr> <tr> <td class="tj-list-item-title">研究方向：</td> <td class="tj-list-item-content zyxx">(', $out += $escape(value.yjfxdm), $out += ") ", $out += $escape(value.yjfxmc), $out += '</td> </tr> <tr> <td class="tj-list-item-title">学习方式：</td> <td class="tj-list-item-content xxfs">', "1" == value.xxfs ? $out += "全日制" : "2" == value.xxfs && ($out += "非全日制"), $out += '</td> </tr> <tr> <td colspan="3"> <div class="tj-list-item-tjsm"><span style="color: #f57716">特殊说明：</span>', "" == value.bz ? $out += "招生单位对该专业暂无特殊要求" : $out += $escape(value.bz), $out += '</div> <div class="tj-list-item-time">', $out += $escape(value.fbsjStr), $out += "</div> </td> </tr> </table> </li> " }), $out += " "), $out += " ", new String($out)
        }),
        template("wap/tjyx-qecx-list", function($data, $filename) {
            "use strict";
            var $utils = this,
                data = ($utils.$helpers, $data.data),
                include = function(filename, data) { data = data || $data; var text = $utils.$include(filename, data, $filename); return $out += text },
                $out = "";
            return $out += '<div id="content-qecxList"> ', "" != data.vo_list.vos && ($out += '  <div class="tj-seach-sort clearfix"> <input type="hidden" id="sysname" value="tjyx-qecx-list"> <ul> <li class="sort-icon" data-name="dwmc" data-value="">单位<span class="sort-icon-i"></span></li> <li class="sort-icon" data-name="yxsmc" data-value="">院系<span class="sort-icon-i"></span></li> <li class="sort-icon" data-name="zymc" data-value="">专业<span class="sort-icon-i"></span></li> <li class="sort-icon sort-icon-more" data-name="xxfs" data-value="">学习方式<span class="sort-icon-i"></span></li> <li class="sort-icon sort-icon-more" data-name="fbsjStr" data-value="">发布时间<span class="sort-icon-i"></span></li> </ul> </div> '), $out += ' <div class="tj-list" id="wrapper"> <ul> ', include("../wap/tjyx-qecx-list-item"), $out += " </ul> </div> ", include("../common/tjyx-tips"), $out += "  </div> ", new String($out)
        }), template("wap/tjyx-qecx-seach", function($data, $filename) {
            "use strict";
            var $utils = this,
                seachType = ($utils.$helpers, $data.seachType),
                $out = "";
            return $out += ' <div class="tj-seach-conditions mar-t10" id="tj_seach_change"> <div class="conditions-warp clearfix"> <div class="conditions-tabs"> <ul> <li ', "accurate" == seachType && ($out += 'class="tabs-check"'), $out += " onclick=\"change_seach('tjyx','accurate',event)\">精确查询</li> <li ", "fuzzy" == seachType && ($out += 'class="tabs-check"'), $out += ' onclick="change_seach(\'tjyx\',\'fuzzy\',event)">模糊查询</li> </ul> </div> <div class="conditions_show"> <input type="hidden" id="sysname" value="tjyx-qecx-list-wap"> <form id="tj_seach_form"> <input type="hidden" name="pageSize" id="page_size"> <input type="hidden" name="start" id="start"> <input type="hidden" name="orderBy" id="order_by"> ', $out += "fuzzy" == seachType ? ' <input type="text" name="mhcx" value="1" style="display: none;"> <select id="ss" name="ssdm2"> <option value="">（省市）不限</option> </select> <select id="xxfs" name="xxfs2"> <option value="">（学习方式）不限</option> <option value="1">全日制</option> <option value="2">非全日制</option> </select> <input type="text" placeholder="请输入招生单位或专业名称的关键词" name="dwmc2" id="dwxx" maxlength="100" autocomplete="off" /> <a class="tj-btn-middle conditions-btn tj-seach-btn" href="javascript:;" onclick="template_qecx_seach(\'tjyx-qecx-list-wap\',this,event,\'0\')">查 询</a> ' : ' <select id="ss" name="ssdm"> <option value="">（省市）不限</option> </select> <input type="text" placeholder="请输入完整的单位名称" name="dwmc" id="dwxx" maxlength="100" autocomplete="off" /> <select id="xxfs" name="xxfs"> <option value="">（学习方式）不限</option> <option value="1">全日制</option> <option value="2">非全日制</option> </select> <input type="text" placeholder="请输入完整的专业名称" name="zymc" id="zyxx" maxlength="100"/> <a class="tj-btn-middle conditions-btn tj-seach-btn" href="javascript:;" onclick="template_qecx_seach(\'tjyx-qecx-list-wap\',this,event,\'1\')">查 询</a> ', $out += " </form> </div> </div> ", "fuzzy" != seachType && ($out += ' <script> $(function () { fuzzy_query("tjyx-dwxx-init","tjyx"); fuzzy_query("tjyx-zyxx-init","tjyx"); }); <\/script> '), $out += " </div>", new String($out)
        }), template("web/page", function($data, $filename) {
            "use strict";
            var $utils = this,
                $helpers = $utils.$helpers,
                data = $data.data,
                $string = $utils.$string,
                $out = "";
            return $out += " ", data.vo_list.pagenation && ($out += " ", data.vo_list.pagenation.totalCount > 0 && ($out += ' <div class="tj-paging mar-t30 clearfix"> <ul> ', $out += $string($helpers.pagerander(data.vo_list.pagenation)), $out += " </ul> </div> "), $out += " "), $out += " ", new String($out)
        }), template("web/qecx-list", function($data, $filename) {
            "use strict";
            var $utils = this,
                $helpers = $utils.$helpers,
                flag = $data.flag,
                data = $data.data,
                $each = $utils.$each,
                $escape = ($data.value, $data.index, $utils.$escape),
                $out = "";
            return $out += ' <div class="mar-t10" id="content-qecxList"> <div class="info-prompt mar-t10"> <ul> <li>提醒：招生单位发布的调剂计划余额每次更新后将至少持续12小时，请各位考生结合各招生单位设定的志愿锁定时间综合考虑，合理申请，错峰报名。</li> </ul> </div> <input type="hidden" id="sysname" value="qecx-list"> <table class="tj-table"> <thead> <tr> <th width="190"><span class="sort-icon" data-name="dwmc" data-value="">招生单位</span></th> <th width="175"><span class="sort-icon" data-name="yxsmc" data-value="">院系所</span></th> <th width="175"><span class="sort-icon" data-name="zymc" data-value="">专业</span></th> <th><span class="sort-icon" data-name="yjfxmc" data-value="">研究方向</span></th> <th width="85"><span class="sort-icon" data-name="xxfs" data-value="">学习方式</span></th> <th width="120"><span class="sort-icon" data-name="qers" data-value="">计划余额</span></th> <th width="120">最后更新</span></th> <th width="70">申请条件</th> <th width="120">操作</th> </tr> </thead> <tbody> ', flag && ($out += " ", data.vo_list.pagenation ? ($out += " ", data.vo_list.pagenation.totalCount > 0 ? ($out += " ", $each(data.vo_list.vos, function(value, index) { $out += ' <tr> <td><a href="/sch/tjzc--method-listTjPub,yxdm-', $out += $escape(value.dwdm), $out += '.dhtml" target="_blank">(', $out += $escape(value.dwdm), $out += ") ", $out += $escape(value.dwmc), $out += "</a></td> <td>(", $out += $escape(value.yxsdm), $out += ") ", $out += $escape(value.yxsmc), $out += "</td> <td>(", $out += $escape(value.zydm), $out += ") ", $out += $escape(value.zymc), $out += "</td> <td>(", $out += $escape(value.yjfxdm), $out += ") ", $out += $escape(value.yjfxmc), $out += "</td> <td>", "1" == value.xxfs ? $out += "全日制" : "2" == value.xxfs && ($out += "非全日制"), $out += '</td> <td class="text-r">', 0 != value.qers ? $out += $escape(value.qers) : $out += "有", $out += "</td> <td>", $out += $escape($helpers.gxsjHelper(value.gxsj)), $out += '</td> <td class="text-c"> ', "" == value.sfmzjybyq && ($out += ' <span class="tj-tip color-blue" onclick="template_zytb_tj(\'', $out += $escape(value.id), $out += "')\">查看</span> "), $out += ' </td> <td class="text-c"> ', value.hasit ? $out += " 已申请 " : ($out += " ", "" == value.sfmzjybyq ? ($out += " ", "" == value.sfmzyq ? ($out += ' <a href="###" class="tj-btn-small" onclick="template_zytb_add(\'', $out += $escape(value.id), $out += "')\">申请</a> ") : ($out += ' <a href="###" class="tj-btn-small-grey" title="', $out += $escape(value.sfmzyq), $out += '" onclick="layer.alert(\'', $out += $escape(value.sfmzyq), $out += "',{title: '提示'})\">申请</a> "), $out += " ") : ($out += ' <a data-tip="具体请参见教育部调剂政策" class="tj-tip action-text tjyx-tip" target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">', $out += $escape(value.sfmzjybyq), $out += "</a> "), $out += " "), $out += " </td> </tr> " }), $out += " ") : $out += ' <tr> <td colspan="9">该条件下没有查询到计划余额信息</td> </tr> ', $out += " ") : $out += ' <tr> <td colspan="9">该条件下没有查询到计划余额信息</td> </tr> ', $out += " "), $out += " </tbody> </table> ",
                function(filename, data) {
                    data = data || $data;
                    var text = $utils.$include(filename, data, $filename);
                    $out += text
                }("../web/page"), $out += " </div> ", new String($out)
        }), template("web/qecx-seach", function($data, $filename) {
            "use strict";
            var $utils = this,
                seachType = ($utils.$helpers, $data.seachType),
                $out = "";
            return $out += ' <div class="qecx-tip mar-t20" id="tj_seach_change"> <h2>查询计划余额信息</h2> <div class="tj-seach"> <div class="tj-seach-tabs"> <ul> <li ', "accurate" == seachType && ($out += 'class="tabs-check"'), $out += " onclick=\"change_seach('','accurate',event)\">精确查询</li> <li ", "fuzzy" == seachType && ($out += 'class="tabs-check"'), $out += ' onclick="change_seach(\'\',\'fuzzy\',event)">模糊查询</li> </ul> </div> <form id="tj_seach_form"> <input type="hidden" name="pageSize" id="page_size"> <input type="hidden" name="start" id="start"> <input type="hidden" name="orderBy" id="order_by"> ', $out += "fuzzy" == seachType ? ' <input type="text" name="mhcx" value="1" style="display: none;"> <table> <tr> <td width="75" class="text-r">所在省市：</td> <td> <select name="ssdm2" id="ss" class="tj-select width112"> <option value="">请选择省市</option> </select> </td> <td width="75" class="text-r">学习方式：</td> <td> <select name="xxfs2" id="xxfs" class="tj-select width94"> <option value="">不限</option> <option value="1">全日制</option> <option value="2">非全日制</option> </select> </td> <td> <input type="text" name="dwmc2" id="dwxx" value="" class="tj-input width240" maxlength="100" placeholder="请输入招生单位或专业名称的关键词" autocomplete="off" /> </td> <td> <a href="javascript:;" class="tj-btn-middle tj-seach-btn" onclick="template_qecx_seach(\'qecx-list\',this,event,\'0\')">查 询</a> </td> <td width="420"></td> </tr> </table> ' : ' <table> <tr> <td width="75" class="text-r">所在省市：</td> <td> <select name="ssdm" id="ss" class="tj-select width112"> <option value="">请选择省市</option> </select> </td> <td width="75" class="text-r">招生单位：</td> <td> <input type="text" name="dwmc" id="dwxx" value="" class="tj-input width145" maxlength="100" placeholder="请输入完整的单位名称"/> </td> <td width="75" class="text-r">学习方式：</td> <td> <select name="xxfs" id="xxfs" class="tj-select width94"> <option value="">不限</option> <option value="1">全日制</option> <option value="2">非全日制</option> </select> </td> <td width="45" class="text-r">专业：</td> <td> <input type="text" name="zymc" id="zyxx" value="" class="tj-input width145" autocomplete="off" maxlength="100" placeholder="请输入完整的专业名称"/> </td> <td class="text-r" width="75"> 余额人数：</td> <td> <input type="text" name="qers" id="qexx" value="" class="tj-input width40" autocomplete="off" maxlength="4" onkeyup="this.value=this.value.replace(/[^\\d]/g,\'\')"/> <span>人以上</span> </td> <td> <a href="javascript:;" class="tj-btn-middle tj-seach-btn" onclick="template_qecx_seach(\'qecx-list\',this,event,\'1\')">查 询</a> </td> </tr> </table> ', $out += " </form> </div> </div> ", $out += "fuzzy" != seachType ? ' <script> $(function () { fuzzy_query("dwxx-init","tj"); fuzzy_query("zyxx-init","tj"); JPlaceHolder.init(); }); <\/script> ' : " <script> $(function () { JPlaceHolder.init(); }); <\/script> ", new String($out)
        }), template("web/tjyx-qecx-list", function($data, $filename) {
            "use strict";
            var $utils = this,
                flag = ($utils.$helpers, $data.flag),
                data = $data.data,
                $each = $utils.$each,
                $escape = ($data.value, $data.index, $utils.$escape),
                $out = "";
            return $out += " ", flag && ($out += ' <div class="mar-t10" id="content-qecxList"> <input type="hidden" id="sysname" value="tjyx-qecx-list"> <table class="tj-table"> <thead> <tr> <th width="200"><span class="sort-icon" data-name="dwmc" data-value="">招生单位</span></th> <th width="200"><span class="sort-icon" data-name="yxsmc" data-value="">院系所</span></th> <th width="200"><span class="sort-icon" data-name="zymc" data-value="">专业</span></th> <th><span class="sort-icon" data-name="yjfxmc" data-value="">研究方向</span></th> <th width="90"><span class="sort-icon" data-name="xxfs" data-value="">学习方式</span></th> <th width="145"><span class="sort-icon" data-name="fbsjStr" data-value="">发布时间</span></th> <th width="75">特殊说明</th> <th width="120">操作</th> </tr> </thead> <tbody> ', flag && ($out += " ", data.vo_list.pagenation ? ($out += " ", data.vo_list.pagenation.totalCount > 0 ? ($out += " ", $each(data.vo_list.vos, function(value, index) { $out += ' <tr> <td><a href="/sch/tjzc--method-listTjPub,yxdm-', $out += $escape(value.dwdm), $out += '.dhtml" target="_blank">(', $out += $escape(value.dwdm), $out += ") ", $out += $escape(value.dwmc), $out += "</a></td> <td>(", $out += $escape(value.yxsdm), $out += ") ", $out += $escape(value.yxsmc), $out += "</td> <td>(", $out += $escape(value.zydm), $out += ") ", $out += $escape(value.zymc), $out += "</td> <td>(", $out += $escape(value.yjfxdm), $out += ") ", $out += $escape(value.yjfxmc), $out += "</td> <td>", "1" == value.xxfs ? $out += "全日制" : "2" == value.xxfs && ($out += "非全日制"), $out += '</td> <td class="text-c">', $out += $escape(value.fbsjStr), $out += '</td> <td class="text-c"> ', "" != value.bz ? ($out += ' <span data-tip="', $out += $escape(value.bz), $out += '" class="tj-tip color-blue tjyx-tip">查看</span> ') : $out += ' <span data-tip="招生单位对该专业暂无特殊要求" class="tj-tip color-blue tjyx-tip">查看</span> ', $out += ' </td> <td class="text-c"> ', value.hasit ? $out += " 已填写 " : ($out += " ", "" == value.sfmzjybyq ? ($out += ' <a href="javascript:;" class="tj-btn-small" onclick="template_zytb_add(\'', $out += $escape(value.id), $out += "')\">填写</a> ") : ($out += ' <a data-tip="具体请参见教育部调剂政策" class="tj-tip tjyx-tip action-text" target="_blank" href="https://yz.chsi.com.cn/kyzx/tjzd/202203/20220311/2172264654.html">', $out += $escape(value.sfmzjybyq), $out += "</a> "), $out += " "), $out += " </td> </tr> " }), $out += " ") : $out += ' <tr> <td colspan="8">该条件下没有查询到调剂意向余额信息</td> </tr> ', $out += " ") : $out += ' <tr> <td colspan="8">该条件下没有查询到调剂意向余额信息</td> </tr> ', $out += " "), $out += " </tbody> </table> ", function(filename, data) {
                data = data || $data;
                var text = $utils.$include(filename, data, $filename);
                $out += text
            }("../web/page"), $out += " </div> "), $out += " ", new String($out)
        }), template("web/tjyx-qecx-seach", function($data, $filename) {
            "use strict";
            var $utils = this,
                seachType = ($utils.$helpers, $data.seachType),
                $out = "";
            return $out += ' <div class="qecx-tip mar-t20" id="tj_seach_change"> <h2>查询调剂意向余额信息</h2> <div class="tj-seach"> <div class="tj-seach-tabs"> <ul> <li ', "accurate" == seachType && ($out += 'class="tabs-check"'), $out += " onclick=\"change_seach('tjyx','accurate',event)\">精确查询</li> <li ", "fuzzy" == seachType && ($out += 'class="tabs-check"'), $out += ' onclick="change_seach(\'tjyx\',\'fuzzy\',event)">模糊查询</li> </ul> </div> <form id="tj_seach_form"> <input type="hidden" name="pageSize" id="page_size"> <input type="hidden" name="start" id="start"> <input type="hidden" name="orderBy" id="order_by"> ', $out += "fuzzy" == seachType ? ' <input type="text" name="mhcx" value="1" style="display: none;"> <table> <tr> <td width="80" class="text-r">所在省市：</td> <td> <select name="ssdm2" id="ss" class="tj-select width112"> <option value="">请选择省市</option> </select> </td> <td width="80" class="text-r">学习方式：</td> <td> <select name="xxfs2" id="xxfs" class="tj-select width94"> <option value="">不限</option> <option value="1">全日制</option> <option value="2">非全日制</option> </select> </td> <td> <input type="text" name="dwmc2" id="dwxx" value="" class="tj-input width240" maxlength="100" placeholder="请输入招生单位或专业名称的关键词" autocomplete="off" /> </td> <td> <a href="javascript:;" class="tj-btn-middle tj-seach-btn" onclick="template_qecx_seach(\'tjyx-qecx-list\',this,event,\'0\')">查 询</a> </td> <td width="420"></td> </tr> </table> ' : ' <table> <tr> <td width="80" class="text-r">所在省市：</td> <td> <select name="ssdm" id="ss" class="tj-select width112"> <option value="">请选择省市</option> </select> </td> <td width="80" class="text-r">招生单位：</td> <td> <input type="text" name="dwmc" id="dwxx" value="" class="tj-input width145" maxlength="100" placeholder="请输入完整的单位名称"/> </td> <td width="80" class="text-r">学习方式：</td> <td> <select name="xxfs" id="xxfs" class="tj-select width94"> <option value="">不限</option> <option value="1">全日制</option> <option value="2">非全日制</option> </select> </td> <td width="50" class="text-r">专业：</td> <td> <input type="text" name="zymc" id="zyxx" value="" class="tj-input width145" maxlength="100" placeholder="请输入完整的专业名称"/> </td> <td width="100" class="text-c"> <a href="javascript:;" class="tj-btn-middle tj-seach-btn" onclick="template_qecx_seach(\'tjyx-qecx-list\',this,event,\'1\')">查 询</a> </td> <td colspan="3" width="280">  </td> </tr> </table> ', $out += " </form> </div> </div> ", $out += "fuzzy" != seachType ? ' <script> $(function () { fuzzy_query("tjyx-dwxx-init","tjyx"); fuzzy_query("tjyx-zyxx-init","tjyx"); JPlaceHolder.init(); }); <\/script> ' : " <script> $(function () { JPlaceHolder.init(); }); <\/script> ", new String($out)
        })
}();
var Mainfest = { "dwxx-init": { template: "", interface_url: "https://t1.chei.com.cn/sytj/stu/getcachezsdws/", description: "单位搜索" }, "zyxx-init": { template: "", interface_url: "https://t1.chei.com.cn/sytj/stu/getcachezys/", description: "专业搜索" }, "tjyx-dwxx-init": { template: "", interface_url: "/sytj/stu/getcachezsdwstjyx/", description: "单位搜索-调剂意向" }, "tjyx-zyxx-init": { template: "", interface_url: "/sytj/stu/getcachezystjyx/", description: "专业搜索-调剂意向" }, "notice-init": { template: "common/notice", interface_url: "/sytj/stu/getnotice.action", description: "获取提醒信息" }, "tjyx-index": { template: "common/tjyx-index", interface_url: "", description: "调剂意向-首页初始化" }, "tjyx-qecx": { template: "common/tjyx-qecx", interface_url: "", description: "调剂意向-缺额查询初始化" }, "tjyx-qecx-seach": { template: "web/tjyx-qecx-seach", interface_url: "/sytj/stu/getzsdws.action", description: "调剂意向-缺额查询-获取招生单位" }, "tjyx-qecx-seach-wap": { template: "wap/tjyx-qecx-seach", interface_url: "/sytj/stu/getzsdws.action", description: "调剂意向-缺额查询-获取招生单位-移动端" }, "tjyx-qecx-list": { template: "web/tjyx-qecx-list", interface_url: "/sytj/stu/tjyxqexxcx.action", description: "调剂意向-缺额查询-列表" }, "tjyx-qecx-list-wap": { template: "wap/tjyx-qecx-list", interface_url: "/sytj/stu/tjyxqexxcx.action", description: "调剂-缺额查询-列表-移动端" }, "tjyx-mytjyx": { template: "common/tjyx-tjzy", interface_url: "/sytj/stu/gettjyx.action", description: "调剂意向-我的意向" }, "tjyx-mytjyx-post": { template: "common/tjyx-tjzy", interface_url: "/sytj/stu/tjyxtotjapply.action", description: "调剂意向-我的意向-转为调剂志愿" }, index: { template: "common/index", interface_url: "", description: "调剂-首页初始化" }, cjxx: { template: "common/cjxx", interface_url: "/sytj/stu/sytjcjdata.action", description: "调剂-成绩信息" }, "tjyx-cjxx": { template: "common/cjxx", interface_url: "/sytj/stu/tjyxcjdata.action", description: "调剂-成绩信息" }, mytjzy: { template: "common/tjzy", interface_url: "/sytj/stu/gettjapply.action", description: "调剂-我的志愿" }, qecx: { template: "common/qecx", interface_url: "", description: "调剂-缺额查询" }, "qecx-seach": { template: "web/qecx-seach", interface_url: "/sytj/stu/getzsdws.action", description: "调剂/意向-缺额查询-获取招生单位" }, "qecx-seach-wap": { template: "wap/qecx-seach", interface_url: "/sytj/stu/getzsdws.action", description: "调剂/意向-缺额查询-获取招生单位-移动端" }, "qecx-list": { template: "web/qecx-list", interface_url: "/sytj/stu/sytjqexxcx.action", description: "调剂-缺额查询-列表" }, "qecx-list-wap": { template: "wap/qecx-list", interface_url: "/sytj/stu/sytjqexxcx.action", description: "调剂-缺额查询-列表-移动端" }, tbtjzy: { template: "common/tbtjzy", interface_url: "/sytj/stu/gettjqexx.action", description: "调剂-缺额查询-志愿填报" }, "tbtjzy-edit": { template: "common/tbtjzy-edit", interface_url: "/sytj/stu/gettjapplyone.action", description: "调剂-缺额查询-志愿修改" }, "tbtjzy-edit-post": { template: "common/tbtjzy-edit", interface_url: "/sytj/stu/modtjapply.action", description: "调剂-缺额查询-志愿修改-提交" }, "tbtjzy-ssdm": { template: "common/tbtjzy-item", interface_url: "", description: "调剂-缺额查询-志愿填报-省市级联" }, "tbtjzy-dwxx": { template: "common/tbtjzy-item", interface_url: "/sytj/stu/getqezsdws.action", description: "调剂-缺额查询-志愿填报-单位级联" }, "tbtjzy-yxxx": { template: "common/tbtjzy-item", interface_url: "/sytj/stu/getqeyxss.action", description: "调剂-缺额查询-志愿填报-院系所级联" }, "tbtjzy-zyxx": { template: "common/tbtjzy-item", interface_url: "/sytj/stu/getqezys.action", description: "调剂-缺额查询-志愿填报-专业级联" }, "tbtjzy-fxxxx": { template: "common/tbtjzy-item", interface_url: "/sytj/stu/getqeyjfxs.action", description: "调剂-缺额查询-志愿填报-研究方向级联" }, "tbtjzy-xffs": { template: "common/tbtjzy-item", interface_url: "/sytj/stu/getqexxfss.action", description: "调剂-缺额查询-志愿填报-学习方式" }, "tjyx-tbtjzy": { template: "common/tjyx-tbtjzy", interface_url: "/sytj/stu/gettjyxqexx.action", description: "调剂意向-缺额查询-志愿填报" }, "tbtjzy-post": { template: "common/tbtjzy", interface_url: "/sytj/stu/addtjapply.action", description: "调剂-缺额查询-提交志愿填报" }, "tjyx-tbtjzy-post": { template: "common/tjyx-tbtjzy", interface_url: "/sytj/stu/addtjyx.action", description: "调剂-缺额查询-提交志愿填报" }, fstz: { template: "common/fstz", interface_url: "/sytj/stu/gettjfstz.action", description: "调剂-我的复试通知" }, "fstz-post-accept": { template: "common/fstz", interface_url: "/sytj/stu/agreefstz.action", description: "调剂-我的复试通知-提交-接受" }, "fstz-post-reject": { template: "common/fstz", interface_url: "/sytj/stu/disagreefstz.action", description: "调剂-我的复试通知-提交-拒绝" }, dlqtz: { template: "common/dlqtz", interface_url: "/sytj/stu/gettjdlqtz.action", description: "调剂-我的待录取通知" }, "dlqtz-post-accept": { template: "common/dlqtz", interface_url: "/sytj/stu/agreedlqtz.action", description: "调剂-我的待录取通知-提交" }, "dlqtz-post-reject": { template: "common/dlqtz", interface_url: "/sytj/stu/disagreedlqtz.action", description: "调剂-我的待录取通知-提交" }, "dlqtz-post-acceptcancel": { template: "common/dlqtz", interface_url: "/sytj/stu/agreecanceldlqtz.action", description: "调剂-我的待录取通知-提交" }, "dlqtz-post-rejectcancel": { template: "common/dlqtz", interface_url: "/sytj/stu/disagreecanceldlqtz.action", description: "调剂-我的待录取通知-提交" }, success: { template: "common/success", interface_url: "", description: "调剂-志愿填报-成功" }, "tjyx-success": { template: "common/tjyx-success", interface_url: "", description: "调剂-志愿填报-成功" }, unuse: { template: "common/unuse", interface_url: "/sytj/tj/sytjshouyedata.action", description: "调剂-志愿填报-权限" }, "tjyx-unuse": { template: "common/tjyx-unuse", interface_url: "/sytj/tjyx/sytjshouyedata.action", description: "调剂意向-志愿填报-权限" }, "bdzkz-post": { template: "", interface_url: "/sytj/stu/bduserandksbh.action", description: "绑定准考证号" }, "tj-tjjcap": { template: "common/tjjcap", interface_url: "/sytj/stu/getgg.action", description: "调剂-调剂进程安排" }, "tj-sqtj": { template: "common/sqtj", interface_url: "/sytj/stu/gettjbktj.action", description: "调剂-申请条件" }, yzytz: { template: "common/yzytz", interface_url: "/sytj/stu/getyzytz.action", description: "调剂-一志愿通知" }, "yzy-dlqtz-accept": { template: "common/yzytz", interface_url: "/sytj/stu/agreeyzydlqtz.action", description: "调剂-一志愿待录取通知-提交-接受" }, "yzy-dlqtz-reject": { template: "common/yzytz", interface_url: "/sytj/stu/disagreeyzydlqtz.action", description: "调剂-一志愿待录取通知-提交-拒绝" }, "yzy-dlqtz-cancel-accept": { template: "common/yzytz", interface_url: "/sytj/stu/agreecancelyzydlqtz.action", description: "调剂-一志愿待录取通知-取消-接受" } };
jQuery.cookie = function(name, value, options) {
    if (void 0 === value) {
        var cookieValue = null;
        if (document.cookie && "" != document.cookie)
            for (var cookies = document.cookie.split(";"), i = 0; i < cookies.length; i++) { var cookie = jQuery.trim(cookies[i]); if (cookie.substring(0, name.length + 1) == name + "=") { cookieValue = decodeURIComponent(cookie.substring(name.length + 1)); break } }
        return cookieValue
    }
    options = options || {}, null === value && (value = "", options = $.extend({}, options), options.expires = -1);
    var expires = "";
    if (options.expires && ("number" == typeof options.expires || options.expires.toUTCString)) { var date; "number" == typeof options.expires ? (date = new Date, date.setTime(date.getTime() + 24 * options.expires * 60 * 60 * 1e3)) : date = options.expires, expires = "; expires=" + date.toUTCString() }
    var path = options.path ? "; path=" + options.path : "",
        domain = options.domain ? "; domain=" + options.domain : "",
        secure = options.secure ? "; secure" : "";
    document.cookie = [name, "=", encodeURIComponent(value), expires, path, domain, secure].join("")
}, AjaxPolling.prototype = {
    callServer: function() {
        var p = this;
        $.ajax({
            type: "get",
            url: this.serverUrl,
            cache: !1,
            dataType: "json",
            data: { taskId: this.taskId, key: this.key, tt: (new Date).getTime() },
            error: function(xhr, textstatus, errothown) { if (parseInt(xhr.status, 10) >= 500) { if (p.retryCount++, p.onError(xhr, p.retryCount), p.retryCount <= p.retryLimit) { var _this = this; return void(p.setTimeout = window.setTimeout(function() { $.ajax(_this) }, p.retryAfter + p.retryGaps * p.retryCount)) } } else; },
            success: function(data) {
                if (++p.pollingCount < p.pollingLimit) { if (clearTimeout(p.setTimeout), p.everyPolling(data), "success" == data.state && (p.pollingCount = 0, p.onSuccess(data)), "wait" == data.state || "update" == data.state) { var _this = this; return void(p.setTimeout = window.setTimeout(function() { $.ajax(_this) }, p.pollingDelay + p.delayGaps * p.pollingCount)) } return "cancel" == data.state ? p.onCancel && p.onCancel(data) : "exception" == data.state ? p.onException && p.onException(data) : void console.log("error:服务没有正确返回结果") }
                p.onTimeOut()
            }
        })
    },
    start: function() { this.useProgressBar && this.progressBar(this.taskId), this.callServer() },
    progressBar: function(taskId) { $("#" + taskId).addClass("progress").html('<div class="progress active"><div class="progress-bar ch-progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div></div>') },
    changeProgressBar: function(data) {
        var progressWidth = function(n) { return (Math.round(1e4 * n) / 100).toFixed(0) + "%" }(data.percent / 100);
        $("#" + data.taskId).find("div.progress-bar").width(progressWidth).html(progressWidth)
    }
};
var host = "",
    mark = {},
    page_size = 20,
    pagenation = {},
    zy_status = {},
    AreaList = [{ code: "1", name: "一区", sub: [{ code: "11", name: "北京" }, { code: "12", name: "天津" }, { code: "13", name: "河北" }, { code: "14", name: "山西" }, { code: "21", name: "辽宁" }, { code: "22", name: "吉林" }, { code: "23", name: "黑龙江" }, { code: "31", name: "上海" }, { code: "32", name: "江苏" }, { code: "33", name: "浙江" }, { code: "34", name: "安徽" }, { code: "35", name: "福建" }, { code: "36", name: "江西" }, { code: "37", name: "山东" }, { code: "41", name: "河南" }, { code: "42", name: "湖北" }, { code: "43", name: "湖南" }, { code: "44", name: "广东" }, { code: "50", name: "重庆" }, { code: "51", name: "四川" }, { code: "61", name: "陕西" }] }, { code: "2", name: "二区", sub: [{ code: "15", name: "内蒙古" }, { code: "45", name: "广西" }, { code: "46", name: "海南" }, { code: "52", name: "贵州" }, { code: "53", name: "云南" }, { code: "54", name: "西藏" }, { code: "62", name: "甘肃" }, { code: "63", name: "青海" }, { code: "64", name: "宁夏" }, { code: "65", name: "新疆" }] }],
    bz_Array = [],
    fs_tz = !1,
    dlq_tz = !1,
    isUse = !0,
    sysIsOpen = !1,
    _sxbz, sxbz2 = "",
    sxbz3 = "",
    ksbh = "",
    sysIsOpen = !1,
    navfest = { index: { url: "/index.html", navhighlight: "index", model: "首页" }, yzytz: { url: "/yzytz.html", navhighlight: "yzytz", model: "一志愿通知" }, cjxx: { url: "/cjxx.html", navhighlight: "cjxx", model: "成绩信息" }, qecx: { url: "/qecx.html", navhighlight: "qecx", model: " 余额查询" }, tbtjzy: { url: "/tbtjzy.html", navhighlight: "tjzy", model: "调剂志愿" }, tjzy: { url: "/tjzy.html", navhighlight: "tjzy", model: "调剂志愿" }, fstz: { url: "/fstz.html", navhighlight: "fstz", model: "复试通知" }, dlqtz: { url: "/dlqtz.html", navhighlight: "dlqtz", model: "待录取通知" } };
Array.prototype.max = function() { return Math.max.apply({}, this) }, jQuery.support.cors = !0;
var MyValidator = function() { var handleSubmit = function() { $(".fromSubmit").validate({ rules: { ssdm: { required: !0 }, dwxx: { required: !0 }, yxxx: { required: !0 }, zyxx: { required: !0 }, fxxxx: { required: !0 }, xxfs: { required: !0 }, yddh: { required: !0, maxlength: 50 }, bz: { maxlength: 50 }, ksbh: { required: !0, maxlength: 15 } }, messages: { ssdm: { required: '<i class="iconfont">&#xe67c;</i>请选择省市' }, dwxx: { required: '<i class="iconfont">&#xe67c;</i>请选择招生单位' }, yxxx: { required: '<i class="iconfont">&#xe67c;</i>请选择院系所' }, zyxx: { required: '<i class="iconfont">&#xe67c;</i>请选择专业' }, fxxxx: { required: '<i class="iconfont">&#xe67c;</i>请选择研究方向' }, xxfs: { required: '<i class="iconfont">&#xe67c;</i>请选择学习方式' }, yddh: { required: '<i class="iconfont">&#xe67c;</i>请填写手机联系方式', maxlength: '<i class="iconfont">&#xe67c;</i>手机号长度不能超过50字符！' }, bz: { maxlength: '<i class="iconfont">&#xe67c;</i>备注长度不能超过50字符！' }, ksbh: { required: '<i class="iconfont">&#xe67c;</i>请填写准考证号', maxlength: '<i class="iconfont">&#xe67c;</i>准考证号长度不能超过15字符！' } }, highlight: function(element) {}, success: function(label) {}, errorPlacement: function(error, element) { element.siblings(".tj-error").length > 0 ? element.siblings(".tj-error").html(error) : $(".tj-error").html(error) }, submitHandler: function(form) { form.submit() } }), $(".fromSubmit input").keypress(function(e) { if (13 == e.which) return $(".fromSubmit").validate().form() && $(".fromSubmit").submit(), !1 }) }; return { init: function() { handleSubmit() } } }();
$(function() { order_qelist() });
var JPlaceHolder = {
    _check: function() { return "placeholder" in document.createElement("input") },
    init: function() { this._check() || this.fix() },
    fix: function() {
        jQuery(":input[placeholder]").each(function(index, element) {
            var self = $(this),
                txt = self.attr("placeholder");
            self.wrap($("<span></span>").css({ position: "relative", display: "inline-block", zoom: "1", border: "none", background: "none", padding: "none", margin: "none" }));
            var pos = self.position(),
                h = self.outerHeight(!0) + "px",
                paddingleft = self.css("padding-left"),
                holder = $("<span></span>").text(txt).css({ position: "absolute", left: pos.left, top: pos.top, height: h, lineHeight: h, paddingLeft: paddingleft, color: "#aaa", fontSize: "14px" }).appendTo(self.parent());
            self.focusin(function(e) { holder.hide() }).focusout(function(e) { self.val() || holder.show() }), holder.click(function(e) { holder.hide(), self.focus() })
        })
    }
};
$(function() {
    $("body").on("click", ".wap-menu", function() { $(".wap-menu-list").show(), $(".menu-list-warp-bg").show(), $("body").addClass("van-overflow-hidden") }), $("body").on("click", ".iconfont-close", function() { $(".wap-menu-list").hide(), $(".menu-list-warp-bg").hide(), $("body").removeClass("van-overflow-hidden") }), $("body").on("click", ".wap-expend", function() { $("#wap-expend-warp").hasClass("expend") ? ($("#wap-expend-warp").removeClass("expend"), $(this).removeClass("expend")) : ($("#wap-expend-warp").addClass("expend"), $(this).addClass("expend")) }), $("body").on("mouseover", ".tjyx-tip", function() {
        var _tipMessage = html2Escape($(this).attr("data-tip"));
        if ("" != _tipMessage) {
            var index = layer.tips(_tipMessage, this, { tips: [1, "#e5db97"], time: 1e5 });
            $(this).on("mouseout", function() { layer.close(index) })
        }
    }), $("body").on("mouseover", ".ewm-fix", function() { $(".ewm-detail").removeClass("tj-hide"), $(this).on("mouseout", function() { $(".ewm-detail").addClass("tj-hide") }) }), $("body").on("touchend", ".ewm-wap", function() { $(".wap-overlay").removeClass("tj-hide"), $(".ewm-detail-wap").removeClass("tj-hide") }), $("body").on("touchend", ".wap-overlay", function() { setTimeout(function() { $(".wap-overlay").addClass("tj-hide"), $(".ewm-detail-wap").addClass("tj-hide") }, 100) }), $("body").on("touchend", ".ewm-detail-wap .close", function() { setTimeout(function() { $(".wap-overlay").addClass("tj-hide"), $(".ewm-detail-wap").addClass("tj-hide") }, 100) })
});