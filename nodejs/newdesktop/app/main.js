/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-18 10:41:41
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-18 10:47:31
 */

// javascript严格模式
'use strict';

// 应用控制模块
const electron = require('electron');
const app = electron.app;

// 原生浏览器窗口控制模块
const BrowserWindow = electron.BrowserWindow;
var mainWinow = null;

// 所有窗口关闭退出应用
app.on('window-all-closed', function() {
    if (process.platform != 'darwin') { app.quit(); }
});

app.on('ready', function() {
    mainWinow = new BrowserWindow({ width: 800, height: 600 });
    mainWinow.loadUrl('file://' + __dirname + '/index.html');
    mainWinow.on('closed', function() {
        mainWinow = null;
    });
});