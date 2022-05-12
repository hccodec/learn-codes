#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct mchar {
    char content;
    mchar *next;
} *lmchar;

lmchar creatRandom(int n = 20) {
    lmchar list = NULL, p = NULL;
    int type = 0;
    while (n--) {
        type = rand() % 3;
        p = (mchar*)malloc(sizeof(mchar));
        switch(type) {
            case 0: p->content = rand() % 10 + '0'; break;
            case 1: p->content = rand() % 26 + 'a'; break;
            default: p->content = rand() % 26 + 'A'; break;
        }
        p->next = list;
        list = p;
    }
    return list;
}

lmchar create(char *c) {
    lmchar list = NULL, p = NULL, q = list;
    int i = 0;
    while (c[i]) {
        p = (lmchar)malloc(sizeof(mchar));
        p->content = c[i++];
        p->next = NULL;
        (list ? q->next : list) = p;
        q = p;
    }
    return list;
}

void printlist(lmchar list) {
    printf("\n");
    while (list) {
        printf("%c", list->content);
        list = list->next;
    }
}

lmchar deldigit(lmchar list) {
    mchar *p = list, *q;
    while (p->content >= '0' && p->content <= '9') {
        p = p->next;
        free(list);
        list = p;
    } // 处理前面的结点
    while (p && p->next) {
        if (p->next->content >= '0' && p->next->content <= '9') {
            q = p->next;
            p->next = q->next;
            free(q);
        }
        p = p->next;
    }
    return list;
}

int main() {
    printf("字符串链表，删除数字结点");
    srand((unsigned int)time(NULL));
    lmchar list = creatRandom(rand() % 100);
    printlist(list);
    list = deldigit(list);
    printlist(list);
    return 0;
}