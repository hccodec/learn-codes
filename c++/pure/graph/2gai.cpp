/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-05 10:56:01
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-06 18:15:09
 */
#include <iostream>
using namespace std;
typedef struct matrix_4
{
    long long a00;
    long long a01;
    long long a10;
    long long a11;
} matrix_four, *matrix_p;
void initialmatrix(matrix_p matrix, long long a, long long b, long long c, long long d)
{
    matrix->a00 = a;
    matrix->a01 = b;
    matrix->a10 = c;
    matrix->a11 = d;
}
void PrintMatrix(matrix_4 *m, const char *msg = NULL)
{
    printf("[%s] ========\n", msg);
    printf("%d  %d\n%d  %d\n", m->a00, m->a01, m->a10, m->a11);
    printf("========\n");
}
void copymat(matrix_p matrix1, matrix_p matrix)
{
    matrix1->a00 = matrix->a00;
    matrix1->a01 = matrix->a01;
    matrix1->a10 = matrix->a10;
    matrix1->a11 = matrix->a11;
}
void multmat(matrix_p matrix2, matrix_p matrix1, matrix_p matrix)
{
    matrix_four *mat_t = (matrix_p)malloc(sizeof(matrix_four));;
    mat_t->a00 = matrix1->a00 * matrix->a00 + matrix1->a01 * matrix->a10;
    mat_t->a01 = matrix1->a00 * matrix->a01 + matrix1->a01 * matrix->a11;
    mat_t->a10 = matrix1->a10 * matrix->a00 + matrix1->a11 * matrix->a10;
    mat_t->a11 = matrix1->a10 * matrix->a01 + matrix1->a11 * matrix->a11;
    copymat(matrix2, mat_t);
}
void calmatrix(matrix_p matrix, long long num)
{
    matrix_four *mat = (matrix_p)malloc(sizeof(matrix_four));;
    bool showDetail = false;
    matrix_p matrix1 = (matrix_p)malloc(sizeof(matrix_four));
    matrix_p matrix2 = (matrix_p)malloc(sizeof(matrix_four));
    initialmatrix(mat, 1, 1, 1, 0);
    if (num == 1)
    {
        copymat(matrix, mat);
    }
    else if (num % 2 == 0)
    {
        calmatrix(mat, num / 2);
        if (showDetail)
            printf("(num=%d) ", num);
        if (showDetail)
            PrintMatrix(mat, "mat");
        multmat(matrix, mat, mat);
        if (showDetail)
            printf("(num=%d) ", num);
        if (showDetail)
            PrintMatrix(matrix, "mat相乘");
    }
    else
    {
        calmatrix(mat, num / 2);
        if (showDetail)
            printf("(num=%d) ", num);
        if (showDetail)
            PrintMatrix(mat, "mat");

        multmat(matrix, mat, mat);
        if (showDetail)
            printf("(num=%d) ", num);
        if (showDetail)
            PrintMatrix(matrix, "mat相乘");

        initialmatrix(matrix1, 1, 1, 1, 0);
        multmat(matrix2, matrix, matrix1);
        copymat(matrix, matrix2);
    }
}
long long Fibnq(long long num)
{
    long long ans;
    matrix_p matrix;
    if (num == 0 || num == 1)
        ans = 1;
    else
    {
        matrix = (matrix_p)malloc(sizeof(matrix_four));
        initialmatrix(matrix, 1, 1, 1, 0);
        calmatrix(matrix, num - 1);
        ans = matrix->a00;
        free(matrix);
    }
    return ans;
}

void printRes(int i)
{
    cout << ("Fibonacci(") << i << ") = " << Fibnq(i) << "\n";;
}

int main(int argc, const char *argv[])
{
    if (argc == 2)
        if (stoi(argv[1]) < 0)
            for (int i = 1; i <= 30; i++)
                printRes(i);
        else
        {
            printf("long long 的最大值为\t%lld\n", LLONG_MAX);
            printRes(stoi(argv[1]));
        }
    else
    {
        cout << "请输入要计算的斐波那契数列的编号：" << endl;
        long long num;
        cin >> num;
        printRes(num);
    }
    // system("pause");
}