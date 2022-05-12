#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int sum(int a[], int n) {
    if (!n) return 0;
    return a[--n] + sum(a, n);
}


int main() {
    printf("n 个整数在数组 a 中，求整数和\n");
    srand((unsigned int)time(NULL));
    const int n = 10;
    int a[n] = {0};
    for (int i = 0; i < n; i++) a[i] = rand() % n;
    printf("数组为：{");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (n - i - 1) printf(", ");
        else printf("}, 和为 %d", sum(a, n));
    }
    return 0;
}