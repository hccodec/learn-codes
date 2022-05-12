#include <stdio.h>

void swap(int *a, int *b) {
    *a += *b;
    *b = *a - *b;
    *a -= *b;
}

inline float equals(float a, float b) {
    return a > b ? a - b < 1e-5 : b - a < 1e-5;
}

float GCD(float a, float b) {
    if (equals(b, 0)) return equals(a, 0) ? 1 : a;
    if (a < b) {
        a += b;
        b = a - b;
        a -= b;
    }
    while (a >= b) a -= b;
    return GCD(b, a);
}

int main() {
    printf("递归求最大公约数");
    while (1) {
        printf("\n依次输入两个数：");
        float a = 0, b = 0;
        scanf("%f", &a);
        if (!a) {
            printf("收到，退出");
            break;
        }
        scanf("%f", &b);
        if (!b) {
            printf("收到，退出");
            break;
        }
        fflush(stdin);
        printf("%.10f", GCD(a, b));
    }
    return 0;
}