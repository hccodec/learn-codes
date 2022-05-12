/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-12 09:33:01
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-12 11:12:30
 */
#include <stdio.h>

void reverse(char *s) {
    int length = 0;
    char tmp;
    while (s[length]) length++;
    for (int i = 0; i < length / 2; i++) {
        tmp = s[i];
        s[i] = s[length - i - 1];
        s[length - i - 1] = tmp;
    }
}

int main() {
    printf("字符串翻转：\n");
    char s[30] = {0};
    scanf("%s", s);
    reverse(s);
    printf("%s", s);
    return 0;
}