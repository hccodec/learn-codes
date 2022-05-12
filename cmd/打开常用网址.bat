@ echo off
title 宝佳
mode con cols=36 lines=26
color 3f
:menu
cls
echo.
echo==================
echo 以下为常用网址
echo==================
echo 01.  itest(公网)
echo 02.  itest(内网)
echo 1.  教务网登录页
echo 2.  慕课（MOOC）
echo 31.  Unipus(公网)
echo 32.  Unipus(内网)
echo 4.  可可英语（听力）
echo 5.  第二课堂
echo 6.  第二课堂（直接登录）
echo 7.  PPT素材 网站
echo 8.  模板下载1
echo 9.  在线抠图
echo a. FIF口语学习平台
echo b. 求字体网
echo c. 在线制作图章
echo d. 直线网
echo e. 超级课程表 - 超级社团
echo.
set /p str=请选择：


echo %str%
if "%str%"=="01" goto 01
if "%str%"=="02" goto 02
if "%str%"=="1" goto 1
if "%str%"=="2" goto 2
if "%str%"=="31" goto 31
if "%str%"=="32" goto 32
if "%str%"=="4" goto 4
if "%str%"=="5" goto 5
if "%str%"=="6" goto 6
if "%str%"=="7" goto 7
if "%str%"=="8" goto 8
if "%str%"=="9" goto 9
if "%str%"=="a" goto a
if "%str%"=="b" goto b
if "%str%"=="c" goto c
if "%str%"=="d" goto d
if "%str%"=="e" goto e

goto ch
:01
start iexplore http://180.85.38.49:8080/
exit
:02
start iexplore http://192.168.187.53:8080/
exit
:1
start chrome https://dean.vatuu.com/service/login.html
exit
:2
start http://www.icourse163.org/?edusave=1
exit
:31
start http://180.85.38.49/
exit
:32
start http://192.168.187.53/
exit
:4
start iexplore http://www.kekenet.com/
exit
:5
start http://www.stuvip.com/LoginController/newLogin.do
exit
:6
start http://www.stuvip.com/LoginController/success.do
exit
:7
start http://www.1ppt.com
exit
:8
start iexplore http://sc.chinaz.com/
exit
:9
start iexplore http://www.asqql.com/gifrestrg/
exit
:a
start http://www.fifedu.com
exit
:b
start http://www.qiuziti.com/
exit
:c
start http://www.makepic.net/Tool/Signet.html
exit
:d
start http://www.linecg.com/
exit
:e
start http://club.super.cn/