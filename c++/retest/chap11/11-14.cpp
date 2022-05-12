#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../name.cpp"

typedef struct node {
    int num, grade;
    char name[nameLen];
    struct node* next;
} ListNode;

ListNode* bubble(ListNode *base) {
    if (!base) return base;
    ListNode* p1, *p, *p2, *q;
    p1 = NULL, p = base, p2 = base->next, q = base;
    
    while (q->next) q = q->next;

    while (base - q) {
        while (p - q) {
            if (p->grade > p2->grade) {
                if (q == p2) q = p;
                p->next = p2->next;
                (p1 ? p1->next : base) = p2;
                p2->next = p;
                p1 = p2;
            } else {
                p1 = p1 ? p1->next : base;
                p = p->next;
            }
            p2 = p->next;
        }
        q = p1;
        p1 = NULL;
        p = base;
        p2 = p->next;
    }
    return base;
}

void tmp(int n = 30) {
    FILE *pf = fopen("11-14.txt", "w");
    char tmpmsg[100], *empty = (char*)"", name[15];
    int num = 2017001;
    while (n--) {
        strcpy(tmpmsg, empty);
        getEnglish(name);
        sprintf(tmpmsg, "%d %s %d", num++, name, rand() % 100 + 1);
        fprintf(pf, "%s", tmpmsg);
        if (n) fprintf(pf, "\n");
    }
    fclose(pf);
}

ListNode* creat(int n = 30) {
    ListNode *base = NULL, *p = NULL, *q;
    int num = 2017111;
    while (n--) {
        p = (ListNode*)malloc(sizeof(ListNode));
        getChinese(p->name, 0);
        p->num = num++;
        p->grade = rand() % 100 + 1;
        (base ? q->next : base) = p;
        q = p;
    }
    return bubble(base);
}

ListNode *read() {
    FILE *pf = fopen("11-14.txt", "r");
    ListNode *base = NULL, *p = NULL, *q = NULL;
    while (!feof(pf)) {
        p = (ListNode*)malloc(sizeof(ListNode));
        fscanf(pf, "%d%s%d", &p->num, p->name, &p->grade);
        (base ? q->next : base) = p;
        q = p;
    }
    fclose(pf);
    return bubble(base);
}

ListNode *Merge(ListNode *base1, ListNode *base2) {
    if (base1 == nullptr) return base2;
    if (base2 == nullptr) return base1;
    ListNode *mergeHead = nullptr;
    if (base1->grade < base2->grade) {
        mergeHead = base1;
        mergeHead->next = Merge(base1->next, base2);
    } else {
        mergeHead = base2;
        mergeHead->next = Merge(base1, base2->next);
    }
    return mergeHead;
}

void print(ListNode *base) {
    while (base) {
        printf("\n%d %d %s", base->num, base->grade, base->name);
        base = base->next;
    }
    printf("\n");
}

int main() {
    printf("读取文件里的学生信息，递归排序");
    // tmp(15);
    ListNode *base1 = creat(15), *base2 = read();
    print(base1);
    print(base2);
    base1 = Merge(base1, base2);
    print(base1);



    return 0;
}