@echo off
Rem code by admzq@qq.com
::::::::::::::::::::::::::  
:生成vbs脚本，用来显示动画  
echo CD=Wscript.Arguments(1)>showplan.vbe
echo Str=Wscript.Arguments(0)>>showplan.vbe
echo Time_Delay=Wscript.Arguments(2)>>showplan.vbe
echo Switch1=Wscript.Arguments(3)>>showplan.vbe
echo Switch2=Wscript.Arguments(4)>>showplan.vbe
echo Str_Len=len(Str)>>showplan.vbe
echo Show_Str Str,Str_Len,CD,Time_Delay,Switch1,Switch2>>showplan.vbe
echo wscript.stdout.write vbcrlf>>showplan.vbe
echo Function Show_Str(str,strlen,CD,Speed,Switch1,Switch2)>>showplan.vbe
echo IF Ucase(Switch2)=^"Y^" then>>showplan.vbe
echo Show_Cursor 8,CD>>showplan.vbe
echo End if>>showplan.vbe
echo IF str^<^>^"^" then>>showplan.vbe
echo For i=1 to strlen>>showplan.vbe
echo if i mod 2=0 then>>showplan.vbe
echo Cursor=^" ^">>showplan.vbe
echo else>>showplan.vbe
echo Cursor=^"_^">>showplan.vbe
echo end if>>showplan.vbe
echo IF i=strlen then>>showplan.vbe
echo Cursor=^" ^">>showplan.vbe
echo End if>>showplan.vbe
echo IF Ucase(Switch1)=^"Y^" then>>showplan.vbe
echo str1=str1 ^& ^"^>^">>showplan.vbe
echo wscript.stdout.write chr(13) ^& CD ^& str1^&Left(str,79-i) ^& chr(8)>>showplan.vbe  
echo Else>>showplan.vbe
echo wscript.stdout.write chr(13) ^& CD ^& Left(str,i)^&Cursor^& chr(8)>>showplan.vbe  
echo End if>>showplan.vbe
echo wscript.sleep Speed>>showplan.vbe
echo Next>>showplan.vbe
echo Else>>showplan.vbe
echo Exit Function>>showplan.vbe
echo End if>>showplan.vbe
echo End Function>>showplan.vbe
echo Function Show_Cursor(Num,CD)>>showplan.vbe
echo for i=1 to Num>>showplan.vbe
echo if i mod 2=0 then>>showplan.vbe
echo Cursor=^" ^">>showplan.vbe
echo else>>showplan.vbe
echo Cursor=^"_^">>showplan.vbe
echo end if>>showplan.vbe
echo wscript.stdout.write chr(13) ^& CD ^& Cursor ^& chr(8)>>showplan.vbe
echo wscript.sleep 200>>showplan.vbe
echo Next>>showplan.vbe
echo End Function>>showplan.vbe
:初始设置;  
set str1=开机  
set str2=快开机  
set str3=他开机  
set str4=你他妈到底开不开机  
set str5=操!!再不开机老子砸了你!!  
set str6=不是内部或外部命令，也不是可运行的程序  
set str7=或批处理文件。  
set str8================================================================================  
:::::::::::::::::::::::::::::::::::::::::::::::  
:主控程序；  
cscript.exe showplan.vbe %str1% "%~dp0>" 120 "" "Y" //nologo
call :Msg %str1%
cscript.exe showplan.vbe %str2% "%~dp0>" 100 "" "Y" //nologo
call :Msg %str2%
cscript.exe showplan.vbe %str3% "%~dp0>" 180 "" "Y" //nologo
call :Msg %str3%
cscript.exe showplan.vbe %str4% "%~dp0>" 180 "" "Y" //nologo
call :Msg %str4%
cscript.exe showplan.vbe %str5% "%~dp0>" 80 "" "Y" //nologo
cscript.exe showplan.vbe "" "" 100 "" "Y" //nologo
echo Loading Windows 2000 ...
echo.  
cscript.exe showplan.vbe %str8% "" 50 "Y" "" //nologo
del showplan.vbe
goto :eof
:Msg  
ping -n 2 127.0.0.1>nul
echo '%1'%str6%
echo %str7%
echo.