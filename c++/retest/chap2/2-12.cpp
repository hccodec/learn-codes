/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 15:54:12
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 16:22:05
 */
#include <stdio.h>

struct Complex {
    int a;
    int b;
    Complex operator+(Complex C2) {return { this->a + C2.a, this->b + C2.b};};
    Complex operator-(Complex C2) {return { this->a - C2.a, this->b - C2.b};};
    Complex operator*(Complex C2) {return { this->a * C2.a - this->b * C2.b, this->a * C2.b + this->b * C2.a};};
};

int main() {
    printf("复数加减乘运算\n");
    int a = 0, b = 0, c = 0, d = 0;
    char x = '+';
    scanf("(%d+%di)%c(%d+%di)", &a, &b, &x, &c, &d);
    Complex C1 = {a, b}, C2 = {c, d}, C = {0, 0};
    switch(x) {
        case '+':   C = C1 + C2;
        case '-':   C = C1 - C2;
        case '*':   C = C1 * C2;
        default:    break;
    }
    printf("%d+%di %c %d+%di = ", a, b, x, c, d);
    if (C.b == 0) printf("%d", C.a);
    else if (C.a == 0) printf("%di", C.b);
    else printf("%d+%di", C.a, C.b);
    return 0;
}