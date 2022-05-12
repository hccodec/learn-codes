/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-16 22:38:12
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 22:45:48
 */
#include <stdio.h>
#include <string.h>

void sort(char st[][10], int n) {
    n--;
    char t[10];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n - i; j++)
            if (strcmp(st[j], st[j + 1])) {
                strcpy(t, st[j]);
                strcpy(st[j], st[j + 1]);
                strcpy(st[j + 1], t);
            }
}

void printA(char st[][10], int n) {
    printf("\n{");
    for (int i = 0; i < n; i++) {
        printf("%s", st[i]);
        if (n - i - 1) printf(" ");
        else printf("}");
    }
}

int main() {
    printf("字符串按字典排序\n");
    char st[4][10] = {"foo", "bar", "bark", "abandon"};
    sort (st, 4);
    printA(st, 4);
    return 0;
}