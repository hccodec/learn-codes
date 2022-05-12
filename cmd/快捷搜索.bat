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

set /p b=输入你想使用的搜索引擎：

goto %b%

:1
set /p c=输入你想搜索的关键字：
start %a% http://www.baidu.com/s?wd=%c%
goto 3

:2
set /p c=输入你想搜索的关键字：
start %a% http://cn.bing.com/search?q=%a%&ie=utf-8
goto 3

:exit
exit