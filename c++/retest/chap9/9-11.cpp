#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

inline int equals(float a, float b) {
    return a > b ? a - b < 1e-5 : b - a < 1e-5;
}

int main() {
    printf("求 100 个点构成的三角形中面积最大的三角形及其面积");
    srand((unsigned int)time(NULL));
    float ax[100], ay[100], tmp = rand() % 50 + 1;
    for (int i = 0; i < 100; i++) {
        ax[i] = rand() % 100 / tmp;
        ay[i] = rand() % 100 / tmp;
    }
    for (int i = 0; i < 100; i++) {
        if (!(i % 10)) printf("\n[%2d] ", (i / 10) * 10 + 1);
        printf("(%5.2f,%5.2f) ", ax[i], ay[i]);
    }

    float max = 0, area = 0;
    int A = 0, B = 0, C = 0;

    // 找到面积最大的第一个组合
    for (int i = 0; i < 100; i++)
        for (int j = 0; j < 100; j++)
            for (int k = 0; k < 100; k++) {
                area = fabsf(((ax[j] - ax[i]) * (ay[k] - ay[i]) - (ay[j] - ay[i]) * (ax[k] - ax[i]))) / 2;
                if (max < area) {
                    max = area;
                    A = i, B = j, C = k;
                }
            }

    int count = 0;
    printf("\n====================================================================================================\n能构成面积最大三角形的组合为");

    // 从第一个组合开始往后遍历，找到面积同样最大的其他组合
    for (int i = A; i < 100; i++)
        for (int j = B; j < 100; j++)
            for (int k = C; k < 100; k++) {
                area = fabsf(((ax[j] - ax[i]) * (ay[k] - ay[i]) - (ay[j] - ay[i]) * (ax[k] - ax[i]))) / 2;
                if (equals(max, area)) {
                    printf("\n[%2d]\t[%2d](%.2f, %.2f)\t[%2d](%.2f, %.2f)\t[%2d](%.2f, %.2f)\t%.2f", ++count, i, ax[i], ay[i], j, ax[j], ay[j], k, ax[k], ay[k], area);
                }
            }
    


    return 0;
}