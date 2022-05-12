import layout, traceback
from PyQt5 import QtCore, QtWidgets
from PyQt5.QtWidgets import QMainWindow, QMessageBox, QTableWidgetItem, QHeaderView
from list import MyMainListItem
from myThread import MyThread, msg
from utils.tools import _log, utils
from utils.qtmsg import warning

# 重写界面 UI 的逻辑
class mywindow(layout.Ui_MainWindow):
   
    def onExit(self, MainWindow:QMainWindow):
        MainWindow.close()

    def __init__(self, MainWindow:QMainWindow) -> None:
        self.window = MainWindow
        self.setupUi(MainWindow)
        super().__init__()
        self.myThread = MyThread()
        self.myThread._signal.connect(self.callback)
        self.util = None
        self.pushButton.clicked.connect(self.addList)
        self.pbar.setVisible(False)

        _log("程序启动...")

    def callback(self, i:msg):
        _translate = QtCore.QCoreApplication.translate
        if i.type == 0:
            if i.num <= 0:
                self.util._log("数据获取完成")
                self.toolButton.setEnabled(True)
                self.toolButton.setText(_translate("MainWindow", "访问数据库"))
                self.pbar.setHidden(True)
                if i.num == -1:
                    self.toolButton.setText(_translate("MainWindow", "重新访问数据库"))
                    self.util._log(i.data)
                elif i.num == -2:
                    warning('数据库连接失败')
                    self.util._log('数据库连接失败')
            else:
                self.pbar.setValue(i.num)
                if i.num == 30:
                    self.toolButton.setText(_translate("MainWindow", "正在访问数据库..."))
                    self.toolButton.setEnabled(False)
                    self.pbar.setVisible(True)
                elif i.num == 100:
                    self.util._log("成功获取数据")
                    self.toolButton.setText(_translate("MainWindow", "更新数据库"))
        else:
            tableWidget = self.tableWidget
            data = i.data
            if i.data_order == None:
                tableWidget.setRowCount(len(data))
                for i in range(len(data)):
                # for i in range(0, len(data)):
                    tableWidget.setVerticalHeaderItem(i, QtWidgets.QTableWidgetItem())
                    tableWidget.verticalHeaderItem(i).setText(_translate("MainWindow", str(i + 1)))
                    
                    for j in range(0, len(data[i])):
                        tableWidget.setItem(i, j, QTableWidgetItem('' if str(data[i][j]) == 'None' else str(data[i][j])))
                        j += 1

                    self.addList(1, data[i])
                        
                    i += 1
                # tableWidget.resizeColumnsToContents()
                tableWidget.horizontalHeader().setSectionResizeMode(QHeaderView.Stretch)
                [tableWidget.horizontalHeader().setSectionResizeMode(x, QHeaderView.ResizeToContents) for x in range(len(data[0]))]

                self.listWidget.itemClicked.connect(lambda item: print(item.ui.label_proName.text()))
            else:
                data = i.data
                datai = i.data_order
                try:
                    tableWidget.setColumnCount(len(data))
                    [tableWidget.setHorizontalHeaderItem(i, QtWidgets.QTableWidgetItem()) for i in range(0, len(data))]
                    for i in range(0, tableWidget.columnCount()):
                        index = [ii for ii,x in enumerate(datai) if int(x[0]) - i == 1][0]
                        tableWidget.horizontalHeaderItem(i).setText(_translate("MainWindow", str(data[index][0])))


                except Exception as e:
                    self.util._log(e.args)
                    traceback.print_exc()
                    return
    

    def addList(self, num = 1, data = ('浙江科技学院', None, '', 1, 1, '安全', None, None, '085500', '机械（专硕）', '04工业互联网与大数据应用', 'C语言（、数电和数据结构三选一，与初试科目不同）', '008信息与电子工程学院', '英二数二', '818 C程序设计', None, 50, None, '0571-85070321 王老师')):

        item = MyMainListItem(num, data)
        self.listWidget.addItem(item)
        self.listWidget.setItemWidget(item, item.widget)

        # self.listWidget.itemClicked.connect(lambda item: print(item.ui.label_proName.text()))


# 重写界面框架的逻辑
class MyMainWindow(QMainWindow):
    
    # 添加一个退出的提示事件
    def closeEvent(self, event):
        """我们创建了一个消息框，上面有俩按钮：Yes和No.第一个字符串显示在消息框的标题栏，第二个字符串显示在对话框，
        第三个参数是消息框的俩按钮，最后一个参数是默认按钮，这个按钮是默认选中的。返回值在变量reply里。"""

        reply = QMessageBox.question(self, '退出提示',"确定退出？",
        QMessageBox.Yes | QMessageBox.No, QMessageBox.No)
        # 判断返回值，如果点击的是Yes按钮，我们就关闭组件和应用，否则就忽略关闭事件
        if reply == QMessageBox.Yes:
            _log("程序退出...")
            _log('exit_signal')
            event.accept()
        else:
            event.ignore()
