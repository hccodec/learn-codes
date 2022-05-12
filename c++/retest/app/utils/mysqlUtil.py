import utils.utils as utils
import logging
import pymysql, traceback


class mysqlUtil():

    def __init__(self, tableschema='test', tablename='soft') -> None:
        self.tableschema = tableschema
        self.tablename = tablename
        self.conn:pymysql.connections.Connection = ''

        try: self.conn = pymysql.connect(host='localhost',port=3306,user='root',password='Hbj123-123',db=tableschema,charset='utf8')
        except Exception as e: print(e)

        self.cursor = self.conn.cursor()
        logging.warning(f'数据库 `{self.tableschema}` 已连接')

    def __repr__(self):
        return f'<我是一个用来访问数据库 `{self.tableschema}` 的操作对象>'

    def __del__(self):
        self.cursor.close()
        self.conn.close()
        logging.warning('已断开连接')

    def readCSVData(self):
        util = utils.BaseUtil()
        data = []
        count = 0
        with open(util.csvName, 'r') as f:
            for line in f.readlines():
                data.append(line[:-1].split(','))
                count += 1
        return data

    # 过程：先创建临时表tmp并把 csv 内容存入，再将表 soft 的“复试”“要求”转移到tmp中，然后分别把soft和tmp重命名为soft_bak和soft
    def csv2mysql(self):

        def list2sql(data, num = 0):
            res = '('
            for i,item in enumerate(data[num]):
                if i > 0: res += ','
                if num == 0:
                    res += f'`{item}`'
                else:
                    if item == '': res += f"NULL"
                    else: res += f"'{item}'"
            res += ')'
            return res

        data, cursor, conn = self.readCSVData(), self.cursor, self.conn

        try: cursor.execute("drop table tmp;")
        except: logging.debug('临时表不存在，创建临时表')
        # 创建临时表
        code = "create table if not exists tmp ("
        for i,item in enumerate(data[0]):
            if i > 0: code += ','
            code += f"`{item}` varchar(255)"
        code += ');'
        cursor.execute(code)

        logging.info('已创建临时表')
        print('已创建临时表')

        code = ''
        for i in range(len(data)):
            if i == 0: continue
            try:
                cursor.execute(f"insert into `tmp` {list2sql(data, 0)} values {list2sql(data, i)}")
            except:
                traceback.print_exc()
                conn.rollback()

        logging.info('已将csv中数据转移到数据库中')
        print('已转移到临时数据库')

        # 从原表{self.tablename}转移“复试”和”要求“到临时表中
        source, target = f'{self.tablename}', 'tmp'
        code = f"update `{target}` inner join `{source}` on \
            `{target}`.`招生单位` = `{source}`.`招生单位` and `{target}`.`院系所` = `{source}`.`院系所` and \
            `{target}`.`专业` = `{source}`.`专业` and `{target}`.`方向` = `{source}`.`方向` \
            set `{target}`.`复试`=`{source}`.`复试`, `{target}`.`要求`=`{source}`.`要求`, `{target}`.`学制`=`{source}`.`学制`"
        try:
            cursor.execute(code)
        except:
            traceback.print_exc()
            conn.rollback()

        logging.info('已将“复试”和“要求”转移至临时数据库')
        print('已将“复试”和“要求”转移至临时数据库')

        # 备份原表{self.tablename}为{self.tablename}_bak，重命名新表tmp为{self.tablename}即可
        try: cursor.execute(f"drop table `{self.tablename}_bak`")
        except: logging.debug('无备份表')
        try:
            cursor.execute(f"alter table `{self.tablename}` rename `{self.tablename}_bak`")
            cursor.execute(f"alter table `tmp` rename `{self.tablename}`")
            conn.commit()
            logging.info('已完成原表备份和临时表更名，请手动替换原数据')
            print('已完成原表备份和临时表更名，请手动替换原数据')
        except:
            traceback.print_exc()
            conn.rollback()


if __name__ == '__main__':
    logging.basicConfig(level=logging.INFO)
    mysql = mysqlUtil()
    # mysql = mysqlUtil(tableschema='调剂情况')
    data = mysql.csv2mysql()