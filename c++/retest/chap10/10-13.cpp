#include <stdio.h>

int digit(int n, int j) {
    if (j < 1) return -1;
    if (j == 1) return n % 10;
    return digit(n / 10, j - 1);
}

int main() {
    printf("递归求正整数 n 右边第 j 为数字\n");
    int n = 0, j = 0;
    while (1) {
        printf("输入 n 和 j: ");
        scanf("%d", &n);
        scanf("%d", &j);
        if (!n && !j) break;
        printf("%d\n", digit(n, j));
    }
    return 0;
}