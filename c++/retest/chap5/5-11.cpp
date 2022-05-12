/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-16 19:01:17
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 20:10:54
 */
#include <stdio.h>

// 输出数组
void printA(int A[], int n) {
    printf("\n{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(" ");
        else printf("}\n");
    }
}

int main() {
    printf("数组右移\n");
    int A[20] = {0}, i = 0, tmp;
    while (20 - i) A[i++] = i;
    printA(A, 20);
    tmp = A[--i]; // 暂存最后一位元素
    while (i) A[i--] = A[i];
    A[0] = tmp;
    printA(A, 20);

    return 0;
}