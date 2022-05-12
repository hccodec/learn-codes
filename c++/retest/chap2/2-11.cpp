/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 12:49:53
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 15:42:42
 */
#include <stdio.h>

int isReapYear(int year) {
    return year % 100 ? year % 4 == 0 : year % 400 == 0;
}

int isLegal(int year, int month, int day) {
    if (day <= 0 || month <= 0 || month > 12) return 0;
    switch (month) {
        case 2:
            return day <= (isReapYear(year) ? 29 : 28);
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            return day <= 31; 
        default: return day <= 30;
    }
}

int month2day(int year, int month, int day) {
    switch (--month) {
        case 11:    day += 30;
        case 10:    day += 31;
        case 9:     day += 30;
        case 8:     day += 31;
        case 7:     day += 31;
        case 6:     day += 30;
        case 5:     day += 31;
        case 4:     day += 30;
        case 3:     day += 31;
        case 2:     day += isReapYear(year) + 28;
        case 1:     day += 31;
        case 0:     default:    break;
    }
    return day;
}

int main() {
    printf("给出年月日，计算该日是该年的第几天 (形如xxxx.x.x)\n");
    int year = 1, month = 1, day = 1;
    while (year && month && day) {
        year = 1, month = 1, day = 1;
        scanf("%d.%d.%d", &year, &month, &day);
        if (!(year && month && day)) return 0;
        if (!isLegal(year, month, day)) {
            printf("\e[31m日期输入不合法\e[0m\n");
            continue;
        }
        printf("%d 年 %d 月 %d 日是 %d 年的第 \e[1;32m%d\e[0;0m 天\n", year, month, day, year, month2day(year, month, day));
    }
    return 0;
}