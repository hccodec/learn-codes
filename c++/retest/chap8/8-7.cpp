#include <stdio.h>

int factial(int n) {
    if (!n) return 1;
    return n * factial(n - 1);
}

int main() {
    printf("计算分式\n");
    int n = 0, i = 0;
    float sum = 0;
    while (1) {
        i = 2, sum = 0;
        printf("请输入 n (n >= 2)：");
        scanf("%d", &n);
        if (!n) break;
        if (n < 2) continue;
        while (i <= n) {
            sum += 1.0 * i / ((i + 1) * factial(i - 2));
            i++;
        }
        printf("结果为 %f\n", sum);
    }
    return 0;
}