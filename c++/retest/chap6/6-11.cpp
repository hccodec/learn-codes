#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int a[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}");
}

int del(int a[], int n, int item) {
    int i = 0, k = 0;
    for (int i = 0; i < n; i++)
        if (a[i] == item) k++;
        else a[i - k] = a[i];
    return n - k;
}

int main() {
    printf("删顺序存储的线性表中值为 item 的元素\n");
    srand((unsigned int)time(NULL));
    const int n = 20;
    int a[n] = {0}, item, res = 0;
    while (1) {
        for (int i = 0; i < n; i++) a[i] = rand() % 20;
        printA(a, n);
        printf(". 请输入要删除的元素：");
        scanf("%d", &item);
        if (!item) break;
        res = del(a, n, item);
        printA(a, res);
        printf("\n\n");
    }

    return 0;
}