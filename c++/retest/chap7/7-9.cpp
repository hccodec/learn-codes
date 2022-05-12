#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(char a[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%c", a[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}\n");
}
void printA(int a[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}\n");
}

int *solution(char *s, char *a, int n) {
    int *times = (int*)malloc(sizeof(int)*n);
    int j = 0;
    for (int i = 0; i < n; i++) times[i] = 0;
    for (int i = 0; i < n; i++) {
        j = 0;
        while (s[j])
            if (s[j++] == a[i]) {
                times[i]++;
            }
    }
    return times;
}

int main() {
    printf("统计一维字符数组a中每个字符在S中出现的次数\n");
    srand((unsigned int)time(NULL));
    const int n = 20;
    char a[n], s[100], c;
    int times[n], len; // len 统计输入字符串的长度
    for (int i = 0; i < n / 2; i++) a[i] = 'a' + i;
    for (int i = n / 2; i < n; i++) a[i] = 'A' + i - n / 2;
    printA(a, n);
    while (1) {
        printf("\n请输入字符串");
        len = 0;
        while ((c = getchar()) != '\n') s[len++] = c;
        if (len == 1 && s[0] == '0') break;
        printA(solution(s, a, n), n);
        for (int i = 0; i < n; i++)
            printf("字母 %c 出现了 %d 次\n", a[i], solution(s, a, n)[i]);
        
    }

    return 0;
}