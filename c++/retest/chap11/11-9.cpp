#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../name.cpp"

char *filename = (char*)"output.txt";

typedef struct teacher {
    char name[15];
    int sex : 1; // 男奇女偶
    int age;
    teacher *next; // 后继指针
    void printInfo() { printf("\n%s\t%s  %2d岁", name, sex ? "男" : "女", age); }
} *teachers;

teachers bubble(teachers list) {
    teacher *p1 = NULL, *p = list, *p2 = p->next, *q = p;
    while (q->next) q = q->next;
    while (list - q) {
        while (p - q) {
            if (p->age > p->next->age) {
                p2 = p->next;
                (p1 ? p1->next : list) = p2;
                if (q == p2) q = p;
                p->next = p2->next;
                p2->next = p;
                p1 = p2;
                p2 = p->next;
            } else {
                if (p1) p1 = p1->next; else p1 = list;
                p = p->next; p2 = p2->next;
            }
        }
        q = p1;
        p1 = NULL, p = list, p2 = p->next;
    }
    return list;
}

teachers creatList(int n = 25) {
    teachers head = NULL;
    teacher *p;
    while (n--) {
        p = (teacher*)malloc(sizeof(teacher));
        p->sex = !(getChinese(p->name, 0) & 1);
        p->age = rand() % 50 + 20;
        p->next = NULL;
        if (head) p->next = head;
        head = p;
    }
    
    return bubble(head);
}

void printList(teachers list) {
    int count = 0;
    while (list) {
        list->printInfo();
        list = list->next;
    }
}

void saveFemale (teachers list) {
    FILE *p = fopen(filename, "w");
    fprintf(p, "年龄| 姓名", list->name, list->age);
    while (list) {
        if (!list->sex) fprintf(p, "\n %2d | %s", list->age, list->name);
        list = list->next;
    }
    fclose(p);
}

void del(teachers list) {
    teacher *p = list;
    while (p->next) {
        p = p->next;
        free(list);
        list = p;
    }
    free(list);
}

int main() {
    printf("给定教师信息的单链表，找女教师并将其信息输出到 %s 中", filename);
    srand((unsigned int)time(NULL));
    teachers list = creatList();
    printList(list);
    saveFemale(list);
    del(list);

    return 0;
}