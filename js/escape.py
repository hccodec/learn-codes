import html, re

datapat = re.compile(r"&#x.{4};")

def unescape(x):
    return html.unescape(x.group())

print('小工具：将文本文件中形如“&#x____”的unicode字符转化成汉字')

with open('layer.js', 'r+',encoding='utf-8') as f:
    lines = f.read()
    f.seek(0)
    n, lines = datapat.subn(unescape, lines)
    f.write(lines)

# [print(html.unescape(x), end='') for x in a]
