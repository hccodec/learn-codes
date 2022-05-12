/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-13 20:34:15
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-14 17:17:43
 */
#include <stdio.h>
#include <time.h>
#include <math.h>

int judgePrint(int printThreshold, int m, int n) {
    return n >= printThreshold && (n > 28 || abs(m - n / 2) < n * 4 / 10);
}

int main () {
    printf("求 S 所有元素（N 个）的个数为 M 的子集\n");
    int m = 0, n = 0, count = 0, printThreshold = 16, lineFeed = 200;
    while (1) {
        printf("依次输入 m 和 n: ");
        scanf("%d%d", &m, &n);

        if (!m && !n) break;
        if (m > n) { printf("需 m < n,请重输\n"); continue; }
        // if (n > 13) { printf("越界，要求 n <= 13，重试\n"); continue; }

        int sets[n] = {0}, count = 0, l = 0; // l 是数组指针，只在添加元素时增，清零时减
        if (n > sqrt(INT_MAX)) { printf("n 不超过 %d,请重输\n", sqrt(INT_MAX)); continue; }
        // 添加元素到子集，这里使用 i 的二进制形式的每一位与数组元素一一对应并用其值（1和0）表示存在与否
        for (int i = 0; i < pow(2, n); i++) {
            while (l) sets[--l] = 0;  // 数组清零

            // 遍历集合中的所有元素，用 i 的按位与运算检查元素 j 的状态是否为“存在”
            for (int j = 0; j < n; j++) if (i & 1 << j) sets[l++] = j + 1;
                
            // 输出符合条件的子集内容
            if (l == m) {
                count++;
                if (judgePrint(printThreshold, m, n)) {
                    if (!((count - 1) % lineFeed)) printf("\n[%6d]", count);
                    printf("\e[1;32m□\e[0;0m");
                }
                else {
                    printf("{");
                    for (int j = 0; j < m; j++) {
                        printf("%d", sets[j]);
                        if (j + 1 < m)printf(", ");
                    }
                    printf("} ");
                }
            }
        }

        if (judgePrint(printThreshold, m, n) && count % lineFeed) printf("(%d)", count % lineFeed);
        
        if (count <= 10000) printf("\n共 %d 条记录\n", count);
        else printf("\n共 %d * %d + %d = \e[1;32m%d\e[0;0m 条记录\n", count / lineFeed, lineFeed, count % lineFeed, count);
    }
    return 0;
}