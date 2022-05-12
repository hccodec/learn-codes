#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int a[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}\n");
}

void bubble(int a[], int n) {
    n--;
    int tmp;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n - i; j++)
            if (a[j] > a[j + 1]) {
                tmp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = tmp;
            }
}

// 遍历 b 的每一个元素 b[j] 并在 a 中找到插入位置 i 并插入
int mergearr(int a[], int m, int b[], int n) {
    int i = 0, len = m;
    for (int j = 0; j < n; j++) {
        if (a[0] > b[j]) {
            for (int i = len; i > 0; i--) a[i] = a[i - 1];
            a[0] = b[j];
        }                                                   // b[j] 最小，放头部
        else if (a[len - 1] < b[j]) a[len] = b[j];          // b[j] 最大，放尾部
        else for (int i = 1; i < len; i++) {                // b[j] 在里面
            if (a[i - 1] <= b[j] && a[i] >= b[j]) {         // 放到 i 处
                for (int j = len; j > i; j--) a[j] = a[j - 1];
                a[i] = b[j];
                break;
            }
        }
        len++;
    }
    return len;
}

int main() {
    printf("递增合并:\n");
    srand((unsigned int)time(NULL));
    const int n = 10;
    int A[n << 1] = {0}, B[n + 2] = {0}, res = 0;
    for (int i = 0; i < n - 2; i++) A[i] = rand() % 50;
    for (int i = 0; i < n + 2; i++) B[i] = rand() % 50;
    bubble(A, n - 2);
    bubble(B, n + 2);
    printA(A, n - 2);
    printA(B, n + 2);
    res = mergearr(A, n - 2, B, n + 2);
    printf("结果：\n", res);
    printA(A, res);


    return 0;
}