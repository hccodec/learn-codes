#include <stdio.h>

int f(int n) {
    if (n < 0) return -1;
    if (n <= 2) return n;
    return f(n - 3) + f(n - 1);
}

int main() {
    printf("求递归函数，定义如下：f(0)=0, f(1)=1, f(2)=2, f(n+2)=f(n-1)+f(n+1) (n≥1)\n");
    int n;
    while (1) {
        scanf("%d", &n);
        if (f(n) + 1) printf("-> %d\n", f(n));
        else printf("计算出错");
        if (!n) break;
    }
    return 0;
}