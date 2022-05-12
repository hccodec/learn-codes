#include <stdio.h>
#include <string.h>

int check(char *target, int n) {
    int A[3] = {0}, sum = 0; // 记录三种字符是否存在
    for (int i = 0; i < n; i++) {
        if (target[i] >= 'A' && target[i] <= 'Z' && !A[0]) A[0] = 1; // 大写
        else if (target[i] >= 'a' && target[i] <= 'z' && !A[1]) A[1] = 1; // 小写
        else if (target[i] >= '0' && target[i] <= '9' && !A[2]) A[2] = 1; // 数字
    }
    for (int i = 0; i < 3; i++) sum += A[i];
    return sum == 2;
}

int main() {
    printf("检查字符串中是否有大写字母、小写字母、数字三类字符中的两类\n");
    char A[30] = {0}, len = 0;
    while (1) {
        len = 0;
        scanf("%s", A);
        while (A[len]) len++;

        if (len == 4 && !strcmp(A, (char *)"exit")) break;

        if (check(A, len)) printf("通过\n");
        else printf("不通过\n");
    }

    return 0;
}