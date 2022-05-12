/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-08 19:32:47
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-08 19:58:04
 */
#include <iostream>

double getLeft(double a, double b) {
    if (!b) {
        printf("出问题，除数不应为 0\n");
        return 0;
    }
    if (a <= b) return 0;
    while (a >= b) a -= b;
    return a;
}

double LCM(double a, double b) {
    double tmp = a * b;
    if (a < b) {
        a += b;
        b = a - b;
        a -= b;
    }
    double LCM = getLeft(a, b);
    // 辗转相除法 找 最大公因数
    while (LCM > 1e-5) {
        a = b;
        b = LCM;
        LCM = getLeft(a, b);
    }
    // printf("得出最大公约数为 %lf\n", b);
    return tmp / b;
}

double LCM(double *a, double *b, double *c, int mode = 1) {
    double res = LCM(LCM(*a, *b), *c);
    *a = res / *a;
    *b = res / *b;
    *c = res / *c;
    if (mode) LCM(a, b, c, 0);
    return res;
}

int main() {
    double a = 0.4;
    double b = 1;
    double c = 1.5;
    double d[3] = {a, b, c};
    double res;
    printf("%lf、%lf 和 %lf 的最小公倍数（LCM）是", a, b, c);
    res = LCM(&a, &b, &c);
    printf(" %lf, ", res);
    printf("结果为 %lf, %lf, %lf", a, b, c);
    return 0;
}
