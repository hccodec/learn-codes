#include <stdio.h>
#include <stdlib.h>

int check(char *input) {
    int i = 0;
    while (input[i])
        if (input[i] <'a' || input[i] > 'z') return 0;
        else i++;
    return 1;
}

// 返回一个长度为 26 的整形数组，依次对应a-z出现的次数
int *fun(char *input) {
    int *a = (int*)malloc(sizeof(int)*26);
    for (int i = 0; i < 26; i++) a[i] = 0;
    int i = 0;
    while (input[i]) {
        // printf("记录 %c : a[%d]=%d\n", input[i], input[i] - 'a', a[input[i] - 'a'] + 1);
        a[input[i++] - 'a']++;
    }
    return a;
}

int main() {
    printf("统计输入的字符串中，每一个小写英文字母出现的次数\n");
    printf("请输入字符串: ");
    char c, input[100] = {0};
    int l = 0, res[26] = {0};
    while ((c = getchar()) - '\n' && 100 - l) input[l++] = c;
    if (!check(input) || l == 1 && input[0] == '0') return 0;
    for (int i = 0; i < 26; i++) res[i] = fun(input)[i];
    // 依次打印字符串中每个字母出现的情况
    for (int i = 0; i < 26; i++) {
        if (res[i]) printf("字母 %c 出现了 %d 次\n", i + 'a', res[i]);
    }
    return 0;
}