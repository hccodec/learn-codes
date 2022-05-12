from ctypes import *
import ctypes, os
import logging as logger
from typing import *

# ctypes.cdll.LoadLibrary(os.getcwd())
lib = CDLL("./main.so", winmode=0)

class LNode(Structure):
    _fields_ = [
        ("data", c_int),
        ("next", c_int)
    ]
    def __repr__(self) -> str:
        return  f"Node({self.data})"

class SingleLinkedList(object):
    def __init__(self):
        self.head = None
    def is_empty(self):
        return self.head is None


class Student(Structure):
    _fields_ = [
        ("name", c_char * 30),
        ("fScore", c_float * 3)
    ]

# p = POINTER(LNode)

su=Student()
su.name = "韩宝佳".encode()
PARAM = c_float * 3
fScore = PARAM()
fScore = 95.1, 93.2, 91.3
su.fScore = fScore

if __name__ == "__main__":
    n1 = LNode(); n1.data = 3
    n2 = LNode(); n2.data = 3
    print(n1)
    print("第一个元素为{}".format(n1.data))
    
    # # lib.InitList.restype = [LNode]
    # n1 = lib.InitList().data
    # print(n1)
    # CreateList().PrintList()
    # lib.main()
    # lib.Display.argtypes = [Student]
    # lib.Display(su)
    # lib.InitList.argtypes = [LNode]
    # lib.CreateList.argtypes = [LNode]
    # lib.PrintList.argtypes = [LNode]
    # L = byref(n1)
    # if lib.InitList(L): print("链表完成初始化")
    # else: print("链表无法初始化")
    # if lib.CreateList(L): print("链表完成初始化")
    # if lib.PrintList(L): print("链表完成初始化")

import struct
import binascii
 
values = (1, b'good', 1.22) #查看格式化对照表可知，字符串必须为字节流类型。
s = struct.Struct('I4sf')
packed_data = s.pack(*values)
unpacked_data = s.unpack(packed_data)
  
print('Original values:', values)
print('Format string :', s.format)
print('Uses :', s.size, 'bytes')
print('Packed Value :', binascii.hexlify(packed_data))


