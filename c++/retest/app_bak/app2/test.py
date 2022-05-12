import configparser, requests, json, pandas as pd
from lxml import etree

class MyParser(configparser.ConfigParser):

    def optionxform(self, optionstr: str) -> str:
        return optionstr

    def as_dict(self):
        d = dict(self._sections)
        for k in d:
            d[k] = dict(self._defaults, **d[k])
            d[k].pop('__name__', None)
        return d

def testCfg():
    cfg = MyParser()
    cfg.read('数据含义及用例.ini', encoding='utf8')
    res = cfg.as_dict()['数据含义']
    [print(res[x], x) for x in res.keys()]

def testLogin():
    url = "https://account.chsi.com.cn/passport/login;jsessionid=76ABE7F3DC9C09EECB884D02037F570D?entrytype=yzgr&service=https://yz.chsi.com.cn/j_spring_cas_security_check;jsessionid=E94DCDC353D80BD457618BDA71F3FDC6"
    headers = {
        "User-Agent":   "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKi"
                            "t/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36",
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
    }
    para = {
        'username': '15946109336',
        'password': 'Hbj123-12',
        'submit': '登  录'
    }

    r=requests.post(url,headers=headers,timeout=30,data=para)
    print(r.text)

def testjson():
    cookie_dict = {}
    cookie_dict1 = {}
    with open('cookie.json', 'r', encoding='utf8') as f:
        cookies = json.load(f)['log']['entries'][1]['request']['cookies']
        
        for i in range(len(cookies)):
            cookie_dict[cookies[i]['name']] = cookies[i]['value']
    print(cookie_dict)
    cookietxt = "JSESSIONID=4432E2615EB38E70AE4AF456137AF268; acw_tc=2f6fc11616487300036391562e1131caa5931dc4479f5d96f5d620c329f5b2; _ga=GA1.3.1126710470.1648730004; _gid=GA1.3.1010062982.1648730004; _ga_YZV5950NX3=GS1.1.1648730107.1.1.1648731608.0; aliyungf_tc=c06c09d657c635b9d3835189b218f34e248df12bd966177973b5935721849d1e; JSESSIONID=43274F3DB9206F219888F78EDEB91D1E; XSRF-CCKTOKEN=9b32f06bb6e9617124fda9fae1f12ccb; CHSICC_CLIENTFLAGYZ=dcc1b03b2c2616ef5ff19597134bef76; _gat_gtag_UA_100524_4=1; CHSICC_CLIENTFLAGSYTJ=301a6a6a705098b9e6abcc7756a34209"
    for i in cookietxt.split('; '):
        v = i.split('=')
        cookie_dict1[v[0]] = v[1]
    print(cookie_dict1)

def mcdm(text):
    print(text)
    text = json.loads(eval(text))
    res = "\n"
    for i in range(len(text)):
        if i > 0: res += ', '
        if i % 10 == 0: res += '\n'
        res += text[i]['dm'] + text[i]['mc']
    return res


def testBaseData():


    # 获取省份及其代码
    # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getSs.jsp").text))
    # # 获取大类
    # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getMl.jsp").text))
    # # 获取所有的大类下的专业
    # print(mcdm(requests.post("https://yz.chsi.com.cn/zsml/pages/getZy.jsp").text))
    # 获取专业的方向
    res = ''
    # mcdm(requests.post("https://yz.chsi.com.cn/zsml/code/zy.do", data={"q":"0835"}).text)
    url = "https://yz.chsi.com.cn/zsml/kskm.jsp?id=1000421002083500011"
    theHeader = {
            "User-Agent":   "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKi"
                            "t/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36"
        }
    selector = etree.HTML(utils.BaseUtil().get_page(url, theHeader))
    info = {}
    print(selector.xpath('//thead/tr/th/text()'))
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[1]/text()')[0].replace('\r', '').replace('\n', '').replace(' ', ''))
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[2]/text()')[0].replace('\r', '').replace('\n', '').replace(' ', ''))
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[3]/text()')[0].replace('\r', '').replace('\n', '').replace(' ', ''))
    print(selector.xpath('//*[@class="zsml-res-items"]/tr/td[4]/text()')[0].replace('\r', '').replace('\n', '').replace(' ', ''))
    # info['专业课'] = selector.xpath('//*[@class="zsml-res-items"]/tr/td[4]/text()')[0].replace('\r', '').replace('\n', '').replace(' ', '')


    
if __name__ == '__main__':
    testBaseData()