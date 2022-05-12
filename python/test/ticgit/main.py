
def createList(n):
    if (type(n).__name__ != 'int'): return
    list = []
    for count in range(pow(2,n)):
        tmpstr = bin(count)[2:].zfill(4).replace('1', '正').replace('0', '反')
        if tmpstr.endswith('反反'): list.append(tmpstr[:tmpstr.index('反反') + 2])
        elif not '反反' in tmpstr: list.append(tmpstr)
    return list

if __name__ == "__main__":
    print(createList(4))