#include <iostream>
#include <string>
#include <cstdlib>
#include <vector>
#include <stdio.h>
#include <windows.h>

class Test{
    private:
        double _calculate(int a, double b);
    public:
        double calculate(int a, double b, char c[], int * d, double * e, char ** f);
};

double Test::_calculate(int a, double b){
    double res = a+b;
    std::cout<<"res: "<<res<<std::endl;
    return res;
}

double Test::calculate(int a, double b, char c[], int * d, double * e, char ** f){
    std::cout<<"a 的值为 "<<a<<std::endl;
    std::cout<<"b 的值为 "<<b<<std::endl;
    std::cout<<"c 的值为 "<<c<<std::endl;
    std::cout<<"d 的值为 "<<d[0]<<d[1]<<std::endl;
    std::cout<<"e 的值为 "<<e[0]<<e[1]<<std::endl;
    std::cout<<"f 的值为 "<<f[0]<<f[1]<<std::endl;
    return this->_calculate(a, b);
}


// 封装C接口
extern "C"{
// 创建对象
    Test* test_new(){
        return new Test;
    }
    double my_calculate(Test* t, int a, double b, char c[], int * d, double * e, char ** f){
        return t->calculate(a, b,c,d,e,f);
    }
}

// gcc -fPIC -shared great_module.c -D MS_WIN64 -I"D:/Users/84697/anaconda3/envs/manim/include" -L"D:/Users/84697/anaconda3/envs/manim/libs" -lpython38 -o a.so

// g++ -shared -I"D:/Users/84697/anaconda3/envs/manim/include" -L"D:/Users/84697/anaconda3/envs/manim/libs" -lpython38 -o test.so -fPIC test.cpp