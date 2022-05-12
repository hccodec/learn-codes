import logging, utils.utils as utils, utils.mysqlUtil as mysqlUtil, dataRetrieve, time, getopt, sys

from PyQt5.QtCore import *

def getMode():
    
    try:
        opts, args = getopt.getopt(sys.argv[1:],"d")
    except getopt.GetoptError:
        print('参数有误，请重新输入')
        return -1

    if opts == [] and args == []: return 0
    for opt, arg in opts:
        if opt == '-d': return 1
    for arg in args:
        if arg == 'd': return 1
        else: return -1
    return 0

def main(_signal:pyqtSignal = None):

    utils.BaseUtil().initLog()

    mode = getMode()

    obj = dataRetrieve.Retrive(getMode(), _signal)
    res = obj.retrieve_data()
    if res == -1:
        print('取消获取数据')
        exit()
    print(f"\n共获取 {res} 条记录，正在转存至数据库")
    print('===================')
    
    # mysql = mysqlUtil(tableschema='调剂情况')
    # mysqlUtil.mysqlUtil().csv2mysql()
    # logging.info('===================')

if __name__ == "__main__":
    main()
