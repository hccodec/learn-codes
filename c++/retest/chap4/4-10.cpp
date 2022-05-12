/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-14 23:35:03
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-15 09:15:13
 */
#include <stdio.h>

int main() {
    printf("十进制转八进制\n");
    int n = 0, i = 0, flag = 0; char res[20] = {0}; // n 存放输入的十进制数，i 是工作指针, res 存放输入结果以及转化结果
    while (1) {
        fflush(stdin);
	printf("> ");
        scanf("%s", res);
        n = 0, flag = 0;
        // 将输入的字符串处理为对应十进制数
        while (res[i]) {
            if (res[i] < '0' || res[i] > '9') { printf("输入有误请重输"); flag = 1; break; }
            n = n * 10 + res[i] - '0';
            i++;
        }
        if (flag) continue;
        // 将十进制数转为八进制数，并以字符串形式存到 res 数组中（倒序）
        i = 0;
        if (!n) break;
        while (n) {
            res[i++] = n % 8 + '0';
            n /= 8;
        }
        while (i) {
            i--;
            printf("\e[1;32m%c\e[0;0m", res[i]);
            res[i] = 0;
        }
        printf("\n");
    }
    return 0;
}
