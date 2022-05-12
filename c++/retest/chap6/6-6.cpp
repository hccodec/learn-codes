#include <stdio.h>

int judge(int A[], int n) {
    int i = 0, tsum = 0;
    while (i < n) {
        tsum += A[i - 1];
        if (tsum == A[i]) break;
        i++;
    }
    return n - i;
}

int main() {
    printf("判断数组中某一元素是否是其前面元素之和\n");
    const int n = 10;
    int A[n] = {1, 1, 2, 3, 5, 8, 13, 21, 55, 76};

    if (judge(A, n)) printf("通过\n");
    else printf("不通过\n");

    return 0;
}