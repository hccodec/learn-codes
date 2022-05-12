#include <stdio.h>

int f(float n) {
    if (n <= 5) return 1;
    return f(n * 2 / 5) + f(n * 3 / 5);
}

int main() {
    printf("给定木条长度 n (n > 5, n 可以为小数) 并按 2:3 的比例折断，\n直到任意一段长度都小于 5，求此时木条被折成多少段");
    double n = 0;
    while (1) {
        printf("\nn = ");
        scanf("%lf", &n);
        if (!n) break;
        printf("长度为 %lf 的一根木条可按这种方法被折成 %d 根.", n, f(n));
    }
    return 0;
}