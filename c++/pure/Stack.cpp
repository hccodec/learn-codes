#define ElemType char
#define MaxSize 10
#include <iostream>
#include <cstring>
using namespace std;
typedef struct { ElemType data[MaxSize]; int top; } SqStack;
void Do(bool fun, const char * msg = "") { if (!fun) printf("\e[3;31m%s执行失败\e[0m ", msg); }
void InitStack(SqStack &S) {S.top = -1; }
bool StackEmpty(SqStack S) { return S.top == -1; }
bool Push(SqStack &S, ElemType x) { if (S.top >= MaxSize) return false; S.data[++S.top] = x; return true; }
bool Pop(SqStack &S, ElemType &x) { if (S.top == -1) return false; x = S.data[S.top--]; return true; }
bool GetTop(SqStack &S, ElemType &x) { if (S.top == -1) return false; x = S.data[S.top]; return true; }
void PrintStack(SqStack S) { for (int i = 0; i < 1 + (S.top + MaxSize) % MaxSize; i++) printf("\e[32m%c\e[0m ", S.data[i]); cout << endl; }


void testStack() {
    SqStack S; InitStack(S); for (int i = 0; i < 11; i++) if(!Push(S, i + 1)) cout << "\e[3;31m入栈失败 \e[0m";
    PrintStack(S);
}

bool Symmetry(const char * s, int n) {
    int mid = n / 2; SqStack S; InitStack(S);
    for (int i = 1; i <= n; i++) {
        if (i <= mid) Push(S, s[i - 1]);
        else {
            if (i == mid + 1) i += n % 2;
            ElemType x; Pop(S, x);
            // printf("\n比较 ()%c 与 (%d)%c", x, i - 1, s[i - 1]);
            if (x != s[i - 1]) return false;
        }
    }
    return true;
}
struct ShareStack { ElemType data[MaxSize]; int top1, top2; };
void InitShareStack(ShareStack &S) { S.top1 = -1; S.top2 = MaxSize; }
bool Push(ShareStack &S, bool type, ElemType x) { if (S.top2 - S.top1 == 1) return false; if (type) S.data[++S.top1] = x; else S.data[--S.top2] = x; return true; }
bool Pop(ShareStack &S, bool type, ElemType &x) {
    if ((type && (S.top1 == -1)) || (!type && (S.top2 == MaxSize))) return false;
    if (type) x = S.data[S.top1--];
    else x = S.data[S.top2++];
    return true;
}
void PrintShareStack(ShareStack S) {
    
    if (S.top1 != -1) {cout << "\n栈1 ";for (int i = 0; i <= S.top1; i++) printf("%c ", S.data[i]);}
    
    if (S.top2 != MaxSize) {cout << "\n栈2 ";for (int i = MaxSize - 1; i >= S.top2; i--) printf("%c| ", S.data[i]);}
}

int main(int argc, char const *argv[]) {
    // testStack();
    ShareStack S; InitShareStack(S);
    for (int i = 0; i < 6; i++) Do(Push(S, 1, (char)(97+i)));
    for (int i = 0; i < 7; i++) Do(Push(S, 0, (char)(97+i)),"栈2压栈");
    PrintShareStack(S);
    // const char * s = "asdghjjhgdsa"; if (Symmetry(s, strlen(s))) printf("对称"); else printf("不对称");

    return 0;
}
