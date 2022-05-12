#include <stdio.h>

double H(double x, int n, int mode) {
    if (n < 0) return 0;
    if (mode) {
        // 递归计算
        if (!n) return 1;
        if (n == 1) return 2 * x;
        return 2 * x * H(x, n - 1, mode) - 2 * (n - 1) * H(x, n - 2, mode);
    }
    // 非递归计算
    if (!n) return 1;
    if (n == 1) return 2 * x;
    double h2 = 1, h1 = 2 * x, res = 0;
    for (int i = 2; i <= n; i++) {
        res = 2 * x * h1 - 2 * (i - 1) * h2;
        h2 = h1;
        h1 = res;
    }
    return res;
}

int main() {
    printf("计算多项式第 n 项值");
    int n = 0;
    double n0 = 0, n1 = 0, x = 0;
    while (1) {
        printf("\n请输入 x 和 n:");
        fflush(stdin);
        scanf("%lf", &x);
        if (!x) break;
        scanf("%d", &n);
        if (!n) break;
        scanf("x = %lf, n = %d\n", x, n);
        n0 = H(x, n, 0); // 递归计算结果
        n1 = H(x, n, 1); // 非递归计算结果
        printf("递归结果和非递归结果分别为：%lf 和 %lf, ", n0, n1);
        printf(n0 == n1 ? "代码通过检验" : "代码不通过检验");
    }
    return 0;
}
