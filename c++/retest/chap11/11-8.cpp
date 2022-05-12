#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../name.cpp"

const char *fileName = "in.txt";

struct date {
    int year, month, day;
    int eval() { return year * 10000 + month * 100 + day; }
    date() {}
    date(int date = 0) {
        day = date % 100;
        date /= 100;
        month = date % 100;
        date /= 100;
        year = date;
    }
};

typedef struct CCF {
    int num;
    char name[nameLen];
    int level;           // 会员等级 lv.0-lv.3
    date validDate;
    CCF *next;
} *pCCF;

int str2int(char *s) {
    if (!s) return 0;
    int i = 0, res = 0;
    while (s[i]) res = res * 10 + s[i++] - '0';
    return res;
}

int checkDateValid(date date) {
    if (date.month <= 0 || date.month >= 12) return 0;
    switch(date.month) {
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            return date.day >= 1 && date.day <= 31;
        case 2:
            return date.day >= 1 && date.day <= 28 + (date.year % 100) ? !(date.year % 4) : !(date.year % 400);
        default:
            return date.day >= 1 && date.day <= 30;
    }
}

pCCF insert(pCCF head, CCF *p) {
    if (!head) head = p;
    CCF *q = head;
    while (q) q = q->next;
    q->next = p;
    return head;
}

void print(pCCF head) {
    printf("\n");
    int count = 0;
    char s[6], format[30] = {0};
    CCF *p = head;
    while (p) {
        sprintf(s, "lv.%d", p->level);
        switch(p->level) {
            case 0: strcpy(format, "\n\e[1;30m%2d %d %s %d %s\e[0m"); break;
            case 1: strcpy(format, "\n\e[1;35m%2d %d %s %d %s\e[0m"); break;
            case 2: strcpy(format, "\n\e[1;32m%2d %d %s %d %s\e[0m"); break;
            case 3: strcpy(format, "\n\e[1;33m%2d %d %s %d %s\e[0m"); break;
            default: strcpy(format, "\n\e[1;34m%2d %d %s %d %s\e[0m"); break;
        }
        printf(format, ++count, p->num, p->level ? s : "普通", p->validDate.eval(), p->name);
        p = p->next;
    }
}

void del(pCCF head) {
    CCF *p = head;
    while (head) {
        head = head->next;
        free(p);
        p = head;
    }
    free(p);
}

void creatCCF(int n = 20) {
    FILE *pf = fopen(fileName, "w");
    pCCF head = NULL, p;
    int num = 15316 + rand() % 4, day = 0, month = 0, year = 0, level = 0;
    date validDate = {0};
    char name[nameLen] = {0};
    while (n--) {
        getChinese(name, 0);
        level = rand() % 4;
        do {
            validDate.day = 1 + rand() % 31;
            validDate.month = 1 + rand() % 12;
            validDate.year = 2021 + rand() % 5;
        } while (!checkDateValid(validDate));
        fprintf(pf, "%d %s %d %d\n", num, name, level, validDate.eval());
        num += rand() % 3 + 1;
    }
    fclose(pf);
}

pCCF readCCF() {
    FILE *pf = fopen(fileName, "r");
    pCCF head = NULL;
    CCF *p = NULL, *q = head;
    int validDate = 0;
    while (!feof(pf)) {
        p = (CCF*)malloc(sizeof(CCF));
        if (!(1 + fscanf(pf, "%d", &p->num))) break;
        fscanf(pf, "%s", p->name);
        fscanf(pf, "%d", &p->level);
        fscanf(pf, "%d", &validDate);
        p->validDate = date(validDate);
        p->next = NULL;
        if (q) q->next = p;
        else head = p;
        q = p;
    }
    fclose(pf);
    return head;
}

void printCondition(CCF *p1, CCF *p, CCF *p2) {
    printf("[%d->%d->%d]", p1 ? p1->num % 100 : 0, p ? p->num % 100 : 0, p2 ? p2->num % 100 : 0);
}

pCCF sort(pCCF head) {
    if (!head) return head;
    CCF *p, *q, *p1, *p2;
    if (!q) return head;

    p = head, q = head->next;
    while (q->next) q = q->next; // q 指尾指针
    p1 = NULL, p2 = head->next; // 由于涉及结点重连，故分别用 p1 和 p2 指向 p 的前驱结点和后继结点

    ///////////// 第一步、按照会员等级进行冒泡排序

    // 用 p 从头到 q 扫描，同时用 q 向头部逐渐收紧尾部，直到 q 指到第二个结点
    while (head - q) {
        while (p - q) {
            // 指针间位置关系：                     p->p2 或 p1 -> p -> p2 -> o
            if (p->num < p2->num) {
                if (q == p2) q = p;                     // 若不修正 q 则交换后 q 所指位置将变化
                if (p1) p1->next = p2; else head = p2;  // p1 -- --> p2 --> o
                                                        //      p ->
                p->next = p2->next;                     // p1 -> p2 ------> o
                                                        //             p ->
                p2->next = p;                           // p1 -> p2 -> p -> o
                p1 = p2; p2 = p->next;                  // ?? -> p1 -> p -> p2
            } else {
                p2 = p2->next;                          // p1 -> p ->(p2)-> p2
                p = p->next;                            // p1 ->(p)->  p -> o
                if (p1) p1 = p1->next; else p1 = head;  // ?? -> p1->  p -> p2
            }
        }
        q = p1;                                         // 前移指针 q
        p1 = NULL;                                      // 将 p1 置空
        p = head;                                       // 将 p 置到最开头
        p2 = p->next;                                   // p2 跟在 p 后
    }

    p = head, q = head->next;
    while (q->next) q = q->next; // q 指尾指针
    p1 = NULL, p2 = head->next; // 由于涉及结点重连，故分别用 p1 和 p2 指向 p 的前驱结点和后继结点

    // 用 p 从头到 q 扫描，同时用 q 向头部逐渐收紧尾部，直到 q 指到第二个结点
    while (head - q) {
        while (p - q) {
            // 指针间位置关系：                     p->p2 或 p1 -> p -> p2 -> o
            if (p->level > p2->level) {
                if (q == p2) q = p;                     // 若不修正 q 则交换后 q 所指位置将变化
                if (p1) p1->next = p2; else head = p2;  // p1 ----> p2 ---> o
                                                        //     p ->
                p->next = p2->next;                     // p1 -> p2 ------> o
                                                        //             p ->
                p2->next = p;                           // p1 -> p2 -> p -> o
                p1 = p2; p2 = p->next;                  // ?? -> p1 -> p -> p2
            } else {
                p2 = p2->next;                          // p1 -> p ->(p2)-> p2
                p = p->next;                            // p1 ->(p)->  p -> o
                if (p1) p1 = p1->next; else p1 = head;  // ?? -> p1->  p -> p2
            }
        }
        q = p1;                                         // 前移指针 q
        p1 = NULL;                                      // 将 p1 置空
        p = head;                                       // 将 p 置到最开头
        p2 = p->next;                                   // p2 跟在 p 后
    }


    return head;
}

int main() {
    printf("从 %s 读入会员信息并构造单链表，再对链表排序", fileName);
    srand((unsigned int)time(NULL));
    creatCCF();
    pCCF list = readCCF();
    list = sort(list);
    print(list);
    del(list);
    return 0;
}