#include <stdio.h>

int main() {
    printf("10*10螺旋矩阵\n");
    int a[10][10], n = 1;
    for (int i = 0; i < 5; i++) {
        for (int j = i; j < 10 - i; j++) a[i][j] = n++;
        for (int j = 1 + i; j < 10 - i; j++) a[j][9 - i] = n++;
        for (int j = 8 - i; j >= i; j--) a[9 - i][j] = n++;
        for (int j = 8 - i; j > i; j--) a[j][i] = n++;
    }

    for (int i = 0; i < 100; i++) {
        if (!(i % 10)) printf("\n");
        printf("%3d ", a[i / 100][i % 100]);
    }

    return 0;
}