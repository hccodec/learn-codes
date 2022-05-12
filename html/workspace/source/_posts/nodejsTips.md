---
title: NodeJS 的一些小贴士
date: 2022-03-04 10:20:34
tags:
---
# 以下是一些小贴士
## 1. npm 换源：

    淘宝源就是阿里源，阿里源就是淘宝源
## 2. npm中，包的管理

    查看所有全局安装的包：`npm list -g --depth 0`

    查看全局是否安装过某个包：`npm ls 包/插件名称 -g`
    查看当前文件下是否安装某个包：`npm ls 包/插件名称`
    
    查看一个包的版本信息（以`jQuery`包为例）：
    npm服务器上所有的jquery版本信息：`npm view jquery versions`
    只查看jquery的最新的版本：`npm view jquery version`
    更全的信息：`npm info jquery`


-----
参考链接：
1. [npm查看全局安装过哪些包，是否安装过某个包](https://blog.csdn.net/taylorzun/article/details/105238710)
2. [npm 如何查看一个包的版本信息？](https://blog.csdn.net/cvper/article/details/79543262?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&dist_request_id=09497030-1718-400c-b6e6-a7f7d2d85fc9&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control)