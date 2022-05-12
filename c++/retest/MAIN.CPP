#include <iostream>
#include <typeinfo>
using namespace std;

#define SEXT(x, len) ({ struct { int64_t  n : len; } __x = { .n = x }; (int64_t)__x.n; })

int main() {
    int x = 3;
    (double)x;
    printf(typeid(x).name());
    return 0;
}