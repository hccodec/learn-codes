#include <stdio.h>

int calculate(int n) {
    if (n) return n % 10 + calculate(n / 10);
    else return 0;
}

int main() {
    printf("递归计算给定整数每一位数字之和");
    int n = 0;
    while (1) {
        printf("请输入数字：");
        scanf("%d", &n);
        if (!n) break;
        printf("%d\n", calculate(n));
    }
    return 0;
}