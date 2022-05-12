---
title: NodeJS 从安装到卸载再到重装
date: 2022-03-04 10:20:34
tags:
---
# NodeJS 的安装与完全卸载

## 一、激动人心的卸载环节

1. 从**程序和功能**按照常规套路卸载我们的`Node.js`
2. 杀掉`NodeJS`的进程：`tasklist|findstr "node" && taskkill /F /IM "node.js" || echo Node.js已不再运行`
3. 寻找下列文件夹并删掉它们！
   `Program Files (x86)\Nodejs`
   `Program Files\Nodejs`
   `%appdata%\npm`
   `%appdata%\npm-cache`
4. 检查环境变量确保没有`Nodejs`或`npm`相关值存在：
   `echo %PATH% | findstr "node"`
5. （可选）命令行输入`where node`检查，凡是显示出的全都可以删掉！！
6. 为了保险，重启！！（我没重启。。。）

## 二、安装/重装

1. 下载安装包（建议通过正规渠道——[nodejs官网(中文)](https://nodejs.org/zh-cn/download/)下载）并运行安装。安装时注意以下几点：

   * 默认安装在`C:\Program Files\nodejs`路径下，可更改，记得确认好安装位置
   * 若不在Custum Setup中选择Add to PATH，则稍后将手动设置其环境变量
   * 其他选项默认即可

2. 命令行输入`node -v`和`npm -v`检查安装，出现版本号代表安装成功

3. 换源
   使用npm的默认镜像源进行包的安装时存在不便，可能拖慢工程进度，可通过换源解决这一问题。
   * 三种方法：
   ```nodejs
   //(原理)更改或追加npm配置文件：`%USERPROFILE%\.npmrc`
   registry = https://registry.npm.taobao.org
   //通过config命令
   npm config set registry https://registry.npm.taobao.org
   //命令行指定
   npm --registry https://registry.npm.taobao.org
   ```

   * 之后可运行命令`type %USERPROFILE%\.npmrc`检查文件内容

4. 修改全局依赖包下载路径
   
   全局依赖包下载路径指的是包在执行`npm i- g XXXX`全局下载时的安装路径。
   * 可先在命令行输入`npm root -g`查看该路径
   * 以下两种方法可对其进行更改
     1. 运行cmd命令(原理与2相同)
   ```
   npm config set perfix XXX  （替换npm root -g的输出值）
   npm config set cache XXXX   （替换执行包的全局安装时同时生成的同级缓存目录）
   ```
     2. 更改或追加npm配置文件：`%USERPROFILE%\.npmrc`：
   ```
   prefix = XXX
   cache = XXXX
   ```
   ```
   本次：
   XXX=D:\ProgramData\node_global_directory\node_global
   XXXX=D:\ProgramData\node_global_directory\node_cache
   ```
   * 之后可运行命令`type %USERPROFILE%\.npmrc`检查文件内容
   * **修改过环境变量后，一定要重启cmd窗口以看到环境变量修改后的效果**


------

参考地址：

1. 完全卸载nodejs：https://www.cnblogs.com/fighxp/p/7410235.html
2. 菜鸟教程-安装nodejs：https://www.runoob.com/nodejs/nodejs-install-setup.html
3. Nodejs安装及环境配置：https://www.jianshu.com/p/13f45e24b1de