/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-18 22:55:22
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-18 23:03:51
 */
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void printA(int a[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}\n");
}

int main() {
    printf("所有整数重复两次\n");
    srand((unsigned int)time(NULL));
    const int n = 10;
    // printf("%d", ((n >> 1) - 1) << 1); return 0;
    int a[50] = {0};
    for (int i = 0; i < (n >> 1); i++) a[i] = rand() % 100 - 50;
    printA(a, n >> 1);
    for (int i = (n >> 1) - 1; i >= 0; i--) {
        a[i << 1] = a[i];
        a[(i << 1) + 1] = a[i];
    }
    printA(a, n);
    return 0;
}