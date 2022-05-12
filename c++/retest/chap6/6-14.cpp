#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printA(int A[10][10]) {
    printf("\n----------------");
    for (int i = 0; i < 100; i++) {
        if (!(i % 10)) printf("\n");
        printf("%2d ", A[i / 100][i % 100]);
    }
    printf("\n----------------");
}

void sort(int A[10][10]) {
    int tmp;
    for (int i = 0; i < 99; i++) {
        for (int j = 0; j < 99 - i; j++)
            if (A[j / 100][j % 100] > A[(j + 1) / 100][(j + 1) % 100]) {
                tmp = A[j / 100][j % 100];
                A[j / 100][j % 100] = A[(j + 1) / 100][(j + 1) % 100];
                A[(j + 1) / 100][(j + 1) % 100] = tmp;
            }
    }
}

int main() {
    printf("\n");
    int A[10][10] = {0};
    srand((unsigned int)time(NULL));
    for (int i = 0; i < 100; i++) A[i / 100][i % 100] = rand() % 100;
    printA(A);
    sort(A);
    printA(A);

    return 0;
}