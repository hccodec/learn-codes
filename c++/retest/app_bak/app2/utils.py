import json, configparser, logging, requests
from sys import stdin, stdout
from lxml import etree

theHeader = {
                "User-Agent":   "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKi"
                                "t/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36"
            }
            
global coolieFName
cookieFName = 'cookies'
     
# 读取 ini 文件
class MyParser(configparser.ConfigParser):

    def optionxform(self, optionstr: str) -> str:
        return optionstr

    def as_dict(self):
        d = dict(self._sections)
        for k in d:
            d[k] = dict(self._defaults, **d[k])
            d[k].pop('__name__', None)
        return d


class BaseUtil:

    def __init__(self) -> None:
        cfg = MyParser()
        cfg.read('hbjCfg.ini', encoding='utf8')
        cfg = cfg.as_dict()
        self.csvName = eval(cfg['基本设置']['结果文件'])
        self.logName = eval(cfg['基本设置']['日志文件'])
        self.dataMap = cfg['数据含义']

    # 获取正确的 cookie，包括重新登录机制
    def getCookies(self):
        count = 0
        cookies = self.readCookies()

        while (cookies == 0 or not self.testLogin()) and count < 3:
            count += 1 # 3 次尝试登录
            if cookies == 0: print(f'cookie文件损坏，重新登录 {count} 次')
            elif not self.testLogin(): print(f'登录过期，重新登录 {count} 次')
            
            self.login()
            cookies = self.readCookies()
        if count == 3:
            logging.error('登录失败')
            print('3 次尝试登录失败，退出')
            exit(-1)
            
        return cookies

    # 本地文件读取 cookie：（貌似只有CASTGC有用）
    # 读取失败则返回 0
    def readCookies(self):
        cookies = ''
        with open(cookieFName, 'r', encoding='utf8') as f:
            try:
                cookies = json.load(f)
            except:
                return 0    
        return cookies

    def readCookiesFromHARFile(self):
        with open(cookieFName, 'r', encoding='utf8') as f:
            cookies = json.load(f)['log']['entries'][1]['request'][cookieFName]

        cookie_dict = {}
        for i in range(len(cookies)):
            s = ''
            if i > 0: s = ' '
            cookie_dict[f"{s}{cookies[i]['name']}"] = cookies[i]['value']
        return cookie_dict

    # cookie 格式修正
    def alterCookieFile(self, cookies:str):
        with open(cookieFName, 'w+', encoding='utf8') as f:
            json.dump(cookies, f)
            f.seek(0, 0)
            s = f.read()
            f.seek(0, 0)
            f.write(s.replace(" ", "").replace('{', "{\n\t").replace('}', "\n}").replace(",", ",\n\t"))

    def initLog(self):
        open(self.logName, 'w').close()
        LOG_FORMAT = "[%(asctime)s][%(levelname)s] %(message)s"
        DATE_FORMAT = "%Y-%m-%d %H:%M:%S %p"
        logging.basicConfig(filename=self.logName, level=logging.INFO, format=LOG_FORMAT, datefmt=DATE_FORMAT)

    def initCSV(self):
        with open(self.csvName, 'w') as csv:
            csv.write('招生单位,院系所,专业,方向,初试(专),初试(公),复试,要求,发布时间,余额,学制\n')
   
    # 模拟登录，更新cookie
    def login(self):
        loginurl = "https://account.chsi.com.cn/passport/login?entrytype=yzgr&service=https://yz.chsi.com.cn/j_spring_cas_security_check"
        data = {
            "username": "15946109336",
            "password": "Hbj123-123",
            "submit": "登  录",
            "lt": [
                "LT-333389-rTZzPjcOexZDKbJHAI5SFSzEwMXu1n-cas",
                "LT-333389-rTZzPjcOexZDKbJHAI5SFSzEwMXu1n-cas"
            ],
            "execution": [
                "33e7f7c8-a24f-4ac8-ba38-fa6a970ce397_ZXlKaGJHY2lPaUpJVXpVeE1pSjkuUlhvMFdUVTJPREJhTm5odlZTdExWREpJTldWVWVtY3JWMUptVkhKSlEyd3plV1ZZYUU5aWQwcGtTRWhSUVZsSVFtdDVVMlV4VW5CSGVVWkVPRTUxV1c1QmREWkNTV0ZUTjNFME5EaFZhalIyY21GdVNUVjVWVkZpY3paVFJFOWxSbUo2Tmk5bFpEUlhWbVpWY0V4VE9FTlFjRVIwVG5GQlp6WlJOMDloWW5veVNEWTBibmRVVUhsbWVHNXhWR3RWZEM5SVFrTlNSblpJWjFoQ2JtbHVhM2t3TjJSd0sxVnFjQzl5WVcxQlRXNTJRMHd3YmxNMFJXaGtZVmRWUkZkdGNtVkZielZpYWxwRlJHMXNVbmxoWkVWUWFHOU9hRkZXWTBZeVpHNXBTMDFIU2tFclZGVm5VR3BpUjFkck9TdDZNREZqVUN0UWEwZzJjM1l6UVZvcmNsaHhUemR5VmxkamRuVnJlSFZKVEVGYU5tdFBSamQ1VFhOc1JHaDZXV2RDTUVoNlMyTTNSM295ZEdSMFZGSnlNazloVGxKeWNXRmxPWE5EY0RoWFYzVldObHBQYVZZeUwwUXdRM0ppTkhRclNuTlpjbVJPTUhoV1ppOU9RWGMzU2t4R09URk9WR3BuTmxVM1dVaHdlR1pXY1ROemRDOVZWbGxZVW1sNFYwbHJWR0pxWmxZd2QyNW1aVWhzSzBab2QxUnpTMlFyVG5wd05FTmtkSFZ6VUVOTVJsRmtPVGxpY1ZOS1FWWlRWMFJUWlU5MlNrdDFWRkp5ZGpVMFYyb3lVa1p6VjFSQ1FtbHNkbWh6U1RjclUwSkJkVVpZY2tOb05FUldRWGgxTlRKYVNEUkhSbm8zTkV4cVJFZEJOemxhZUc0Mk9HSnJhU3N4TjFaS2NVaHhXRkZLVERaU1QyMTZjR0ZNWkdwaVdUZzRabkpYT0ZsNk1uUTVOR2h4TlVOdWVsZG5ObWhQY1VSUFZWUmxjbmhpTkRZME5HTnZMME5IY0RGbU1UVjFVRkZCVmtWUGRIRlhLM2N5Y1VFMlpWcDRjazVpYlZSTVYzWkZUMlZCUW1wcWQxVlRVSFZPY0ZKQ1UzUmlUa05MUTJrdmFGQXZWMVJ5VjFnMlprcFhlVTFtWTBkQ2VIWlZObWxXUm5kYWJYRkdhVnBSTWxGYWJHeHNVMGhLYmxoek0yeGhSaTlsTkdzemRqSXhTbEJuYmpsMk5UVmxVVTFUTkVac1ZUVTRRMlZLVTJWYU1sRjFXbGRQVm05QlMwbEJja1ZWWWpsT1YxWkZXbEZQTUVvMFVGWmlUQ3RyYlZOcWNYbHBRblpSTjNSVmVteHlOM2dyZDNGR2NITmpPVzVGYlhONGRXNUhZM2tyTmxoRWFXeG9WM2t5VjFSRWNFTjVZM0J1V1VkTVNXTmphbmQ2YVhka1JEWkhUVFZaUkcxVmRrMTBaVE0wVkhwU05GSTROSGhFVHpKT1RXMVRXSFJxWmpoT2JHVmpUbkYwU1hSUmIwMVdNbWxVWjFSSmJ6SlBVelZ4TkhWWmFrZFZWVXBFVVZkck9FbEVZVkoyT0RaYU0zWTBTV05RVG5sbldWWnBaa3BOVFdoQ1dtMVNiV0YwUlRCWU5WQmxWMjlsUTA5V2N5ODNRbVJwYVhwcVJVTnRRMnBTTVZGTldqRmpSRmxtV1cxclpETTNUbkI2TkhJMFIzTjZTRWczVDBoQ1JYQmpVa3dyT0hjd1JsZHlXRXBRYzFkNlZqVlFNMDF0T0ZOdFZuUXpUVlZZZVRWV2VESXljbUZwYm1wVk0xaFVSbmhRTWl0UmN6aFRNVEJFYWtRMU5qVkhSM2xoYVVkWmVFSlhNMUF5UzJwclQyMTNNbWR5VkZkb2RuTlFabTVrUVZSdk1FUmpSbEpVVkdneVZWZE9ZMFUzTW5JNU5HTmFNMFJuV0RORldtUk1NVXd3V0dadFVVVnZkemhTYTJKUGRFTjNXRVprWVdScE5GSndSSFZsUldOSlVGbHNNMVE0YzFoNlNIVjRVbFpuU1ZsR1dXNXFWbkpFWVZkc2NHNXhWM1pvVERJMVdUbG1WR3RFUkhvMGF5OWxZbFF4ZEd4SmRGaDBVWEI1UjBaa01WWmtMM2xwU21WRGExTkhSMGx4TlhOU1RsYzFNMHRIU2t4d2FFTkpUelJwYWxWek5VcGpjbGR2Ym01bFJqWmtZemswVlVWRGNVeEVjRzlSTW1zeVdXRjNOR2RIVEU5dE56RkNORlJNUkZacFNGbHRTVVE0Y2xJeU1URkZkRklyYTNCUlFYaHNPV05yYmt0VFNYQjVPVFEwZEVsb0syOWhTVm93Um14R2JVcEhPR2cxUkd4dWVHOVNWMGR2TTBwNVRFSTFRVUYwYzFaTFRtMVpMMDVOUzJoaE9XVjZRWEJKVUVoQ2JHRlFZWEpyVW5WalpVVkhXVXRuUzJKclFuaFJMekpXUzJ0VWFFUnVMemxEZVhodFdVVjBVazE1YUVkcFFXY3lPSE5IVmtwMlEyUlBUbWd6WTNVMlZDOUNUVnAyWjNkcWFqZ3lNR05zTTJOek9ETnhkU3RqY1VneVRtNXdlRUpUTDFkTU5tTllPVkJGWmtOV09GRjFjWGhNTjNSS04yMHlVaTlLUXpGVVZtOWhaR0V4V1VvMFlrOHJiQzlHU1ZOdVNGcDBURGRRZVhCb1ZXMDNZM1ZLWm01M2JGaFJOa1o1ZDA1U1RTOUNOVmRLTmtaelF6bG9jMFpQTVRVMFMxVnpTMWRwYVVselMyOUZkekpEVFZKb04yZzJaamx0UVU5VU5HZHFiVWN5WkRac1RqWXJSek5KUkdoa2RsTjBNU3R6S3pkaFdWTnpiRlpYYW5RMU5rWnFlalJhVkhwMVVYaGpjVGN6ZVc5Sk5HRXpaRWx3V1M5RVptdE5PWHA2ZW5obFZubE5aMHR4WlVKdllWcDZSRTQ1VEU5Rk1sSnlUMmt3Y0dkUldsTkdkelpuU0daSWJFb3ZUMnRpV0VoWlZuaE9PWE5vUkRWNmQzRjZlRTV2VGxwbk1ITjRNa3BoUm5ZdlRGZFZOVUoxVDA4eVVFWjNlbEJNUWtsVWRWVnRlWHAzYkd0V1IzWXlTbmRMYURGbVMwb3hjRU0wYVVOU1VVRlVPRVJZU0dwNFFqQnpTVnBqVjJwWFZERmFLeXRoYUhKV00yaGphSE5UZW14MVRXc3JRa3BYUzFNNFRUSndOVGxIU0Vaa1RqTmtaMlJXWjNCaVN6TmxXbGxwT1ZaUlZFVmpUVTlsVDIxdlkwMW5jelV6WTJwbk5qQkJWVEppVlhRck9VWnRWRVlyVURZMU0xUllTRWN3UVV4aFZFRlRUMkpZV201NVZGQkpUbU4zVTNWTlZGaEJPWHBCVTNCeVFUZDJXWGxzZEVOeVkxWldjMUkzTW0xeVNrVlhOR2M1T1hKVFdXbDZlRmRwVFV4VFpIcFNTRlJLVm1kWk5EWjVPVlZEY0VoSGJsRkViMlprTTAxRVpsaFFZbFoxSzFkUk56aFRWQzlRTUdSalVWVXlabXROYkhGSmIwRnVjVFZET1doRFVVazNiSEJVYTNSWU9XSTRiVkpGZVV4WWFqaHdPR1J1TWxOS1pEUmtTM1JaVkU5a1F5dHRiMmxsY21wcGRHdEtSbTQwV1hKTVIxaHRNMmxKYW10SE5YcFhPV1JvVDNwVk1qWlRaa3MzVURVcmRFMVRNQzlyWjFGelNraHplbWhrYlc1U2VsUjBhSGwyZUZkRVRUVnFhMWRTT0VWNGFFeExUVlI0VGpka2JpOW9lVVoxZW5Vdk9Hc3dOVmxXV2pCV1pTdE1ibkJyTWtkNFlVcFlUemRZVlZwd1dHRklSRXQ2Y2xkd1FYRkJjbmxxYVdkNlQzWlFSUzlaTm5ab1FWcGpabFZYZVdWNVduSlVkSGhKU0VSbk5WTTJVVzVuU2xkalN6ZDJPVWhKUzNkblNqUkRXRzF2UTFoNllVWTJlRXR6TXpCd09VbzBkMnR6WWtSTlQxTlNZME54WWtRNFRraFdWRGMzTW5WT2R6QmxibHA0YkhsRmMzY3ZZM2R4YnpnMVdtTnlWbHA2ZVc1bmJUTjFSVEoyWVd4T1VVSmxhbEY2ZHpOYVdWZHhTR040ZVdKTlJ6UkhhbWx4V0ZOTVVHbFpOekoySzFKUk0xSnpjRWxYWjJ3elVVOUpTM2t2YTNJNGJraFRWekpTVm5kNVdsbzVTRzVHVXpGSWFsbEZURXRDTkZwM1FVUmtMemRXWm5CM1NteFlXbnAzVjJSSVkzcE5aVk5xZFZaa1pGaGhjakZyYTNZM1RYUkxUa0o0WjJKcVpqUnlkMWw1UW1NdlJWaE1iMGxpUnpkUWNFY3ZUekVyVlhwV2J6WjBWWE41UkRscmRuUndkbUpCZDJnM2EwaFFhMDVFU1RSU1VucEJNakY0VVdwS0wwSlhOM3A2YjJrd2NrcFZVRkZhZGtwMGFEaDVRVVU1VEU0cmVrdFNiMU5hTURGdFoxWTBWa1Z2VEhabE0xSlZjMG8wVUVJMVRIaHpkbVY1WkVoT00waFpTRFZZUkhoMlJFdGFia0pCWVRNNWF6WXdUekJPUkM5eGIxUkthVmhDYjNSbk0wUm9OR3RhU0c1Sk1HVnVNMWhKYjBGQlEyUmFVSFV6YUhsVVNFbzBTMmcwTW5SWEwzaEpiVzR6Y1dOU1JsTTVhMHBEWjB0b01IaGhVSEZsUlhablJqZFNjbW8xVm1WVWRVTkVWRFJCVG5sV1dFVmxZemswU1VKYVRVbFllRVIwU0ZoNVZYRkhhMVYyWlVvd2N6ZEVMek14YUhobk5GZDRka1IyYkU5TlRtZFRZMjV4YmpkUFdGVnhOSFZPZG5KbFV6Rk9iWFJuTTJ4WmNWcDNWRWR4TTNnemFuaElZMDlaVXk5M2NVbE9aR2c0YUVka1YyazFTRTVzVFRoTFFtNHpjbTlUYUVOSlpWRTVTM05hZEZOWE1VVnRlVFo0Wkc5RmVrZEZZVmczUVROMU1GVnJNVk5UYjJvNU16ZFdRWEJ1TTBSc1lrcFlZMU5sVlVZd2RFRktXbmN5UTJ4SU4waElOalV6UmtSR2EyOTNRWGhKUTBObVNsRm1hRFpCU0N0aE5uTm5WVk5IUWpoQ1NFNUVTRGt4TTBkbGRHMWxWalZTTkdoRlVIVTBUR296YmtSNlVuSTBRbWxuY25WTVlsZHdRWFpOVjFCU2JtdExORFJKUm1ocU0zaGxRV1FyUTNjcmRHeFBVWFZZY1RaVWFHeHdkRk5TTmtwRGVtcE1NbkYzYkdOUFNuSTNZWGsyTTNWc1UzWjFhbUpzY2pZcmFsSldVbFl2VlZwS05HWlJjVlpwV1VsQ1JVb3dZVmhWTDFCMmFGVnROVzVLU1hwSVJXUmpXazQxWVVKbmJFVjFkVFpwUzI5UWVHWlFNa1ZUS3pWc1ZHSnJiMGR2YWtWNlRVbDBOa3BXY1VORE5sRkVUamhGTldWV1NsWlNNaXRsWldGb1JHOVRSMlZUYzA1cVNWVkJRalJVVlhGdE1ETXhhMlJJVHk5T05UUlpiM1IzUlcwMFVqSnBNbE5rY0dSWFF6ZzFhbE50VWtKR2RGRkRha2RtTkV4MlVuRk9VVnBUYjBGUGRWVlJaM1ZvT0VOVVZESlVlVXB2TWxOT2MwRk5kMU5xV1hoWVZqaFZjV2xhTDBSWlJHRllhVkZZVVZOa0wxUkpWRzl3TW1oQk1VZG5XVzV1YUVKdFRXVTJSek5HSzJSUGRXcHNPVGxQV2tZelFWQkhVMnQyZGtaUU4wUjBNWEpVUW1oTGNHTmhOMEZSZUd3MVRrRXhUWEJKUjJkYVYyRm5UMUZ1VEVaMlVHdDBRamR0TDNOeFUwSllZM1ZSWjNOQ2RsbHhTbWxqYkVocUsxbFZibFUxYUU5NVJGVnBha1ZoV25Wbk1FUkhSbW95Y2s0dlpHRkZUMk00T1dkbWJXNUdSM0ExVUVOaFVFZ3ZOMU5hSzBWUWMwa3lUa1ZqYjJWcU1UbHJZelpvYTBoaWVuVjRUR05KVTFsQ05YYzJRamRqY2tOdFkxWnFkek5xVmtFM00zbE5ibmhIWVVKd1l6QXhTa3RQSzBGWVZrSlZRazF4V1ZaQmVXVXphelpwTTJaVmFHNTRUMnRMZGpsSE9EWjRZWGRSUkVoc01XOUNSMUpCYlV4V04xQnhRMUZMUTJoSFZtZHJja1E1U0hWUlpDOURRMjVuV1ZCU2MyZEpWVmw1YURaRlpWaFZlSFZPTDFKeVpIYzlQUS5JcW93NWIxSF83VDFEd25EZ2tSUXU4Z3p1cmY1LW9vSzUxVDQwZjNFbUF2N0JDWnQtWnJMWUVURnBRc3g2UnRFNFZmaUYwSkFUZmZkbjNJSUxVQ2tNZw==",
                "33e7f7c8-a24f-4ac8-ba38-fa6a970ce397_ZXlKaGJHY2lPaUpJVXpVeE1pSjkuUlhvMFdUVTJPREJhTm5odlZTdExWREpJTldWVWVtY3JWMUptVkhKSlEyd3plV1ZZYUU5aWQwcGtTRWhSUVZsSVFtdDVVMlV4VW5CSGVVWkVPRTUxV1c1QmREWkNTV0ZUTjNFME5EaFZhalIyY21GdVNUVjVWVkZpY3paVFJFOWxSbUo2Tmk5bFpEUlhWbVpWY0V4VE9FTlFjRVIwVG5GQlp6WlJOMDloWW5veVNEWTBibmRVVUhsbWVHNXhWR3RWZEM5SVFrTlNSblpJWjFoQ2JtbHVhM2t3TjJSd0sxVnFjQzl5WVcxQlRXNTJRMHd3YmxNMFJXaGtZVmRWUkZkdGNtVkZielZpYWxwRlJHMXNVbmxoWkVWUWFHOU9hRkZXWTBZeVpHNXBTMDFIU2tFclZGVm5VR3BpUjFkck9TdDZNREZqVUN0UWEwZzJjM1l6UVZvcmNsaHhUemR5VmxkamRuVnJlSFZKVEVGYU5tdFBSamQ1VFhOc1JHaDZXV2RDTUVoNlMyTTNSM295ZEdSMFZGSnlNazloVGxKeWNXRmxPWE5EY0RoWFYzVldObHBQYVZZeUwwUXdRM0ppTkhRclNuTlpjbVJPTUhoV1ppOU9RWGMzU2t4R09URk9WR3BuTmxVM1dVaHdlR1pXY1ROemRDOVZWbGxZVW1sNFYwbHJWR0pxWmxZd2QyNW1aVWhzSzBab2QxUnpTMlFyVG5wd05FTmtkSFZ6VUVOTVJsRmtPVGxpY1ZOS1FWWlRWMFJUWlU5MlNrdDFWRkp5ZGpVMFYyb3lVa1p6VjFSQ1FtbHNkbWh6U1RjclUwSkJkVVpZY2tOb05FUldRWGgxTlRKYVNEUkhSbm8zTkV4cVJFZEJOemxhZUc0Mk9HSnJhU3N4TjFaS2NVaHhXRkZLVERaU1QyMTZjR0ZNWkdwaVdUZzRabkpYT0ZsNk1uUTVOR2h4TlVOdWVsZG5ObWhQY1VSUFZWUmxjbmhpTkRZME5HTnZMME5IY0RGbU1UVjFVRkZCVmtWUGRIRlhLM2N5Y1VFMlpWcDRjazVpYlZSTVYzWkZUMlZCUW1wcWQxVlRVSFZPY0ZKQ1UzUmlUa05MUTJrdmFGQXZWMVJ5VjFnMlprcFhlVTFtWTBkQ2VIWlZObWxXUm5kYWJYRkdhVnBSTWxGYWJHeHNVMGhLYmxoek0yeGhSaTlsTkdzemRqSXhTbEJuYmpsMk5UVmxVVTFUTkVac1ZUVTRRMlZLVTJWYU1sRjFXbGRQVm05QlMwbEJja1ZWWWpsT1YxWkZXbEZQTUVvMFVGWmlUQ3RyYlZOcWNYbHBRblpSTjNSVmVteHlOM2dyZDNGR2NITmpPVzVGYlhONGRXNUhZM2tyTmxoRWFXeG9WM2t5VjFSRWNFTjVZM0J1V1VkTVNXTmphbmQ2YVhka1JEWkhUVFZaUkcxVmRrMTBaVE0wVkhwU05GSTROSGhFVHpKT1RXMVRXSFJxWmpoT2JHVmpUbkYwU1hSUmIwMVdNbWxVWjFSSmJ6SlBVelZ4TkhWWmFrZFZWVXBFVVZkck9FbEVZVkoyT0RaYU0zWTBTV05RVG5sbldWWnBaa3BOVFdoQ1dtMVNiV0YwUlRCWU5WQmxWMjlsUTA5V2N5ODNRbVJwYVhwcVJVTnRRMnBTTVZGTldqRmpSRmxtV1cxclpETTNUbkI2TkhJMFIzTjZTRWczVDBoQ1JYQmpVa3dyT0hjd1JsZHlXRXBRYzFkNlZqVlFNMDF0T0ZOdFZuUXpUVlZZZVRWV2VESXljbUZwYm1wVk0xaFVSbmhRTWl0UmN6aFRNVEJFYWtRMU5qVkhSM2xoYVVkWmVFSlhNMUF5UzJwclQyMTNNbWR5VkZkb2RuTlFabTVrUVZSdk1FUmpSbEpVVkdneVZWZE9ZMFUzTW5JNU5HTmFNMFJuV0RORldtUk1NVXd3V0dadFVVVnZkemhTYTJKUGRFTjNXRVprWVdScE5GSndSSFZsUldOSlVGbHNNMVE0YzFoNlNIVjRVbFpuU1ZsR1dXNXFWbkpFWVZkc2NHNXhWM1pvVERJMVdUbG1WR3RFUkhvMGF5OWxZbFF4ZEd4SmRGaDBVWEI1UjBaa01WWmtMM2xwU21WRGExTkhSMGx4TlhOU1RsYzFNMHRIU2t4d2FFTkpUelJwYWxWek5VcGpjbGR2Ym01bFJqWmtZemswVlVWRGNVeEVjRzlSTW1zeVdXRjNOR2RIVEU5dE56RkNORlJNUkZacFNGbHRTVVE0Y2xJeU1URkZkRklyYTNCUlFYaHNPV05yYmt0VFNYQjVPVFEwZEVsb0syOWhTVm93Um14R2JVcEhPR2cxUkd4dWVHOVNWMGR2TTBwNVRFSTFRVUYwYzFaTFRtMVpMMDVOUzJoaE9XVjZRWEJKVUVoQ2JHRlFZWEpyVW5WalpVVkhXVXRuUzJKclFuaFJMekpXUzJ0VWFFUnVMemxEZVhodFdVVjBVazE1YUVkcFFXY3lPSE5IVmtwMlEyUlBUbWd6WTNVMlZDOUNUVnAyWjNkcWFqZ3lNR05zTTJOek9ETnhkU3RqY1VneVRtNXdlRUpUTDFkTU5tTllPVkJGWmtOV09GRjFjWGhNTjNSS04yMHlVaTlLUXpGVVZtOWhaR0V4V1VvMFlrOHJiQzlHU1ZOdVNGcDBURGRRZVhCb1ZXMDNZM1ZLWm01M2JGaFJOa1o1ZDA1U1RTOUNOVmRLTmtaelF6bG9jMFpQTVRVMFMxVnpTMWRwYVVselMyOUZkekpEVFZKb04yZzJaamx0UVU5VU5HZHFiVWN5WkRac1RqWXJSek5KUkdoa2RsTjBNU3R6S3pkaFdWTnpiRlpYYW5RMU5rWnFlalJhVkhwMVVYaGpjVGN6ZVc5Sk5HRXpaRWx3V1M5RVptdE5PWHA2ZW5obFZubE5aMHR4WlVKdllWcDZSRTQ1VEU5Rk1sSnlUMmt3Y0dkUldsTkdkelpuU0daSWJFb3ZUMnRpV0VoWlZuaE9PWE5vUkRWNmQzRjZlRTV2VGxwbk1ITjRNa3BoUm5ZdlRGZFZOVUoxVDA4eVVFWjNlbEJNUWtsVWRWVnRlWHAzYkd0V1IzWXlTbmRMYURGbVMwb3hjRU0wYVVOU1VVRlVPRVJZU0dwNFFqQnpTVnBqVjJwWFZERmFLeXRoYUhKV00yaGphSE5UZW14MVRXc3JRa3BYUzFNNFRUSndOVGxIU0Vaa1RqTmtaMlJXWjNCaVN6TmxXbGxwT1ZaUlZFVmpUVTlsVDIxdlkwMW5jelV6WTJwbk5qQkJWVEppVlhRck9VWnRWRVlyVURZMU0xUllTRWN3UVV4aFZFRlRUMkpZV201NVZGQkpUbU4zVTNWTlZGaEJPWHBCVTNCeVFUZDJXWGxzZEVOeVkxWldjMUkzTW0xeVNrVlhOR2M1T1hKVFdXbDZlRmRwVFV4VFpIcFNTRlJLVm1kWk5EWjVPVlZEY0VoSGJsRkViMlprTTAxRVpsaFFZbFoxSzFkUk56aFRWQzlRTUdSalVWVXlabXROYkhGSmIwRnVjVFZET1doRFVVazNiSEJVYTNSWU9XSTRiVkpGZVV4WWFqaHdPR1J1TWxOS1pEUmtTM1JaVkU5a1F5dHRiMmxsY21wcGRHdEtSbTQwV1hKTVIxaHRNMmxKYW10SE5YcFhPV1JvVDNwVk1qWlRaa3MzVURVcmRFMVRNQzlyWjFGelNraHplbWhrYlc1U2VsUjBhSGwyZUZkRVRUVnFhMWRTT0VWNGFFeExUVlI0VGpka2JpOW9lVVoxZW5Vdk9Hc3dOVmxXV2pCV1pTdE1ibkJyTWtkNFlVcFlUemRZVlZwd1dHRklSRXQ2Y2xkd1FYRkJjbmxxYVdkNlQzWlFSUzlaTm5ab1FWcGpabFZYZVdWNVduSlVkSGhKU0VSbk5WTTJVVzVuU2xkalN6ZDJPVWhKUzNkblNqUkRXRzF2UTFoNllVWTJlRXR6TXpCd09VbzBkMnR6WWtSTlQxTlNZME54WWtRNFRraFdWRGMzTW5WT2R6QmxibHA0YkhsRmMzY3ZZM2R4YnpnMVdtTnlWbHA2ZVc1bmJUTjFSVEoyWVd4T1VVSmxhbEY2ZHpOYVdWZHhTR040ZVdKTlJ6UkhhbWx4V0ZOTVVHbFpOekoySzFKUk0xSnpjRWxYWjJ3elVVOUpTM2t2YTNJNGJraFRWekpTVm5kNVdsbzVTRzVHVXpGSWFsbEZURXRDTkZwM1FVUmtMemRXWm5CM1NteFlXbnAzVjJSSVkzcE5aVk5xZFZaa1pGaGhjakZyYTNZM1RYUkxUa0o0WjJKcVpqUnlkMWw1UW1NdlJWaE1iMGxpUnpkUWNFY3ZUekVyVlhwV2J6WjBWWE41UkRscmRuUndkbUpCZDJnM2EwaFFhMDVFU1RSU1VucEJNakY0VVdwS0wwSlhOM3A2YjJrd2NrcFZVRkZhZGtwMGFEaDVRVVU1VEU0cmVrdFNiMU5hTURGdFoxWTBWa1Z2VEhabE0xSlZjMG8wVUVJMVRIaHpkbVY1WkVoT00waFpTRFZZUkhoMlJFdGFia0pCWVRNNWF6WXdUekJPUkM5eGIxUkthVmhDYjNSbk0wUm9OR3RhU0c1Sk1HVnVNMWhKYjBGQlEyUmFVSFV6YUhsVVNFbzBTMmcwTW5SWEwzaEpiVzR6Y1dOU1JsTTVhMHBEWjB0b01IaGhVSEZsUlhablJqZFNjbW8xVm1WVWRVTkVWRFJCVG5sV1dFVmxZemswU1VKYVRVbFllRVIwU0ZoNVZYRkhhMVYyWlVvd2N6ZEVMek14YUhobk5GZDRka1IyYkU5TlRtZFRZMjV4YmpkUFdGVnhOSFZPZG5KbFV6Rk9iWFJuTTJ4WmNWcDNWRWR4TTNnemFuaElZMDlaVXk5M2NVbE9aR2c0YUVka1YyazFTRTVzVFRoTFFtNHpjbTlUYUVOSlpWRTVTM05hZEZOWE1VVnRlVFo0Wkc5RmVrZEZZVmczUVROMU1GVnJNVk5UYjJvNU16ZFdRWEJ1TTBSc1lrcFlZMU5sVlVZd2RFRktXbmN5UTJ4SU4waElOalV6UmtSR2EyOTNRWGhKUTBObVNsRm1hRFpCU0N0aE5uTm5WVk5IUWpoQ1NFNUVTRGt4TTBkbGRHMWxWalZTTkdoRlVIVTBUR296YmtSNlVuSTBRbWxuY25WTVlsZHdRWFpOVjFCU2JtdExORFJKUm1ocU0zaGxRV1FyUTNjcmRHeFBVWFZZY1RaVWFHeHdkRk5TTmtwRGVtcE1NbkYzYkdOUFNuSTNZWGsyTTNWc1UzWjFhbUpzY2pZcmFsSldVbFl2VlZwS05HWlJjVlpwV1VsQ1JVb3dZVmhWTDFCMmFGVnROVzVLU1hwSVJXUmpXazQxWVVKbmJFVjFkVFpwUzI5UWVHWlFNa1ZUS3pWc1ZHSnJiMGR2YWtWNlRVbDBOa3BXY1VORE5sRkVUamhGTldWV1NsWlNNaXRsWldGb1JHOVRSMlZUYzA1cVNWVkJRalJVVlhGdE1ETXhhMlJJVHk5T05UUlpiM1IzUlcwMFVqSnBNbE5rY0dSWFF6ZzFhbE50VWtKR2RGRkRha2RtTkV4MlVuRk9VVnBUYjBGUGRWVlJaM1ZvT0VOVVZESlVlVXB2TWxOT2MwRk5kMU5xV1hoWVZqaFZjV2xhTDBSWlJHRllhVkZZVVZOa0wxUkpWRzl3TW1oQk1VZG5XVzV1YUVKdFRXVTJSek5HSzJSUGRXcHNPVGxQV2tZelFWQkhVMnQyZGtaUU4wUjBNWEpVUW1oTGNHTmhOMEZSZUd3MVRrRXhUWEJKUjJkYVYyRm5UMUZ1VEVaMlVHdDBRamR0TDNOeFUwSllZM1ZSWjNOQ2RsbHhTbWxqYkVocUsxbFZibFUxYUU5NVJGVnBha1ZoV25Wbk1FUkhSbW95Y2s0dlpHRkZUMk00T1dkbWJXNUdSM0ExVUVOaFVFZ3ZOMU5hSzBWUWMwa3lUa1ZqYjJWcU1UbHJZelpvYTBoaWVuVjRUR05KVTFsQ05YYzJRamRqY2tOdFkxWnFkek5xVmtFM00zbE5ibmhIWVVKd1l6QXhTa3RQSzBGWVZrSlZRazF4V1ZaQmVXVXphelpwTTJaVmFHNTRUMnRMZGpsSE9EWjRZWGRSUkVoc01XOUNSMUpCYlV4V04xQnhRMUZMUTJoSFZtZHJja1E1U0hWUlpDOURRMjVuV1ZCU2MyZEpWVmw1YURaRlpWaFZlSFZPTDFKeVpIYzlQUS5JcW93NWIxSF83VDFEd25EZ2tSUXU4Z3p1cmY1LW9vSzUxVDQwZjNFbUF2N0JDWnQtWnJMWUVURnBRc3g2UnRFNFZmaUYwSkFUZmZkbjNJSUxVQ2tNZw=="
            ],
            "_eventId": [
                "submit",
                "submit"
            ]
        }
        session = requests.Session()
        session.post(loginurl, headers=theHeader, data=data)
        self.alterCookieFile(session.cookies.get_dict()) # 登录完成后更新 cookie

    # 访问用户中心测试登录
    def testLogin(self):
        url='https://yz.chsi.com.cn/user/center.jsp'
        
        cookies = BaseUtil().readCookies() # 用本地 cookie 尝试登录
        if cookies == '0':
            print('本地无 cookie')
            return False
        page = self.get_page(url=url, cookies=cookies)
        pageTitle = etree.HTML(page).xpath('/html/head/title/text()')[0]
        res = pageTitle == '用户中心_中国研究生招生信息网'
        if not res: logging.info(f'测试网址网页标题: {pageTitle}')
        return pageTitle == '用户中心_中国研究生招生信息网'
            
    # 获取指定url网页内容
    def get_page(self, url, cookies=None):
        try:
            r = requests.get(url, headers=theHeader, cookies=cookies)
            r.raise_for_status()
            r.encoding = r.apparent_encoding
            return r.text
        except Exception as e:
            print(f"get_page: ", e)
            logging.error(f"get_page: ", e)

if __name__ == '__main__':
    if BaseUtil().testLogin():
        print("貌似已登录")
    else:
        print("不能登录")
        # logging.error("尝试重新登录")
        # login()
        # if testLogin():
        #     print("登录成功")

# 直接输出
class _Getch:
    """Gets a single character from standard input.  Does not echo to the
screen."""
    def __init__(self):
        try:
            self.impl = _GetchWindows()
        except ImportError:
            self.impl = _GetchUnix()
 
    def __call__(self):
        print('不建议调用，建议直接使用 get() 方法')
        return self.__repr__()

    def __repr__():
        print("请调用 get() 方法获取值")

    def get(self):
        return str(self.impl(), 'utf-8')

    
 
class _GetchUnix:
    def __init__(self):
        import tty, sys
 
    def __call__(self):
        import sys, tty, termios
        fd = sys.stdin.fileno()
        old_settings = termios.tcgetattr(fd)
        try:
            tty.setraw(sys.stdin.fileno())
            ch = sys.stdin.read(1)
        finally:
            termios.tcsetattr(fd, termios.TCSADRAIN, old_settings)
        return ch
 
class _GetchWindows:
    def __init__(self):
        import msvcrt
 
    def __call__(self):
        import msvcrt
        return msvcrt.getch()