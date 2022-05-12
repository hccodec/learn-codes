/*
 * @Description: 哈夫曼树
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-09-15 21:52:10
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-02 17:18:41
 */
#include <queue>
#include <cmath>
#include <iostream>
using namespace std;
#define ElemType char

typedef struct Node { double weight; char data; Node *lchild, *rchild; } *Tree;

void BubbleSort(float weights[], Node *chars[]) {
    float tmp; Node *tmp2;
    for (int i = 0; i < 26; i++)
        for (int j = 0; j < 25 - i; j++)
        if (weights[j] > weights[j + 1]) {
                tmp = weights[j]; tmp2 = chars[j];
                weights[j] = weights[j + 1]; chars[j] = chars[j + 1];
                weights[j + 1] = tmp; chars[j + 1] = tmp2;
    }
}

void BubbleSort(Node *chars[]) {
    Node *tmp;
    for (int i = 0; i < 26; i++)
        for (int j = 0; j < 25 - i; j++)
        if (chars[j]->weight > chars[j + 1]->weight) {
                tmp = chars[j];
                chars[j] = chars[j + 1];
                chars[j + 1] = tmp;
    }
}

// 英文字母频次 哈夫曼树

Node * NewNode(ElemType e) {
    Node *p;
    p = new Node;
    p->data = e;
    p->lchild = p->rchild = NULL;
    p->weight = 0;
    return p;
}

void CalcHuffman(Tree T, float &sum2, bool printinfo = false) {
    long size = 0, height = 1, count = 2 * 26 - 1; char* repr_string = NULL;

    /** 计算哈夫曼树
        在层序遍历过程中计算WPL：对每层结点对应weight值求和再乘以层数
    **/

    queue<ElemType> q; queue<Node*> _q;
    q.push(T->data); _q.push(T); // 根节点入队
    Node *p = NULL; int tmp = 1; // tmp 统计结点数，已有根节点故设为 1
    printf("\n");
    float tempWeight = 0;
    printf("(\e[31m%c\e[0m)%.2f\n", T->data, T->weight);
    while (tmp < count) {
        p = _q.front();
        // 左子树入队
        if (p->lchild) {
            _q.push(p->lchild); q.push(p->lchild->data); tmp++;
            if (p->lchild->data >= 'A' && p->lchild->data <= 'Z') {
                printf("(\e[32m%c\e[0m)%.2f- ", p->lchild->data, p->lchild->weight);
                tempWeight += p->lchild->weight;
            }
            else printf("(\e[31m%c\e[0m)%.2f- ", p->lchild->data, p->lchild->weight);
        }
        else {
            q.push('^'); _q.push(NewNode('^'));
        }
        // 右子树入队
        if (p->rchild) {
            _q.push(p->rchild); q.push(p->rchild->data); tmp++;
            if (p->rchild->data >= 'A' && p->rchild->data <= 'Z') {
                printf("-(\e[32m%c\e[0m)%.2f ", p->rchild->data, p->rchild->weight);
                tempWeight += p->rchild->weight;
            }
            else printf("-(\e[31m%c\e[0m)%.2f ", p->rchild->data, p->rchild->weight);
        }
        else {
            q.push('^'); _q.push(NewNode('^'));
        }

        if (q.size() == pow(2, height + 1) - 1) {
            sum2 += tempWeight * height;
            // printf("-->(%.2f)\n", tempWeight * height);
            printf("\n");
            height++; tempWeight = 0;
        }

        // 队首出队
        _q.pop();
    }
    printf("\n");
    size = q.size();
    int tempHeight = floor(log2(size + 1)) + (log2(size + 1) - floor(log2(size + 1)) == 0 ? 0 : 1);
    repr_string = new ElemType[size]; for (int i = 0; i < size; i++) { repr_string[i] = q.front(); q.pop(); }
    if (printinfo) printf("[结点数] \e[32m%d\e[0m，[所占位置] \e[32m%d\e[0m，[树高(层数)] \e[32m%d(%d)\e[0m \n\e[33m", count, size, ++height, tempHeight);

    // 计算完毕
    // int i = 0, j = 0;

    // if (printinfo) printf("[序列字符串] \e[32m%s\e[0m \n\e[33m", repr_string);
    // height -= 6; size = 200;
    // for (int i = 0; i < 2 * pow(2, height - 1) - 1; i++) printf("--"); std::cout << std::endl;
    // for (i = 1, j = 1; i <= size; i++) {
    //     if (i == pow(2, j - 1)) { for (int k = 0; k < pow(2, height - j) - 1; k++) printf("  "); }   // 行前空格(判据：当前是否为行首)
    //     if (repr_string[i - 1] == '^') printf(" ."); else printf("%2c", repr_string[i - 1]);
    //     if (i == pow(2, j) - 1) { cout << endl; j++; }                                          // 换行(判据：当前是否为上一行行尾)
    //     else{
    //         for (int k = 1; k < pow(2, height - j + 1); k++) printf("  ");
    //     }                  //行中空格
    // }
    // if (i != pow(2, j - 1)) cout << endl;
    // for (int i = 0; i < 2 * pow(2, height - 1) - 1; i++) printf("--");
    // printf("\e[0m\n");
}


int main() {
    float weights[] = {
        8.19, 1.47, 3.83, 3.91, 12.25,  // ABCDE
        2.26, 1.71, 4.57, 7.10, 0.14,   // FGHIJ
        0.41, 3.77, 3.34, 7.06, 7.26,   // KLMNO
        2.89, 0.09, 6.85, 6.36, 9.41,   // PQRST
        2.58, 1.09, 1.59, 0.21, 1.58,   // UVWXY
        0.08                            // Z
        };

        double sum = 0; int o = 0;
        while(o < 26) sum+=weights[o++];
        if (sum - 100 < 1e-6) printf("\e[32mweights数组校验通过\e[0m\n");

    
    Node *chars[26]; // 初始化结点

    for (int i = 0; i < 26; i++) {
        chars[i] = new Node;
        chars[i]->data = 'A' + i;
        chars[i]->weight = weights[i];
        chars[i]->lchild = chars[i]->rchild = NULL;
    }

    float sum1 = 0; for (int i = 0; i < 26; i++) sum1 += weights[i]; sum1 *= 5;


    Tree T; int i = 0;
    for (i = 0; i < 25; i++) {
        BubbleSort(weights, chars);
        // BubbleSort(chars);

        T = new Node; T->data = '^';
        T->lchild = chars[i]; T->rchild = chars[i + 1];
        // printf("%c %c 组树\n", T->lchild->data, T->rchild->data);
        T->weight = weights[i] + weights[i + 1];        // 将最小两项节点连接为新子树

        weights[i + 1] += weights[i];                   // 最小权值加和
        // sum2 += weights[i + 1];                         // 额
        chars[i + 1] = T;                              // 将生成的子树也放入结点的数组中
        
    }

    float sum2 = 0;
    printf("计算WPL（Weighted Path Length）：\n");
    CalcHuffman(T, sum2, true);

    printf("%.2f bit -> %.2f bit\n\e[1;32m数据压缩率为 %.2f%\e[0m\n", sum1, sum2, sum2 / sum1 * 100);

    return 0;
}