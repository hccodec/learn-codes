#include <stdio.h>
#include <math.h>

double thr = 1e-3;

struct Point {
    double x;
    double y;
};

struct Line {
    // 直线的一般表示法：Ax + By + C = 0
    double A;
    double B;
    double C;
};

struct Circle {
    Point c; // 圆心
    double r; // 半径
};

int equals(double a, double b) {
    return a > b ? (a - b < thr) : (b - a < thr);
}

// 理论基础：外接圆的圆心是三点构成的三角形三条边的垂直平分线的交点
int main() {
    printf("求任意三点外接圆\n\n");
    Point A, B, C;
    int flag = 1;

    double distance(Point a, Point b);      // 求两点间距离
    Line PB(Point a, Point b);              // 求两点的垂直平分线
    Point getIntersection(Line a, Line b);  // 求两条线的交点

    while (flag) {

        printf("请依次输入三个点在直角坐标系下的坐标 (遇 123 则退出)");
        
        if (1) {
            // 依次接收三个点坐标的输入
            printf("\n点 A: ");
            scanf("%lf,%lf", &A.x, &A.y);
            if (A.x == 123 || A.y == 123) break;
            fflush(stdin);
            printf("点 B: ");
            scanf("%lf,%lf", &B.x, &B.y);
            if (B.x == 123 || B.y == 123) break;
            fflush(stdin);
            printf("点 C: ");
            scanf("%lf,%lf", &C.x, &C.y);
            if (C.x == 123 || C.y == 123) break;
            fflush(stdin);
        }
        else {
            A = {0, 1}, B = {2, 1}, C = {1, 2};
            flag--;
        }

        // 判断三点是否共线
        if ((B.x - A.x) * (C.y - B.y) == (B.y - A.y) * (C.x - B.x)) {
            printf("这三点没有外接圆，请重输\n\n");
            continue;
        }

        Line l1 = PB(A, B), l2 = PB(B, C);
        printf("\n三点 (%.2lf, %.2lf) (%.2lf, %.2lf) (%.2lf, %.2lf)，", A.x, A.y, B.x, B.y, C.x, C.y);
        Point O = getIntersection(l1, l2);
        printf("外接圆的圆心是 (%.2lf, %.2lf)，半径为 %.4lf 。", O.x, O.y, distance(A, O));
        if (equals(distance(A, O), distance(B, O)) && equals(distance(B, O), distance(C, O))) printf(" \e[32m[√]\e[0m\n");
        else printf("\n检验不通过: AO = %lf, BO = %lf, CO = %lf, AO-BO = %f, BO-CO = %f", distance(A, O), distance(B, O), distance(C, O), distance(A, O) - distance(B, O), distance(B, O) - distance(C, O));

        printf("\n");        
    }
    return 0;
}


// 以下是对浮点数求最小公倍数相关代码
double getLeft(double a, double b) {
    if (!b) {
        printf("\n[log] 异常，除数不应为 0 ");
        return 0;
    }
    if (a <= b) return 0;
    while (a >= b) a -= b;
    return a;
}

double LCM(double a, double b) {
    double tmp = a * b;
    if (a < b) {
        a += b;
        b = a - b;
        a -= b;
    }
    double LCM = getLeft(a, b);
    // 辗转相除法 找 最大公因数
    while (LCM > thr) {
        a = b;
        b = LCM;
        LCM = getLeft(a, b);
    }
    // printf("得出最大公约数为 %lf\n", b);
    return tmp / b;
}

double LCM(double *a, double *b, double *c, int mode = 1) {
    int cond0[3] = {0}, sign[3] = {0}, cond = 0; // cond0 记录 a, b, c 是否为 0，sign 记录 a, b, c 是否为负
    double res = 0;
    if (*a < 0) { *a = -*a; sign[0] = 1; }
    if (*b < 0) { *b = -*b; sign[1] = 1; }
    if (*c < 0) { *c = -*c; sign[2] = 1; }
    if (equals(*a, 0)) { cond0[0] = 1; cond++; }
    if (equals(*b, 0)) { cond0[1] = 1; cond++; }
    if (equals(*c, 0)) { cond0[2] = 1; cond++; }

    if (cond == 3) *a = *b = *c = 0; // 若三个值都为 0，则全置 0
    else if (cond == 2) {
        // 如果有两个值全接近 0，则找出非零的值（）并把它置为 1，其余的置为 0
        for (int i = 0; i < 3; i++) {
            if (!cond0[i]) {
                *a = *b = *c = 0;
                // 找出非 0 的，并用 switch 语句置其为 1
                switch(i) {
                    case 0: *a = 1; break;
                    case 1: *b = 1; break;
                    case 2: *c = 1; break;
                }
                break;
            }
        }
        res = 1;
    } else if (cond == 1) {
        // 若仅一个值为 0，则置其为 0，并对其余两数进行转换
        for (int i = 0; i < 3; i++) {
            if (cond0[i]) {
                // 找出非 0 的，并用 switch 语句置其为 1
                switch(i) {
                    case 0: *a = 0; LCM(*b, *c); break;
                    case 1: *b = 0; LCM(*a, *c); break;
                    case 2: *c = 0; LCM(*a, *b); break;
                }
                break;
            }
        }
        res = 1;
    } else {
        // 三个值都不为 0，则对三个数进行最小公倍数取整数运算
        res = LCM(LCM(*a, *b), *c);
        *a = res / *a;
        *b = res / *b;
        *c = res / *c;
        if (mode) LCM(a, b, c, 0);
    }

    if (sign[0]) *a = -*a;
    if (sign[1]) *b = -*b;
    if (sign[2]) *c = -*c;
    if(*a < 0) { *a = -*a; *b = -*b; *c = -*c; }
    return res;
}

/////////////////////////////////

// 求两点垂直平分线
Line PB(Point a, Point b) {
    printf("\n[log] 求点 (%.2lf, %.2lf) 和点 (%.2lf, %.2lf) 的垂直平分线:  ", a.x, a.y, b.x, b.y);
    Line res = {0};
    if (equals(a.x, b.x)) {                   // 铅直线
        if (equals(a.y, b.y)) {
            res.A = res.B = res.C = 0;
            printf(" %.2lf x + %.2lf y + %.2lf = 0", res.A, res.B, res.C);
            return res;
        }
        res.A = 0;
        res.B = 1;
        res.C = -(a.y + b.y) / 2;
    } else if (equals(a.y, b.y)) {            // 水平线
        res.A = 1;
        res.B = 0;
        res.C = -(a.x + b.x) / 2;
    } else {
        res.A = 2 * (a.x - b.x);
        res.B = 2 * (a.y - b.y);
        res.C = (b.x - a.x) * (b.x + a.x) + (b.y - a.y) * (b.y + a.y);
        LCM(&res.A, &res.B, &res.C);
    }
    printf("%.2lf x + %.2lf y + %.2lf = 0", res.A, res.B, res.C);
    return res;
}

 // 求两条线的交点
Point getIntersection(Line l1, Line l2) {
    Point res = {0};
    res.x = (l1.B * l2.C - l1.C * l2.B) / (l1.A * l2.B - l1.B * l2.A);
    res.y = (l1.C * l2.A - l1.A * l2.C) / (l1.A * l2.B - l1.B * l2.A);
    if (equals(res.x, 0)) res.x = 0;
    if (equals(res.y, 0)) res.y = 0;
    return res;
}
// 求两点间距离
double distance(Point a, Point b) {
    return sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2));
}
