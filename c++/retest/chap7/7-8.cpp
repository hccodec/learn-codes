#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int check(int nums[], int len, int k) {
    for (int i = 0; i < len; i++) if (nums[i] == k) return i;
    return len;
}

int main() {
    srand((unsigned int)time(NULL));
    const int n = 10;
    int len = 0, k = 0;

    int A[n] = {0};
    for (int i = 0; i < n; i++) A[i] = rand() % 10;
    // int A[n] = {3, 5, 3, 3, 10, 7, 7, 5, 3, 7};

    int nums[n] = {0}, times[n] = {0}; // A 是原数组、B 依次存放出现的新元素、C 记录 B 中元素在 A 中出现的次数
    for (int i = 0; i < n; i++) {
        // 检查 nums 中是否已有 A[i]
        k = check(nums, len, A[i]);
        if (len - k) {
            times[k]++;
        }
        else {
            nums[k] = A[i];
            times[k++]++;
            len++;
        }
    }
    printf("{");
    for (int i = 0; i < n; i++) printf(n - i - 1 ? "%d " : "%d", A[i]);
    printf("}\n");
    for (int i = 0; i < len; i++) {
        printf("%-2ld 出现的次数是%2d\n", nums[i], times[i]);
    }
        
    return 0;
}