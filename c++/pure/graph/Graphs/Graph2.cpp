/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-08 17:30:52
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-10 00:38:32
 */
/* 林邻接表
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-03 12:46:00
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-08 17:24:33
 */

#include <iostream>
using namespace std;
#define MaxVertexNum 10 // 顶点数目最大值
typedef char* VertexType; // 顶点数据类型
typedef int EdgeType;    // 带权图边上权值数据类型

struct ArcNode { int adjVex, weight; struct ArcNode *nextarc; };
typedef struct VNode { VertexType data; int indegree = 0, outdegree = 0; ArcNode *firstarc; } VNode, AdjList[MaxVertexNum];
typedef struct { AdjList verticles; int vexnum = 0, arcnum = 0; } Graph;

/** 添加顶点
 * @param {Graph} *G
 * @param {char} name
 * @return {*}
 * @author: hccodec
 */
void AddV(Graph *G, const char* name) {
    if (G->vexnum == MaxVertexNum) { printf("图已满\n"); return; }
    G->verticles[G->vexnum].data = (char*)name;
    G->verticles[G->vexnum].firstarc = NULL;
    G->vexnum++;
}

/** 添加弧
 * @param {Graph} *G
 * @param {int} start
 * @param {int} end
 * @param {int} weight
 * @return {*}
 * @author: hccodec
 */
void AddA(Graph *G, int start, int end, int weight = 1) {
    if (start < 0 || end < 0 || start >= G->vexnum || end >= G->vexnum) { printf("无法插入顶点，位置不合法。"); return; }
    if (!G->verticles[start].firstarc) {
        ArcNode *q = new ArcNode;
        q->adjVex = end; q->weight = weight;
        q->nextarc = NULL;
        G->verticles[start].firstarc = q;
        G->arcnum++;
    }
    else {
        ArcNode *p = G->verticles[start].firstarc;
        while (p->nextarc) p = p->nextarc;
        p->nextarc = new ArcNode; p->nextarc->adjVex = end; p->nextarc->weight = weight; p->nextarc->nextarc = NULL;
    }
    G->verticles[start].outdegree++;
    G->verticles[end].indegree++;
}

void AddA(Graph *G, const char* tail, const char* head, int weight = 1) {
    int start = -1, end = -1;
    for (int i = 0; i < G->vexnum; i++) {
        if (G->verticles[i].data == tail) start = i;
        if (G->verticles[i].data == head) end = i;
    }
    if (!(start + 1)) printf("顶点“%s”不存在", tail);
    else if (!(end + 1)) printf("顶点“%s”不存在", head);
    else AddA(G, start, end, weight);
}

// void InitGraph(Graph *G) {
//     if (!G->vexnum) {
//         printf("请先输入顶点信息，再进行初始化。\n");
//     }
//     else
        
// }

void PrintGraph(Graph *G) {
    printf("【邻接表】\n");
    for (int i = 0; i < G->vexnum; i++) {
        printf("(入%d出%d) %s|", G->verticles[i].indegree, G->verticles[i].outdegree, G->verticles[i].data);
        if (G->verticles[i].firstarc) {
            ArcNode *p = G->verticles[i].firstarc;
            while (p) { printf("->[%s]", G->verticles[p->adjVex].data); p = p->nextarc; }
        }
        else printf(" ^");
        printf("\n");
    }
}