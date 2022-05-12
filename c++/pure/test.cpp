/*
 * @Description: cpp文件
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-09-08 17:02:41
 * @LastEditors: hccodec
 * @LastEditTime: 2021-09-15 16:09:10
 */
#include <iostream>
using namespace std;
#include "BiTree.cpp"

const char *args[] = {"先序", "中序", "后序", "层序"};

/** 测试输出
 * @param {BiTree} *T 递归输出所用二叉树
 * @param {ElemType} * 存放原结果及三种线索化结果的数组
 * @return {*}
 */
void TestOutput(BiTree *T, ElemType **res) {
    printf("\e[0m递归    "); for (int i = 1; i <= 3; i++) { printf("\e[0m[\e[32m%s\e[0m] \e[33m", args[i - 1]); T->PrintRecursion(i); } cout << endl;
    printf("\e[0m非递归  "); for (int i = 1; i <= 3; i++) { printf("\e[0m[\e[32m%s\e[0m] \e[33m", args[i - 1]); res[i - 1] = T->GetOutput(i, true); } cout << endl;
}

void TestInThread(BiTree **Trees) {
    printf("->线索化\n");
    printf("\e[0m正向输出");
    for (int i = 0; i < 3; i++) { printf("\e[0m[\e[32m%s\e[0m] \e[33m", args[i]); Trees[i + 1]->InOrder(); } cout << endl;
    printf("\e[0m逆向输出");
    for (int i = 0; i < 3; i++) { printf("\e[0m[\e[32m%s\e[0m] \e[33m", args[i]); Trees[i + 1]->RevInOrder(); } cout << endl;
}

void TestPrePost(BiTree **Trees) {
    printf("->\e[1;36m前驱\e[1;35m后继\n");

    for (int i = 1; i <= 3; i++)
    {
        BiTree *Tmp = Trees[i]; TNode *p, *q = Tmp->FirstNode();
        printf("\e[0m[\e[32m%s\e[0m] ", args[i - 1]);
        for (int j = 0; j < Tmp->count; j++) {
            p = q;
            if (Tmp->PreNode(p)) printf("\e[1;36m%2c\e[0;33m", Tmp->PreNode(p)->data);
            else printf("\e[0;36m .\e[0;33m");
            printf("%2c", p->data);
            if (Tmp->NextNode(p)) printf("\e[1;35m%2c\e[0;33m", Tmp->NextNode(p)->data);
            else printf("\e[0;35m .\e[0;33m");
            cout << " |";
            q = Tmp->NextNode(q);
        }
        cout << "\b " << endl;
    }
}

void TestSimiliar(BiTree *A, BiTree *B) {
    printf("两树%s相似\n", A->IsSimiliar(A->T, B->T) ? "" : "不");
}

void test()
{
    ElemType *A = (char*)"ABCDEFG";
    ElemType *B = (char*)"CBDAFEG";
    // ElemType *A = (char*)"ABCDEFGHI", *B = (char*)"BCAEDGHFI";
    BiTree *T, *PreBiTree, *InBiTree, *PostBiTree;
    // Tree *Tmp = (new BiTree(T))->InThread(1);
    T = new BiTree(A, B);
    PreBiTree = (new BiTree(A, B))->InThread(1);
    InBiTree = (new BiTree(A, B))->InThread(2);
    PostBiTree = (new BiTree(A, B))->InThread(3);
    BiTree *Trees[4] = {T, PreBiTree, InBiTree, PostBiTree};
    ElemType *res[4]; // 存放结果序列的数组

    printf("\n\e[0m[\e[32m%s\e[0m输出] \e[33m", args[3]); res[3] = T->GetOutput(4, true); cout << "\e[0m" << endl; T->PrettyPrint(true);

    /////////////////////////////////////////////////////////////

    TestOutput(T, res);
    TestInThread(Trees);
    // TestPrePost(Trees);
    
    cout << "\e[0m";
    return;
}

int main(int argc, char const *argv[])
{
    test();
    cout << endl;
    (new BiTree())->PrettyPrint(true);
    return 0;
}
