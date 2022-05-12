@echo off
cls
chcp 65001 > nul
g++ main.cpp -o out &&(
    out || (
        echo 程序运行出错
    )
) || ( echo 编译出错 )
