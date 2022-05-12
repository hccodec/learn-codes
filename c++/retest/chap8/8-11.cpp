#include <stdio.h>

int main() {
    printf("计算数列 a_k=(-1)^(k - 1)(1/k) (k ≥ 1) 前 n 项和：");
    // 正文
    int sign = 1, n = 0;
    double res = 0;
    while (1) {
        sign = 1;
        res = 0;
        printf("\n请输入 n: ");
        scanf("%d", &n);
        if (!n) break;
        printf("res = ");
        for (int i = 1; i <= n; i++) {
            res += (sign ? 1 : -1) * 1.0 / i;
            printf("%s1/%d", (i == 1) ? "" : sign ? " + " : " - ", i, sign);
            sign = !sign;
        }
        printf(" = %lf", res);
    }
    return 0;
}
