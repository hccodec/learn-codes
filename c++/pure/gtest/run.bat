@echo off
chcp 65001 > nul
cls


if "%1"=="clean" (
    del /s *.exe
    del /s *.o
    echo 清除完成
    goto eof
)

set TARGET=%1
if "%1"=="" (
   set TARGET=main
)
if exist out.exe (
    del out.exe
    echo exe文件已删除
)
set PERFIX=D:\Users\84697\anaconda3\envs\manim
set/p=正在编译 %TARGET%.cpp... <nul
(
    (g++ %TARGET%.cpp -o %TARGET%)
) && (
    echo 编译完成 √
    @REM ping 0.0 -n 2 >nul | echo .
    echo +++++++++++++++++++++
    %TARGET% && (
        echo.
        echo +++++++++++++++++++++
        echo 执行完成
    ) ||(
        echo.
        echo ---------------------
        echo 程序执行出错)
) || (echo 编译出错)

:eof

@REM set PERFIX=D:\Users\84697\anaconda3\envs\manim

@REM  g++ test.cpp -ID:\Users\84697\anaconda3\envs\manim\include  -fPIC -L D:\Users\84697\anaconda3\envs\manim\libs -lpython38 -shared -o TestClass.dll