#include <stdio.h>

int f(int n) {
    if (n < 0) return -1;
    if (n <= 5) return n;
    return f(n - 1) - f(n - 3) - f(n - 5);
}

int main() {
    printf("求递归函数，定义如下：f(n)=n (0≤n≤5), f(n)=f(n-1)-f(n-3)-f(n-5) (n≥6)\n");
    int n;
    while (1) {
        scanf("%d", &n);
        if (f(n) + 1) printf("-> %d\n", f(n));
        else printf("计算出错");
        if (!n) break;
    }
    return 0;
}