import os, random
os.system('cls')

name = 'Eric'
print('=============================================')
# 打印字符串，其中名字从变量获取
print(f"\033[1;32m[2-3]\033[0m Hello {name}, would you like to learn some Python today?")
# 输出名字的全小写、全大写和首字母大写形式
print(f"\033[1;32m[2-4]\033[0m 小写: {name.lower()}  大写: {name.upper()}  首字母大写: {name.title()}")
# 输出带引号的字符串
print(f"\033[1;32m[2-5]\033[0m Albert Einstein once said, \"A person who never made a mistake never tried anything new.\"")
name = 'Albert Einstein'
print(f"\033[1;32m[2-6]\033[0m {name} once said, \"A person who never made a mistake never tried anything new.\"")
name = '\n\t' + name + '  \t'
print(f"\033[1;32m[2-7]\033[0m 原【{name}】去左【{name.lstrip()}】去右【{name.rstrip()}】去两边【{name.strip()}】")
print(f"\033[1;32m[2-8]\033[0m {3 + 5} { 13 - 5} {2 * 4} {16 / 2}")
print("\033[1;32m[2-9]\033[0m {}".format(f"My favorite number is {random.randint(1, 100)}"))
print()
