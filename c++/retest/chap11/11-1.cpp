#include <stdio.h>

struct Student {
    char name[10]; // 姓名
    int sex; // 性别（男 1、女 0）
    char num[20];// 学号
    struct birthday { // 出生日期（年、月、日）
        int year;
        int munth;
        int day;
    } birth;
    struct unit {// 所在单位（学院、年级、班级）
        char school[20];
        int grade;
        char mClass[15];
    } un;
    void printInfo() {
        printf("\n[姓名] %10s\t[性别] %s\t[学号] %20s", name, sex ? "男" : "女", num);
        printf("\n[生日] %4d 年 %d 月 %d 日\t", birth.year, birth.munth, birth.day);
        printf("\n[所在单位] %s学院 %d 级 %s班", un.school, un.grade, un.mClass);
    };
};

int main() {
    printf("定义结构体类型描述学生信息卡片\n");
    Student Joe = {"Joe", 1, "2017114482", {2008,8,8}, {"人文",2017,"钢琴 3 "}};
    Joe.printInfo();

    return 0;
}