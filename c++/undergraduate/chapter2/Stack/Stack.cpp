#include <iostream>
#include "Stack.h"
#include "../../CLike.h"

Status InitStack(SqStack &S)
{
    S.base = new SElemType[MAXSIZE];
    if (!S.base)
        exit(OVERFLOW);
    S.top = S.base;
    S.stacksize = MAXSIZE;
    return OK;
}

Status StackEmpty(SqStack S)
{
    if (S.base == S.top)
        return TRUE;
    else
        return FALSE;
}

int StackLength(SqStack S)
{
    return S.top - S.base;
}

Status ClearStack(SqStack S)
{
    if (S.base)
        S.top = S.base;
    return OK;
}

Status DestroyStack(SqStack &S)
{
    if (S.base)
    {
        delete S.base;
        S.stacksize = 0;
        S.base = S.top = NULL;
    }
    return OK;
}

Status Push(SqStack &S, SElemType e)
{
    if (S.top - S.base == S.stacksize)
        return ERROR;
    *S.top++ = e;
    return OK;
}

Status Pop(SqStack &S, SElemType &e)
{
    if (S.top == S.base)
        return ERROR;
    e = *--S.top;
    return OK;
}

void PrintStack(SqStack S)
{
    cout << "| ";
    SElemType *p = S.base;
    int count = 0;
    while (p < S.top)
    {
        printf("%c | ", *p++);
    }
    cout << endl;
}

/////////////////////////////////////////////////////

Status InitStack(LinkStack &S)
{
    S = NULL;
    return OK;
}

Status StackEmpty(LinkStack S)
{
    if (S == NULL)
        return TRUE;
    else
        return FALSE;
}

SElemType GetTop(LinkStack S)
{
    if (S != NULL)
    {
        return S->data;
    }
    return 0;
}

int StackLength(LinkStack S)
{
    printf("当前方法尚未实现"); //TODO
    return OK;
}

Status ClearStack(LinkStack S)
{
    printf("当前方法尚未实现"); //TODO
    return OK;
}

Status DestroyStack(LinkStack &S)
{
    printf("当前方法尚未实现"); //TODO
    return OK;
}

Status Push(LinkStack &S, SElemType e)
{
    LinkStack p = new StackNode;
    p->data = e;
    p->next = S;
    S = p; //修改栈顶指针
    return OK;
}

Status Pop(LinkStack &S, SElemType &e)
{
    if (S == NULL)
        return ERROR;
    e = S->data;
    LinkStack p = S;
    S = S->next;
    delete p;
    return OK;
}

void PrintStack(LinkStack S)
{
    cout << "| ";
    LinkStack p = S->next;
    while (p)
    {
        printf("%d | ", p->data);
        p = p->next;
    }
    cout << endl;
}
