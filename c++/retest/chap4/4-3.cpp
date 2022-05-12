/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-13 16:08:16
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-13 16:27:12
 */
#include <stdio.h>
#include <math.h>

int checkhex(char *hex, int length) {
    for (int i = 1; i < length; i++) 
        if (hex[i] >= '0' && hex[i] <= '9') continue;
        else if (hex[i] >= 'A' && hex[i] <= 'F') continue;
        else if (hex[i] >= 'a' && hex[i] <= 'f') continue;
        else return 0;
    return 1;
}

int convert(char hex) {
    if (hex >= '0' && hex <= '9') return hex - '0';
    else if (hex >= 'A' && hex <= 'F') return hex - 'A' + 10;
    else if (hex >= 'a' && hex <= 'f') return hex - 'a' + 10;
    return -1;
}

int main() {
    printf("16进制转十进制\n");
    char hex[50] = {0};
    int length = 0, dec = 0;
    while (1) {
        gets(hex);
        length = 0, dec = 0;-
        while (hex[length]) length++; // 获取输入的 16 进制数的长度
        if (length == 1 && hex[0] == '0') break;
        if (!checkhex(hex, length)) {
            printf("输入有误，请重新输入\n");
            continue;
        }
        for (int i = 0; i < length; i++)
            dec = dec * 16 + convert(hex[i]);
        printf("\e[1;32m%d\e[0m\n", dec);
    }
    return 0;
}