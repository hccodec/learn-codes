#include <stdio.h>

inline int fact(int n) {
    int sum = 1;
    for (int i = 2; i <= n; i++) sum *= i;
    return sum;
}

float f(int n) {
    if (n <= 0) return 0;
    return 1.0 * n/((n + 1) * fact(n + 2)) + f(n - 1);
}

int main() {
    printf("实现计算多项式：f(n) = 0/(1*2!)+...+n/((n+1)*(n+2)!)");
    int n = 0;
    while (1) {
        printf("\nn = ");
        scanf("%d", &n);
        if (!n) break;
        printf("f(%d) = %f", n, f(n));
    }
    return 0;
}