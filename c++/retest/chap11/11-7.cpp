#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

// 结点数据类型定义
typedef struct LNode {
    char c;     // 字符
    LNode *n;   // 指针域
} *LList;

int range(int a, int b) {
    if (a > b) {
        a += b;
        b = a - b;
        a -= b;
    }
    return rand() % (b - a) + a;
}

LList move(LList str) {
    // q 指向处理后的最后一个数字串，p 用于扫描链表，r 指向 p 的前一个结点
    LNode *p = str, *q = NULL, *r = NULL;
    
    // 使 q 指向从头开始连续数字结点的最后一个
    while (p->c >= '0' && p->c <= '9') { q = p; p = p->n; }
    /*
        此时若 p 非空，p 一定指向非数字结点
        用 p 继续找到后面的数字结点，将 p 所指结点插到 q 后面
        重复该过程,直到扫描完成
    */
    while (p) {
        // 用 p 找到后面的数字结点
        while (p && (p->c < '0' || p->c > '9')) { r = p; p = p->n; }
        if (!p) break; // 扫描完成

        if (q) {
            // 将 p 所指结点插到 q 后面
            r->n = p->n;
            p->n = q->n;
            q->n = p;
        } else {
            // 若 q 为空说明首元结点非数字结点，需要将 p 所指结点插到链表首
            r->n = p->n;
            p->n = str;
            str = p;
        }
        // 后移 q 指针
        q = p;
        p = r->n;
    }
    return str;
}

void printList(LList str) {
    printf(" ");
    while (str) {
        if (str->c >= '0' && str->c <= '9') printf("\e[32m%c\e[0m", str->c);
        else printf("%c", str->c);
        str = str->n;
    }
    printf("\n");
}

int main() {
    printf("把一个由数字和字母组成的字符串，用单向链表存储\n");
    srand((unsigned int)time(NULL));

    int strlen = rand() % 50, type = 0;                // 字符串长度
    LList str = NULL, p = NULL, q = str;            // 字符串存储空间
    if (1) {
        for (int i = 0; i < strlen; i++) {
            type = rand() % 3;
            p = (LNode*)malloc(sizeof(LNode));
            p->n = NULL;
            switch (type) {
                case 0: p->c = range('0', '9'); break;
                case 1: p->c = range('a', 'z'); break;
                case 2: p->c = range('A', 'Z'); break;
            }
            if (q) q->n = p;
            else str = p;
            q = p;
        }                                                   // 随机生成字符串
    } else {
        strlen = 3;
        for (int i = 0; i < strlen; i++) {
            type = rand() % 3;
            p = (LNode*)malloc(sizeof(LNode));
            p->n = NULL;
            switch (i) {
                case 0: p->c = 'p'; break;
                case 1: p->c = 'O'; break;
                case 2: p->c = 'F'; break;
            }
            if (q) q->n = p;
            else str = p;
            q = p;
        }        
    }


    printf("[%d]", strlen);
    printList(str);
    str = move(str);
    printf("[%d]", strlen);
    printList(str);


    return 0;
}

/*////////////////////////////////////////////////

总结：
    1. 第 34 行
        while (p && (p->c < '0' || p->c > '9')) { r = p; p = p->n; }
        注意括号与优先级
    2. 移动过程中，
        q 总是指向最后一个数字结点（若没有则指空）
        p 总是指向被移动的结点，
        r 总是指向 p 的前一个结点

        1) 指针移动方法：
            孤立 p：
                r->next = p->next;
            让 p 接到目标位置 q 的后面（或链表首）：(修正 p 的指针域)
                p->next = q->next; （p->next = str;）
            让 p 接到目标位置前面：                (修正 q 的指针域（或链表首的指针)
                q->next = p; （str = p;）
        2) 复原为上述 p、q、r 的状态
            q = p;
            p = r->next;


*/////////////////////////////////////////////////