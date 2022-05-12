/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-15 09:16:51
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-15 10:43:45
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int *A, int n, int m) {
    for (int i = 0; i < 3 * n; i++) printf("-");
    for (int i = 0; i < m; i++) {
        printf("\n");
        for (int j = 0; j < n; j++) printf("%2d ", A[i * n + j]);
    }
    printf("\n");
    for (int i = 0; i < 3 * n; i++) printf("-");
}



int main() {
    printf("求 N * M 二维整形数组的\e[32m平均值\e[0m和\e[32m最大值\e[0m\n");
    int threshold = 10, n = 0, m = 0, max = 0;
    float avg = 0;
    while (1) {
        printf("请输入 N（列数）和M（行数），程序将随机生成0-%d的元素：", threshold);
        scanf("%d%d", &n, &m);
        int A[n * m] = {0};
        avg = 0, max = 0;
        srand((unsigned int)(time(NULL)));
        if (!n || !m) break;
        for (int i = 0; i < n * m; i++) A[i] = rand() % threshold;
        for (int i = 0; i < n * m; i++) {
            avg += (float)A[i];
            if (A[i] > max) max = A[i];
        }
        avg /= n * m;
        printA(A, n, m);
        printf("\n平均值和最大值分别为 %.2f 和 %d\n", avg, max);
    }
    return 0;
}