#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int a[], int len, int n) {
    int i = 0;
    while (i++ < n - len) printf(" ");
    i = 0;
    while (i < len) printf("%d", a[i++]);
}

int maxPlatform(int a[], int n) {
    int len = 1, max = 0;
    for (int i = 1; i < n; i++) {
        if (a[i] == a[i - 1]) len++;
        else len = 1;
        if (len > max) max = len;
    }
    return max;
}

int main() {
    printf("求数位递减的数字的最长平台长度\n");
    srand((unsigned int)time(NULL));
    const int n = 25;
    int a[n] = {0}, len = 0, count = 10;
    while (count--) {
        if (count & 1) {
            printf("\n");
            continue;
        }
        len = 0;
        a[0] = 9 - rand() % 3;
        while (a[len] && len < n) {
            a[len + 1] = a[len] - rand() % 2;
            len++;
        }
        printA(a, len, n);
        printf(" - %d\n", maxPlatform(a, len));
    }



    return 0;
}