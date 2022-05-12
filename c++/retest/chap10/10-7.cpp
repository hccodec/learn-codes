#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void revPrint(char s[], int i) {
    if (!i) return;
    putchar(s[--i]);
    revPrint(s, i);
}

int main() {
    int n = 5;
    srand((unsigned int)time(NULL));
    printf("用递归方式逆序打印输入的 n 个字符。请输入不超过行首提示数的字符");
    char *s, c;
    int i = 0;
    while (1) {
        n = rand() % 8 + 2;
        i = 0;
        s = (char*)malloc(sizeof(char) * n);
        printf("\n\e[1;33m%d\e[0m> ", n);
        fflush(stdin);
        while (i < n) {
            if ((c = getchar()) != '\n') s[i++] = c;
            else break;
        }
        if (i == 4 && s[0] == 'e' && s[1] == 'x' && s[2] == 'i' && s[3] == 't' || i == 1 && s[0] == '0') break;
        if (i > n) {
            printf("请输入5个字符");
            continue;
        }
        printf("[%d] ", i);
        revPrint(s, i);
        free(s);
    }
    return 0;
}