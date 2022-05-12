/*
 * @Description: 最小生成树
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-02 22:35:53
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-03 14:37:16
 */

#include "Graphs/Graph.cpp"

/** 用 Prim 算法计算单源最短路径
 * @param {Graph} *G 图
 * @return {*}
 * @author: hccodec
 */
int Prim(Graph *G, int startVex = 0, bool showDetail = false) {
    if (showDetail) printf("Prim算法执行过程(从点");
    if (startVex < 0 || startVex >= G->vexnum) {
        if (showDetail) printf("\e[31m");
        startVex = 0;
    }
    else { if (showDetail) printf("\e[32m"); }
    if (showDetail) printf("%c(%d)\e[0m开始)\n", G->Vex[startVex], startVex);

    // 初始化 isJoin 数组和 lowCost 数组
    bool *isJoin = new bool[G->vexnum]; int *lowCost = new int[G->vexnum];
    for (int i = 0; i < G->vexnum; i++) { isJoin[i] = false; lowCost[i] = INT_MAX; }

    int p = startVex; lowCost[p] = 0;

    for (int a = 0; a < G->vexnum; a++) { // 每次新增一个结点
        if(isJoin[p]) break;
        isJoin[p] = true;
        // 更新 lowCost 数组
        for (int i = 0; i < G->vexnum; i++)
            if (!isJoin[i] && G->Edge[p][i] < lowCost[i]) lowCost[i] = G->Edge[p][i];

        if (showDetail) {
            printf("[lowCost] ");
            for (int j = 0; j < G->vexnum; j++) {
                if (isJoin[j]) printf("\e[1;32m");
                else printf("\e[31m");
                if (lowCost[j] == INT_MAX) printf("∞");
                else printf("%d", lowCost[j]);
                printf("\e[0m ");
            }
        }
        int min = INT_MAX, temp = 0;
        for (int i = 0; i < G->vexnum; i++)
            if (!isJoin[i] && lowCost[i] < min) {
                min = lowCost[i];
                temp = i;
            }
        // if (showDetail) printf("%d %d", temp, min);
        p = temp;
        if (showDetail) cout << endl;
    }
    if (showDetail) cout << endl;
    int WPL = 0;
    for (int i = 0; i < G->vexnum; i++) WPL += lowCost[i];
    return WPL;
}

int Kruskal(Graph *G, int startVex = 0, bool showDetail = false) {
    cout << "Kruskal 算法执行过程用到了 排序->并查集" << endl;
    return 0;
}

void CreatGraph(Graph *&G) {
    AddV(G, new VertexType[6]{'A', 'B', 'C', 'D', 'E', 'F'}, 6);
    for (int i = 0; i < G->vexnum; i++) G->Edge[i][i] = 0;
    AddA(G, 1, 2, 6); AddA(G, 1, 3, 5); AddA(G, 1, 4, 1);
    AddA(G, 4, 2, 5); AddA(G, 4, 3, 4);
    AddA(G, 2, 5, 3); AddA(G, 4, 5, 6); AddA(G, 4, 6, 4); AddA(G, 3, 6, 2);
    AddA(G, 5, 6, 6);
}

int main(int argc, char const *argv[])
{
    // system("chcp 65001");
    Graph *G = new Graph;
    InitGraph(G);
    CreatGraph(G);
    PrintMatrix(G);

    printf("WPL (Prim) = %d\n", Prim(G, 0, true));
    printf("WPL (Kruskal) = %d\n", Kruskal(G, 0, true));

    // system("pause");
    return 0;
}