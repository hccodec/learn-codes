/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-18 22:25:29
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-18 22:52:31
 */
#include <stdio.h>

int main() {
    printf("统计字符串中英文字母、空格、数字和其他字符的个数\n");
    char c, len = 0, input[50];
    int a[4] = {0}; // a数组中4个元素分别统计四种字符的个数
    while (1) {
        len = 0;
        for (int i = 0; i < 4; i++) a[i] = 0;
        printf("请输入字符：");
        while ((c = getchar()) != '\n') {
            input[len++] = c;
            if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') a[0]++;
            else if (c == ' ') a[1]++;
            else if (c >= '0' && c <= '9') a[2]++;
            else a[3]++;
        }
        if (len == 1 && input[0] == '0') break;
        printf("英文字母有 %d 个、空格有 %d 个、数字有 %d 个、其他字符有 %d 个。\n", a[0], a[1], a[2], a[3]);
    }


    return 0;
}