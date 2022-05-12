#include <stdio.h>

// 用辗转相除法对分式进行通分
void reduction(int *child, int *mom) {
    int max = *child > *mom ? *child : *mom, min = *child + *mom - max, rest = min;
    while (rest) {
        min = rest;
        rest = max % min;
        max = min;
    }
    if (max - min) printf("出错");
    *child /= min, *mom /= min;
}

int main() {
    printf("调和级数前 n 项和，要求形如 A / B\n：n = ");
    int n = 0, a = 1, b = 1, resa = 1, resb = 1;
    while (1) {
        a = b = resa = resb = 1;
        scanf("%d", &n);
        if (!n) break;
        while (n - b) {
            b++;
            resa = resa * b + resb * a;
            resb = resb * b;
            reduction(&resa, &resb);
        }
        printf("%d/%d\n", resa, resb);
        
    }
}