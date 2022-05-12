/*
 * @Description: 单源最短路径
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-03 11:40:08
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-03 12:59:32
 */

#include <queue>
#include "Graphs/Graph.cpp"
bool visited[MaxVertexNum];

int FirstNeighbor(Graph *G, int v) {
    for (int i = 0; i < MaxVertexNum; i++)
        if (!visited[i] && G->Edge[v][i] != 0 && G->Edge[v][i] != INT_MAX) return i;
    return -1;
}

int NextNeighbor(Graph *G, int v,int w) {
    for (int i = w + 1; i < MaxVertexNum; i++)
        if (!visited[i] && G->Edge[v][i] != 0 && G->Edge[v][i] != INT_MAX) return i;
    return -1;
}

void visit(int v) {

}

void BFS(Graph *G, int v, bool showDetail = false) {
    v--;
    if (showDetail) printf("BFS 算法执行过程(从点");
    if (v < 0 || v >= G->vexnum) { if (showDetail) printf("\e[31m"); v = 0; } else { if (showDetail) printf("\e[32m"); }
    if (showDetail) printf("%c(%d)\e[0m开始)\n", G->Vex[v], v);
    
    // 初始化 d 数组和 path 数组
    for (int i = 0; i < G->vexnum; i++) visited[i] = false;
    int *d = new int[G->vexnum], *path = new int[G->vexnum];
    for (int i = 0; i < G->vexnum; i++) { d[i] = INT_MAX; path[i] = -1; }

    d[v] = 0;

    queue<int> q; int w;
    visit(v);
    visited[v] = true;
    q.push(v);
    while (!q.empty()) {
        v = q.front();
        for (w = FirstNeighbor(G, v); w >= 0; w = NextNeighbor(G, v, w))
            if (!visited[w]) {
                // visit(w);
                d[w] = d[v] + 1; path[w] = v;

                visited[w] = true;
                q.push(w);
            }
        q.pop();
    }
    
} 

int main(int argc, char const *argv[])
{
    Graph *G = new Graph;
    InitGraph(G);
    for (int i = 0; i < 8; i++) AddV(G, '1' + i);
    for (int i = 0; i < G->vexnum; i++) G->Edge[i][i] = 0;
    AddA(G, 1, 2); AddA(G, 1, 5); AddA(G, 2, 6);
    AddA(G, 6, 3); AddA(G, 6, 7);
    AddA(G, 3, 7); AddA(G, 3, 4); 
    AddA(G, 4, 7); AddA(G, 4, 8); AddA(G, 7, 8);
    PrintMatrix(G);
    BFS(G, 1, true);


    return 0;
}
