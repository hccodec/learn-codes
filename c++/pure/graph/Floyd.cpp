/*
 * @Description: 弗洛伊德算法
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-02 17:50:08
 * @LastEditors: hccodec
 * @LastEditTime: 2021-12-14 15:27:28
 */

#include "Graphs/Graph.cpp"
struct pathInfo { int path; bool changed; };

char *lessImportant = (char*)"\e[2;33m";
char *changed = (char*)"\e[1;34m";

void CreatGraph1(Graph *&G) {
    printf("生成表一");
    for (int i = 0; i < 3; i++) AddV(G, '0' + i);
    for (int i = 0; i < G->vexnum; i++) G->Edge[i][i] = 0;
    AddA(G, 1, 2, 6, 1); AddA(G, 2, 1, 10, 1);
    AddA(G, 1, 3, 13, 1); AddA(G, 3, 1, 5, 1);
    AddA(G, 2, 3, 4, 1);
}
void CreatGraph2(Graph *&G) {
    printf("生成表二");
    for (int i = 0; i < 5; i++) AddV(G, '0' + i);
    for (int i = 0; i < G->vexnum; i++)
        for (int j = 0; j < G->vexnum; j++)
            if (i == j) G->Edge[i][j] = 0; else G->Edge[i][j] = INT_MAX;
    AddA(G, 1, 3, 1, 1); AddA(G, 1, 5, 10, 1);
    AddA(G, 2, 4, 1, 1); AddA(G, 2, 5, 5, 1);
    AddA(G, 3, 2, 1, 1); AddA(G, 3, 5, 7, 1);
    AddA(G, 4, 5, 1, 1);
}
void CreatGraph3(Graph *&G) {
    printf("生成表三");
    for (int i = 0; i < 3; i++) AddV(G, '0' + i);
    for (int i = 0; i < G->vexnum; i++) G->Edge[i][i] = 0;
    AddA(G, 1, 2, 1, 1);
    AddA(G, 2, 3, -5, 1);
    AddA(G, 1, 3, 7, 1);
}

void PrintFloydMatrix(Graph *G, int** A, pathInfo** path, int n, int k) {
    bool withOrigin = false;
    if (withOrigin) printf("[邻接矩阵]");
    printf(" [A矩阵] [path矩阵]\n  \e[4m");
    // 打印列表头
    for (int j = 0; j < (1 + withOrigin); j++) 
        for (int i = 0; i < n; i++) {
            if (i == k) printf("%s", changed); printf("%2c \e[0;4m", G->Vex[i]);
            }
    printf("\e[0;4m|");

    for (int i = 0; i < n; i++) { printf("\e[4m%2c ", G->Vex[i]); }

    for (int i = 0; i < n; i++) {
        // 打印行表头
        if (i == k) printf("%s", changed); printf("\e[0m\n%c|", G->Vex[i]);
        /** 邻接矩阵 **/
        if (withOrigin) {
            for (int j = 0; j < n; j++) {
                if (i != j && G->Edge[i][j] != INT_MAX){
                    if (path[i][j].changed) printf("%s", changed);
                    else printf("\e[32m");
                } else printf("%s", lessImportant);
                if (G->Edge[i][j] == INT_MAX) printf(" ∞");
                else printf("%2d", G->Edge[i][j]);
                printf("\e[0m ");
            }
            printf("|");
        }
        /**/

        /** A 矩阵 **/
        for (int j = 0; j < n; j++) {
            if (i != j && A[i][j] != INT_MAX){
                if (path[i][j].changed) printf("%s", changed);
                else printf("\e[32m");
            } else printf("%s", lessImportant);
            if (A[i][j] == INT_MAX) printf(" ∞");
            else printf("%2d", A[i][j]);
            printf("\e[0m ");
        }
        printf("|");

        /** path 矩阵 **/
        for (int j = 0; j < n; j++) {
            // if (path[i][j].changed) printf("\e[1;45m");
            if (path[i][j].path != -1) printf("\e[32m"); else printf("%s", lessImportant);
            if (path[i][j].path == INT_MAX) printf(" ∞");
            else printf("%2d", path[i][j].path);
            printf("\e[0m ");
        }
    }
    cout << endl << endl;
}

