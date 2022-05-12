/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 23:52:26
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-12 09:29:54
 */
#include <stdio.h>

int main() {
    printf("求反序数：\n");
    int n = 1, res = 0;
    while (1) {
        scanf("%d", &n);
        if (!n) break;
        res = 0;
        while (n) {
            res = res * 10 + n % 10;
            n /= 10;
        }
        printf("\e[32m%d\e[0m\n", res);
    }
    return 0;
}