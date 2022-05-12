#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct LNode {
    int num;
    LNode *next;
} *LList;

void printList(LList L) {
    while (L) {
        printf("%d ", L->num);
        L = L->next;
    }
}

// 没有头结点的单链表冒泡排序
void bubble(LList L) {
    if (!L) return;
    LNode *p = L, *q = L->next;
    if (!q) return;
    while (q->next) q = q->next; // 使q指向链表尾结点
    while (q - L->next) {
        while (1) {
            if (p->num > p->next->num) {
                p->num += p->next->num;
                p->next->num = p->num - p->next->num;
                p->num -= p->next->num;
            }
            if (p->next == q) { q = p; p = L; break; }
            p = p->next;
        }
    }

}

int main() {
    printf("整数单链表冒泡排序\n");
    srand((unsigned int)time(NULL));
    const int n = 20;
    int i = 20;
    LList head = NULL;
    LNode *p = head, *q; // q 用于创建结点，p 用于在链表中指示插入位置
    while (i--) {
        q = (LNode*)malloc(sizeof(LNode));
        q->num = rand() % n;
        q->next = NULL;
        if (p) p->next = q;
        else head = q;
        p = q;
    }
    printf("排序前：");
    printList(head);
    bubble(head);
    printf("\n排序后：");
    printList(head);


    return 0;
}