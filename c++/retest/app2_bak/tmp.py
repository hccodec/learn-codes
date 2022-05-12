#!/usr/bin/python
# -*- coding: UTF-8 -*-

import sys, getopt

def main():
    
    try:
        opts, args = getopt.getopt(sys.argv[1:],"d")
    except getopt.GetoptError:
        print('参数有误，请重新输入')
        return 0

    if opts == [] and args == []: return 0
    for opt, arg in opts:
        if opt == '-d': return 1
    for arg in args:
        if arg == 'd': return 1
    return 0


if __name__ == "__main__":
    main()