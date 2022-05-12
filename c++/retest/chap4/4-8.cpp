/*
 * @Description:
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-14 10:32:53
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-14 22:53:06
 */
#include <stdio.h>

// 该函数检查字符转化为十进制数字的合法性，并返回十进制对应的值
int map(char n, int sys) {
    int res = 0;
    if (n >= '0' && n <= '9') res = n - '0';
    else if (n >= 'a' && n <= 'f') res = n - 'a' + 10;
    else if (n >= 'A' && n <= 'F') res = n - 'A' + 10;
    else return -1;

    if (res < sys) return res;
    return -1;
}

// 以“进制-数值”的格式解析输入并返回结果
int handleInput(int *sys, char *n)
{
    int i = 0, j = 0; // i 指针用于扫描，j 指针用于赋值
    *sys = 0;

    // 提取进制数 sys
    while (n[i]) {
        if (n[i] >= '0' && n[i] <= '9')
            *sys = *sys * 10 + n[i] - '0';
        else if (n[i] != '-') return 0;
        else break;
        i++;
    }
    
    i++;
    if (!n[i]) return 0;
    // 将数值重新赋给变量 n
    while (n[i]) {
        if (map(n[i], *sys) == -1) {
            printf("符号 %c 非法，", n[i]);
            return 0;
        }
        else n[j++] = n[i++];
    }
    while (j < i) n[j++] = 0;
    return 1;
}

int other2dec(char *n, int sys) {
    int res = 0, i = 0; // i 是工作指针
    while (n[i]) {
        res = res * sys + map(n[i], sys);
        n[i] = 0;
        i++;
    }
    return res;
}

void dec2other(int n, int sys, char *res) {
    char tmp;
    int i = 0;
    while (n) {
        res[i++] = n % sys < 10 ? n % sys + '0' : n % sys - 10 + 'A';
        n /= sys;
    }
    for (int j = 0; j < i / 2; j++) {
        tmp = res[j];
        res[j] = res[i - j - 1];
        res[i - j - 1] = tmp;
    }
}

int main()
{
    printf("求任意两个不同进制(2-16)非负整数转换(输入形如:二进制表示为\"2-1010\"、16进制表示为\"16-A7C\")\n");
    char n[20] = {0}, res[20] = {0};
    int sys = 0, toSys = 0, i = 0; // i 是工作指针
    while (1)
    {
        sys = 0;
        while (i) res[--i] = 0;
        printf("输入\e[33m待转换的数\e[0m（格式如上）和\e[33m目标进制数\e[0m：");
        fflush(stdin); 
        scanf("%s", n);
        if (!handleInput(&sys, n)) { if (!sys) break; printf("输入有误，请重输\n"); continue; }
        
        scanf("%d", &toSys);
        if (toSys < 2 || toSys > 16) { printf("进制数输入有误，应在 2 到 16 之间，而非 %d。请重输\n", toSys); continue; }

        dec2other(other2dec(n, sys), toSys, res);

        while (res[i]) printf("\e[1;32m%c\e[0m", res[i++]);
        printf("\n");
    }
    return 0;
}
