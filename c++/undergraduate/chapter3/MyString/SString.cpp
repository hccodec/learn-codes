#include "SString.h"
#include "../../chapter2/LinkList/LinkList.cpp"

#define DEBUG 0
#define TStringLength 8

Status InitSString(SString &S)
{
    // S.ch[0] = 'a';
    S.length = 0;
    return OK;
}

Status StrAssign(SString &T, char s[])
{
    // T.ch = s;
    return OK;
}

Status InsertSString(SString &S, int i, char e)
{
    if (i < 1 || i > S.length + 1)
        return ERROR;
    if (S.length == MAXLEN)
        return OVERFLOW;
    S.ch[i] = e;
    S.length++;
    return OK;
}

Status AppendSString(SString &S, char e)
{
    return InsertSString(S, S.length + 1, e);
}

int SStringLength(SString S)
{
    return S.length;
}

void PrintSString(SString S)
{
    for (int i = 1; i <= S.length; i++)
        cout << "" << S.ch[i] << "";
}

int Index_BF(SString S, SString T)
{
    int i = 1, j = 1, count = 0;
    LinkList R;
    InitList(R);
    while (i <= S.length && j <= T.length)
    {
        if (S.ch[i] == T.ch[j])
        {
            ++i;
            ++j;
        }
        else
        {
            InsertElem(R, ListLength(R) + 1, count);
            i = i - j + 2;
            j = 1;
        }
        count++;
    }
    printf("[频度为%d] ", count);
    if (j >= T.length)
        return i - T.length;
    return 0;
}

void get_nextval(SString T, int (&nextval)[TStringLength])
{
    int i = 1, j = 0;
    nextval[1] = 0;
    while (i < T.length)
    {
        if (j == 0 || T.ch[i] == T.ch[j])
        {
            ++i, ++j;
            if (T.ch[i] != T.ch[j])
                nextval[i] = j;
            else
                nextval[i] = nextval[j];
        }
        else
            j = nextval[j];
    }
}

void getNext(SString T, int (&next)[TStringLength])
{
    next[1] = 0;
    int i = 1, j = 0, count = 0;
    while (i < T.length)
    {
        if (j == 0 || T.ch[i] == T.ch[j])
        {
            ++i;
            ++j;
            next[i] = j;
        }
        else
            j = next[j];
        count++;
    }
}

int Index_KMP(SString S, SString T)
{
    int i = 1, j = 1, count = 0;
    int next[TStringLength] = {};
    int nextval[TStringLength] = {};
    getNext(T, next);
    get_nextval(T, nextval);
    while (i <= S.length && j <= T.length)
    {
        if (j == 0 || S.ch[i] == T.ch[j])
        {
            ++i;
            ++j;
        }
        else
        {
            j = next[j];
        }
        count++;
    }
    printf("[频度为%d] ", count);
    // cout << endl;
    if (j > T.length)
        return i - T.length;
    return 0;
}