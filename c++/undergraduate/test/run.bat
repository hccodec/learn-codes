@echo off
chcp 65001 > nul
cls

if "%1"=="clean" (
    del /s *.exe
    del /s *.o
    echo 清除完成
    goto eof
)
if exist out.exe (
    del out.exe
    echo exe文件已删除
)
set PERFIX=D:\Users\84697\anaconda3\envs\manim
echo 正在编译 main.cpp ...
(
    (g++ -shared -I"D:/Users/84697/anaconda3/envs/manim/include" -L"D:/Users/84697/anaconda3/envs/manim/libs" -lpython38 -o test.so -fPIC test.cpp)
) && (
    echo 编译完成 正在执行
    @REM ping 0.0 -n 2 >nul | echo .
    echo +++++++++++++++++++++
    (python main.py) && (
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