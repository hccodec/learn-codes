@echo off
::mshta javascript:window.execScript("msgBox('hello world!'):window.close","vbs")
::mshta vbscript:window.execScript("alert('hello world!');","javascript)
::mshta vbscript:msgbox("该干活了，伙计!",64,"提示")(window.close)
::mshta vbscript:CreateObject("Wscript.Shell").popup("该干活了，伙计！",7,"提示",64)(window.close)

::mshta vbscript:execute("msgbox ""one BOX"":msgbox ""two BOX"":window.close")

mshta vbscript:createobject("sapi.spvoice").speak("又想起我啦")(window.close)
@echo off
::mshta vbscript:createobject("SAPI.SpVoice").speak("I love you.")(window.close)

::mshta javascript:window.execScript("msgBox('hello world!'):window.close","vbs")
::mshta vbscript:window.execScript("alert('hello world!');","javascript)
::mshta vbscript:msgbox("哈哈",64,"提示")(window.close)
::mshta vbscript:CreateObject("Wscript.Shell").popup("TIME WAITS FOR NO ONE.",7,"提示",64)(window.close)
::mshta vbscript:execute("msgbox ""one BOX"":msgbox ""two BOX"":window.close")

echo 韩宝佳 
pause
::mshta vbscript:createobject("SAPI.SpVoice").speak(time)(window.close)
::mshta vbscript:createobject("SAPI.SpVoice").speak(date)(window.close)

mshta vbscript:createobject("sapi.spvoice").speak("Good luck")(window.close)