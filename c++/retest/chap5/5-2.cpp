/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: length22-02-15 10:44:41
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-15 16:36:46
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int *A, int length) {
    printf("{");
    for (int i = 0; i < length; i++) {
        if (A[i] & 1) printf("\e[1;31;44m%2d", A[i]);
        else printf("\e[1;0;41m%2d", A[i]);
        if (length - i - 1) printf(" ");
        else printf("\e[0m}");
    }
}

int main() {
    printf("将数组划分为左\e[1;31;44m奇\e[0m右\e[1;41m偶\e[0;0m\n\n");
    const int thr = 15, length = 20;
    int i = 0, j = 0, tmp = 0; // 工作指针
    char input[1] = {0};
    int *A = (int *)malloc(sizeof(int) * length);
    
    srand((unsigned int)time(NULL));
    for (int i = 0; i < length; i++) A[i] = rand() % thr;   // 用工作指针 i 为数组随机赋值

    printA(A, length);
    
    for (int i = 0, j = length - 1; i < j; i++, j--) {
        while (A[i] & 1) i++;                   // 右移 i 直到 i 指偶数
        while (!(A[j] & 1)) j--;                // 左移 j 直到 j 指奇数

        if (i >= j) break;
        
        // swap
        tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    printf("\n\n");                      // 用工作指针 i 扫描并输出一次
    
    printA(A, length);


    return 0;
}