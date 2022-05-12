#include <stdio.h>

void printA(int A[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(", ");
        else printf("}\n");
    }
}

int main() {
    printf("输入一个正整数，输出由各个数位数字构成的最大数字\n");
    int n = 0, A[20] = {0}, len = 0, tmp, res = 0;
    while (1) {

        scanf("%d", &n);
        if (!n) break;

        res = 0, len = 0;
        // 从个位一次读取各个数位的数字并存到 A 数组中
        while (n) {
            A[len++] = n % 10;
            n = n / 10;
        }

        // A 数组逆置
        for (int i = 0; i < len >> 1; i++) {
            tmp = A[i];
            A[i] = A[len - i - 1];
            A[len - i - 1] = tmp;
        }

        // A 数组排序
        for (int i = 0; i < len - 1; i++)
            for (int j = 0; j < len - i - 1; j++)
                if (A[j] < A[j + 1]) {
                    tmp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = tmp;
                }

        for (int i = 0; i < len; i++) res = res * 10 + A[i];
        printf("结果为 %d\n", res);
        
        
    }


    return 0;
}