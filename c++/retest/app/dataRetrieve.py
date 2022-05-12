from getopt import getopt
import sys
import traceback, myThread
import requests, logging, utils.utils as utils, json
from lxml import etree
from tqdm import tqdm
from PyQt5.QtCore import *

def myEval(self):
    return self.replace('\r', '').replace('\n', '').replace(' ', '').replace(',', ', ')

class Retrive:

    def __init__(self, mode = 0, signal : pyqtSignal = None):
        self.signal = signal
        self.mode = mode
        self.util = utils.BaseUtil()
        self.total = 1
        self.csvName, self.dataMap = self.util.csvName, self.util.dataMap
        self.util.initCSV()
        
    # 获取所有数据
    def retrieve_data(self, url='https://yz.chsi.com.cn/sytj/stu/tjyxqexxcx.action'):
        
        cookies = self.util.getCookies()
        self.pbar:tqdm = ''
        
        logging.info('成功登录')
        print('成功登录')

        # content 存放返回的数据结果
        content, count, i = '', 0, 0
        
        while i * 20 < self.total:
            start = ''
            if i > 0: start = i * 20
            para = { 'pageSize': 20, 'start': start, 'orderBy':'' , 'ssdm': '', 'xxfs': '1', 'zymc': '软件工程', 'data_type': 'json', 'agent_from':'wap', 'pageid': 'tjyx_qe_list'}
            try:
                # 只在这里进行网络请求
                r=requests.post(url,headers=utils.theHeader,timeout=30,data=para, cookies=cookies)
                r.raise_for_status()
                r.encoding='utf-8'
                text=json.loads(r.text)
                # 从返回包中获取表格中的信息
                content=text['data']['vo_list']['vos']

                if i == 0:
                    # 获取数据总数
                    try:
                        self.total = int(text['data']['vo_list']['pagenation']['totalCount'])
                    except:
                        print('')

                    # ---- 询问是否获取 ----
                    if self.mode == 0:
                        print(f'共有 {self.total} 条数据，是否获取：[(Y/y/yes)] ')
                        input = utils._Getch().get()
                        def isYes(input): return input == 'y' or input == 'Y' or input == '\r'
                        if not isYes(input):
                            if self.signal == None: return -1
                            self.signal.emit()
                    # ---- 询问是否获取 ----

                    self.pbar = tqdm(total=self.total, desc='正在获取数据')


            except Exception as e:
                traceback.print_exc()
                logging.error(e)

            i += 1
            count = self.save(content, count)

        del self.pbar
        return count

    # 将获取到的数据转成一页
    def parse_one_page(self, content):

        for item in content:
            dict = {}
            for x in self.dataMap.keys():
                # eval函数很重要，可以去掉ini中可能出现的双引号
                dict[eval(self.dataMap[x])] = item[x]
            yield dict
            
    def save(self, content, baseCount:int):
        type_dict = {}
        type_dict['1'] = "全日制" 
        type_dict['2'] = "非全日制"
        count = 0

        for i, item in enumerate(self.parse_one_page(content)):
            with open(self.csvName, 'a') as csv:
                csv.write(
                    item['单位代码']+item['单位名称'] + ',' + item['院系所代码']+item['院系所名称'] + ',' +
                    item['专业代码']+item['专业名称'] + ',' + item['研究方向代码']+item['研究方向名称'] + ','
                )

                # 进一步从另外的地方获取初试科目
                # if content[0]['yjfxdm'] == '00': content[0]['yjfxdm'] = '01'
                try:
                    info = PreTest(content[i]['dwdm'], content[i]['yxsdm'], content[i]['zydm'], content[i]['yjfxdm'], util=self.util).getInfo()
                    csv.write(info['专业课']+','+info['公共课']+',')
                except Exception as e:
                    traceback.print_exc()
                    csv.write('None'+','+'None'+',')
                finally:
                    csv.write(',,' + str(item['发布时间'])+','+str(item['余额人数'])+',\n')


            count += 1
            # print(f'正在取得数据 {baseCount + count} ...',end='\r')
            self.pbar.update(1)
        return count + baseCount


