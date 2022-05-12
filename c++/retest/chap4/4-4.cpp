/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-13 16:30:20
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-13 17:17:42
 */
#include <stdio.h>
#include <math.h>

int main() {
    printf("十进制转二进制(输入0停止)\n");
    int dec, length = 0;
    int bin[10];
    while (1) {
        scanf("%d", &dec);
        if (!dec) {
            printf("\e[1;32m%d\e[0;0m", 0);
            break;
        }
        length = 0;
        while (dec) {
            bin[length++] = dec % 2;
            dec >>= 1;
        }
        while (length) printf("\e[1;32m%d", bin[--length]);
        printf("\e[0;0m\n");
    }
    return 0;
}