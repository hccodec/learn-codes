/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 16:45:43
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 17:33:17
 */
#include <stdio.h>

void bubblesort(int *k, int n) {
    int tmp = 0;
    for (int i = 0; i < n; i++) for (int j = i; j < n; j++) {
        if (k[i] >= k[j]) {
            tmp = k[i];
            k[i] = k[j];
            k[j] = tmp;
        }
    }
}

int main() {
    printf("输入三个整数并从小到大输出: \n");
    int a = 1, b = 1, c = 1;
    while (a || b || c) {
        scanf("%d %d %d", &a, &b, &c);
        int k[3] = {a, b, c};
        bubblesort(k, 3);
        for (int i = 0; i < 3; i++) printf("\e[1;32m%d\e[0m ", k[i]);
        printf("\n");
    }
    return 0;
}