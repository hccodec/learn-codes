#include<iostream>
#define ElemType int
// #include "main.h"
#include <iomanip>
using namespace std;
extern "C" {
typedef struct LNode { ElemType data; LNode * next; } LNode , * LinkList;
void Do(bool func, const char * msg = "") { if (func) return; printf("\e[2;31m[禁行]\e[0m \e[33m%s\e[0m\n", msg); }
bool InitList(LinkList &L) { L = new LNode; if (!L) return false; L->next = NULL; return true; }
LNode * GetElem (LinkList L, int i) { if (i < 0) return NULL; LNode * p = L; int j = 0; while(p && j < i) {p = p->next; j++;}return p; }
bool _Del(LNode* pre) { if (!pre) return false; LNode * p = pre->next; pre->next = p->next; delete p; return true; }
bool _Ins(LNode* pre, LNode* s) { if(!(pre && s)) return false; s->next = pre->next; pre->next = s; return true; }
bool DeleteElem(LinkList &L, int i) { if (i < 1) return false; return _Del(GetElem(L, i - 1));}
bool InsertElem(LinkList &L, int i, ElemType e) { if (i < 1) return false; LNode *s = new LNode; s->next = NULL; s->data = e; return _Ins(GetElem(L, i - 1), s); }
bool CreateList(LinkList &L) { for (int i = 1; i <= rand() % 20; i++) Do(InsertElem(L, i, i), "插入 "); return true; }
bool PrintList(LinkList L) { printf("list打印如下：\n"); if (!L) return false; while(L->next){ printf("%d->", L->next->data); L = L->next;} cout << endl; return true; }

LNode * setData(LNode * p, ElemType data) { p->data = data; return p;}

bool Rev(LinkList &L)
{
    LNode *p, *q, *r, *s;
    p = q = L;
    if (!p->next) return false;
    else if (!p->next->next) return false;
    while(p && q) if (q->next) { p = p->next; q = q->next->next; } else break;
    q = p->next;
    while (q->next) { r = q->next; q->next = r->next; r->next = p->next; p->next = r;}
    r = p; p = L->next; 
    while (p->next) if (p->next->next) { q = r->next; r->next = q->next; q->next = p->next; p->next = q; p = q->next;
    //  printf("|%d %d %d|", p->data, q->data, r->data);PrintList(L);
    } else break;
    return true;
}

struct Student{char name[30];float fScore[3];};

void Display(struct Student su)
{
    printf("-----Information------\n");
    printf("姓名: %s\n",su.name);
    printf("语文: %.2f\n",su.fScore[0]);
    printf("数学: %.2f\n",su.fScore[1]);
    printf("英语: %.2f\n",su.fScore[2]);
    printf("总分: %.2f\n", su.fScore[0]+su.fScore[1]+su.fScore[2]);
    printf("平均分 :%.2f\n",((su.fScore[0]+su.fScore[1]+su.fScore[2]))/3);
}

int main(int argc, char const *argv[])
{
    LinkList L; Do(InitList(L));
    srand((unsigned int)time(NULL));
    for (int i = 1; i <= rand() % 20; i++) Do(InsertElem(L, i, i), "插入 ");
    Do(PrintList(L)); Do(Rev(L), "链表长度小于2，无法进行后半段逆置穿插");
    Do(PrintList(L));
    // cout << typeid((new LNode())->next).name() << endl;
    return 0;
}
}