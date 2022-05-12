@echo off

@REM  set -xe

python setup.py build_ext --inplace

python test.py
