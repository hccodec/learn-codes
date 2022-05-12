#include <stdio.h>

void printA(int A[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}\n");
}

int main() {
    printf("用数组计算小数。依次输入被除数和除数\n");
    int M = 1, N = 4;
    int A[50] = {0}, B[50] = {0}, n = 0, start = 0, end = 0;
    while (1) {
        scanf("%d%d", &M, &N);
        fflush(stdin);
        if (!M || !N) break;
        n = start = end = 0;
        printf("%d ÷ %d = ", M, N);
        if (M >= N) {
            printf("%d", M / N);
            M = M % N;
            if (M) printf(".");
        }
        else printf("0.");

        while (M) {
            M *= 10;
            A[n] = M / N;
            M = M % N;
            B[n] = M;
            for (int i = 0; i < n; i++)
                if (B[i] == M) {
                    start = ++i;
                    end = n;
                    M = 0;
                }
            n++;
        }
        for (int i = 0; i < n - 1 + !(start && end); i++) printf("%d", A[i]);
        if (start && end) {
            if (start - end) printf("(从第 %d 位到第 %d 位循环)\n", start, end);
            else printf("(第 %d 位循环)\n", start);
        }
        printf("\n");
    }
}