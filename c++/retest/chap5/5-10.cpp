#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// 输出数组
void printA(int A[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(" ");
        else printf("}\n");
    }
}

int main() {
    printf("将数组逆序输出\n");
    srand((unsigned int)time(NULL));
    int len = 10;
    int A[len];
    for (int i = 0; i < len; i++) A[i] = rand() % 100; // 随机生成数组元素值

    printA(A, len);
    printf("{");
    while (len) { printf("%d", A[--len]); if (len) printf(" "); else printf("}"); }

    return 0;
}