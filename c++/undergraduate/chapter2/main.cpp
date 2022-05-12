#include <iostream>
#include <iomanip>
#include "./Test.cpp"
#include "./Temp.cpp"
using namespace std;

int GetMax(LinkList p, int max)
{
    if (!p)
        return max;
    if (max < p->data)
        max = p->data;
    return GetMax(p->next, max);
}

int CountNode(LinkList p, int count)
{
    if (!p)
        return count;
    return CountNode(p->next, ++count);
}

double GetAvg(LinkList p, int count, int max)
{
    if (!p)
    {
        return ((float)max / (float)count);
    }
    if (max < p->data)
        max = p->data;
    return GetAvg(p->next, ++count, max);
}

void TestConvolution()
{
    LinkList L;
    InitList(L);
    srand(time(NULL));
    for (int a = 0; a < 5; a++)
        InsertElem(L, ListLength(L) + 1, rand() % 10 + 97);
    PrintList(L);
    printf("[GetMax] %d\n", GetMax(L, 0));
    printf("[CountNode] %d\n", CountNode(L, 0));
    printf("[GetAvg] %.2f\n", GetAvg(L, 0, 0));
    cout << endl;
}

int main(int argc, char const *argv[])
{
    ChoiceMaker();
    // TestConvolution();

    return 0;
}
