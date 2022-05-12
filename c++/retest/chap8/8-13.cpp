#include <stdio.h>
#include <math.h>

int fac(int n) {
    if (!n) return 1;
    return n * fac(n - 1);
}

int main() {
    printf("计算正弦值");
    double x = 1, term = 1, res = 0;
    int n = 1, sign = 1;
    while (1) {
        n = x = term = 1;
        res = 0;
        printf("\n请输入 x 值: ");
        scanf("%lf", &x);
        if (!x) break;
        while (term > 1e-8) {
            term = pow(x, (n << 1) - 1) / fac((n << 1) - 1);
            res += (sign ? 1 : -1) * term;
            n++;
            sign = !sign;
        }
        printf("sin(%lf) = %lf", x, res);
    }
    return 0;
}
