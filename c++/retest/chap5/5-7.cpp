#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int A[], int n) {
    printf("\n{");
    for (int i = 0; i < n; i++) {
        printf("%2d", A[i]);
        if (n - i - 1) printf(" ");
        else printf("}");
    }
}

// 递减冒泡排序
void bubble(int A[], int n) {
    int tmp;
    n--;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n - i; j++)
            if (A[j] < A[j + 1]) {
                tmp = A[j];
                A[j] = A[j + 1];
                A[j + 1] = tmp;
            }
}

int binSearch(int A[], int n, int target) {
    int start = 0, end = n - 1, mid;
    while (start <= end) {
        mid = start + ((end - start) >> 1);
        if (A[mid] < target) end = mid - 1;             // 中间数大，说明目标数在左边，往左找
        else if (A[mid] > target) start = mid + 1;      // 中间数小，说明目标数在右边，往右找
        else return mid;
    }
    return -1;
}

int main() {
    srand((unsigned int)time(NULL));
    printf("折半查找\n");
    int target = 0, res = 0;
    while (1) {
        int len = 15;
        int *A = (int *)malloc(sizeof(int) * len);
        A[0] = 1; for (int i = 1; i < len; i++) A[i] = A[i - 1] + rand() % 10;
        bubble(A, len);
        printA(A, len);

        printf("\n请输入要查找的值: ");
        scanf("%d", &target);
        if (!target) break;
        res = binSearch(A, len, target);
        if (res + 1) printf("%d 在第 %d 位", target, res);
        else printf("未找到\n");
        printf("\n");
    }
}