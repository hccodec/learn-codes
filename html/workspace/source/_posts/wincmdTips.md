---
title: Windows 命令行相关小贴士
date: 2022-03-04 10:20:34
tags:
---
# Windows 命令行相关小贴士
## 控制台字符的**打印输出**
`echo str`
## powershell处理字符串
1. 分割字符串：`$(nvm root).split("")[3]`
2. 连接字符串：`$s1+$s2`或`-Join($s1, $s2)`或`$s1*3`
## **环境变量**
1. 表示：
    在 cmd 中：`%path%`；在 powershell 中：`$env:path`
2. 输出：
    在 cmd 中：`echo %path%`；
    在 powershell 中：直接输入`$env:path`或`echo $env:path`