
# _*_ encoding : utf-8 _*_

from ctypes import *

import os
# add_path = os.path.split("D:\\documents\\learn-codes\\c++\\undergraduate\\test\TestClass.dll\\")
os.add_dll_directory("D:\\documents\\learn-codes\\c++\\undergraduate\\test\\")
pyt = cdll.LoadLibrary("D:\\documents\\learn-codes\\c++\\undergraduate\\test\\TestClass.dll")

pyt.print_msg("hello,my shared object used by python!")

print("4+5=%s" %pyt.add_Integer(4,5))
