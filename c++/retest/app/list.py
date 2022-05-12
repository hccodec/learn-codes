from PyQt5 import QtWidgets
from listitemwidget import Ui_item


# 应该继承生成的 ui 文件
class mylistitem(Ui_item):

    def __init__(self, item:QtWidgets.QWidget) -> None:

        self.setupUi(item)

        super().__init__()

# 应该继承生成 qt 控件
class MyMainListItem(QtWidgets.QListWidgetItem):
    
    def trans(self, cond:str):
        if (cond == '1'): return "√"
        if (cond == '0'): return "×"
        return cond

    def __init__(self, num, args):
        super(MyMainListItem, self).__init__()
        
        self.widget = QtWidgets.QWidget()
        self.ui = mylistitem(self.widget)
        self.ui.label_num.setText(str(num))
        self.ui.lable_university.setText(args[0])
        self.ui.label_level.setText(args[1])
        if args[1] != None:
            self.ui.label_level.setStyleSheet("border: 2px solid red; background-color: rgb(0, 170, 255); color: rgb(255, 255, 255);")
        self.ui.label_condition.setText(f"{self.trans(args[3])}|{self.trans(args[4])}")
        self.ui.label_evaluation.setText(args[5])
        self.ui.label_score.setText(f"{str(args[6])}|{str(args[7])}")
        self.ui.label_proCode.setText(args[8])
        self.ui.label_proName.setText(args[9])
        self.ui.label_school.setText(args[12])
        self.ui.proDirection.setText(args[10])
        self.ui.label_retest.setText(f"复试：{args[11]}")
        self.ui.label_pretest.setText(f"初试：{args[13]} {args[14]}")
        self.ui.detail.setText(args[2])
        
        self.setSizeHint(self.widget.sizeHint())

