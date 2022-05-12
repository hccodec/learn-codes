from os import path, remove
from PyQt5.QtCore import QDateTime, Qt



logName = 'run.log'


class utils:
    def __init__(self, window):
        self.window = window

    def _log(self, s:str):
        now = QDateTime.currentDateTime().toString(Qt.ISODate)
        self.window.statusbar.showMessage(f"[{now[0:10]} {now[-8:]}] {s}")
        _log(s, now)

    def clearLog(self):
        now = QDateTime.currentDateTime().toString(Qt.ISODate)
        if path.exists(logName):
            open(logName, 'w').close()
            self.window.statusbar.showMessage(f"[{now[0:10]} {now[-8:]}] 已成功清除日志")
        else:
            self.window.statusbar.showMessage(f"[{now[0:10]} {now[-8:]}] 日志不存在")

        pass


def _log(s:str, now:str = None):

    if (s == 'exit_signal'):
        with open(logName, 'a', encoding='utf-8') as f:
            f.write("=====================================\n")
            f.close()
    else:
        if (now is None): 
            now = QDateTime.currentDateTime().toString(Qt.ISODate)
        print(f"[{now[0:10]} {now[-8:]}] {s}")
        with open(logName, 'a', encoding='utf-8') as f:
            f.write(f"[{now[0:10]} {now[-8:]}] {s}\n")

            f.close()