class PreTest:

    def __init__(self, dwdm, yxsdm, zydm, yjfxdm = '00', util = None):
        self.dwdm = str(dwdm).zfill(5)            # 单位代码
        self.yxsdm = str(yxsdm).zfill(3)          #  院系所代码
        self.zydm = str(zydm).zfill(6)            # 专业代码
        self.yjfxdm = str(yjfxdm).zfill(2)        # 研究方向代码
        self.selector, self.page = None, ''         # 网页和选择器
        if util is None: self.util = utils.BaseUtil()
        else: self.util = util

    # 初试科目查询 URL
    def getUrl(self):
        return f"https://yz.chsi.com.cn/zsml/kskm.jsp?id={self.dwdm}21{self.yxsdm}{self.zydm}{self.yjfxdm}1"

    def getUniversityName(self):
        self.retrieve_selector()
        return etree.HTML(self.util.get_page(self.getUrl())).xpath('//*[@class="zsml-summary"]/text()')[0][7:]

    # 获取目标页面对应的网页元素选择器
    def retrieve_selector(self):
        if not self.selector is None: return self.selector
        url = self.getUrl()
        self.page= self.util.get_page(url)
        self.selector = etree.HTML(self.page)
        if  self.selector.xpath('//*[@class="zsml-res-items"]/tr/td[1]/text()') == [] or\
            self.selector.xpath('//*[@class="zsml-res-items"]/tr/td[2]/text()') == [] or\
            self.selector.xpath('//*[@class="zsml-res-items"]/tr/td[3]/text()') == [] or\
            self.selector.xpath('//*[@class="zsml-res-items"]/tr/td[4]/text()') == []:
                self.selector = None
                if url[-3:-1] == '00':
                    self.yjfxdm = '01'
                    return self.retrieve_selector()
                elif url[-3:-1] == '01':
                    self.yjfxdm = 'Z1'
                    return self.retrieve_selector()
                else:
                    raise Exception("未查到相关信息")
        return self.selector

    def getInfo(self):

        selector = self.retrieve_selector()
        
        # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getSs.jsp").text)) # 获取省份及其代码        
        # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getMl.jsp").text)) # # 获取大类        
        # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getZy.jsp").text)) # # 获取所有的大类下的专业        
        # mcdm(requests.post("https://yz.chsi.com.cn/zsml/code/zy.do", data={"q":"0835"}).text) # 获取专业的方向
        info, infoval = {}, []
        infokey = selector.xpath('//thead/tr/th/text()')
        for x in range(4):
            try: infoval.append(myEval(selector.xpath(f'//*[@class="zsml-res-items"]/tr/td[{x + 1}]/text()')[0]))
            except Exception as e:
                raise Exception(f"失败(){x}，请检查生成的HTML文件, {self.getUrl()}")
        info = dict(zip(infokey, infoval))
        
        fininfo = {}
        fininfokeys = ['专业课','公共课']

        commomLesson = ''

        if '201' in info['外语']: commomLesson += '英一'
        elif '204' in info['外语']: commomLesson += '英二'
        elif '202' in info['外语']: commomLesson += '俄语'
        else: raise Exception(f"未识别外语科目：{info['外语']}")
        if '301' in info['业务课一']: commomLesson += '数一'
        elif '302' in info['业务课一']: commomLesson += '数二'
        else: raise Exception(f"未识别: {info['业务课一']}")

        fininfo = dict(zip(fininfokeys, [info['业务课二'], commomLesson]))
        return fininfo

    # 获取当前专业目录
    def getDirects(self):
        return self.util.get_page(f"https://yz.chsi.com.cn/zsml/querySchAction.do?dwmc={self.getUniversityName()}&yjxkdm={self.zydm}")

if __name__ == '__main__':
    args = []
    if not getopt(sys.argv[1:], '')[1] == []: args = getopt(sys.argv[1:], '')[1]
    while 1:
        if args.__len__() == 0:
            msg = input("> \033[38m")
            if (msg == 'exit'): exit()
            if (msg == 'clear' or msg == 'cls'): import os; os.system('cls')
            args = msg.split(' ')
        print("\033[0m")
        if args.__len__() < 3: print('请依次输入院校、学院、专业(默认083500)、方向(默认00、01、Z1)的代码'); args = []; continue
        print("【 \033[1;35m请稍候……\033[0m 】", end='')
        res = PreTest(*args)
        try:
            info = res.getInfo()
            print(f"\r【 \033[1;32m{res.dwdm} {res.getUniversityName()}\033[0m 】 \033[1;33m{info['专业课']}\033[0m ({info['公共课']})")
        except Exception as e:
            print(f"\r【 \033[1;31m{e}\033[0m 】")
        args = []
