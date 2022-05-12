import sys, utils.tools
from logics import mywindow, MyMainWindow
from PyQt5.QtWidgets import QApplication


def init(ui:mywindow):
    ui.util = util = utils.tools.utils(ui)

    thread = ui.myThread
    thread.init(util)

    ui.action_clearLog.triggered.connect(util.clearLog)
    ui.action_exit.triggered.connect(lambda:ui.onExit(MainWindow))
    ui.toolButton.clicked.connect(thread.start)


if __name__ == '__main__':
    app, MainWindow = QApplication(sys.argv), MyMainWindow()

    ui = mywindow(MainWindow)
    init(ui)

    MainWindow.show()
    
    sys.exit(app.exec_())
