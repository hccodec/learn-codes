/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 21:33:12
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 21:49:57
 */
#include <stdio.h>

int main() {
    printf("输入正整数，计算并输出每一位的阶乘之和：\n");
    int n = 1, factial = 1, sum = 0;
    while (1) {
        scanf("%d", &n);
        sum = 0;
        if (!n) break;
        while (n) {
            factial = 1;
            printf("%d", n % 10);
            for (int i = 1; i <= n % 10; i++) factial *= i;
            printf("(%d) ", factial);
            sum += factial;
            n /= 10;
        }
        printf("\e[31m%d\e[0m\n", sum);
    }
    return 0;
}