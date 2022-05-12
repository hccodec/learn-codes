#include <iostream>
#define ElemType int
#include "main.h"
#include <iomanip>
using namespace std;
extern "C"
{
    LinkList *L;
    LinkList * _Del(LinkList *pre)
    {
        if (!pre)
            return NULL;
        LinkList *p = pre->next;
        pre->next = p->next;
        delete p;
        return pre;
    }
    LinkList * _Ins(LinkList *pre, LinkList *s)
    {
        if (!(pre && s))
            return NULL;
        s->next = pre->next;
        pre->next = s;
        return pre;
    }

    LinkList *InitList()
    {
        L = new LinkList;
        if (!L)
            return NULL;
        L->next = NULL;
        return L;
    }
    LinkList *GetElem(int i)
    {
        if (i < 0)
            return NULL;
        LinkList *p = L;
        int j = 0;
        while (p && j < i)
        {
            p = p->next;
            j++;
        }
        return p;
    }
    bool DeleteElem(int i)
    {
        if (i < 1)
            return false;
        return _Del(GetElem(i - 1));
    }
    bool InsertElem(int i, ElemType e)
    {
        if (i < 1)
            return false;
        LinkList *s = new LinkList;
        s->next = NULL;
        s->data = e;
        return _Ins(GetElem(i - 1), s);
    }
    bool CreateList(LinkList &L)
    {
        for (int i = 1; i <= rand() % 20; i++)
            InsertElem(i, i);
        return true;
    }
    bool PrintList()
    {
        printf("list打印如下：\n");
        if (!L)
            return false;
        while (L->next)
        {
            printf("%d->", L->next->data);
            L = L->next;
        }
        cout << endl;
        return true;
    }

    LinkList *setData(LinkList *p, ElemType data)
    {
        p->data = data;
        return p;
    }

    bool Rev()
    {
        LinkList *p, *q, *r, *s;
        p = q = L;
        if (!p->next)
            return false;
        else if (!p->next->next)
            return false;
        while (p && q)
            if (q->next)
            {
                p = p->next;
                q = q->next->next;
            }
            else
                break;
        q = p->next;
        while (q->next)
        {
            r = q->next;
            q->next = r->next;
            r->next = p->next;
            p->next = r;
        }
        r = p;
        p = L->next;
        while (p->next)
            if (p->next->next)
            {
                q = r->next;
                r->next = q->next;
                q->next = p->next;
                p->next = q;
                p = q->next;
                //  printf("|%d %d %d|", p->data, q->data, r->data);PrintList(L);
            }
            else
                break;
        return true;
    }

    struct Student
    {
        char name[30];
        float fScore[3];
    };

    void Display(struct Student su)
    {
        printf("-----Information------\n");
        printf("姓名: %s\n", su.name);
        printf("语文: %.2f\n", su.fScore[0]);
        printf("数学: %.2f\n", su.fScore[1]);
        printf("英语: %.2f\n", su.fScore[2]);
        printf("总分: %.2f\n", su.fScore[0] + su.fScore[1] + su.fScore[2]);
        printf("平均分 :%.2f\n", ((su.fScore[0] + su.fScore[1] + su.fScore[2])) / 3);
    }

    int main(int argc, char const *argv[])
    {
        LinkList L;
        L.InitList();
        srand((unsigned int)time(NULL));
        for (int i = 1; i <= rand() % 20; i++)
            L.InsertElem(i, i);
        L.PrintList();
        L.Rev();
        L.PrintList();
        // cout << typeid((new LNode())->next).name() << endl;
        return 0;
    }
}
