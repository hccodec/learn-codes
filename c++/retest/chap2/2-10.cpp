/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 12:25:24
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 12:46:45
 */
#include <stdio.h>
#include <math.h>

int isPrime(int n) {
    for (int i = 2; i <= sqrt(n); i++) if (n % i == 0) return 0; // 遇到因数，直接返回 0 表示 非质数
    return 1; // 未遇到任何非 1 或其本身的因数，返回 1 表示 是质数
}

int main() {
    printf("从小到大输出前 20 组孪生素数对：\n");
    int count = 0, a = 0, flag = 1;
    while (count < 20) {                    // 确保只输出 20 个数对
        if (count % (20 / 4) == 0 && flag) {
            printf("\n");
            flag = 0;
        }                                   // 输出 10 对，换行
        if (isPrime(a) && isPrime(a + 2)) {
            printf("(%d, %d) ", a, a + 2);
            count++;
            flag = 1;
        }                                   // 若该数和比其大 2 的数都是质数，则输出数对，并计数加一
        a++;                                // 处理完成，待处理数加一
    }
    return 0;
}