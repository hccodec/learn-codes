---
title: windows 下 nvm 的安装与管理
date: 2022-03-04 10:20:34
tags:
---
# windows 下 nvm 的安装与管理

## 安装

nvm 用于管理不同版本的 node 与 npm
安装前，请先[卸载计算机上已有的node.js](https://www.cnblogs.com/fluttery/p/nodejs.html#_caption_0)，否则会发生冲突。步骤如下：
1. 官方渠道下载安装包或压缩文件：[https://github.com/coreybutler/nvm-windows](https://github.com/coreybutler/nvm-windows)（本次用安装包），直接运行exe文件安装即可）
2. 检查安装情况：命令行输入`nvm v`{language: cmd}，输出版本号即代表安装成功
3. 列出远程服务器上所有的可用版本：`nvm ls available`（非 Windows ： `nvm ls-remote`）

## 换源
   使用nvm的默认镜像源进行包的安装时存在不便，可能拖慢工程进度，可通过换源解决这一问题。
   ```nodejs
   //更改或追加nvm配置文件：`(-split $(nvm root))[3]\settings.txt`
    node_mirror: https://npm.taobao.org/mirrors/node/
    npm_mirror: https://npm.taobao.org/mirrors/npm/


    //命令行：
    设置npm_mirror:
    nvm npm_mirror https://npm.taobao.org/mirrors/npm/

    设置node_mirror:
    nvm node_mirror https://npm.taobao.org/mirrors/node/
   ```
### 无网络情况下文件配置：
直接从[淘宝镜像](https://npm.taobao.org/mirrors/node)下载zip版本（如[https://npm.taobao.org/mirrors/node/v4.6.0/node-v4.6.0-win-x64.zip](https://npm.taobao.org/mirrors/node/v4.6.0/node-v4.6.0-win-x64.zip)），然后解压到nvm所在的路径`$(nvm root)`并改文件夹名为v4.6.0，删除zip文件，使用`nvm use 4.6.0`，再查看。
* 注意：C:\Program Files\nodejs 这里保存的只是一个快捷方式而已。
* 原理：每次切换只是替换快捷方式。
* 结果：先nvm list；再nvm use xxx；再nvm list，当看到*（星号指向你要切换的本版即为切换成功）。
* 切换完之后再用node -v 检查一下是不是真的切换到正确的Node.js版本了！
* nvm管理的多个npm版本各需独立配置淘宝镜像！

## 版本管理
### 安装管理多版本 node/npm
1. 特定版本（4.2.2）：命令行输入`nvm install 4.2.2`
2. 某系列的最新版本（[语义化版本](https://semver.bootcss.com/)命名规则）（如4.2的最新版本）：命令行输入`nvm install 4.2`
3. 不同版本间切换：`nvm use 4.2.2`，规则同上
4. 别名：
    给不同的版本号设置别名：`nvm alias awesome-version 4.2.2`
    取消别名：`nvm unalias awesome-version`
    另外，还可以设置 default 这个特殊别名：`nvm alias default node`
### 其他命令
0. 快捷命令
    `nvm install node` 安装最新版 Node
    `nvm install iojs` 安装最新版 iojs
    `nvm install unstable` 安装最新不稳定版本的 Node
1. 直接运行特定版本的 Node
    `nvm run 4.2.2 --version`
2. 在当前终端的子进程中运行特定版本的 Node
    `nvm exec 4.2.2 node --version`
3. 确认某个版本Node的路径
    `nvm which 4.2.2`
4. 安装 Node 的其他实现，例如 iojs（一个基于 ES6 的 Node 实现，现在已经和 Node 合并）
    `nvm install iojs-v3.2.0`


## 其他要点
1. **当命令行运行命令时提示“`could not be found or does not exist.`”时，将`settings.txt`文件改为utf-8编码保存即可。**
-----
参考网址：
1. 使用 nvm 管理不同版本的 node 与 npm：https://www.runoob.com/w3cnote/nvm-manager-node-versions.html
2. 其他要点1：https://github.com/coreybutler/nvm-windows/issues/145