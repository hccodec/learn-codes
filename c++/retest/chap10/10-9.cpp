#include <stdio.h>

int f(int n) {
    if (n < 2) return -1;
    if (n - 2) {
        int res = 0;
        for (int i = 2; i < n; i++) res += f(i) + f(n + 1 - i);
        return res;
    } else return 1;
}

int main() {
    printf("计算凸 n 边形三角形不同剖分方法的个数：");
    int n = 0;
    while (1) {
        printf("\nn = ");
        scanf("%d", &n);
        if (!n) break;
        if (f(n) + 1) printf("%d 边形分法有 %d 个.", n, f(n));
        else printf("计算错误，请确保 n 大于 2");
    }
    return 0;
}