/*
 * @Description: 判断是否是机器人对话
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-16 21:31:46
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 22:12:29
 */

#include <stdio.h>

int judge(char str[], int len)
{
    int i = 0, j = 0;
    if (str[0] != 'Y' && str[0] != 'N')
        return 0; // conversation must begin by M1
    while (i < len)
    {
        j = i;
        while (str[i] == 'Y' || str[i] == 'N')
            i++; // M1 talking
        if (j != i)
        { // M1 talked
            if (!str[i])
            {
                printf("M1 未说完——");
                return 0;
            } // M1 hanging
            else if (str[i++] != '2')
                return 0; // M1 without tail
        }
        else if (str[i])
            return 0; // M1 should has talked but no
        j = i;
        while (str[i] == 'y' || str[i] == 'n')
            i++; // M2 talking
        if (j != i)
        { // M2 talked
            if (!str[i])
            {
                printf("M2 未说完——");
                return 0;
            } // M2 hanging
            else if (str[i++] != '1')
                return 0; // M2 without tail
        }
        else if (str[i])
            return 0; // M2 should has talked but no
    }
    return 1;
}

int main()
{
    printf("判断是否是两机器人对话：先手M1(Y, N, 2), 后手M2(y, n, 1)\n");
    char str[100] = {0};
    int len = 0, i = 0;
    while (1)
    {
        i = len = 0;
        scanf("%s", str);
        while (str[len])
            len++;
        if (len == 1 && str[0] == '0')
            break; // 遇单独 0 结束程序
        // 依次扫描并判断
        ;
        if (judge(str, len))
            printf("是机器人对话\n");
        else
            printf("不是机器人对话\n");
    }
    return 0;
}