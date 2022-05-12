/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-12 11:51:09
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-12 12:10:06
 */
#include <stdio.h>

int judge(char *s) {
    int length = 0;
    while (s[length]) length++;
    for (int i = 0; i < length; i++) if (s[i] < '0' || s[i] > '9') return 0;
    return 1;
}

int char2int(char *s) {
    int i = 0, res = 0;
    while (s[i]) res = s[i++] - '0' + res * 10;
    return res;
}

int main() {
    printf("整数文法判断\nN->N D|D\nD->0|1|2|3|4|5|||8|9\n");
    char s[100] = {0};
    while (1) {
        scanf("%s", s);        
        if (judge(s)) {
            printf("%d\n", char2int(s));
            if(char2int(s) == 0) break;
        }
        else printf("错误\n");
    }
    return 0;
}