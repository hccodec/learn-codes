/*
 * @Description:
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-12 16:24:12
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-12 16:42:00
 */
#include <stdio.h>

int main()
{
    printf("数字加密 (对各数位的数字，先全加 5，再除以 10 求余数，再分别将第一位和第四位、第二位和第三位互换)\n");
    int n = 0, A[4], i = 0;
    while (1) {
        printf("请输入四位数：");
        scanf("%d", &n);
        if (!n) break;
        for (i = 0; i < 4; i++) A[i] = 0;
        if (n < 1000 || n >= 10000)
        {
            printf("不是四位数，请重新输入\n");
            continue;
        }
        i = 0;
        while (n)
        {
            A[i++] = n % 10;
            n /= 10;
        }

        // 数组逆置
        // for (i = 0; i < 2; i++) {
        //     n = A[i];
        //     A[i] = A[3 - i];
        //     A[3 - i] = n;
        // }

        for (i = 0; i < 4; i++)
            A[i] = (A[i] + 5) % 10;
        for (i = 0; i < 4; i++)
            printf("%d", A[i]);
        printf("\n");
    }

    return 0;
}