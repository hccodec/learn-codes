#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void swap(int *a, int *b) {
    *a += *b;
    *b = *a - *b;
    *a -= *b;
}

void sort(int a[], int n) {
    n--;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n - i; j++)
            if (a[j] > a[j + 1])
                swap(&a[j + 1], &a[j]);
}

int search(int a[], int start, int end, int target) {
    if (start > end) {
        return -1;
    }
    int mid = (end + start) / 2;
    if (a[mid] == target) return mid;
    else if (a[mid] > target) return search(a, start, mid - 1, target);
    else return search(a, mid + 1, end, target);
}

int main() {
    printf("递归 - 折半查找\n");
    srand((unsigned int)time(NULL));
    const int n = 20;
    int a[n] = {0}, tmp = 0, res = 0;
    for (int i = 0; i < n; i++) {
        tmp = rand() % 40;
        for (int j = 0; j < i; j++) {
            if (tmp == a[j]) {
                tmp = rand() % 40;
                j = 0;
            }
        }
        a[i] = tmp;
    }
    sort(a, n);
    for (int i = 0; i < n; i++) printf("%2d|", i + 1);
    printf("\n");
    for (int i = 0; i < n; i++) printf("%2d ", a[i]);
    printf("\n");
    tmp = rand() % 20;
    res = 1 + search(a, 0, n - 1, tmp);
    if (res) printf("元素 %d 在第 %d 个位置", tmp, 1 + search(a, 0, n - 1, tmp));
    else printf("未找到元素 %d", tmp);

    return 0;
}