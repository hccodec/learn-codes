#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>

void printA(int A[], int n) {
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", A[i]);
        if (n - i - 1) printf(" ");
    }
    printf("}\n");
}

int prime(int n) {
    for (int i = 2; i < sqrt(n); i++) {
        if (!(n % i)) return 0;
    }
    return 1;
}

int delarr(int a[], int n) {
    int k = 0;
    for (int i = 0; i < n; i++) {
        if (prime(a[i])) k++;
        else a[i - k] = a[i];
    }
    return n - k;
}

int main () {
    printf("删素数\n");
    int A[10], res;
    srand((unsigned int)time(NULL));
    for (int i = 0; i < 10; i++) A[i] = rand() % 100;
    printA(A, 10);
    res = delarr(A, 10);
    printA(A, res);
    return 0;
}