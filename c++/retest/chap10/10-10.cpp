#include <stdio.h>
#include <stdlib.h>

int f(int n) {
    if (n < 1) return -1;
    if (n == 1) return 0;
    int min = f(n - 1) + n - 2, tmp = 0;
    for (int i = 2; i <= n / 2; i++) {
        tmp = (n - i) - i + f(i) + f(n - i);
        if (min > tmp) min = tmp;
    }
    return min;
}

int main() {
    printf("把正整数 n 拆分成 n 个 1：\n[拆分过程]\t首先将 n 拆分为两个数，二者之和为 n，将二者之差的绝对值加入累加器；\n\t\t再对所得两数分别进行“拆分-累加”的操作，直到所有数都为 1。");
    printf("\n递归程序需要计算所有可行的拆分过程所对应累加器的最小值。");
    int n = 0;
    while (1) {
        printf("\nn = ");
        scanf("%d", &n);
        if (f(n) + 1) printf("%d", f(n));
        else printf("计算错误");
        if (!n) break;
    }
    return 0;
}