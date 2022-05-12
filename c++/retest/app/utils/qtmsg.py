from PyQt5 import QtWidgets
from PyQt5.QtWidgets import QMessageBox


def warning(s:str, ss:str = None):
    msgbox = QMessageBox()
    msgbox.setWindowTitle('警告')
    msgbox.setIcon(QMessageBox.Warning)
    msgbox.setText(s)
    msgbox.setInformativeText(ss)
    Ok = msgbox.addButton('好的', QMessageBox.AcceptRole)
    msgbox.setDefaultButton(Ok)
    relpy = msgbox.exec()