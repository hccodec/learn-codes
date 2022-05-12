/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-12 17:18:20
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-13 10:35:16
 */
#include <stdio.h>

int insert(int *M, int length, int t) {
    // 得出新数应该插入的位置 i
    if (M[length - 1] < t) M[length] = t;
    else for (int i = length - 1; i > 0; i--) {
        if (M[i] == t) return 0;
        if (M[i] > t && M[i - 1] < t) {
            // 得出 i 后，将 i 及其后面的元素集体后移一位
            for (int j = length; j > i; j--) M[j] = M[j - 1];
            M[i] = t;
        }
    }
    return 1;
}

int main() {
    printf("求集合 M 的前100个元素\n");
    int M[200] = {0}, i = 0, length = 1, t = 0;
    M[0] = 1;
    while (length <= 100) {
        if(insert(M, length, 2 * M[i] + 1)) length++;
        if(insert(M, length, 3 * M[i] + 1)) length++;
        i++;
    }
    
    for (int i = 0; i < 100; i++) {
        if (i % 20 == 0) printf("\n%d| ", i / 20 + 1);
        printf(" %3d", M[i]);
    }

    return 0;
}