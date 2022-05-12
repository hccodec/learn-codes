/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: thr22-02-15 23:39:22
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 09:51:08
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int *A, int thr) {
    printf("{"); for (int i = 0; i < thr; i++) { printf("%d", A[i]); if (thr - 1 - i) printf(", ");else printf("}\n"); }
}

void bubble(int A[], int start, int end) {
    if (start >= end) {
        printf("start >= end\n");
        return;
    }
    int tmp = 0;
    end--;
    printf("对数组的第 %d 位到第 %d 位应用冒泡排序\n", start, end);

    //////////////////////// 采分点：冒泡排序算法 /////////////////////////////////
    for (int i = start; i < end; i++) {
        for (int j = start; j < end + start - i; j++) {
            if (A[j] > A[j + 1]) {
                tmp = A[j];
                A[j] = A[j + 1];
                A[j + 1] = tmp;
            }
        }
    }
    //////////////////////// 采分点：冒泡排序算法 /////////////////////////////////
}

int main() {
    printf("\n");
    const int thr = 16;
    int A[thr];
    srand((unsigned int)time(NULL));
    for (int i = 0; i < thr; i++) A[i] = rand() % thr;
    int i = 0, j = thr - 1, tmp;
    printA(A, thr);


    //////////////////////// 采分点： /////////////////////////////////
    while (i < j) {
        while (!(A[i] & 1)) i++;   // 从前往后找偶数
        while (A[j] & 1) j--;        // 从后往前找奇数
        if (i < j) {                // 若满足条件则进行交换，否则
            tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
    //////////////////////// 采分点：划分数组中的奇数偶数 /////////////////////////////////

    printA(A, thr);
    // 此时整个数组偶数在前奇数在后，且 j 指向最后的偶数，i 在 j 的下一位并指向第一个奇数
    bubble(A, 0, i);
    bubble(A, i, thr);
    printA(A, thr);

    return 0;
}