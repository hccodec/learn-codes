/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-16 22:14:19
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 22:32:03
 */
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void printA(int a[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (n - i - 1) printf(" ");
        else printf("}\n");
    }
}

void bubble(int a[], int m) {
    m--;
    int tmp;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < m - i; j++)
            if (a[j] < a[j + 1]) {
                tmp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = tmp;
            }
    }
}

int main() {
    printf("求数组中第 k 大的整数\n");
    srand((unsigned int)time(NULL));
    int m = 20, i = 0, k = 1;
    int *a = (int *)malloc(sizeof(int) * m);
    while (1) {
        i = 0, k = 1;
        a[i++] = rand() % 10; while (i < m) a[i] = a[i++] + rand() % 10 - 2; // 生成数组元素
        printA(a, m);

        bubble(a, m);
        while (k - 100) {
            scanf("%d", &k);
            if (k < m && k >= 1 || k == 100) break;
            printf("应介于 1 - %d 之间，请重输：", m - 1);
        }
        if (k == 100) break; // 用数字 100 停止程序
        printf("第 %d 小的元素是 %d\n", k, a[k - 1]);

    }


    return 0;
}