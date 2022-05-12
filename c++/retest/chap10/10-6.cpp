#include <stdio.h>

int f(int n) {
    if (n < 0) return -1;
    if (!n) return 1;
    return n-- * f(n);
}

int main() {
    printf("递归求阶乘\n");
    int n;
    while (1) {
        scanf("%d", &n);
        if (f(n) + 1) printf("%d! = %d\n", n, f(n));
        else printf("计算出错");
        if (!n) break;
    }
    return 0;
}