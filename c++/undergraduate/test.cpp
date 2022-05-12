extern "C"
{
#include <stdio.h>
#include <string.h>

    typedef struct student
    {
        char name;
        short theClass;
        double num;
        int age;
    } stu;

    typedef struct stu_struct_array
    {
        stu stu_array[2];
    } stu_struct_array_t;

    int struct_test(stu *msg, stu_struct_array_t *nest_msg, char *buff)
    {

        int index = 0;

        printf("学生姓名: \t%d\n", msg->name);
        printf("学生班级: \t%d\n", msg->theClass);
        printf("学生编号: \t%.0f\n", msg->num);
        printf("学生年龄: \t%d\n", msg->age);

        memcpy(nest_msg->stu_array, msg, sizeof(stu));

        printf("0号学生姓名: %d\n", nest_msg->stu_array[0].name);
        printf("0号学生班级: %d\n", nest_msg->stu_array[0].theClass);

        memcpy(buff, msg, sizeof(stu));
        printf("---------------------\nbuff:\t %d %d\n", buff[0], buff[1]);

        return 1;
    }
}