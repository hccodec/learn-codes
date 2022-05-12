/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 17:42:00
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 18:39:39
 */
#include <stdio.h>

int main() {
    printf("1!");
    int res = 1, n = 7, flag = 1;
    for (int i = 2; i <= n; i++) {
        int k = 1;
        for (int j = 1; j <= i; j++) k *= j;
        res += k;
        if (n <= 9 || n > 9 && (i < 4 || n  == i)) printf(" + %d!", i);
        else if (flag) {
            printf(" + ...");
            flag = 0;
        }
    }
    printf(" = \e[1;32m%d\e[0;0m", res);
    return 0;
}