#include <iostream>
#include <math.h>
using namespace std;

struct ElemType { int data; };
#define MaxSize 100
typedef struct TreeNode { ElemType value; bool isEmpty; } SqBiTree[MaxSize];

void PrintTree(TreeNode *t, int n) {
    int m = floor(log2(n+1)) + (log2(n+1) - floor(log2(n+1)) == 0 ? 0 : 1);
    int i = 0, j = 0;
    printf("[info] 结点总数为%d，层数为 %d\n", n, m);
    for (int i = 0; i < 2 * pow(2, m - 1) - 1; i++) printf("--"); cout << endl;
    for (i = 1, j = 1; i <= n; i++) {
        if (i == pow(2, j - 1)) { for (int k = 0; k < pow(2, m - j) - 1; k++) printf("  "); }
        if (t[i].isEmpty) printf(" ^"); else printf("%2d", t[i].value.data);
        
        if (i == pow(2, j) - 1) { for (int k = 0; k < pow(2, m - j) - 1; k++) printf("  "); cout << endl; j++; }
        else for (int k = 0; k < pow(2, m - j + 1) - 1; k++) printf("  ");
    }
    if (i != pow(2, j - 1)) cout << endl;
    for (int i = 0; i < 2 * pow(2, m - 1) - 1; i++) printf("--"); cout << endl;
}

void CreateTree(SqBiTree &t, int n) {
     int size = 0; for (int i = 0; i < MaxSize; i++) { t[i].isEmpty = true; } for (int i = 1; i <= n; i++) {t[i].value = {i}; t[i].isEmpty = false; size++; } PrintTree(t, size);
}

int main(int argc, char const *argv[])
{
    SqBiTree t; CreateTree(t, 31);
    return 0;
}
