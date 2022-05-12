/*
 * @Description: 临时的 cpp
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-09-15 14:32:43
 * @LastEditors: hccodec
 * @LastEditTime: 2022-01-11 19:07:09
 */
#define maxNodes 15
#include <iostream>
using namespace std;
#define DataType char
typedef struct CSNode { DataType data; CSNode *lchild, *rsibling; } CSTree[maxNodes];

void CreateCSTree_Degree(CSTree&T, DataType e[], int degree[], int n) {
    CSNode *pointer = new CSNode[maxNodes];
    int i, j, d, k = 0;
    for (i = 0; i < n; i++) {
        pointer[i].data = e[i];
        pointer[i].lchild = pointer[i].rsibling = NULL;
    }
    for (i = 0; i < n; i++) {
        d = degree[i];
        if (d) {
            k++;
            pointer[i].lchild = &pointer[k];
            for (j = 0; j < d; j++) {
                k++;
                pointer[k - 1].rsibling = &pointer[k];
            }
        }
    }
    *T = pointer[0];
    delete [] pointer;

}

int divide(int dividend, int divisor) {
    int res = 0, isMinus = (dividend >= 0 && divisor < 0) || (dividend <= 0 && divisor > 0);
    while ((dividend >= 0 && divisor < 0 && dividend + divisor >= 0) ||
            (dividend <= 0 && divisor < 0 && dividend - divisor <= 0) ||
            (dividend <= 0 && divisor > 0 && dividend + divisor <= 0) ||
            (dividend >= 0 && divisor > 0 && dividend - divisor >= 0)
    ) {
        if (isMinus) {
            if (res == INT_MIN) return INT_MAX; else res--;
            dividend += divisor;
        }
        else {
            if (res == INT_MAX) return res; else res++;
            dividend -= divisor;
        }
    }
    return res;
}

int main(int argc, char const *argv[])
{
    /* code */
    cout << divide(-2147483648, -1);


    
    return 0;
}
