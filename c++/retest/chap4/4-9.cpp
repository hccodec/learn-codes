/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-14 22:55:22
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-14 23:33:50
 */
#include <stdio.h>

int main() {
    printf("输入二进制数，用*结尾：\n");
    int flag = 1, i = 0, res = 0;
    char input[50] = {0};
    while (flag) {
        res = 0; while (i) input[--i] = 0;
        fflush(stdin);
        scanf("%s", input);
        while (input[i]) {
            if (input[i] == '1' || input[i] == '0') res = res * 2 + input[i] - '0';
            else if (input[i] == '*') printf("\e[1;32m%d\e[0m\n", res);
            else {
                if (input[i] == '/') flag = 0;
                else printf("只可输入 0 和 1。输入有误请重输: ");
                if (input[i] != ' ') break;
            }
            i++;
        }
    }
    return 0;
}