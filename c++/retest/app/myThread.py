from logging import error, log
import time, pymysql, traceback
from list import MyMainListItem
import app

from utils.tools import _log, utils

from PyQt5.QtCore import *

class msg:

    @classmethod
    # 0 类消息，不传数据
    def msg0(cls, num:int, message = None):
        obj = cls()
        obj.type = 0;
        obj.num = num
        obj.data = message
        return obj

    @classmethod
    # 1 类消息，传输数据
    # 若 data_order 有值，则默认该数据代表表头元素
    def msg1(cls, data:tuple, data_order = None):
        obj = cls()
        obj.type = 1;
        obj.data = data;
        obj.data_order = data_order;
        return obj
        # item = MyMainListItem(num, data)
        # self.listWidget.addItem(item)
        # self.listWidget.setItemWidget(item, item.widget)
        # self.listWidget.itemClicked.connect(lambda item: print(item.ui.label_proName.text()))


class MyThread(QThread):

    _signal : pyqtSignal = pyqtSignal(msg)
    
    def __init__(self):
        super(MyThread, self).__init__()

    def init(self, util:utils):
        self.util = util
        
    
    def run(self):
        app.main(self._signal)
        # visitmysql(self)

    def __del__(self):
        self.wait()

def visitmysql(self:MyThread):
    
    tableschema='调剂情况'
    tablename='可以考虑'
    conn=''
    self._signal.emit(msg.msg0(30))
    try:
        # 连接数据库
        conn = pymysql.connect(
            host='localhost',
            port=3306,
            user='root',
            password='Hbj123-123',
            db=tableschema,
            charset='utf8'
        )
    except Exception as e:
        # 连接数据库失败 -2
        self._signal.emit(msg.msg0(-2, "连接数据库失败"))
        return


    self._signal.emit(msg.msg0(60))

    cursor = conn.cursor()

    ########### 获取表头 ###########
    res = cursor.execute("select column_name from information_schema.columns where table_schema='{}' and table_name='{}'".format(tableschema, tablename))
    data = cursor.fetchall()
    res = cursor.execute("select ordinal_position from information_schema.columns where table_schema='{}' and table_name='{}'".format(tableschema, tablename))
    datai = cursor.fetchall()

    self._signal.emit(msg.msg1(data, datai))

    ########### 获取数据 ###########

    sql = f"SELECT * FROM {tablename};"
    res = cursor.execute(sql)
    data = cursor.fetchall()
    
    self._signal.emit(msg.msg1(data))

    ########### 获取数据 ###########

    
    cursor.close()
    conn.close()

    if res:
        self._signal.emit(msg.msg0(100))
    else:
        self._signal.emit(msg.msg0(-1, f"登陆失败 {res}"))

    self._signal.emit(msg.msg0(0))
