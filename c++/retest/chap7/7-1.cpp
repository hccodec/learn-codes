/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-18 00:17:15
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-18 00:18:53
 */
#include <stdio.h>

void printA(int A[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(", ");
    }
    printf("}\n");
}

int judge(int A[], int n) {
    int len = 1, max = 0;
    for (int i = 1; i < n; i++) {
        if (A[i] == A[i - 1]) len++;
        else len = 1;
        if (len > max) max = len;
    }
    return max;
}

int main() {
    printf("判断饱和平台及其最大长度\n");
    const int n = 12;
    int A[n] = {2, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 0};
    printA(A, n);
    printf("%d", judge(A, n));
    return 0;
}