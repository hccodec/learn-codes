# 2
#include "D:\documents\learn-codes\c++\undergraduate\chapter2\LinkList\LinkList.cpp"

void MergeList(LinkList &La, LinkList &Lb, LinkList &Lc)
{ //合并链表La Lb,合并后的新表使用头指针Lc指向
    LinkList pa, pb, Lc, pc;
    pa = La->next;
    pb = Lb->next;
    Lc = pc = La;
    while (pa && pb)
    {
        if (pa->data < pb->data)
        {
            pc->next = pa;
            pc = pa;
            pa = pa->next;
        }
        else if (pa->data > pb->data)
        {
            pc->next = pb;
            pc = pb;
            pb = pb->next;
        }
        else
        {
            //相等时将两个元素都插入Lc中
            pc->next = pa;
            pc = pa;
            pc->next = pb;
            pc = pb;
            pa = pa->next;
            pb = pb->next;
        }
    }
    pc->next = pa ? pa : pb;
    delete Lb;
}