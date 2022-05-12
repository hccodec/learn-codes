/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-08 17:19:47
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-10 18:34:51
 */
/* 拓扑排序
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-08 17:19:47
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-08 17:19:48
 */


#include <stack>
#include "Graphs/Graph2.cpp"

void Topo (const Graph *G) {
    stack<int> s;

    //// 统计入度
    int indegree[G->vexnum], print[G->vexnum];
    for (int i = 0; i < G->vexnum; i++) {
        indegree[i] = G->verticles[i].indegree;
        print[i] = -1;
    }

    //////////////////

    for (int i = 0; i < G->vexnum; i++) if (indegree[i] == 0) s.push(i);
    int count = 0, i = 0;
    while (!s.empty()) {
        i = s.top(); s.pop();
        print[count++] = i;
        for (ArcNode *p = G->verticles[i].firstarc; p; p = p->nextarc) {
            int v = p->adjVex;
            if (!--indegree[v]) s.push(v);
        }
    }
    if (count < G->vexnum) printf("拓扑排序失败 (%d, %d)\n", count, G->vexnum);
    else {
        printf("拓扑排序成功: ");
        for (int i = 0; i < count; i++) printf("%s ", G->verticles[print[i]].data);
        printf("\n");
    }
}

int visited[MaxVertexNum];

void DFS(const Graph *G, int v) {
    visited[v] = true;
    for (ArcNode *w = G->verticles[v].firstarc; w; w = w->nextarc)
        if (!visited[w->adjVex]) DFS(G, w->adjVex);
    printf("%s ", G->verticles[v].data);
}

void ReTopo (const Graph *G) {
    int v;
    for (v = 0; v < G->vexnum; v++) visited[v] = false;
    for (v = 0; v < G->vexnum; v++) if (!visited[v]) DFS(G, v);
}

Graph * CreatGraph (int type = 1) {
    Graph *G = new Graph;
    // InitGraph(G);
    switch (type) {
        case 1:
            AddV(G, "0"); AddV(G, "1"); AddV(G, "2"); AddV(G, "3"); AddV(G, "4");
            AddA(G, 0, 1); AddA(G, 1, 3);
            AddA(G, 2, 3); AddA(G, 2, 4);
            AddA(G, 3, 4);
        break;
        case 2:
            AddV(G, "V1"); AddV(G, "V2"); AddV(G, "V3"); AddV(G, "V4"); AddV(G, "V5"); AddV(G, "V6");
            AddA(G, "V1", "V2", 3); AddA(G, "V1", "V3", 2);
            AddA(G, "V2", "V4", 2); AddA(G, "V3", "V4", 4);
            AddA(G, "V2", "V5", 3); AddA(G, "V4", "V6", 2); AddA(G, "V3", "V6", 3);
            AddA(G, "V5", "V6", 1);
            break;
        default:
        break;
    }
    return G;
}

int main(int argc, char const *argv[])
{
    // printf("拓扑排序\n");
    Graph *G = argc == 2 ? CreatGraph(stoi(argv[1])) : CreatGraph();
    PrintGraph(G);
    printf("\n");
    Topo(G);
    printf("\n");
    ReTopo(G);
    return 0;
}
