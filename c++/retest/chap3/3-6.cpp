/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 23:39:15
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 23:43:43
 */
#include <stdio.h>

int main() {
    printf("整数每一位数求和\n");
    int n = 1, sum = 0;
    while (1) {
        scanf("%d", &n);
        sum = 0;
        if (!n) break;
        while (n) {
            sum += n % 10;
            n /= 10;
        }
        printf("\e[1;32m%d\e[0m\n", sum);
    }
    return 0;
}