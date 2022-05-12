/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-15 17:23:37
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 09:08:19
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void bubbleSort(int *A, int n) {
    int tmp = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - i - 1; j++)
            if (A[j] > A[j + 1]) {
                tmp = A[j];
                A[j] = A[j + 1];
                A[j + 1] = tmp;
            }
    }
}

void printArray(int *A, int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%2d", A[i]);
        if (n - i - 1) printf(", ");
        else printf("} ");
    }
}

int main() {
    printf("将两个递增数组递增排序\n");
    const int thr = 100;
    int i = 0, j = 0, k = 0;
    int A[10], B[10], C[20] = {0};
    srand((unsigned int)time(NULL));
    for (int i = 0; i < 10; i++) {
        A[i] = rand() % thr;
        B[i] = rand() % thr;
    }

    bubbleSort(A, 10);
    bubbleSort(B, 10);

    printArray(A, 10);
    printf("\n");
    printArray(B, 10);

    while (i < 10 && j < 10) {
        C[k++] = A[i] > B[j] ? B[j++] : A[i++];
    }
    while (i < 10) C[k++] = A[i++];
    while (j < 10) C[k++] = B[j++];

    printf("\n");
    printArray(C, 20);
    return 0;
}
