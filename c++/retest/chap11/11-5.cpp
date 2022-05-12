#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../name.cpp"

struct date { int year, month, day; };

typedef struct Student {
    char name[nameLen];
    date birthday;
    Student *next;
} *pStudent;

// 判断生日日期是否合法
int checkBirthday (date birthday) {
    if (birthday.month < 0 || birthday.month > 12) return 0;
    switch(birthday.month) {
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            return birthday.day > 0 && birthday.day <= 31;
        case 2:
            return birthday.day > 0 && birthday.day <= 28 + (!(birthday.year % 100) ? !(birthday.year % 400) : !(birthday.year % 4));
        default:
            return birthday.day > 0 && birthday.day <= 30;
    }
}

pStudent insert(pStudent head, Student *p) {
    if (!head) { head = p; return head; }
    if (strcmp(head->name, p->name) != -1) {
        p->next = head; head = p; return head;
    }
    Student *q = head;
    while (q->next) {
        if (strcmp(q->next->name, p->name) != -1 && strcmp(p->name, q->name) != -1) {
            p->next = q->next;
            q->next = p;
            return head;
        }
        q = q->next;
    }
    q->next = p;
    return head;
}

void printStudents(pStudent head) {
    int count = 0;
    printf("\n序号| 出生日期 | 姓名");
    while (head) {
        printf("\n %2d |%4d-%02d-%02d| %s", ++count, head->birthday.year, head->birthday.month, head->birthday.day, head->name);
        head = head->next;
    }
}

void clean(pStudent head) {
    int count = 0;
    Student *p = head;
    if (!p) return;
    while (head->next) {
        p = head->next;
        free(head);
        head = p;
    }
    free(head);
}

void saveToFile(pStudent head, char *fileName) {
    FILE *pfile = fopen(fileName, "w");
    int count = 0;
    while (head) {
        if (count) fprintf(pfile, "\n");
        else fprintf(pfile, "序号,出生日期,姓名\n");

        fprintf(pfile, "%02d,%d-%02d-%02d,%s", ++count, head->birthday.year, head->birthday.month, head->birthday.day, head->name);
        head = head->next;
    }
    fclose(pfile);
    printf("\n\e[2m[已存入文件 %s 中]\e[0m", fileName);
}

int main() {
    printf("键盘输入学生信息，创建单向链表。最后存入文件\n");
    srand((unsigned int)time(NULL));

    int count = 0;
    pStudent head = NULL, q;

    while (1 - count) {
        q = (pStudent)malloc(sizeof(Student));
        printf("请输入第 %d 位学生的信息: 名字与出生年月日(于禁 190 5 31): ", count + 1);
        fflush(stdin);
        scanf("%s %d %d %d", q->name, &q->birthday.year, &q->birthday.month, &q->birthday.day);
        q->next = NULL;
        if (checkBirthday(q->birthday)) {
            head = insert(head, q);
            count++;
        } else printf("生日不合法，请重输\n");
    }
    printf("\e[2m以下将自动生成其余 %d 条信息，并存入链表\e[0m\n", 30 - count);
    while (30 - count) {
        q = (pStudent)malloc(sizeof(Student));
        getEnglish(q->name);
        q->birthday = {rand() % 3 + 2015, rand() % 12 + 1, rand() % 31 + 1};
        q->next = NULL;
        if (checkBirthday(q->birthday)) {
            head = insert(head, q);
            count++;
        } else continue;
    }
    printStudents(head);
    saveToFile(head, (char*)"student.txt");
    clean(head);
    return 0;
}
