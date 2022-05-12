/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-12 19:49:08
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-14 09:40:15
 */
#include <stdio.h>
#include <math.h>

void powerset(int n) {
    int m = pow(2, n);
    if (n > 10) {
        printf("共 %d 个子集\n", m);
        return;
    }
    int subsets[n];
    int nums; // 当前已输出的子集元素个数
    for (int i = 0; i < m; i++) {
        printf("{");
        nums = 0;
        for (int j = 0; j < n; j++) {
            if (i & 1 << j) subsets[nums++] = j;
        }
        // 输出子集内容
        for (int j = 0; j < nums; j++) {
            printf("%d", subsets[j]);
            if (j < nums - 1) printf(", ");
        }
        printf("}\n", subsets[nums - 1]);
        
    }
    printf("共 %d 个子集\n", m);
}

int main() {
    printf("输出子集\n");
    int n;
    while (1) {
        scanf("%d", &n);
        if (n > 49) break;
        powerset(n);
    }
    return 0;
}
