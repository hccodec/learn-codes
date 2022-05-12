/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 23:18:13
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 23:38:38
 */
#include <stdio.h>

int main() {
    printf("将整数 M 的各位保存到数组中\n");
    int m = 1, k = 0, s[9] = {0};
    while (1) {
        k = 0;    
        scanf("%d", &m);
        if (!m) break;
        // 从个位开始，逐个填入数组
        while (m) {
            s[k++] = m % 10;
            m /= 10;
        }
        // 数组原地逆置
        for (int i = 0; i < k / 2; i++) {
            s[i] = s[i] + s[k - i - 1];
            s[k - i - 1] = s[i] - s[k - i - 1];
            s[i] = s[i] - s[k - i - 1];
        }
        for (int i = 0; i < k; i++) printf("%d ", s[i]);
        printf("\n");
    }
    return 0;
}