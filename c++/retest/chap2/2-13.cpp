/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 16:22:50
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 16:33:43
 */
#include <stdio.h>

int main() {
    printf("1， 2， 3， 4这四个数字能组成多少互不相同且无重复数字的三位数：\n");
    int count = 0;
    for (int i = 1; i < 5; i++)
        for (int j = 1; j < 5; j++)
            for (int k = 1; k < 5; k++) {
                if (!(i == j || j == k || i == k)){
                    printf("%d%d%d ", i, j, k);
                    count++;
                }
            }
            printf("\n共 \e[1;32m%d\e[0;0m 个", count);
    return 0;
}