void PrintFloydMatrix(Graph *G, int **A, pathInfo* path[]) {
    PrintFloydMatrix(G, A, path, G->vexnum, G->vexnum - 1);
}

void Floyd(Graph *G, int **&A, pathInfo **&path, int k) {

        /********** 核心代码 **********/

        for (int i = 0; i < G->vexnum; i++) for (int j = 0; j < G->vexnum; j++) // 遍历所有顶点
            if (A[i][k] != INT_MAX && A[k][j] != INT_MAX) // 
                if (A[i][j] > A[i][k] + A[k][j]) {
                    A[i][j] = A[i][k] + A[k][j];
                    path[i][j].path = k;
                    path[i][j].changed = true; // 自己添加的方便打印的代码
                }

        /** *********************** **/
    
}

void DoFloyd(Graph *G, int **&A, pathInfo **&path, bool printDetail = false) {

    for (int k = 0; k < G->vexnum; k++) {
        
        if (printDetail) printf("%d) V%d 中转", k + 1, k);
        for (int i = 0, j = 0; i < G->vexnum, j < G->vexnum; i++, j++) path[i][j].changed = false;

        Floyd(G, A, path, k);

        if (printDetail) PrintFloydMatrix(G, A, path, G->vexnum, k);
    }
}

void FloydPath(Graph *G, pathInfo **path, int tail, int head) {
    if (!(tail >= 0 && tail < G->vexnum && head >= 0 && head < G->vexnum) || tail == head) return;
    FloydPath(G, path, tail, path[tail][head].path);
    if (path[tail][head].path != -1) printf("-> V%d ", path[tail][head].path);
    FloydPath(G, path, path[tail][head].path, head);
}

void GetFloydPath(Graph *G, int **A, pathInfo **path, int tail, int head) {
    if (tail == head) return;
    if (A[tail][head] == INT_MAX) {
        printf("(∞)\t\e[32mV%d\e[0m \e[1;31m^\e[0;32m V%d\e[0m\n", tail, head);
        return;
    }

    if (G->Edge[tail][head] == INT_MAX) printf("(%s∞", lessImportant);
    else printf("(%s%d", lessImportant, G->Edge[tail][head]);

    if (G->Edge[tail][head] != A[tail][head]) printf("->\e[0m%d", A[tail][head]);
    printf("\e[0m)\t\e[32mV%d\e[0m ", tail);
    FloydPath(G, path, tail, head);
    printf("-> \e[32mV%d\e[0m\n", head);
}

int main(int argc, char const *argv[])
{
    Graph *G = new Graph;
    InitGraph(G);
    if (argc == 2) {
        switch (atoi(argv[1])) {
            case 1:
                CreatGraph1(G);
                break;
            case 2:
                CreatGraph2(G);
                break;
            case 3:
                CreatGraph3(G);
                break;
            default:
                CreatGraph2(G);
                break;
        }
    }
    else CreatGraph2(G);
    PrintMatrix(G);

    ///////////////////////////////////////
    // A 矩阵
    int **A = new int *[G->vexnum];
    pathInfo **path = new pathInfo *[G->vexnum];
    for (int i = 0; i < G->vexnum; i++) {
        path[i] = new pathInfo[G->vexnum];
        A[i] = new int[G->vexnum];
    }
    // path 矩阵
    for (int i = 0; i < G->vexnum; i++)
        for (int j = 0; j < G->vexnum; j++) {
            A[i][j] = G->Edge[i][j];
            path[i][j].path = -1;
            path[i][j].changed = false;
        }
    ///////////////////////////////////////

    // DoFloyd(G, A, path);
    DoFloyd(G, A, path, true);
    PrintFloydMatrix(G, A, path);
    printf("[result (DoFloyd)]\n");
    for (int i = 0; i < G->vexnum; i++) for (int j = 0; j < G->vexnum; j++)
        GetFloydPath(G, A, path, i, j);
    

    return 0;
}
