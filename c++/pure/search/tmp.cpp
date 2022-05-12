/* 算法题
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-11-04 16:49:02
 * @LastEditors: hccodec
 * @LastEditTime: 2021-11-04 16:49:03
 */
#include <cstdio>
#include <cstdlib>
#include <ctime>

#define RED -1
#define WHITE 0
#define BLUE 1

void Swap(int &a, int &b) {
    int tmp = a;
    a = b;
    b = tmp;
}

void PrintColor(int a[], int n, int hint = -1) {
    for (int i = 0; i < n; i++) {
        switch (a[i]) {
            case -1: printf("\e[31m"); break;
            case 0: printf("\e[33m"); break;
            case 1: printf("\e[34m"); break;
        }
        printf("█\e[0m");
        // printf("%d\e[0m", i);
    }
    printf("\n");
}

void Flag_Arrange (int a[], int n) {
    int i = 0, j = 0, k = n - 1;
    while (j <= k) {
        switch (a[j]) {
            case RED: Swap(a[i++], a[j++]); PrintColor(a, n, j); break;
            case WHITE: j++; break;
            case BLUE: Swap(a[j], a[k--]); PrintColor(a, n, j); break;
        }
    }
}
int main(int argc, char const *argv[])
{
    int n = 50, i, color[n]; srand(time(NULL));
    for (i = 0; i < n; i++) color[i] = rand() % 3 - 1;
    PrintColor(color, n);
    Flag_Arrange(color, n);
    PrintColor(color, n);


    return 0;
}
