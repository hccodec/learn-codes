#include <stdio.h>
#include <math.h>

int main() {
    printf("计算X-(X²/2!)+(X³/3!)+...\n当第 k 项满足 |t_k|≤e^(-3)时计算结束\n请输入 X：");
    int n = 1, sign = 1, X;
    double term = 1, res = 0;
    int fact(int n);
    while (1){
        term = 1, res = 0, sign = 1, n = 1;
        printf("请输入 X：");
        scanf("%d", &X);
        if (!X) break;
        while (term > 1e-3) {
            term = 1.0 * pow(X, n) / fact(n);
            if (sign) res += term;
            else res -= term;
            if (n - 1) {
                printf(sign ? "+" : "-");
                printf("%d^%d/%d!", X, n, n);
            } else printf("%d", X);
            sign = !sign;
            n++;
        }
        printf("=%lf\n", res);
    }
    return 0;
}

int fact(int n) {
    int res = 1;
    if (!n) return 1;
    for (int i = 1; i <= n; i++) res *= i;
    return res;
}