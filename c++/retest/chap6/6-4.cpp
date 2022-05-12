/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-16 23:41:31
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-17 09:15:58
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int A[], int n) {
    printf("\n{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}");
}

int main() {
    printf("删除值相同的元素");
    srand((unsigned int)time(NULL));
    int n = 8, t = 0, i = 0, j = 0, k = 0;
    int *a = (int*)malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++) a[i] = rand() % n;
    // int a[n] = {1, 2, 3, 1, 1, 4, 2, 5};

    printA(a, n);

    for (i = 0; i < n; i++) {
        k = 0;
        for (j = i + 1; j < n; j++) {
            if (a[j] == a[i]) k++;
            else a[j - k] = a[j];
        }
        n -= k;
    }
    printA(a, n);
    return 0;
}