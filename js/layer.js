! function(window, undefined) {
    'use strict';
    var $,
        win,
        isLayui = window.layui && layui.define,
        ready = {
            getPath: function() {
                var js = document.scripts,
                    script = js[js.length - 1],
                    jsPath = script.src;
                if (!script.getAttribute('merge')) return jsPath.substring(0, jsPath.lastIndexOf('/') + 1)
            }(),
            config: {},
            end: {},
            minIndex: 0,
            minLeft: [],
            btn: [
                '确定',
                '取消'
            ],
            type: [
                'dialog',
                'page',
                'iframe',
                'loading',
                'tips'
            ]
        },
        layer = {
            v: '3.0.1',
            ie: function() {
                var agent = navigator.userAgent.toLowerCase();
                return !!(window.ActiveXObject || 'ActiveXObject' in window) && ((agent.match(/msie\s(\d+)/) || [])[1] || '11')
            }(),
            index: window.layer && window.layer.v ? 100000 : 0,
            path: ready.getPath,
            config: function(options, fn) {
                return options = options || {},
                    layer.cache = ready.config = $.extend({}, ready.config, options),
                    layer.path = ready.config.path || layer.path,
                    'string' == typeof options.extend && (options.extend = [
                        options.extend
                    ]),
                    ready.config.path && layer.ready(),
                    options.extend ? (isLayui ? layui.addcss('modules/layer/' + options.extend) : layer.link('skin/' + options.extend), this) : this
            },
            link: function(href, fn, cssname) {
                if (layer.path) {
                    var head = $('head')[0],
                        link = document.createElement('link');
                    'string' == typeof fn && (cssname = fn);
                    var app = (cssname || href).replace(/\.|\//g, ''),
                        id = 'layuicss-' + app,
                        timeout = 0;
                    link.rel = 'stylesheet',
                        link.href = layer.path + href,
                        link.id = id,
                        $('#' + id)[0] || head.appendChild(link),
                        'function' == typeof fn && function poll() {
                            if (++timeout > 80) return window.console && console.error('layer.css: Invalid');
                            1989 === parseInt($('#' + id).css('width')) ? fn() : setTimeout(poll, 100)
                        }()
                }
            },
            ready: function(callback) {
                return isLayui ? layui.addcss('modules/layer/default/layer.css?v=' + layer.v + '1110', callback, 'skinlayercss') : layer.link('skin/default/layer.css?v=' + layer.v + '1110', callback, 'skinlayercss'),
                    this
            },
            alert: function(content, options, yes) {
                var type = 'function' == typeof options;
                return type && (yes = options),
                    layer.open($.extend({
                            content: content,
                            yes: yes
                        }, type ? {} :
                        options))
            },
            confirm: function(content, options, yes, cancel) {
                var type = 'function' == typeof options;
                return type && (cancel = yes, yes = options),
                    layer.open($.extend({
                            content: content,
                            btn: ready.btn,
                            yes: yes,
                            btn2: cancel
                        }, type ? {} :
                        options))
            },
            msg: function(content, options, end) {
                var type = 'function' == typeof options,
                    rskin = ready.config.skin,
                    skin = (rskin ? rskin + ' ' + rskin + '-msg' : '') || 'layui-layer-msg',
                    anim = doms.anim.length - 1;
                return type && (end = options),
                    layer.open($.extend({
                            content: content,
                            time: 3000,
                            shade: !1,
                            skin: skin,
                            title: !1,
                            closeBtn: !1,
                            btn: !1,
                            resize: !1,
                            end: end
                        }, type && !ready.config.skin ? {
                            skin: skin + ' layui-layer-hui',
                            anim: anim
                        } :
                        function() {
                            return options = options || {},
                                (-1 === options.icon || void 0 === options.icon && !ready.config.skin) && (options.skin = skin + ' ' + (options.skin || 'layui-layer-hui')),
                                options
                        }()))
            },
            load: function(icon, options) {
                return layer.open($.extend({
                    type: 3,
                    icon: icon || 0,
                    resize: !1,
                    shade: 0.01
                }, options))
            },
            tips: function(content, follow, options) {
                return layer.open($.extend({
                    type: 4,
                    content: [
                        content,
                        follow
                    ],
                    closeBtn: !1,
                    time: 3000,
                    shade: !1,
                    resize: !1,
                    fixed: !1,
                    maxWidth: 210
                }, options))
            }
        },
        Class = function(setings) {
            var that = this;
            that.index = ++layer.index,
                that.config = $.extend({}, that.config, ready.config, setings),
                document.body ? that.creat() : setTimeout(function() {
                    that.creat()
                }, 50)
        };
    Class.pt = Class.prototype;
    var doms = [
        'layui-layer',
        '.layui-layer-title',
        '.layui-layer-main',
        '.layui-layer-dialog',
        'layui-layer-iframe',
        'layui-layer-content',
        'layui-layer-btn',
        'layui-layer-close'
    ];
    doms.anim = [
            'layer-anim',
            'layer-anim-01',
            'layer-anim-02',
            'layer-anim-03',
            'layer-anim-04',
            'layer-anim-05',
            'layer-anim-06'
        ],
        Class.pt.config = {
            type: 0,
            shade: 0.3,
            fixed: !0,
            move: doms[1],
            title: '信息',
            offset: 'auto',
            area: 'auto',
            closeBtn: 1,
            time: 0,
            zIndex: 19891014,
            maxWidth: 360,
            anim: 0,
            icon: -1,
            moveType: 1,
            resize: !0,
            scrollbar: !0,
            tips: 2
        },
        Class.pt.vessel = function(conType, callback) {
            var that = this,
                times = that.index,
                config = that.config,
                zIndex = config.zIndex + times,
                titype = 'object' == typeof config.title,
                ismax = config.maxmin && (1 === config.type || 2 === config.type),
                titleHTML = config.title ? '<div class="layui-layer-title" style="' + (titype ? config.title[1] : '') + '">' + (titype ? config.title[0] : config.title) + '</div>' : '';
            return config.zIndex = zIndex,
                callback([config.shade ? '<div class="layui-layer-shade" id="layui-layer-shade' + times + '" times="' + times + '" style="z-index:' + (zIndex - 1) + '; background-color:' + (config.shade[1] || '#000') + '; opacity:' + (config.shade[0] || config.shade) + '; filter:alpha(opacity=' + (100 * config.shade[0] || 100 * config.shade) + ');"></div>' : '',
                    '<div class="' + doms[0] + ' layui-layer-' + ready.type[config.type] + (0 != config.type && 2 != config.type || config.shade ? '' : ' layui-layer-border') + ' ' + (config.skin || '') + '" id="' + doms[0] + times + '" type="' + ready.type[config.type] + '" times="' + times + '" showtime="' + config.time + '" conType="' + (conType ? 'object' : 'string') + '" style="z-index: ' + zIndex + '; width:' + config.area[0] + ';height:' + config.area[1] + (config.fixed ? '' : ';position:absolute;') + '">' + (conType && 2 != config.type ? '' : titleHTML) + '<div id="' + (config.id || '') + '" class="layui-layer-content' + (0 == config.type && -1 !== config.icon ? ' layui-layer-padding' : '') + (3 == config.type ? ' layui-layer-loading' + config.icon : '') + '">' + (0 == config.type && -1 !== config.icon ? '<i class="layui-layer-ico layui-layer-ico' + config.icon + '"></i>' : '') + (1 == config.type && conType ? '' : config.content || '') + '</div><span class="layui-layer-setwin">' + function() {
                        var closebtn = ismax ? '<a class="layui-layer-min" href="javascript:;"><cite></cite></a><a class="layui-layer-ico layui-layer-max" href="javascript:;"></a>' : '';
                        return config.closeBtn && (closebtn += '<a class="layui-layer-ico ' + doms[7] + ' ' + doms[7] + (config.title ? config.closeBtn : 4 == config.type ? '1' : '2') + '" href="javascript:;"></a>'),
                            closebtn
                    }() + '</span>' + (config.btn ? function() {
                        var button = '';
                        'string' == typeof config.btn && (config.btn = [
                            config.btn
                        ]);
                        for (var i = 0, len = config.btn.length; i < len; i++) button += '<a class="' + doms[6] + i + '">' + config.btn[i] + '</a>';
                        return '<div class="' + doms[6] + ' layui-layer-btn-' + (config.btnAlign || '') + '">' + button + '</div>'
                    }() : '') + (config.resize ? '<span class="layui-layer-resize"></span>' : '') + '</div>'
                ], titleHTML, $('<div class="layui-layer-move"></div>')),
                that
        },
        Class.pt.creat = function() {
            var that = this,
                config = that.config,
                times = that.index,
                content = config.content,
                conType = 'object' == typeof content,
                body = $('body');
            if (!$('#' + config.id)[0]) {
                switch ('string' == typeof config.area && (config.area = 'auto' === config.area ? [
                    '',
                    ''
                ] : [
                    config.area,
                    ''
                ]), config.shift && (config.anim = config.shift), 6 == layer.ie && (config.fixed = !1), config.type) {
                    case 0:
                        config.btn = 'btn' in config ? config.btn : ready.btn[0],
                            layer.closeAll('dialog');
                        break;
                    case 2:
                        var content = config.content = conType ? config.content : [
                            config.content || 'http://layer.layui.com',
                            'auto'
                        ];
                        config.content = '<iframe scrolling="' + (config.content[1] || 'auto') + '" allowtransparency="true" id="' + doms[4] + times + '" name="' + doms[4] + times + '" onload="this.className=\'\';" class="layui-layer-load" frameborder="0" src="' + config.content[0] + '"></iframe>';
                        break;
                    case 3:
                        delete config.title,
                            delete config.closeBtn, -1 === config.icon && config.icon,
                            layer.closeAll('loading');
                        break;
                    case 4:
                        conType || (config.content = [
                                config.content,
                                'body'
                            ]),
                            config.follow = config.content[1],
                            config.content = config.content[0] + '<i class="layui-layer-TipsG"></i>',
                            delete config.title,
                            config.tips = 'object' == typeof config.tips ? config.tips : [
                                config.tips, !0
                            ],
                            config.tipsMore || layer.closeAll('tips')
                }
                that.vessel(conType, function(html, titleHTML, moveElem) {
                        body.append(html[0]),
                            conType ? function() {
                                2 == config.type || 4 == config.type ? function() {
                                    $('body').append(html[1])
                                }() : function() {
                                    content.parents('.' + doms[0])[0] || (content.data('display', content.css('display')).show().addClass('layui-layer-wrap').wrap(html[1]), $('#' + doms[0] + times).find('.' + doms[5]).before(titleHTML))
                                }()
                            }() : body.append(html[1]),
                            $('.layui-layer-move')[0] || body.append(ready.moveElem = moveElem),
                            that.layero = $('#' + doms[0] + times),
                            config.scrollbar || doms.html.css('overflow', 'hidden').attr('layer-full', times)
                    }).auto(times),
                    2 == config.type && 6 == layer.ie && that.layero.find('iframe').attr('src', content[0]),
                    4 == config.type ? that.tips() : that.offset(),
                    config.fixed && win.on('resize', function() {
                        that.offset(),
                            (/^\d+%$/.test(config.area[0]) || /^\d+%$/.test(config.area[1])) && that.auto(times),
                            4 == config.type && that.tips()
                    }),
                    config.time <= 0 || setTimeout(function() {
                        layer.close(that.index)
                    }, config.time),
                    that.move().callback(),
                    doms.anim[config.anim] && that.layero.addClass(doms.anim[config.anim]).data('anim', !0)
            }
        },
        Class.pt.auto = function(index) {
            function setHeight(elem) {
                elem = layero.find(elem),
                    elem.height(area[1] - titHeight - btnHeight - 2 * (0 | parseFloat(elem.css('padding'))))
            }
            var that = this,
                config = that.config,
                layero = $('#' + doms[0] + index);
            '' === config.area[0] && config.maxWidth > 0 && (layer.ie && layer.ie < 8 && config.btn && layero.width(layero.innerWidth()), layero.outerWidth() > config.maxWidth && layero.width(config.maxWidth));
            var area = [
                    layero.innerWidth(),
                    layero.innerHeight()
                ],
                titHeight = layero.find(doms[1]).outerHeight() || 0,
                btnHeight = layero.find('.' + doms[6]).outerHeight() || 0;
            switch (config.type) {
                case 2:
                    setHeight('iframe');
                    break;
                default:
                    '' === config.area[1] ? config.fixed && area[1] >= win.height() && (area[1] = win.height(), setHeight('.' + doms[5])) : setHeight('.' + doms[5])
            }
            return that
        },
        Class.pt.offset = function() {
            var that = this,
                config = that.config,
                layero = that.layero,
                area = [
                    layero.outerWidth(),
                    layero.outerHeight()
                ],
                type = 'object' == typeof config.offset;
            that.offsetTop = (win.height() - area[1]) / 2,
                that.offsetLeft = (win.width() - area[0]) / 2,
                type ? (that.offsetTop = config.offset[0], that.offsetLeft = config.offset[1] || that.offsetLeft) : 'auto' !== config.offset && ('t' === config.offset ? that.offsetTop = 0 : 'r' === config.offset ? that.offsetLeft = win.width() - area[0] : 'b' === config.offset ? that.offsetTop = win.height() - area[1] : 'l' === config.offset ? that.offsetLeft = 0 : 'lt' === config.offset ? (that.offsetTop = 0, that.offsetLeft = 0) : 'lb' === config.offset ? (that.offsetTop = win.height() - area[1], that.offsetLeft = 0) : 'rt' === config.offset ? (that.offsetTop = 0, that.offsetLeft = win.width() - area[0]) : 'rb' === config.offset ? (that.offsetTop = win.height() - area[1], that.offsetLeft = win.width() - area[0]) : that.offsetTop = config.offset),
                config.fixed || (that.offsetTop = /%$/.test(that.offsetTop) ? win.height() * parseFloat(that.offsetTop) / 100 : parseFloat(that.offsetTop), that.offsetLeft = /%$/.test(that.offsetLeft) ? win.width() * parseFloat(that.offsetLeft) / 100 : parseFloat(that.offsetLeft), that.offsetTop += win.scrollTop(), that.offsetLeft += win.scrollLeft()),
                layero.attr('minLeft') && (that.offsetTop = win.height() - (layero.find(doms[1]).outerHeight() || 0), that.offsetLeft = layero.css('left')),
                layero.css({
                    top: that.offsetTop,
                    left: that.offsetLeft
                })
        },
        Class.pt.tips = function() {
            var that = this,
                config = that.config,
                layero = that.layero,
                layArea = [
                    layero.outerWidth(),
                    layero.outerHeight()
                ],
                follow = $(config.follow);
            follow[0] || (follow = $('body'));
            var goal = {
                    width: follow.outerWidth(),
                    height: follow.outerHeight(),
                    top: follow.offset().top,
                    left: follow.offset().left
                },
                tipsG = layero.find('.layui-layer-TipsG'),
                guide = config.tips[0];
            config.tips[1] || tipsG.remove(),
                goal.autoLeft = function() {
                    goal.left + layArea[0] - win.width() > 0 ? (goal.tipLeft = goal.left + goal.width - layArea[0], tipsG.css({
                        right: 12,
                        left: 'auto'
                    })) : goal.tipLeft = goal.left
                },
                goal.where = [
                    function() {
                        goal.autoLeft(),
                            goal.tipTop = goal.top - layArea[1] - 10,
                            tipsG.removeClass('layui-layer-TipsB').addClass('layui-layer-TipsT').css('border-right-color', config.tips[1])
                    },
                    function() {
                        goal.tipLeft = goal.left + goal.width + 10,
                            goal.tipTop = goal.top,
                            tipsG.removeClass('layui-layer-TipsL').addClass('layui-layer-TipsR').css('border-bottom-color', config.tips[1])
                    },
                    function() {
                        goal.autoLeft(),
                            goal.tipTop = goal.top + goal.height + 10,
                            tipsG.removeClass('layui-layer-TipsT').addClass('layui-layer-TipsB').css('border-right-color', config.tips[1])
                    },
                    function() {
                        goal.tipLeft = goal.left - layArea[0] - 10,
                            goal.tipTop = goal.top,
                            tipsG.removeClass('layui-layer-TipsR').addClass('layui-layer-TipsL').css('border-bottom-color', config.tips[1])
                    }
                ],
                goal.where[guide - 1](),
                1 === guide ? goal.top - (win.scrollTop() + layArea[1] + 16) < 0 && goal.where[2]() : 2 === guide ? win.width() - (goal.left + goal.width + layArea[0] + 16) > 0 || goal.where[3]() : 3 === guide ? goal.top - win.scrollTop() + goal.height + layArea[1] + 16 - win.height() > 0 && goal.where[0]() : 4 === guide && layArea[0] + 16 - goal.left > 0 && goal.where[1](),
                layero.find('.' + doms[5]).css({
                    'background-color': config.tips[1],
                    'padding-right': config.closeBtn ? '30px' : ''
                }),
                layero.css({
                    left: goal.tipLeft - (config.fixed ? win.scrollLeft() : 0),
                    top: goal.tipTop - (config.fixed ? win.scrollTop() : 0)
                })
        },
        Class.pt.move = function() {
            var that = this,
                config = that.config,
                _DOC = $(document),
                layero = that.layero,
                moveElem = layero.find(config.move),
                resizeElem = layero.find('.layui-layer-resize'),
                dict = {};
            return config.move && moveElem.css('cursor', 'move'),
                moveElem.on('mousedown', function(e) {
                    e.preventDefault(),
                        config.move && (dict.moveStart = !0, dict.offset = [
                            e.clientX - parseFloat(layero.css('left')),
                            e.clientY - parseFloat(layero.css('top'))
                        ], ready.moveElem.css('cursor', 'move').show())
                }),
                resizeElem.on('mousedown', function(e) {
                    e.preventDefault(),
                        dict.resizeStart = !0,
                        dict.offset = [
                            e.clientX,
                            e.clientY
                        ],
                        dict.area = [
                            layero.outerWidth(),
                            layero.outerHeight()
                        ],
                        ready.moveElem.css('cursor', 'se-resize').show()
                }),
                _DOC.on('mousemove', function(e) {
                    if (dict.moveStart) {
                        var X = e.clientX - dict.offset[0],
                            Y = e.clientY - dict.offset[1],
                            fixed = 'fixed' === layero.css('position');
                        if (e.preventDefault(), dict.stX = fixed ? 0 : win.scrollLeft(), dict.stY = fixed ? 0 : win.scrollTop(), !config.moveOut) {
                            var setRig = win.width() - layero.outerWidth() + dict.stX,
                                setBot = win.height() - layero.outerHeight() + dict.stY;
                            X < dict.stX && (X = dict.stX),
                                X > setRig && (X = setRig),
                                Y < dict.stY && (Y = dict.stY),
                                Y > setBot && (Y = setBot)
                        }
                        layero.css({
                            left: X,
                            top: Y
                        })
                    }
                    if (config.resize && dict.resizeStart) {
                        var X = e.clientX - dict.offset[0],
                            Y = e.clientY - dict.offset[1];
                        e.preventDefault(),
                            layer.style(that.index, {
                                width: dict.area[0] + X,
                                height: dict.area[1] + Y
                            }),
                            dict.isResize = !0
                    }
                }).on('mouseup', function(e) {
                    dict.moveStart && (delete dict.moveStart, ready.moveElem.hide(), config.moveEnd && config.moveEnd()),
                        dict.resizeStart && (delete dict.resizeStart, ready.moveElem.hide())
                }),
                that
        },
        Class.pt.callback = function() {
            function cancel() {
                !1 === (config.cancel && config.cancel(that.index, layero)) || layer.close(that.index)
            }
            var that = this,
                layero = that.layero,
                config = that.config;
            that.openLayer(),
                config.success && (2 == config.type ? layero.find('iframe').on('load', function() {
                    config.success(layero, that.index)
                }) : config.success(layero, that.index)),
                6 == layer.ie && that.IE6(layero),
                layero.find('.' + doms[6]).children('a').on('click', function() {
                    var index = $(this).index();
                    if (0 === index) config.yes ? config.yes(that.index, layero) : config.btn1 ? config.btn1(that.index, layero) : layer.close(that.index);
                    else {
                        !1 === (config['btn' + (index + 1)] && config['btn' + (index + 1)](that.index, layero)) || layer.close(that.index)
                    }
                }),
                layero.find('.' + doms[7]).on('click', cancel),
                config.shadeClose && $('#layui-layer-shade' + that.index).on('click', function() {
                    layer.close(that.index)
                }),
                layero.find('.layui-layer-min').on('click', function() {
                    !1 === (config.min && config.min(layero)) || layer.min(that.index, config)
                }),
                layero.find('.layui-layer-max').on('click', function() {
                    $(this).hasClass('layui-layer-maxmin') ? (layer.restore(that.index), config.restore && config.restore(layero)) : (layer.full(that.index, config), setTimeout(function() {
                        config.full && config.full(layero)
                    }, 100))
                }),
                config.end && (ready.end[that.index] = config.end)
        },
        ready.reselect = function() {
            $.each($('select'), function(index, value) {
                var sthis = $(this);
                sthis.parents('.' + doms[0])[0] || 1 == sthis.attr('layer') && $('.' + doms[0]).length < 1 && sthis.removeAttr('layer').show(),
                    sthis = null
            })
        },
        Class.pt.IE6 = function(layero) {
            $('select').each(function(index, value) {
                var sthis = $(this);
                sthis.parents('.' + doms[0])[0] || 'none' === sthis.css('display') || sthis.attr({
                        layer: '1'
                    }).hide(),
                    sthis = null
            })
        },
        Class.pt.openLayer = function() {
            var that = this;
            layer.zIndex = that.config.zIndex,
                layer.setTop = function(layero) {
                    var setZindex = function() {
                        layer.zIndex++,
                            layero.css('z-index', layer.zIndex + 1)
                    };
                    return layer.zIndex = parseInt(layero[0].style.zIndex),
                        layero.on('mousedown', setZindex),
                        layer.zIndex
                }
        },
        ready.record = function(layero) {
            var area = [
                layero.width(),
                layero.height(),
                layero.position().top,
                layero.position().left + parseFloat(layero.css('margin-left'))
            ];
            layero.find('.layui-layer-max').addClass('layui-layer-maxmin'),
                layero.attr({
                    area: area
                })
        },
        ready.rescollbar = function(index) {
            doms.html.attr('layer-full') == index && (doms.html[0].style.removeProperty ? doms.html[0].style.removeProperty('overflow') : doms.html[0].style.removeAttribute('overflow'), doms.html.removeAttr('layer-full'))
        },
        window.layer = layer,
        layer.getChildFrame = function(selector, index) {
            return index = index || $('.' + doms[4]).attr('times'),
                $('#' + doms[0] + index).find('iframe').contents().find(selector)
        },
        layer.getFrameIndex = function(name) {
            return $('#' + name).parents('.' + doms[4]).attr('times')
        },
        layer.iframeAuto = function(index) {
            if (index) {
                var heg = layer.getChildFrame('html', index).outerHeight(),
                    layero = $('#' + doms[0] + index),
                    titHeight = layero.find(doms[1]).outerHeight() || 0,
                    btnHeight = layero.find('.' + doms[6]).outerHeight() || 0;
                layero.css({
                        height: heg + titHeight + btnHeight
                    }),
                    layero.find('iframe').css({
                        height: heg
                    })
            }
        },
        layer.iframeSrc = function(index, url) {
            $('#' + doms[0] + index).find('iframe').attr('src', url)
        },
        layer.style = function(index, options, limit) {
            var layero = $('#' + doms[0] + index),
                contElem = layero.find('.layui-layer-content'),
                type = layero.attr('type'),
                titHeight = layero.find(doms[1]).outerHeight() || 0,
                btnHeight = layero.find('.' + doms[6]).outerHeight() || 0;
            layero.attr('minLeft');
            type !== ready.type[3] && type !== ready.type[4] && (limit || (parseFloat(options.width) <= 260 && (options.width = 260), parseFloat(options.height) - titHeight - btnHeight <= 64 && (options.height = 64 + titHeight + btnHeight)), layero.css(options), btnHeight = layero.find('.' + doms[6]).outerHeight(), type === ready.type[2] ? layero.find('iframe').css({
                height: parseFloat(options.height) - titHeight - btnHeight
            }) : contElem.css({
                height: parseFloat(options.height) - titHeight - btnHeight - parseFloat(contElem.css('padding-top')) - parseFloat(contElem.css('padding-bottom'))
            }))
        },
        layer.min = function(index, options) {
            var layero = $('#' + doms[0] + index),
                titHeight = layero.find(doms[1]).outerHeight() || 0,
                left = layero.attr('minLeft') || 181 * ready.minIndex + 'px',
                position = layero.css('position');
            ready.record(layero),
                ready.minLeft[0] && (left = ready.minLeft[0], ready.minLeft.shift()),
                layero.attr('position', position),
                layer.style(index, {
                    width: 180,
                    height: titHeight,
                    left: left,
                    top: win.height() - titHeight,
                    position: 'fixed',
                    overflow: 'hidden'
                }, !0),
                layero.find('.layui-layer-min').hide(),
                'page' === layero.attr('type') && layero.find(doms[4]).hide(),
                ready.rescollbar(index),
                layero.attr('minLeft') || ready.minIndex++,
                layero.attr('minLeft', left)
        },
        layer.restore = function(index) {
            var layero = $('#' + doms[0] + index),
                area = layero.attr('area').split(',');
            layero.attr('type');
            layer.style(index, {
                    width: parseFloat(area[0]),
                    height: parseFloat(area[1]),
                    top: parseFloat(area[2]),
                    left: parseFloat(area[3]),
                    position: layero.attr('position'),
                    overflow: 'visible'
                }, !0),
                layero.find('.layui-layer-max').removeClass('layui-layer-maxmin'),
                layero.find('.layui-layer-min').show(),
                'page' === layero.attr('type') && layero.find(doms[4]).show(),
                ready.rescollbar(index)
        },
        layer.full = function(index) {
            var timer,
                layero = $('#' + doms[0] + index);
            ready.record(layero),
                doms.html.attr('layer-full') || doms.html.css('overflow', 'hidden').attr('layer-full', index),
                clearTimeout(timer),
                timer = setTimeout(function() {
                    var isfix = 'fixed' === layero.css('position');
                    layer.style(index, {
                            top: isfix ? 0 : win.scrollTop(),
                            left: isfix ? 0 : win.scrollLeft(),
                            width: win.width(),
                            height: win.height()
                        }, !0),
                        layero.find('.layui-layer-min').hide()
                }, 100)
        },
        layer.title = function(name, index) {
            $('#' + doms[0] + (index || layer.index)).find(doms[1]).html(name)
        },
        layer.close = function(index) {
            var layero = $('#' + doms[0] + index),
                type = layero.attr('type');
            if (layero[0]) {
                var WRAP = 'layui-layer-wrap',
                    remove = function() {
                        if (type === ready.type[1] && 'object' === layero.attr('conType')) {
                            layero.children(':not(.' + doms[5] + ')').remove();
                            for (var wrap = layero.find('.' + WRAP), i = 0; i < 2; i++) wrap.unwrap();
                            wrap.css('display', wrap.data('display')).removeClass(WRAP)
                        } else {
                            if (type === ready.type[2]) try {
                                var iframe = $('#' + doms[4] + index)[0];
                                iframe.contentWindow.document.write(''),
                                    iframe.contentWindow.close(),
                                    layero.find('.' + doms[5])[0].removeChild(iframe)
                            } catch (e) {}
                            layero[0].innerHTML = '',
                                layero.remove()
                        }
                        'function' == typeof ready.end[index] && ready.end[index](),
                            delete ready.end[index]
                    };
                layero.data('anim') && layero.addClass('layer-anim-close'),
                    $('#layui-layer-moves, #layui-layer-shade' + index).remove(),
                    6 == layer.ie && ready.reselect(),
                    ready.rescollbar(index),
                    layero.attr('minLeft') && (ready.minIndex--, ready.minLeft.push(layero.attr('minLeft'))),
                    setTimeout(function() {
                        remove()
                    }, layer.ie && layer.ie < 10 || !layero.data('anim') ? 0 : 200)
            }
        },
        layer.closeAll = function(type) {
            $.each($('.' + doms[0]), function() {
                var othis = $(this),
                    is = type ? othis.attr('type') === type : 1;
                is && layer.close(othis.attr('times')),
                    is = null
            })
        };
    var cache = layer.cache || {},
        skin = function(type) {
            return cache.skin ? ' ' + cache.skin + ' ' + cache.skin + '-' + type : ''
        };
    layer.prompt = function(options, yes) {
            var style = '';
            if (options = options || {}, 'function' == typeof options && (yes = options), options.area) {
                var area = options.area;
                style = 'style="width: ' + area[0] + '; height: ' + area[1] + ';"',
                    delete options.area
            }
            var prompt,
                content = 2 == options.formType ? '<textarea class="layui-layer-input"' + style + '>' + (options.value || '') + '</textarea>' : function() {
                    return '<input type="' + (1 == options.formType ? 'password' : 'text') + '" class="layui-layer-input" value="' + (options.value || '') + '">'
                }();
            return layer.open($.extend({
                type: 1,
                btn: [
                    '确定',
                    '取消'
                ],
                content: content,
                skin: 'layui-layer-prompt' + skin('prompt'),
                maxWidth: win.width(),
                success: function(layero) {
                    prompt = layero.find('.layui-layer-input'),
                        prompt.focus()
                },
                resize: !1,
                yes: function(index) {
                    var value = prompt.val();
                    '' === value ? prompt.focus() : value.length > (options.maxlength || 500) ? layer.tips('最多输入' + (options.maxlength || 500) + '个字数', prompt, {
                        tips: 1
                    }) : yes && yes(value, index, prompt)
                }
            }, options))
        },
        layer.tab = function(options) {
            options = options || {};
            var tab = options.tab || {};
            return layer.open($.extend({
                type: 1,
                skin: 'layui-layer-tab' + skin('tab'),
                resize: !1,
                title: function() {
                    var len = tab.length,
                        ii = 1,
                        str = '';
                    if (len > 0)
                        for (str = '<span class="layui-layer-tabnow">' + tab[0].title + '</span>'; ii < len; ii++) str += '<span>' + tab[ii].title + '</span>';
                    return str
                }(),
                content: '<ul class="layui-layer-tabmain">' + function() {
                    var len = tab.length,
                        ii = 1,
                        str = '';
                    if (len > 0)
                        for (str = '<li class="layui-layer-tabli xubox_tab_layer">' + (tab[0].content || 'no content') + '</li>'; ii < len; ii++) str += '<li class="layui-layer-tabli">' + (tab[ii].content || 'no  content') + '</li>';
                    return str
                }() + '</ul>',
                success: function(layero) {
                    var btn = layero.find('.layui-layer-title').children(),
                        main = layero.find('.layui-layer-tabmain').children();
                    btn.on('mousedown', function(e) {
                        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0;
                        var othis = $(this),
                            index = othis.index();
                        othis.addClass('layui-layer-tabnow').siblings().removeClass('layui-layer-tabnow'),
                            main.eq(index).show().siblings().hide(),
                            'function' == typeof options.change && options.change(index)
                    })
                }
            }, options))
        },
        layer.photos = function(options, loop, key) {
            var dict = {};
            if (options = options || {}, options.photos) {
                var type = options.photos.constructor === Object,
                    photos = type ? options.photos : {},
                    data = photos.data || [],
                    start = photos.start || 0;
                if (dict.imgIndex = 1 + (0 | start), options.img = options.img || 'img', type) {
                    if (0 === data.length) return layer.msg('没有图片')
                } else {
                    var parent = $(options.photos),
                        pushData = function() {
                            data = [],
                                parent.find(options.img).each(function(index) {
                                    var othis = $(this);
                                    othis.attr('layer-index', index),
                                        data.push({
                                            alt: othis.attr('alt'),
                                            pid: othis.attr('layer-pid'),
                                            src: othis.attr('layer-src') || othis.attr('src'),
                                            thumb: othis.attr('src')
                                        })
                                })
                        };
                    if (pushData(), 0 === data.length) return;
                    if (loop || parent.on('click', options.img, function() {
                            var othis = $(this),
                                index = othis.attr('layer-index');
                            layer.photos($.extend(options, {
                                    photos: {
                                        start: index,
                                        data: data,
                                        tab: options.tab
                                    },
                                    full: options.full
                                }), !0),
                                pushData()
                        }), !loop) return
                }
                dict.imgprev = function(key) {
                        dict.imgIndex--,
                            dict.imgIndex < 1 && (dict.imgIndex = data.length),
                            dict.tabimg(key)
                    },
                    dict.imgnext = function(key, errorMsg) {
                        ++dict.imgIndex > data.length && (dict.imgIndex = 1, errorMsg) || dict.tabimg(key)
                    },
                    dict.keyup = function(event) {
                        if (!dict.end) {
                            var code = event.keyCode;
                            event.preventDefault(),
                                37 === code ? dict.imgprev(!0) : 39 === code ? dict.imgnext(!0) : 27 === code && layer.close(dict.index)
                        }
                    },
                    dict.tabimg = function(key) {
                        data.length <= 1 || (photos.start = dict.imgIndex - 1, layer.close(dict.index), layer.photos(options, !0, key))
                    },
                    dict.event = function() {
                        dict.bigimg.hover(function() {
                                dict.imgsee.show()
                            }, function() {
                                dict.imgsee.hide()
                            }),
                            dict.bigimg.find('.layui-layer-imgprev').on('click', function(event) {
                                event.preventDefault(),
                                    dict.imgprev()
                            }),
                            dict.bigimg.find('.layui-layer-imgnext').on('click', function(event) {
                                event.preventDefault(),
                                    dict.imgnext()
                            }),
                            $(document).on('keyup', dict.keyup)
                    },
                    dict.loadi = layer.load(1, {
                        shade: !('shade' in options) && 0.9,
                        scrollbar: !1
                    }),
                    function(url, callback, error) {
                        var img = new Image;
                        if (img.src = url, img.complete) return callback(img);
                        img.onload = function() {
                                img.onload = null,
                                    callback(img)
                            },
                            img.onerror = function(e) {
                                img.onerror = null,
                                    error(e)
                            }
                    }(data[start].src, function(img) {
                        layer.close(dict.loadi),
                            dict.index = layer.open($.extend({
                                type: 1,
                                area: function() {
                                    var imgarea = [
                                            img.width,
                                            img.height
                                        ],
                                        winarea = [
                                            $(window).width() - 100,
                                            $(window).height() - 100
                                        ];
                                    if (!options.full && (imgarea[0] > winarea[0] || imgarea[1] > winarea[1])) {
                                        var wh = [
                                            imgarea[0] / winarea[0],
                                            imgarea[1] / winarea[1]
                                        ];
                                        wh[0] > wh[1] ? (imgarea[0] = imgarea[0] / wh[0], imgarea[1] = imgarea[1] / wh[0]) : wh[0] < wh[1] && (imgarea[0] = imgarea[0] / wh[1], imgarea[1] = imgarea[1] / wh[1])
                                    }
                                    return [imgarea[0] + 'px',
                                        imgarea[1] + 'px'
                                    ]
                                }(),
                                title: !1,
                                shade: 0.9,
                                shadeClose: !0,
                                closeBtn: !1,
                                move: '.layui-layer-phimg img',
                                moveType: 1,
                                scrollbar: !1,
                                moveOut: !0,
                                anim: 5 * Math.random() | 0,
                                skin: 'layui-layer-photos' + skin('photos'),
                                content: '<div class="layui-layer-phimg"><img src="' + data[start].src + '" alt="' + (data[start].alt || '') + '" layer-pid="' + data[start].pid + '"><div class="layui-layer-imgsee">' + (data.length > 1 ? '<span class="layui-layer-imguide"><a href="javascript:;" class="layui-layer-iconext layui-layer-imgprev"></a><a href="javascript:;" class="layui-layer-iconext layui-layer-imgnext"></a></span>' : '') + '<div class="layui-layer-imgbar" style="display:' + (key ? 'block' : '') + '"><span class="layui-layer-imgtit"><a href="javascript:;">' + (data[start].alt || '') + '</a><em>' + dict.imgIndex + '/' + data.length + '</em></span></div></div></div>',
                                success: function(layero, index) {
                                    dict.bigimg = layero.find('.layui-layer-phimg'),
                                        dict.imgsee = layero.find('.layui-layer-imguide,.layui-layer-imgbar'),
                                        dict.event(layero),
                                        options.tab && options.tab(data[start], layero)
                                },
                                end: function() {
                                    dict.end = !0,
                                        $(document).off('keyup', dict.keyup)
                                }
                            }, options))
                    }, function() {
                        layer.close(dict.loadi),
                            layer.msg('当前图片地址异常<br>是否继续查看下一张？', {
                                time: 30000,
                                btn: [
                                    '下一张',
                                    '不看了'
                                ],
                                yes: function() {
                                    data.length > 1 && dict.imgnext(!0, !0)
                                }
                            })
                    })
            }
        },
        ready.run = function(_$) {
            $ = _$,
                win = $(window),
                doms.html = $('html'),
                layer.open = function(deliver) {
                    return new Class(deliver).index
                }
        },
        window.layui && layui.define ? (layer.ready(), layui.define('jquery', function(exports) {
            layer.path = layui.cache.dir,
                ready.run(layui.jquery),
                window.layer = layer,
                exports('layer', layer)
        })) : 'function' == typeof define ? define(['jquery'], function() {
            return ready.run(window.jQuery),
                layer
        }) : function() {
            ready.run(window.jQuery),
                layer.ready()
        }()
}(window); ? define(['jquery'], function() {
            return ready.run(window.jQuery),
                layer
        }) : function() {
            ready.run(window.jQuery),
                layer.ready()
        }()
}(window);