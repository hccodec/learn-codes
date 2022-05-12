#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

struct Point {
    int x;
    int y;
};

int check(Point p[], int n, Point p0) {
    for (int i = 0; i < n; i++) {
        if (p[i].x == p0.x && p[i].y == p0.y) return 0;
    }
    return 1;
}

// 两点距离公式的平方
int distance(Point a, Point b) {
    return pow(a.x - b.x, 2) + pow(a.y - b.y, 2);
}

// 求点集合中最长的距离
float maxLen(Point p[], int n, int *ip1, int *ip2) {
    int max2 = 0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            if (i - j && max2 < distance(p[i], p[j])) {
                max2 = distance(p[i], p[j]);
                *ip1 = i;
                *ip2 = j;
            }
    return sqrt(max2);
}

int main() {
    const int n = 100;
    printf("在 %d 个点中选出距离最大的两个点，并输出这两点间的距离\n", n);
    srand((unsigned int)time(NULL));
    // 正文
    Point p[n] = {0};
    int x = 0, y = 0, bias = 0, ip1 = 0, ip2 = 0;
    float res = 0;
    printf("===================================================");
    // 随机生成数值
    for (int i = 0; i < n; i++) {
        x = rand() % (int)(n * 10);
        y = rand() % (int)(n * 10);
        bias = 0;
        while (!check(p, i, {x, y})) {
            x += bias++;
            y += bias++;
        }
        p[i] = {x, y};
        
        if (!(i % 10)) printf("\n[%2d]", i / 10 + 1);
        if (!(i % 5)) printf("   ");

        printf(" (%*d, %*d)", (int)log10((double)(n * 10)), p[i].x, (int)log10((double)(n * 10)), p[i].y);
    }
    printf("\n===================================================\n");
    res = maxLen(p, n, &ip1, &ip2);
    printf("第 %d 个点和第 %d 个点距离最大，距离为 %.2f", ip1 + 1, ip2 + 1, res);

    return 0;
}
