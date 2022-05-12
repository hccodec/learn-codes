#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../name.cpp"

char *filename = (char*)"in.txt";
int k = 25;

struct date {
    int year, month;
};

typedef struct Staff {
    int num; // 工号
    char name[nameLen]; // 姓名
    date birthmonth;
    Staff *next;
    void printInfo() { printf("\n%5d %4d-%d%s  %s", num, birthmonth.year, birthmonth.month, birthmonth.month < 10 ? " ": "", name); }
} *lStaff;

lStaff bubble(lStaff list) {
    Staff *p1, *p, *p2, *q;
    p1 = NULL, p = list, p2 = p->next, q = p;
    while (q->next) q = q->next;
    while (list - q) {
        while (p - q) {
            if (p->num > p2->num) {
                if (q == p2) q = p;
                
                (p1 ? p1->next : list) = p2;
                p->next = p2->next;
                p2->next = p;
                
                p1 = p2;
            } else {
                p1 = p1 ? p1->next : list;
                p = p->next;
            }
            p2 = p->next;
        }
        q = p1, p1 = NULL, p = list, p2 = p->next;
    }
    return list;
}

lStaff creatStaff(int n = k) {
    lStaff list = NULL, p;
    while (n--) {
        p = (Staff*)malloc(sizeof(Staff));
        getChinese(p->name, 0);
        p->num = rand();
        p->birthmonth.year = rand() % 15 + 1990;
        p->birthmonth.month = rand() % 12 + 1;
        p->next = list;
        list = p;
    }
    list = bubble(list), p = list;
    FILE *pf = fopen(filename, "w");
    while (p) {
        fprintf(pf, "\n%5d %4d %d%s  %s", p->num, p->birthmonth.year, p->birthmonth.month, p->birthmonth.month < 10 ? " ": "", p->name);
        p = p->next;
    }
    fclose(pf);
    return list;
}

lStaff create() {
    lStaff list = NULL, p = NULL, q = list;
    FILE *pf = fopen(filename, "r");
    char name[15];
    while (!feof(pf)) {
        p = (Staff*)malloc(sizeof(Staff));
        fscanf(pf, "%d%d%d%d", &p->num, &p->birthmonth.year, &p->birthmonth.month);
        fscanf(pf, "%s", name);
        strcpy(p->name, name);
        if (q) { q->next = p; q = p; }
        else { list = q = p; }
    }
    fclose(pf);
    return list;
}

lStaff addStaff(lStaff list, Staff *s) {
    Staff *p = list;
    while (p->next && p->next->num < s->num) p = p->next;
    s->next = p->next;
    p->next = s;
    return list == p ? s : list;
}

void printlist(lStaff list) {
    while (list) {
        list->printInfo();
        list = list->next;
    }
}

void del(lStaff list) {
    Staff *p = list;
    while (p) {
        p = p->next;
        free(list);
        list = p;
    }
    free(list);
}

int main() {
    printf("向从 %s 读取到的职工信息列表中输入信息，更新列表\n", filename);
    srand((unsigned int)time(NULL));
    lStaff staffs = create();

    printf("请输入新职工信息\n工号: ");
    Staff *p = (Staff*)malloc(sizeof(Staff));
    scanf("%d", &p->num);
    printf("姓名: ");
    scanf("%s", p->name);
    printf("出生年月");
    int birthmonth = 0;
    scanf("%d", &birthmonth);
    p->birthmonth.year = birthmonth / 100;
    p->birthmonth.month = birthmonth % 100;
    staffs = addStaff(staffs, p);
    printlist(staffs);
    del(staffs);
    return 0;
}
