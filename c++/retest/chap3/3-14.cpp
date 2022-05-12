/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-12 16:42:38
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-12 17:12:52
 */
#include <stdio.h>

int main() {
    printf("偶数则除以2，奇数则乘3加1，直到结果为1。输出运算次数\n");
    int n = 0, step = 0;
    while (1) {
        scanf("%d", &n);
        printf("%d", n);
        if (!n) break;
        step = 0;
        while (n - 1) {
            if (n & 1) n = n * 3 + 1;
            else n >>= 1;
            printf("->%d", n);
            step++;
        }
        printf("\nSTEP=\e[1;32m%d\e[0;0m\n", step);
    }
    return 0;
}
