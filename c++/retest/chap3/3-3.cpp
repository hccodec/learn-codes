/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 21:52:05
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 22:21:47
 */
#include <stdio.h>

int main() {
    printf("输出正整数的位数\n");
    int n = 1, count = 0;
    while (1) {
        scanf("%d", &n);
        if (!n) break;
        count = 0;
        while (n) {
            n /= 10; // 整个数右移一位
            count++;
        }
        printf("该数有 %d 位\n", count);
    }
    return 0;
}
