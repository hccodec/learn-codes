#include <stdio.h>

float Hermite (float x, int n) {
    if (!n) return 1;
    if (n == 1) return 2 * x;
    return 2 * x * Hermite(x, --n) - 2 * n * Hermite(x, --n);
}

int main() {
    printf("计算 Hermite 多项式");
    float x = 0;
    int n = 0;
    while (1) {
        x = n = 0;
        printf("\n请输入 X 和 n (要求 x 可以是小数，但 n 必须是整数): ");
        scanf("%f", &x);
        if (!x) break;
        scanf("%d", &n);
        printf("%f", Hermite(x, n));
    }
    return 0;
}