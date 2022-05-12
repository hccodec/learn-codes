#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct Node {
    int num;
    Node *next;
} *List;

List creatRandomList(int n) {
    List list = NULL;
    Node *p = NULL, *q = list;
    while (n--) {
        p = (Node*)malloc(sizeof(Node));
        p->num = rand() % 20;
        p->next = list;
        list = p;
    }
    return list;
}

void print(List list) {
    printf("\n");
    while (list) {
        printf("%d ", list->num);
        list = list->next;
    }
}

void cut(List list) {
    if (!(list && list->next)) return;
    Node *p;
    while (list->next) {
        if (list->next->num <= list->num) {
            p = list->next;
            list->next = p->next;
            free(p);
        } else list = list->next;
    }
}

int main() {
    printf("构造整数链表，仅保留数据域比前驱结点大的结点\n");
    srand((unsigned int)time(NULL));
    List list = creatRandomList(1 + rand() % 50);
    print(list);
    cut(list);
    print(list);
    return 0;
}