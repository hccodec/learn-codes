#include <stdio.h>

void sort (char *s) {
    int n = 0;
    char tmp;
    while (s[n]) n++;
    n--;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n - i; j++)
            if (s[j] > s[j + 1]) {
                tmp = s[j];
                s[j] = s[j + 1];
                s[j + 1] = tmp;
            }
}

int main() {
    printf("字符串字符排序\n");
    char s[50] = {0};
    scanf("%s", s);
    sort(s);
    printf("%s", s);

    return 0;
}