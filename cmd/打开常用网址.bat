@ echo off
title ����
mode con cols=36 lines=26
color 3f
:menu
cls
echo.
echo==================
echo ����Ϊ������ַ
echo==================
echo 01.  itest(����)
echo 02.  itest(����)
echo 1.  ��������¼ҳ
echo 2.  Ľ�Σ�MOOC��
echo 31.  Unipus(����)
echo 32.  Unipus(����)
echo 4.  �ɿ�Ӣ�������
echo 5.  �ڶ�����
echo 6.  �ڶ����ã�ֱ�ӵ�¼��
echo 7.  PPT�ز� ��վ
echo 8.  ģ������1
echo 9.  ���߿�ͼ
echo a. FIF����ѧϰƽ̨
echo b. ��������
echo c. ��������ͼ��
echo d. ֱ����
echo e. �����γ̱� - ��������
echo.
set /p str=��ѡ��


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