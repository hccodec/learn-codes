#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../name.cpp"

char *filename = (char*)"output.txt";

typedef struct student {
    int num;
    char name[nameLen];
    int age;
    student *next;
} *lStud;

lStud creat(int n = 30) {
    lStud list = NULL, p = NULL;
    while (n--) {
        p = (student*)malloc(sizeof(student));
        p->num = 2020001 + rand() % 30;
        p->age = 20 + rand() % 5;
        getChinese(p->name);
        p->next = list;
        list = p;
    }
    return list;
}

void print(lStud list, int z) {
    int ages[5] = {0};
    while (list) {
        ages[list->age-20]++;
        printf(list->age - z ? "\n%d %d %s" : "\n\e[1;32m%d %d %s\e[0m", list->num, list->age, list->name);
        list = list->next;
    }
    printf("\n");
    for (int i = 0; i < 5; i++) printf(" %d岁%d人", 20 + i, ages[i]);
}

void saveWithoutZ(lStud list, int z) {
    FILE *file = fopen(filename, "w");
    while (list) {
        if (list->age != z) {
            fprintf(file, "%d %s %d", list->num, list->name, list->age);
            if (list->next) fprintf(file, "\n");
        }
        list = list->next;
    }
    fclose(file);
}

int main() {
    printf("从学生信息单链表中删除所有学生年龄为 z 的结点，并将结果存储到 %s 文件中.\n", filename);
    srand((unsigned int)time(NULL));
    lStud list = creat();
    int z;
    printf("请输入要删除的学生的年龄：");
    scanf("%d", &z);
    print(list, z);
    saveWithoutZ(list, z);


    return 0;
}