/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: thr22-02-16 23:06:39
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 23:41:03
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int A[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}");
}

int del(int A[], int n, int x, int y) {
    if (x > y) return -1;
    int k = 0; // k 为偏移量
    for (int i = 0; i < n; i++) {
        if (i < n - k) {
            while (A[i + k] >= x && A[i + k] <= y) k++; //如果已判定 i + k 位该删，则判定位向后一直偏移，直到遇到不该删的元素
            A[i] = A[i + k];
        }
        else A[i] = 0;
    }
    return n - k;
}

int main() {
    // printf("\n");
    const int thr = 40;
    int A[thr] = {0};
    srand((unsigned int)time(NULL));
    for (int i = 0; i < thr; i++) A[i] = 30 + i;
    // for (int i = 0; i < thr; i++) A[i] = rand() % 100;
    int x = rand() % 100, y = x + rand() % (100 - x);
    printA(A, thr);
    printf("\n删除 %d 到 %d 之间的元素:\n", x, y);
    int d = del(A, thr, x, y);
    printA(A, d);
    return 0;
}