/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 23:44:19
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 23:51:18
 */
#include <stdio.h>

int main() {
    printf("判断给定整数是否由1, 3, 7, 9组成\n");
    int n = 0, signal = 0;
    while (1) {
        scanf("%d", &n);
        if (!n) break;
        while (n) {
            switch (n % 10) {
                case 1: case 3: case 7: case 9: break;
                default:
                    printf("该数并非由1,3,7,9组成\n");
                    signal = 1;
                    break;
            }
            if (signal) break;
            n /= 10;
        }
        if (signal) {
            signal = 0;
            continue;
        }
        else printf("该数由1,3,7,9组成\n");
    }
    return 0;
}