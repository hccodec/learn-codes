/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-18 18:54:39
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-18 22:25:01
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

const int size = 5, thr = 100, bias = 0;

void printA(int a[][size], int size, int res) {
    printf("\n---------------");
    for (int i = 0; i < size * size; i++) {
        if (!(i % size)) printf("\n");
        printf(res == a[i / size][i % size] ? "\e[1;32m%2d\e[0m " : "%2d ", a[i / size][i % size]);
    }
    printf("\n---------------");
}

int main() {
    printf("求%d*%d矩阵中频度(出现次数)最高的数", size, size);
    clock_t start = clock();
    srand((unsigned int)time(0));
    int a[size][size], count = 1, max = 0; // max记录a数组中最大值
    int bmax = 0, res = 0; // 统计数组b中的最大值bmax 所在下标res。res即a中频度最大的值
    while (count--) {
        printf("\n");
        max = 0;
        // 生成含有随机值的5*5数组a，并得出a中最大值max，用于生成用于记录频度的（即值与下标对应的）b数组
        for (int i = 0; i < size * size; i++) {
            a[i / size][i % size] = rand() % thr + bias;
            if (max < a[i / size][i % size]) max = a[i / size][i % size];
        }
        max++;
        int *b = (int*)malloc(sizeof(int)*(max)); for (int i = 0; i < max; i++) b[i] = 0; // 定义一个长度比a数组最大值大1的数组b，并初始化为全0，用于记录a中元素出现次数

        for (int i = 0; i < size * size; i++) b[a[i / size][i % size]]++;                   // 采用数组下标访问的方式记录元素出现次数

        for (int i = 0; i < max; i++)
            if (bmax < b[i]) {
                bmax = b[i]; // bmax记录b中最大值（a中最大频度值）
                res = i;     // res记录b的下标（a中对大频度元素的值）
            }
        if (bmax - 1) printf("频度最高的数为\e[32m%d\e[0m(%d/%d)", res, bmax, size * size);
        else printf("没有频度最高的数");
        printA(a, size, bmax - 1 ? res : -1);
    }
    clock_t end = clock();
    printf("\n运行用时 %.1f ms", (float)(end - start));

    return 0;
}