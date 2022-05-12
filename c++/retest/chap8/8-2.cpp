#include <stdio.h>
#include <stdlib.h>

void printA(int a[], int n) {
    printf("=================");
    for (int i = 0; i < n * n; i++) {
        if (!(i % n)) printf("\n");
        printf("%2d ", a[i]);
    }
    printf("\n=================");
}

int main() {
    printf("输出1-n²魔方阵(n为奇数)\n");
    int n, i = 0, j = 0;
    scanf("%d", &n);
    while (!(n & 1)) {
        if (!n) return 0;
        printf("n应为奇数，重新输入：");
        scanf("%d", &n);
    }
    ////////////////////////////////////////////////////
    int **a = (int**)malloc(sizeof(int*)*n);
    for (int i = 0; i < n; i++)
        a[i] = (int*)malloc(sizeof(int)*n);
    int *printa = (int*)malloc(sizeof(int)*n*n);

    for (int i = 0; i < n * n; i++) a[i / n][i % n] = 0;         // 魔方阵初始化为全 0

    i = 0, j = n / 2;
    a[i][j] = 1;
    for (int k = 2; k <= n * n; k++) {
        if (a[(i - 1 + n) % n][(j + 1 + n) % n]) a[++i][j] = k;
        else {
            i = (i - 1 + n) % n;
            j = (j + 1 + n) % n;
            a[i][j] = k;
        }
    }

    for (int i = 0; i < n * n; i++) printa[i] = a[i / n][i % n];
    printA(printa, n);

    ////////////////////////////////////////////////////


    return 0;
}