#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../name.cpp"

char fname[12] = "2018Exp.txt";

typedef struct grade {
    int num;
    char name[20];
    int disscore, repscore, testscore;
    // 讨论成绩、报告成绩、测试成绩
    int totscore; // 实验成绩（总成绩）
    struct grade *next;
} *pgrade;

// 随机生成文件信息
void creatRandomMsg(int n = 10) {
    FILE *fpin = fopen(fname, "w");
    srand((unsigned int)time(NULL));
    int num = 2017000;
    char name[20] = {0};
    int disscore = 0, repscore = 0, testscore = 0;
    while (n--) {
        num++;
        if (0) {
            for (int i = 0; i < nameLen; i++) name[i] = 0;
            for (int i = 0; i < rand() % nameLen + 1; i++) name[i] = rand() % 26 + (rand() % 1 ? 'A' : 'a');
        } else {
            getChinese(name);
        }
        disscore = rand() % 101;
        repscore = rand() % 101;
        testscore = rand() % 101;
        fprintf(fpin, "%d %s %d %d %d", num, name, disscore, repscore, testscore);
        if (n) fprintf(fpin, "\n");
    }
    fclose(fpin);
}

void printList(pgrade head) {
    int count = 0, same = 0;
    if (!head) return;
    printf("\n名次|  学号 |讨论|报告|测试| 总分|  姓名\n");
    printf("----|-------|----|----|----|-----|---------");
    printf("\n");
    while (head) {
        count++;
        printf(" %2d |%d| %3d| %3d| %3d| %3d | %s\n", count - same, head->num, head->disscore, head->repscore, head->testscore, head->totscore, head->name);
        if (head->next && head->totscore == head->next->totscore) same++;
        else same = 0;
        head = head->next;
    }
    printf("----|-------|----|----|----|-----|---------");
    // printf("\n共 %d 条记录", count);
}

int main() {
    printf("从文件 %s 中读取成绩信息，并按实验成绩降序排序\n（实验成绩 = 20%% 讨论成绩 + 20%% 报告成绩 + 60%% 测试成绩）\n", fname);

    pgrade head = NULL, p, p1, p2;
    creatRandomMsg(30);

    FILE *fpin = fopen(fname, "r");
    while (!feof(fpin)) {
        // if ()
        ///// 将文件中的文本信息读取到结构体中 ////
        p = (pgrade)malloc(sizeof(grade));
        if (!(fscanf(fpin, "%d", &p->num) + 1)) break;
        fscanf(fpin, "%s", &p->name);
        fscanf(fpin, "%d", &p->disscore);
        fscanf(fpin, "%d", &p->repscore);
        fscanf(fpin, "%d", &p->testscore);
        p->totscore = 0.2 * p->disscore + 0.2 * p->repscore + 0.6 * p->testscore;
        p->next = NULL;
        ////////////////////////////////////////

        if (head) {
            p1 = head, p2 = NULL;
            if (p1->totscore < p->totscore) { // 分数最高，插在最前面
                p->next = head;
                head = p;
                continue;
            }
            while (p1 && p1->totscore >= p->totscore) { // 找到位置，使 p 能插在 p2 与 p1 之间
                p2 = p1;
                p1 = p1->next;
            }
            if (p1) {
                p2->next = p;
                p->next = p1;
            } else p2->next = p;
        } else head = p;
    }
    fclose(fpin);
    printList(head);

    free(p);
    return 0;
}