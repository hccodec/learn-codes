/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 22:24:53
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 23:18:05
 */
#include <stdio.h>
#include <limits.h>

int main() {
    printf("求 Sn = a + aa + aaa + aaa...a(n个a) 的值：依次输入n和a\n");
    int a = 1, n = 1, sum = 0, add = 0, signal = 0;
    while (1) {
        scanf("%d%d", &n, &a);
        if (!(a && n)) break;
        if (a < 0 || a >= 10) {
            printf("a 应为一位数，请重新输入\n");
            continue;
        }
        sum = 0, signal = 0, add = 0;
        printf("Sn = %d", a);

        // 计算
        for (int i = 1; i <= n; i++) {
            add = 10 * add + a;
            if (add < 0) {
                signal = 1;
                break;
            }
            sum += add;
            if (i > 1) printf(" + %d", add);
        }

        if (signal) {
            signal = 0;
            printf(" \e[1;31m越界\e[0m\n");
        }
        else printf(" = \e[32m%d\e[0m\n", sum);
    }

    return 0;
}
