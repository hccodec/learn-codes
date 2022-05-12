#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {
    printf("N 阶楼梯上楼问题：一次只能走一到两阶，问有多少种上楼方式");
    int n = 0;
    long long F[91] = {0};
    F[1] = 1;
    F[2] = 2;
    for (int i = 3; i <= 90; i++) F[i] = F[i - 1] + F[i - 2];
    while (1) {
        fflush(stdin);
        printf("\nn> ");
        scanf("%d", &n);
        if (!n) break;
        if (n < 0) {
            printf("n 应为整数，请重新输入");
            continue;
        }
        printf(" %d 阶上楼方式有 %lld 种", n, F[n]);
    }
    return 0;
}
