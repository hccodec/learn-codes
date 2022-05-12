#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    printf("3*3矩阵对角线元素之和\n");
    srand((unsigned int)time(NULL));
    int a[3][3] = {0}, sum = 0;
    for (int i = 0; i < 9; i++) a[i / 3][i % 3] = rand() % 9 + 1;
    printf("-------");
    for (int i = 0; i < 9; i++) {
        if (!(i % 3)) printf("\n");
        printf("%d ", a[i / 3][i % 3]);
    }
    printf("\n-------");
    for (int i = 0; i < 3; i++) sum += a[i][i];
    printf("\n%d", sum);



    return 0;
}