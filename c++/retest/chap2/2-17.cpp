/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 18:48:32
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 19:11:13
 */
#include <stdio.h>
#include <math.h>

int calPrime(int n) {
    int res = 1;
    for (int i = 2; i <= sqrt(n); i++) {
        if (n % i == 0) {
            res += i;
            res += n / i;
        }
    }
    return res;
}

int main () {
    printf("判断两数是否友好：互为约数和：\n");
    int start = 1, end = 1000;
    for (int i = start; i < end; i++) for (int j = i + 1; j < end; j++) {
        if (calPrime(i) == j && calPrime(j) == i) printf("%d 和 %d 友好\n",i , j);
    }
    return 0;
}