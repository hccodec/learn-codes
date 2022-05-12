/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-12 11:13:40
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-12 11:39:01
 */
#include <stdio.h>

int main() {
    printf("判断五位数是不是回文数：\n");
    int n = 1;
    while (n) {
        scanf("%d", &n);
        if (!n) break;
        if (n == 1) {
            int count = 0;
            printf("所有的五位数中，回文数如下（每行 20 个）：");
            for (n = 10000; n < 100000; n++)
                if (n / 10000 == n % 10 && n % 10000 / 1000 == n % 100 / 10) {
                    if (!((++count - 1) % 20)) printf("\n[%2d]", count / 20 + 1);
                    printf(" %d", n);
                }
                printf("\n共有 %d 位回文数\n", count);
                continue;
        }
        if (n < 10000 || n >= 100000) {
            printf("不是五位数，请重新输入\n");
            continue;
        }
        if (n / 10000 == n % 10 && n % 10000 / 1000 == n % 100 / 10) {
            printf("%d 是回文数\n", n);
        }
        else
            printf("%d 不是回文数\n", n);
    }
    return 0;
}