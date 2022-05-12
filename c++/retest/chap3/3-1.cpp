/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 19:15:51
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 19:21:09
 */
#include <stdio.h>
#include <math.h>

int main() {
    printf("输出所有 “水仙花数” (一个三位数，其各个位数字的立方和等于该数本身)\n");
    for (int i = 100; i < 1000; i++) {
        if (pow(i / 100, 3) + pow(i % 100 / 10, 3) + pow(i % 10, 3) == i) printf("%d ", i);
    }
    return 0;
}