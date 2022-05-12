#include <stdio.h>

int main() {
    printf("顺序生成前100项，规则如下：\n(1) 前两项分别为 2 和 3\n(2) 生成新项的方法是取原序列最后两项成绩的每一数位依次成为新项，如2, 3, 6, 1, 8...\n\n结果如下：");
    int a[100] = {0}, i = 0, tmp = 0;
    a[i++] = 2;
    a[i++] = 3;
    while (i < 100) {
        tmp = a[i - 1] * a[i - 2];
        if (tmp < 10) a[i++] = tmp;
        else {
            a[i++] = tmp / 10;
            if (i < 100) a[i++] = tmp % 10;
        }
    }
    for (int i = 0; i < 100; i++) {
        if (!(i % 25)) printf("\n[%d]", i / 25 + 1);
        if (!(i % 5)) printf("  ");
        printf("%2d ", a[i]);
    }

    return 0;
}