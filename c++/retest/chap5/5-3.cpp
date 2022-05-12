/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-15 16:52:39
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-15 17:21:39
 */
#include <stdio.h>
#include <stdlib.h>

int main() {
    printf("数组元素交换\n");
    int m = 0, n = 0; // m 行 n 列
    int thr = 20, tmp;
    while (1) {
        scanf("%d%d", &m, &n);
        fflush(stdin);
        if (!m || !n) break;

        // 给数组分配空间
        int **A = (int **)malloc(sizeof(int *) * m);
        for (int i = 0; i < m; i++) A[i] = (int *)malloc(sizeof(int) * n);

        // 随机赋值
        for (int i = 0; i < m * n; i++) A[i / n][i % n] = rand() % thr;
        
        // 输出数组
        printf("----------------\n");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                printf("%5d ", A[i][j]);
            }
            printf("\n");
        }

        printf("----------------\n");

        // 用一维数组下标访问的方式 将数组内的元素 首尾交换
        for (int i = 0; i < m * n / 2; i++) {
            tmp = A[i / n][i % n];
            A[i / m][i % m] = A[(m * n - i - 1) / n][(m * n - i - 1) % n];
            A[(m * n - i - 1) / n][(m * n - i - 1) % n] = tmp;
        }

        // 输出数组
        printf("----------------\n");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                printf("%5d ", A[i][j]);
            }
            printf("\n");
        }
        printf("----------------\n");
    }
    return 0;
}