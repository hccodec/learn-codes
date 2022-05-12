#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <string.h>
static char str[80]; 
struct Point {
    float x;
    float y;
    float distance(Point p) { return sqrt(pow(x - p.x, 2) + pow(y - p.y, 2)); };
};

struct Circle {
    struct Point {
        float x;
        float y;
        float distance(Point p) { return sqrt(pow(this->x - p.x, 2) + pow(this->y - p.y, 2)); };
    } center;
    float radius;
    char *info() { sprintf_s(str, 80, "(%.2f, %.2f), %2.2f", center.x, center.y, radius); return str; };
};

typedef struct LNode {
    Circle m, n;
    LNode *link;
} *LinkedList;

int equals(float f1, float f2) {
    return (f1 < f2 ? f2 - f1 : f1 - f2) < 1e-5;
}


// 判断两个圆是否相切
int IsTan(Circle c1, Circle c2) {
    float d = c1.center.distance(c2.center); // 圆心间的距离
    if (equals(d, c1.radius + c2.radius) || equals(fabsf(c1.radius - c2.radius), d)) return 1;
    else return 0;
}

// 解题函数
LinkedList getTan(Circle c[], int n) {
    LinkedList res = (LinkedList)malloc(sizeof(LNode) * n);
    res->link = NULL;
    LNode *p = res; // p 指向链表尾，q 用于生成新结点
    for (int i = 0; i < n; i++)
        for (int j = 0; j < i; j++)
            if (i - j && IsTan(c[i], c[j])) {
                LNode *q = (LinkedList)malloc(sizeof(LNode));
                q->m = c[i];
                q->n = c[j];
                q->link = NULL;
                p->link = q;
                p = p->link;
                // printf("\n[log] 添加一个相切圆记录");
            }
    return res;
}

int PrintList(LinkedList ll) {
    if (!ll) return 0;          // 链表为空，执行失败
    LNode *p = ll->link;
    if (!p) return 0;           // 头结点为空，执行失败
    printf("\n相切的圆的信息如下");
    int i = 0;
    while (p) {
        if (i < 10) {
            printf("\n[%2d]%22s 与 ", ++i, p->m.info());
            printf("%22s 相%s切, 圆心距为 %.2f\t", p->n.info(), equals(p->m.center.distance(p->n.center), p->m.radius + p->n.radius) ? "外" : "内", p->m.center.distance(p->n.center));
        }
        p = p->link;
    }

    return 1;
}


int main() {
    const int n = 100, thr = 20;
    void print(LinkedList);                   // 解题函数
    printf("从 %d 个圆中找出相切的圆并放到链表中：", n);
    srand((unsigned int)time(NULL)); // 初始化随机函数
    Circle circles[n] = {0};
    float tmpx, tmpy, tmpr, tmp0, tmp; // 临时存储生成的圆

    // 随机生成不重复的100个圆
    for (int i = 0; i < n; i++) {
        tmp0 = 1 + rand() % thr;
        tmp = (float)(rand() % thr / tmp0) + 1;
        tmpx = (float)(rand() % thr / tmp);
        tmpy = (float)(rand() % thr / tmp);
        tmpr = (float)(rand() % thr / tmp);
        int j = 0;
        while (j < i) {
            if (tmpx == circles[j].center.x && tmpy == circles[j].center.y && tmpr == circles[j].radius) {
                tmpx = rand() % n;
                tmpy = rand() % n;
                tmpr = rand() % n;
                j = 0;
            }
            j++;
        }
        circles[i] = {{tmpx, tmpy}, tmpr};
    }
    
    // 打印生成的圆
    char info[80] = {0};
    for (int i = 0; i < n; i++) {
        if (!(i % 5)) printf("\n[%2d] ", i / 5 + 1);
        strcpy(info, circles[i].info());
        printf_s("%23s", info);
    }
    printf("\n");
    
    LinkedList res = getTan(circles, n);
    PrintList(res);


    return 0;
}
