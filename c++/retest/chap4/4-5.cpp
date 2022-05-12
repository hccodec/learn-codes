/*
 * @Description: 数组存储的二进制数的加一运算
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-13 17:15:45
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-13 17:30:15
 */
#include <stdio.h>

void plusOne(int *bin, int n) {
    int needPlus = 1, check = 0;
    for (int i = 0; i < n; i++)
        if (bin[n]) check++;
    if (!(n - check)) {
        printf("越界\n");
        return;
    }
    while (n--) {
        if (needPlus) {
            if (bin[n]) bin[n] = 0;
            else {
                bin[n] = 1;
                needPlus = 0;
            }
        }
    }
}

int main() {
    printf("（数组存储）二进制数加一\n");
    int n = 5, bin[n] = {1,1,0,1,1}, i = 0;
    plusOne(bin, n);
    while (n - i) printf("%d", bin[i++]);
    return 0;
}