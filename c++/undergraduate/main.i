/* File: main.i */
%module main

//用来生成指针
%include "cpointer.i"
//用来生成数组
%include "carrays.i"

%pointer_class(long, long_p);
%array_class(int, intArray);

%include "main.h"

%{
#include "main.h"
%}