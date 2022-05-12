#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

struct Point {
    int x;
    int y;
    Point operator-(Point p) { return {p.x - x, p.y - y}; }; // B - A 计算向量 AB
    float operator*(Point p) { return p.y * x - p.x * y; }; // B - A 计算向量 AB
} *Points;

int check(Point p[], int n, int x, int y) {
    for (int i = 0; i < n; i++)
        if (p[i].x == x && p[i].y == y) return 0;
    return 1;
}
double distance(Point A, Point B) {
    return sqrt(pow(A.x - B.x, 2) + pow(A.y - B.y, 2));
}

double getArea(Point A, Point B, Point C) {
    if (1) {
        // 海伦公式解法
        double a = distance(A, B), b = distance(B, C), c = distance(A, C), p = (a + b + c) / 2;
        // 计算三边长 a, b, c 以及
        return sqrt(p * (p - a) * (p - b) * (p - c));
    } else {
        // 向量叉乘解法
        return fabsf((A - B) * (A - C) / 2);
        
    }
}

double findMaxTriangle(Point p[], int n, int res[3]) {
    double area = 0;
    int flag = 1;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            for (int k = 0; k < n; k++) {
                if (i - j && j - k && i - k && area < getArea(p[i], p[j], p[k])) {
                    area = getArea(p[i], p[j], p[k]);
                    res[0] = i, res[1] = j, res[2] = k;
                    if (--flag) return area;
                }
            }
    return area;
}

int main() {
    printf("输出 100 个点中某三点构成的三角形中，面积最大的三角形面积\n");
    srand((unsigned int)time(NULL));
    Point p[100] = {0};
    int x = rand() % 100, y = rand() % 100, res[3] = {0};
    double maxArea = 0;
    for (int i = 0; i < 100; i++) {
        while ((!check(p, i, x, y))) {
            x = rand() % 100;
            y = rand() % 100;
        }
        p[i] = {x, y};
    }
    // 打印出这 100 个点
    for (int i = 0; i < 100; i++) {
        if (!(i % 10)) printf("\n[%2d]", i + 1);
        if (!(i % 5)) printf("   ");
        printf("(%2d, %2d) ", p[i].x, p[i].y);
    }

    maxArea = findMaxTriangle(p, 100, res);
    printf("\n\n第 %d, %d 和 %d 个点构成的三角形面积最大, 面积值约为 %.2lf", res[0] + 1, res[1] + 1, res[2] + 1, maxArea);
    return 0;
}
