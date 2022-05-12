@echo off

cd cqueue
gcc -fPIC -shared -g *.c -o libcqueue.so

cd ..
set CFLAGS="-I`pwd`/cqueue/"
set LDFLAGS="-L`pwd`/cqueue/"
python setup.py build_ext --inplace

set LD_LIBRARY_PATH=`pwd`/cqueue/ python test.py
