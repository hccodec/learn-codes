@ echo off
:1
cls
set a="C:\Windows\SystemApps\Microsoft.MicrosoftEdge_8wekyb3d8bbwe\MicrosoftEdge.exe"

echo 1.baidu
echo.
echo 2.bing
echo.
echo 3.exit
echo.

set /p b=��������ʹ�õ��������棺

goto %b%

:1
set /p c=�������������Ĺؼ��֣�
start %a% http://www.baidu.com/s?wd=%c%
goto 3

:2
set /p c=�������������Ĺؼ��֣�
start %a% http://cn.bing.com/search?q=%a%&ie=utf-8
goto 3

:exit
exit