#include <stdio.h>
#include <math.h>

int fact(int n) {
    if (n) return n * fact(n - 1);
    return 1;
}

int main() {
    printf("计算 X-(X²/2!)+(X³/3!)+...+(-1)^(n - 1)/n! 的和：");
    int X = 0, n = 1, sign = 1;
    double term = 1, res = 0;
    while (1) {
        n = term = sign = 1, res = 0;
        printf("\n请输入 X：");
        scanf("%d", &X);
        if (!X) break;
        while (term > 1e-3) {
            term = 1.0 * pow(X, n) /fact(n);
            if (sign) res += term;
            else res -= term;
            if (n - 1) {
                printf(sign ? "+" : "-");
                printf("%d^%d/%d!", X, n, n);
            } else printf("%d", X);
            sign = !sign;
            n++;
        }
        printf("%lf", res);
    }
    return 0;
}