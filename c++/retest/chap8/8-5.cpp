#include <stdio.h>

int main() {
    printf("输出前100项，规则为：\n(1) 前两项分别为 0 和 1\n(2) 奇数项为前两项之和，偶数项为前两项之差\n\n结果如下：");
    int a[100] = {0}, i = 0;
    a[i++] = 0, a[i++] = 1;
    while (100 - i) {
        if (i & 1) a[i] = a[i - 2] - a[i - 1];
        else a[i] = a[i - 2] + a[i - 1];
        i++;
    }
    
    for (int i = 0; i < 100; i++) {
        if (!(i % 25)) printf("\n[%d]", i / 25 + 1);
        if (!(i % 5)) printf("  ");
        printf("%2d ", a[i]);
    }
    return 0;
}