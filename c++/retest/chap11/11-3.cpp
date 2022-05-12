#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../name.cpp"

typedef struct StudentNode {
    int num; // 学号
    char name[nameLen]; // 姓名
    char sex[4]; // 性别
    int score; // 成绩
    StudentNode *next;
} *StudentList;

// 建立具有 n 个结点的链表，存储班级学生的信息
StudentList CreateList(int n = 10) {
    srand((unsigned int)time(NULL));
    StudentList head = NULL;
    StudentNode *p, *q = head; // p 创建新结点，q 指向链表最后结点(插入位置)
    int init_num = 17001;
    while (n--) {
        p = (StudentNode*)malloc(sizeof(StudentNode));
        p->num = init_num++;
        strcpy(p->sex, (getChinese(p->name) & 1) ? "女" : "男");
        p->score = rand() % 100;
        p->next = NULL;
        if (q) q->next = p;
        else head = p;
        q = p;
    }
    return head;
}


void printList(StudentList head) {
    int count = 0;
    if (!head) return;
    StudentNode *p = head;
    printf("\n");
    while (p) {
        count++;
        printf("%d %s %3d %s\n", p->num, p->sex, p->score, p->name);
        p = p->next;
    }
    printf("\n共 %d 条记录", count);
}

void WriteFile(StudentList head, const char *fileName) {
    printf("\n[正在写入文件 %s]", fileName);
    FILE *fpin = fopen(fileName, "w");
    int count = 0;
    StudentNode *p = head;
    fprintf(fpin, "学生成绩 %d\n", head->num);
    while (p) {
        fprintf(fpin, "%d %s %s %d\n", p->num, p->name, p->sex, p->score);
        printf("\n[%2d\e[32m√\e[0m]: %d %s", ++count, p->num, p->name);
        p = p->next;
    }
    fclose(fpin);
    printf("\n[写入完成]");
}

int main(int argc,const char *argv[]) {
    char fileName[9]; strcpy(fileName, argv[0]); strcat(fileName, ".txt");
    StudentList head = CreateList();
    // printList(head);
    WriteFile(head, fileName);
    return 0;
}