# 1
void MergeList(LinkList &La, LinkList &Lb, LinkList &Lc)
{//合并链表La和Lb，合并后的新表使用头指针Lc指向
    pa=La->next; pb=Lb->next;
    //pa和pb分别使链表La和Lb的工作指针，初始化为相应链表的第一个结点
    Lc=pc=La;//用La的头结点作为Lc的头结点
    while(pa&&pb)
    {
        if(pa->data<pb->data) {pc->next=pa;pc=pa;pa=pa->next;}
        //取较小者La中的元素，将pa链接在pc的后面，pa指针后移
        else if (pa->data>pb->data) {pc->next=pb;pc=pb;pb=pb->next;}
        //取较小者Lb中的元素，将pb链接在pc的后面，pb指针后移
        else //相等时取La中的元素，删除Lb中的元素
        {
            pc->next=pa;pc=pa;pa=pa->next;
            q=pb->next;delete pb;pb=q;
        }
    }
    pc->next=pa?pa:pb; //插入剩余段
    delete Lb; //释放Lb的头结点
}