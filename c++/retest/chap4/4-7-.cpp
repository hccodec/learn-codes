/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-13 20:34:15
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-14 10:33:15
 */
#include <stdio.h>
#include <math.h>


struct subsets{
    int length = 0;
    int vals[50] = {0};
} *psubsets;

int main () {
    printf("求 S 所有元素（N 个）的个数为 M 的子集\n");
    int m = 0, n = 0, count = 0;
    while (1) {
        scanf("%d%d", &m, &n);
        // m = 2, n = 3;
        if (!m && !n) break;
        if (m > n) {
            printf("需 m < n,请重输\n");
            continue;
        }
        if (n > 13) {
            printf("越界，要求 n <= 13，重试\n");
            continue;
        }
        subsets sets[(int)pow(2, n)]; // 获取到 n 再分配数组空间
        count = 0;

        // 初始化子集结构体
        for (int i = 0; i < pow(2, n); i++) {
            for (int j = 0; j < 50; j++) sets[i].vals[j] = 0;
            sets[i].length = 0;
        }
        
        // 添加元素到子集
        for (int i = 0; i < pow(2, n); i++) {
            for (int j = 0; j < n; j++)
                if (i & 1 << j) {
                    sets[i].vals[sets[i].length++] = j + 1;
                }
            // 统计符合条件的子集个数
            if (sets[i].length == m) count++;
        }

        if (count <= 200) {
            for (int i = 0; i < pow(2, n); i++) {
                // 输出符合条件的子集内容
                if (sets[i].length == m) {
                    printf("{");
                    for (int j = 0; j < m; j++) {
                        printf("%d", sets[i].vals[j]);
                        if (j + 1 < m)printf(", ");
                    }
                    printf("} ");
                }
            }
        }
        if (count > 20) {
            if (count <= 200) printf("\n");
            printf("共 %d 条记录", count);
        }
        printf("\n");
    }
    return 0;
}