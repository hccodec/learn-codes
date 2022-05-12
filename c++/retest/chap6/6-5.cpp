#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void printA(int A[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}\n");
}

int main() {
    printf("求每行最大元素构成的向量 A 和每列最小元素 B 构成的向量的乘积 C\n");
    srand((unsigned int)time(NULL));
    int A[10][10] = {0}, B[10] = {0}, C[10] = {0}, t1, t2, res = 0;
    for (int i = 0; i < 100; i++) A[i / 10][i % 10] = rand() % 10; // 生成数组 A


    // 得出数组 B 和 C
    for (int i = 0; i < 10; i++) {
        t1 = 0, t2 = 10;
        for (int j = 0; j < 10; j++) {
            if (A[i][j] > t1) t1 = A[i][j]; // 在第 i 行中扫描得出最大值
            if (A[j][i] < t2) t2 = A[j][i]; // 在第 i 列中扫描得出最小值
        }
        B[i] = t1;
        C[i] = t2;
    }

    // 打印数组ABC
    printf("-----------------------------\n");
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) printf("%d ", A[i][j]);
        printf("\n");
    }
    printf("-----------------------------\n");
    printA(B, 10);
    printA(C, 10);

    // 求元素乘积之和
    for (int i = 0; i < 10; i++) res += B[i] * C[i];

    printf("%d\n", res);

    return 0;
}