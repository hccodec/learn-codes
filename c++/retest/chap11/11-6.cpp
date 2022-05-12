#include <stdio.h>
#include <string.h>

const char fname[9] = "test.txt";

void convert(char *s) {
    int i = 0;
    while (s[i]) {
        if (s[i] >= 'a' && s[i] <= 'z') s[i] += ('A' - 'a');
        i++;
    }
}

void write(char *s) {
    FILE *pf = fopen(fname, "a");
    fprintf(pf, "%s\n", s);
    fclose(pf);
}

int main() {
    printf("键盘输入字符串，小写转大写，输出到文件 %s 中.\n> ", fname);
    char s[50] = {0}, c = 0;
    int i = 0;
    while (1) {
        while (i) s[i--] = 0;
        while ((c = getchar()) != '\n' && i < 50) s[i++] = c;
        if (!strcmp(s, "exit")) break;
        convert(s);
        write(s);
        printf("%s\n> ", s);
    }
    return 0;
}