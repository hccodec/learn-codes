/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-12 12:16:46
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-12 16:22:13
 */
#include <stdio.h>

int main() {
    printf("特殊乘法（举例：123*45=1*4+1*5+2*4+2*5+3*4+3*5）\n");

    /* 变量的初始化：
    *  a 和 b 存放两个乘数，la 和 la 存放两个数的位数(即分别表示数组 A 和 B 的长度)，数组 A 和 B 存放两个数每一位的数字
    *  sum 存放加和的结果，tmp 作为临时变量用于进行数组逆置，i 和 j 是循环变量
    */ 
    int a = 1, b = 1, la = 0, lb = 0, A[15] = {0}, B[15] = {0}, sum = 0, tmp = 0, i = 0, j = 0;
    
    while (1) {
        scanf("%d%d", &a, &b);
        rewind(stdin);
        if (!a && !b) break;
        // 分别获得 a 和 b 的位数并把每一位存到对应数组中
        printf("%d * %d = ", a, b);
        la = 0, lb = 0, sum = 0;
        for (i = 0; i < la; i++) A[i] = 0;
        for (i = 0; i < lb; i++) B[i] = 0;

        // 通过将两个数进行按位右移的方式，从个位提取 a 和 b 两数各个数位上的数字，并分别存放到相应数组 A 和 B 中（该方法得到的结果与原数顺序相反）
        while (a) {
            A[la++] = a % 10;
            a /= 10;
        }
        while (b) {
            B[lb++] = b % 10;
            b /= 10;
        }

        // 两数组逆置，确保数组中元素位置与原数各个数位上的数字一一对应
        for (i = 0; i < la / 2; i++) {
            tmp = A[i];
            A[i] = A[la - i - 1];
            A[la - i - 1] = tmp;
        }
        for (i = 0; i < lb / 2; i++) {
            tmp = B[i];
            B[i] = B[lb - i - 1];
            B[lb - i - 1] = tmp;
        }

        // 二重循环分别将两数组元素相乘并加和
        for (int i = 0; i < la; i++) for (int j = 0; j < lb; j++) {
            sum += A[i] * B[j];
            printf("%d*%d", A[i], B[j]);
            if (!(la - i == 1 && lb - j == 1)) printf(" + ");
        }
        printf(" = %d\n", sum);
    }
    return 0;
}