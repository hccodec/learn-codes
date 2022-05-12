import traceback
import requests, logging, utils.utils as utils, json
from lxml import etree
from tqdm import tqdm


class Retrive:

    def __init__(self, mode = 0):
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
                    self.total = int(text['data']['vo_list']['pagenation']['totalCount'])

                    # ---- 询问是否获取 ----
                    if self.mode == 0:
                        print(f'共有 {self.total} 条数据，是否获取：[(Y/y/yes)] ')
                        input = utils._Getch().get()
                        def isYes(input): return input == 'y' or input == 'Y' or input == '\r'
                        if not isYes(input): return -1
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

    def __init__(self, dwdm, yxsdm, zydm, yjfxdm, util = None):
        self.dwdm = str(dwdm).zfill(5)            # 单位代码
        self.yxsdm = str(yxsdm).zfill(3)          #  院系所代码
        self.zydm = str(zydm).zfill(6)            # 专业代码
        self.yjfxdm = str(yjfxdm).zfill(2)        # 研究方向代码
        if util is None: self.util = utils.BaseUtil()
        else: self.util = util

    # 初试科目查询 URL
    def getUrl(self):
        return f"https://yz.chsi.com.cn/zsml/kskm.jsp?id={self.dwdm}21{self.yxsdm}{self.zydm}{self.yjfxdm}1"

    def getUniversityName(self):
        return etree.HTML(self.get_page(self.getUrl())).xpath('//*[@class="zsml-summary"]/text()')[0][7:]


    def getInfo(self, url = None):

        # 检查传入的参数。若未传入则用 getUrl() 获取，若已传入则直接用传入的 url
        if url is None: url = self.getUrl()
        

        # 获取省份及其代码
        # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getSs.jsp").text))
        # # 获取大类
        # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getMl.jsp").text))
        # # 获取所有的大类下的专业
        # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getZy.jsp").text))
        # 获取专业的方向
        # mcdm(requests.post("https://yz.chsi.com.cn/zsml/code/zy.do", data={"q":"0835"}).text)
        page= self.util.get_page(self.getUrl())
        selector = etree.HTML(page)

        with open("tmp.html", 'w', encoding = 'utf8') as f:
            f.write(page)
                
        if  selector.xpath('//*[@class="zsml-res-items"]/tr/td[1]/text()') == [] or\
            selector.xpath('//*[@class="zsml-res-items"]/tr/td[2]/text()') == [] or\
            selector.xpath('//*[@class="zsml-res-items"]/tr/td[3]/text()') == [] or\
            selector.xpath('//*[@class="zsml-res-items"]/tr/td[4]/text()') == []:
                if url[-3:-1] == '00':
                    return PreTest(self.dwdm, self.yxsdm, self.zydm, '01', util=self.util).getInfo()
                elif url[-3:-1] == '01':
                    return PreTest(self.dwdm, self.yxsdm, self.zydm, 'Z1', util=self.util).getInfo()
                else:
                    logging.error(f"{self.getUniversityName()} 出错 ({url})")
                    raise Exception(f"{self.getUniversityName()} {url[-19:]}")
            
        info = {}
        infokey = selector.xpath('//thead/tr/th/text()')
        infoval = [selector.xpath(f'//*[@class="zsml-res-items"]/tr/td[{x + 1}]/text()')[0].replace('\r', '').replace('\n', '').replace(' ', '') for x in range(4)]
        info = dict(zip(infokey, infoval))
        
        fininfo = {}
        fininfokeys = ['专业课','公共课']

        commomLesson = ''

        if '201' in info['外语']: commomLesson += '英一'
        elif '204' in info['外语']: commomLesson += '英二'
        else: raise Exception(f"出错 {info['外语']}")
        if '301' in info['业务课一']: commomLesson += '数一'
        elif '302' in info['业务课一']: commomLesson += '数二'
        else: raise Exception(f"未识别: {info['业务课一']}")

        fininfo = dict(zip(fininfokeys, [info['业务课二'], commomLesson]))
        return fininfo

    # 获取当前专业目录
    def getDirects(self):
        return self.get_page(f"https://yz.chsi.com.cn/zsml/querySchAction.do?dwmc={self.getUniversityName()}&yjxkdm={self.zydm}")

if __name__ == '__main__':
    res = PreTest('10338', '005', '083500', '01')
    # page= res.get_page(res.getUrl())
    # selector = etree.HTML(page)
    # print(selector.xpath('//*[@class="zsml-summary"]/text()')[0][7:])
    print(res.getUniversityName())
    print(res.getInfo())
    selector = etree.HTML(res.get_page(res.getUrl()))

    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[1]/text()'))
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[2]/text()'))
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[3]/text()'))
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[4]/text()'))

    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[1]/text()') == [])
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[2]/text()') == [])
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[3]/text()') == [])
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[4]/text()') == [])

    page = res.getDirects()
    selector = etree.HTML(page)
    with open("tmp.html", 'w', encoding='utf8') as f:
        f.write(page)
    print(len(selector.xpath('//tbody/tr')))
    print(selector.xpath('//tbody/tr/td/text()')[0])

    # print(res.getDirects())
    # if res.getUrl()[-3:-1] == '01': print("含有 01")

def myEval(str:str):
    return str.replace('\r', '').replace('\n', '').replace(' ', '').replace(',', ', ')