#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>

struct Point {
    float x;
    float y;
    Point operator-(Point p) { return {p.x - x, p.y - y}; };
    float operator*(Point p) { return x * p.x + y * p.y; };
    float operator^(Point p) { return p.y * x - p.x * y; };
};

int equals(float a, float b) { return a > b ? a - b < 1e-5 : b - a < 1e-5; }

inline float getArea(Point A, Point B, Point C) {
    return fabsf(((B - A) ^ (C - A)) / 2);
}

// 判断 p1, p2 两个点是否在直线 ab 的同侧
inline int aside(Point p1, Point p2, Point a, Point b) {
    return ((b - a) ^ (p1 - a)) * ((b - a) ^ (p2 - a)) > 0;
}

inline int IsPointInTriangle3(Point A, Point B, Point C, Point D) {
    Point AB = B - A, AC = C - A, AD = D - A;
    float q, u, v;
    q = (AC * AC) * (AB * AB) - (AC * AB) * (AB * AC);
    u = (AB * AB) * (AD * AC) - (AC * AB) * (AD * AB);
    v = (AC * AC) * (AD * AB) - (AC * AB) * (AD * AC);
    u /= q;
    v /= q;
    return u >= 0 && u <= 1 && v >= 0 && v <= 1;
}

inline int IsPointInTriangle4(Point A, Point B, Point C, Point D) {
    Point DA = A - D, DB = B - D, DC = C - D;
    float t1 = DA * DB, t2 = DB * DC, t3 = DC * DA;
    int res = 0;
    if (!(t1 && t2 && t3)) {
        printf("计算出错：t1=%.2f, t2 = %.2f, t3 = %.2f", t1, t2, t3);
        return 0;
    }
    if (t1 > 0) res++; else res--;
    if (t2 > 0) res++; else res--;
    if (t3 > 0) res++; else res--;
    return abs(res) == 3;

}

int main() {
    printf("判断 D 点是否落在 A、B、C 三点构成的三角形内\n");
    srand((unsigned int)time(NULL));
    Point A = {0, 0}, B = {0, 0}, C = {0, 0}, D = {0, 0};
    float x = 0, y = 0;
    int res = 0;

    switch(3) {
        case 1:
            A = {0, 0}, B = {4, 0}, C = {1, 2}, D = {1,2};
            break;
        case 2:
            printf("请依次输入构成三角形的三个点 A, B, C 的坐标以及 D 点坐标\n");
            printf("A: "); scanf("%f,%f", &x, &y);
            if (equals(x, 123) || equals(y, 123)) return 0;
            A = {x, y};
            fflush(stdin);
            printf("B: "); scanf("%f,%f", &x, &y);
            if (equals(x, 123) || equals(y, 123)) return 0;
            B = {x, y};
            fflush(stdin);
            printf("C: "); scanf("%f,%f", &x, &y);
            if (equals(x, 123) || equals(y, 123)) return 0;
            C = {x, y};
            fflush(stdin);
            printf("D: "); scanf("%f,%f", &x, &y);
            if (equals(x, 123) || equals(y, 123)) return 0;
            D = {x, y};
            fflush(stdin);
            break;
        case 3:
            float tmp = rand() % 5 + 1;
            while (!((B - A) * (C - A)))
            A = {rand() % 50 / tmp, rand() % 50 / tmp};
            B = {rand() % 50 / tmp, rand() % 50 / tmp};
            C = {rand() % 50 / tmp, rand() % 50 / tmp};
            D = {rand() % 50 / tmp, rand() % 50 / tmp};
            break;
    }

    printf("点 (%.2f, %.2f) 是否在由 (%.2f, %.2f) (%.2f, %.2f) (%.2f, %.2f) 三点构成的三角形内：", D.x, D.y, A.x, A.y, B.x, B.y, C.x, C.y);
    // 以下分别用 4 种方法判断
    // 1: 面积法
    if (equals(getArea(A, B, D) + getArea(A, C, D) + getArea(C, B, D), getArea(A, B, C))) printf("\e[32m1√ \e[0m", res++);
    // 2: 叉乘符号法：A、D两点在线BC同侧；B、D两点在线AC同侧；C、D两点在线AB同侧
    if (aside(A, D, B, C) && aside(B, D, A, C) && aside(C, D, A, B)) printf("\e[32m2√ \e[0m", res++);
    // 3: 向量公式法：在 向量式 AP = uAC + vAB 中，若 P 在三角形内部则 u, v ∈ [0 , 1] 且 u + v <= 1;
    if (IsPointInTriangle3(A, B, C, D)) printf("\e[32m3√ \e[0m", res++);
    // 3: 叉乘符号法：A、D两点在线BC同侧；B、D两点在线AC同侧；C、D两点在线AB同侧
    if (IsPointInTriangle4(A, B, C, D)) printf("\e[32m3√ \e[0m", res++);
    printf(" → ");
    switch (res) {
        case 4:  printf("在内"); break;
        default: printf("不在内"); break;
    }

    return 0;
}