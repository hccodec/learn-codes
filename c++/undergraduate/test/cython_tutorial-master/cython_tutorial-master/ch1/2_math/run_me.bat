@echo off

cd cmath
gcc -fPIC -shared -g *.c -o libcmath.so

cd ../pymath
set CFLAGS="-I`pwd`/../cmath/"
set LDFLAGS="-L`pwd`/../cmath/"
python setup.py build_ext --inplace

set LD_LIBRARY_PATH=`pwd`/../cmath/
python test.py
