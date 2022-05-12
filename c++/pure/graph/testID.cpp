/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-11-30 18:58:23
 * @LastEditors: hccodec
 * @LastEditTime: 2022-01-23 20:53:45
 */
#include <iostream>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <cmath>
using namespace std;

void Printit(int a[], int n) {
    for (int i = 0; i < n; i++) printf(" %2d", a[i]);
    printf("\n");
}

void sort(int a[], int n, bool print) {
    printf("\n对含有%d个元素的数组进行排序\n");
    int i = 0, j = n - 1, tmp;
    while(i < j) 
        if (a[i] < 0) i++;
        else {
            if (a[j] < 0) {
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
            }
            j--;
        if (print) {
            Printit(a, n);
            printf("[交换%2d(%d) 和%2d(%d)] ", a[i], i, a[j], j);
        }
    }
}

void testsort() {
    int n = 10;
    int a[n];
    srand((unsigned int)time(NULL));
    for (int i = 0; i < n; i++) a[i] = rand() % 10 - 5;
    for (int i = 0; i < n; i++) printf("[%d]", i); printf("\n");
    Printit(a, n);
    sort(a, n, false);
    Printit(a, n);
}

bool testIDLegal(char *num) {
    for (int i = 0; i < 19; i++) {
        if (i == 17) if (!(num[i] >= '0' && num[i] <= '9' || num[i] == 'x')) return false;
        else if (!(num[i] >= '0' && num[i] <= '9')) return false;
    }
    return true;
}

bool IDCheck(char *num, char &res) {
    long sum = 0;
    for (int i = 0; i < 17; i++) {
        sum += (num[i] - 48) * ((long)pow(2, 17 - i) % 11);
    }
    if ((12 - sum % 11) % 11 == 10) res = 'X'; else res = (12 - sum % 11) % 11 + 48;
    return res == num[17];
}

void testID(bool &flag) {
    char num[19], res;
    printf("请输入身份证号: \e[33m");
    cin >> num;
    printf("\e[0m");
    if (!testIDLegal(num)) {
        printf("身份号输入有误. ");
        strcpy_s(num, "232301199907300211");
        printf("更改身份证号为: %s\n", num);
        flag = false;
    }
    printf("[身份证校验结果] "); if (IDCheck(num, res)) printf("\e[32;1m通过\e[0m"); else printf("\e[31;1m失败\e[0m，校验值为 %c", res);
    printf("\n+++++++++++++++++++++\n");
}

int main(int argc, char const *argv[])
{
    bool flag = true;
    while (flag) testID(flag);
    return 0;
}
