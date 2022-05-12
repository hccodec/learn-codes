#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printN(int a[], int n) {
    for (int i = 0; i < n; i++) printf("%d", a[i]);
}

void judge(int a[], int n, int *n1, int *n0) {
    int l1 = 1, l0 = 1; // 分别是平台1和平台0的计数
    for (int i = 1; i < n; i++) {
        if (a[i] - a[i - 1]) l0 = l1 = 1; // 平台统计结束，计数赋值为1
        else a[i] ? l1++ : l0++;          // 平台统计中
        if (*n1 < l1) *n1 = l1;   // 监听平台1的计数，随时获取最大值
        if (*n0 < l0) *n0 = l0;  // 监听平台0的计数，随时获取最大值
     }
}

int main() {
    printf("分别输出输出由 0 1 构成的序列中，1 平台的最长长度 N1 和 0 平台的最长长度 N0\n");
    srand((unsigned int)time(0));
    int n = 15; // S 的长度
    int a[n], i = 0, N1 = 0, N0 = 0, count = 4;
    while (count--) {
        i = 0, N1 = 0, N0 = 0;
        if (1) {
            while (n - i)  a[i++] = rand() % 2;
        }
        else {
            n = 20;
            char s[n] = "00010111001110001111";
            while (n - i) a[i++] = s[i] - '0';
        }


        printN(a, n);
        judge(a, n, &N1, &N0);
        printf(", N1 = %d, N0 = %d\n", N1, N0);
    }
    return 0;
}