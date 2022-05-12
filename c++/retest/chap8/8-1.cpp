#include <stdio.h>

int main() {
    printf("s=");
    int n = 1;
    float res = 0, term = 1;
    while (term > 1e-6) {
        term = 1.0 * 2.0 * n / (2 * n - 1) / (2 * n - 1);
        res += term;
        n++;
    }
    printf("%f", res);
    return 0;
}