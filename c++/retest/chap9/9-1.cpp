#include <stdio.h>
#include <math.h>

struct Point {
    int x;
    int y;
};

int main() {
    printf("确定一点是否在给定圆心和半径的圆内\n");
    // 正文
    Point p = {0}, o = {0}; // 点 和 圆心
    float r = 0, d2 = 0; // 圆的半径 和 点到圆心的距离
    while (1) {
        printf("\n请输入圆的半径 r: ");
        scanf("%f", &r);
        fflush(stdin);
        if (!r) break;
        printf("请输入点的横纵坐标 (x, y): ");
        scanf("%d,%d", &p.x, &p.y);
        fflush(stdin);
        printf("请输入圆心坐标 (x0, y0) : ");
        scanf("%d,%d", &o.x, &o.y);
        fflush(stdin);
        d2 = pow(o.x - p.x, 2) + pow(o.y - p.y, 2); // 计算点到圆距离的平方
        // printf("点到圆心的距离为 %f\n", d2);
        if (d2 - r * r) printf("点 (%d, %d) 在以 %.2f 为半径，(%d, %d) 为圆心的圆%s.\n", p.x, p.y, r, o.x, o.y, d2 < r * r ? "内" : "外");
        else printf("点 (%d, %d) 在以 %.2f 为半径，(%d, %d) 为圆心的圆上.\n", p.x, p.y, r, o.x, o.y);
    }
    return 0;
}
