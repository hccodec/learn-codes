#include <stdio.h>
#include <string.h>
#include <typeinfo.h>

int main() {
    
    double i = 0.34;
    float f = 1.2;
    int a = 4;

    printf(typeid(10 + 'a' + i * f).name());

    return 0;
}