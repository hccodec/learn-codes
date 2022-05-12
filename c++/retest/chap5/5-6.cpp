#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    printf("数组两向量位置交换\n");
    const int lenthr = 20;
    int tmp;
    srand((unsigned int)time(NULL));

    int m = rand() % (lenthr >> 1) + 6, n = 0;
    while (m >= n || m < n && n - m < m / 3) n = rand() % lenthr;
    printf("m = %d, n = %d", m, n);
    int *A = (int *)malloc(sizeof(int) * (m + n));
    for (int i = 0; i < m + n; i++) if (m > i) A[i] = i + 1; else A[i] = 51 - m + i;

    printf("\n{"); for (int i = 0; i < m + n; i++) { printf("%d", A[i]); if (m + n - i - 1) printf(" "); else printf("}"); }

    ////////////////// 答题 ///////////////////////

    // 整体逆置 -> 做逆置 -> 右逆置
    for (int i = 0; i < (m + n) >> 1; i++) { tmp = A[i]; A[i] = A[m + n - i - 1]; A[m + n - i - 1] = tmp; }

    printf("\n{"); for (int i = 0; i < m + n; i++) { printf("%d", A[i]); if (m + n - i - 1) printf(" "); else printf("}"); }


    for (int i = 0; i < n >> 1; i++) { tmp = A[i]; A[i] = A[n - 1 - i]; A[n - 1 - i] = tmp; }

    // 总结：该行代码功能是将后半段数组进行逆置，由于变量 i 在参与计算数组下标时既有加法又有减法，故不能简单地将其看做指针并让 i 从 n 递增，而只能让 i 从 0 递增。
    // 之后，为了让代码从后半段第一个元素（而非 i 所指的 0 号元素）开始处理，只能将前半段长度值加到数组下标中，从而使代码作用范围向后偏移
    for (int i = 0; i < m >> 1; i++) { tmp = A[n + i]; A[n + i] = A[m + n - 1 - i]; A[m + n - 1 - i] = tmp; }

    ////////////////// 答题 ///////////////////////

    printf("\n{"); for (int i = 0; i < m + n; i++) { printf("%d", A[i]); if (m + n - i - 1) printf(" "); else printf("}"); }

    return 0;
}