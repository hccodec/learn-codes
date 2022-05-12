/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-03 12:46:00
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-09 00:32:48
 */

#include <iostream>
using namespace std;
#define MaxVertexNum 10 // 顶点数目最大值
typedef char VertexType; // 顶点数据类型
typedef int EdgeType;    // 带权图边上权值数据类型
struct Graph {
    VertexType Vex[MaxVertexNum]; EdgeType Edge[MaxVertexNum][MaxVertexNum];
    int vexnum = 0, arcnum = 0;
};

/** 初始化图，包括
 * @param {Graph} *
 * @return {*}
 * @author: hccodec
 */
void InitGraph(Graph *&G) {
    if (!G->vexnum) {
        printf("请先输入顶点信息，再进行初始化。\n");
    }
    else
        for (int i = 0; i < G->vexnum; i++)
            for (int j = 0; j < G->vexnum; j++)
                G->Edge[i][j] = INT_MAX;
}

void AddV(Graph *&G, char name) {
    if (G->vexnum != MaxVertexNum) G->Vex[G->vexnum++] = name;
    else printf("\e[31m图已满\e[0m\n");
}

void AddV(Graph *&G, VertexType name[], int n) {
    int i = 0;
    while (i < n)
    if (G->vexnum != MaxVertexNum)
    G->Vex[G->vexnum++] = name[i++];
    else printf("\e[31m图已满\e[0m\n");
}

void AddA(Graph *&G, int tail, int head, int weight = 1, int mode = 0) {
    tail--; head--;
    if (G->arcnum == G->vexnum * (G->vexnum - 1) / (1 + (mode == 0)) ) {
        printf("(弧已满");
        return;
    }
    if (head >= 0 && head < MaxVertexNum && tail >= 0 && tail < MaxVertexNum) {
        if (mode == 0)
            G->Edge[tail][head] = G->Edge[head][tail] = weight;
        else
            G->Edge[tail][head] = weight;
        G->arcnum++;
    }
    else printf("位置 %d --> %d 不合法", tail, head);
}


void PrintMatrix(Graph *G) {
    printf("【邻接矩阵】\n  ");
    for (int i = 0; i < G->vexnum; i++) {
        printf("\e[4m%2c \e[0m", G->Vex[i]);
    }
    for (int i = 0; i < G->vexnum; i++) {
        printf("\n%c|", G->Vex[i]);
        for (int j = 0; j < G->vexnum; j++) {
            if (i != j && G->Edge[i][j] != INT_MAX) printf("\e[32m"); else printf("\e[2;33m");
            if (G->Edge[i][j] == INT_MAX) printf(" ∞");
            else printf("%2d", G->Edge[i][j]);
            printf("\e[0m ");
        }
    }
    cout << endl << endl;
}

void PrintMatrix(Graph *G, int* a[], int n) {
    printf("【邻接矩阵】\n  ");
    for (int i = 0; i < n; i++) {
        printf("%2c ", G->Vex[i]);
    }
    for (int i = 0; i < n; i++) {
        printf("\n%c|", G->Vex[i]);
        for (int j = 0; j < n; j++) {
            if (i != j && a[i][j] != INT_MAX) printf("\e[32m"); else printf("\e[32m");
            if (a[i][j] == INT_MAX) printf(" ∞");
            else printf("%2d", a[i][j]);
            printf("\e[0m ");
        }
    }
    cout << endl << endl;
}

void CreatGraph(Graph *&G);