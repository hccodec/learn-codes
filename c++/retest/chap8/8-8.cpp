#include <stdio.h>
#include <math.h>

int fact(int n) {
    if (!n) return 1;
    return n * fact(n - 1);
}

int main1() {
    printf("计算 sin(x) 的值。");
    int n = 1;
    double x, term = 1, res = 0; // term 是每一项的绝对值 即 x^(2n-1)/(2n-1)!
    scanf("%lf", &x);
    // x = 6.28;
    printf("\nsin(%.3lf)=", x);
    while (term >= 5e-6) {
        if (term - 1) {
            if (n & 1) printf("+(%.3lf^%d)/(%d!)", x, (n << 1) - 1, (n << 1) - 1);
            else printf("-(%.3lf^%d)/(%d!)", x, (n << 1) - 1, (n << 1) - 1);
        }

        else printf("(%.3lf^%d)/(%d!)", x, (n << 1) - 1, (n << 1) - 1);
        term = pow(x, (n << 1) - 1) / fact((n << 1) - 1);
        res += pow(-1, n - 1) * term;
        n++;
    }
    printf("=%lf", res);
    return 0;
}

int main() {
    int x, n, sign = 1;
    double term, s=0;
    scanf("%d", &x);
    term = x;
    for (int n = 1; term > 1e-8; n+=2) {
        term = pow(x, n) / fact(n);
        s=s+sign*term;
        sign=-sign;
    }
    printf("sin%d近似值：%10.8f", x, s);
    return 0;
}