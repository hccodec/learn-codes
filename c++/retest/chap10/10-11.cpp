#include <stdio.h>
#include <stdlib.h>
#include <time.h>

float comp(float a[][2], int n) {
    if (!n) return 0;
    --n;
    return a[n][0] * a[n][1] + comp(a, n);
}

int main() {
    srand((unsigned int)time(NULL));
    printf("计算 n 维向量的内积. ");
    printf("输入 n: ");
    int n = 0;
    scanf("%d", &n);
    printf("生成的两个 n 维向量如下：");
    float a[n][2] = {0};
    for (int i = 0; i < n; i++) {
        a[i][0] = rand() % 5;
        a[i][1] = rand() % 5;
    }
    printf("\n{");
    for (int i = 0; i < n; i++) {
        printf("%4.2f", a[i][0]);
        if (n - i - 1) printf(", ");
        else printf("}");
    }
    printf("\n{");
    for (int i = 0; i < n; i++) {
        printf("%4.2f", a[i][1]);
        if (n - i - 1) printf(", ");
        else printf("}");
    }

    printf("\n内积为：%.2f", comp(a, n));
    return 0;
}