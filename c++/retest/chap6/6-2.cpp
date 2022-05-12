/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-16 22:47:35
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 23:05:38
 */
#include <stdio.h>
#include <string.h>

void printA(int a[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (n - i - 1) printf(" ");
        else printf("}");
    }
}

// 将 tar 值移到数组后面
void move(int A[], int n, int tar = 0) {
    int i = 0, j = 0, tmp = 0;
    // 开始
    while (i < 7)
        if (A[i]) i++; // 从前往后找 0
        else {
            if (j == tar) j = i;
            while (!A[j]) j++; // 从 i 往后找非 0 元素
            if (j >= 7) break;
            tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }

}

int main() {
    printf("将非负整数的 0 移到数组后面，其他元素次序不变\n");
    int A[7] = {0, 0, 2, 0, 0, 5, 1};
    int i = 0, j = 0; // i 指针用于查找前面的 0，j 指针用于查找后面的非 0 元素
    int tmp;
    printA(A, 7);
    // move(A, 7);

    // 更优解，非零元素前移，i 用于指向非零元素前移的目的地，j 用于向后扫描非零元素
    while (A[i]) i++;
    j = i;                 // i 和 j 指向第一个 0 所在位置
    while (j < 7) {
        while (!A[j]) j++; // 向后寻找 0 后面的非 0 元素
        if (j >= 7) break; // 若已经遍历完数组，则退出循环
        A[i++] = A[j];     // 将非 0 元素放到指针 i 处并后移 i 指针
        A[j++] = 0;        // 将 j 处置为 0 并后移 j 指针
    }
    /////////////////////////////////////////////////////////////////////////
    
    printf("\n");
    printA(A, 7);

    return 0;
}