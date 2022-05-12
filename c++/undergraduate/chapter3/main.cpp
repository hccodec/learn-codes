#include "Test.cpp"
#include "../chapter2/Stack/Stack.cpp"
#include "../chapter3/MyString/SString.cpp"
#include <string.h>

void CreateSString(SString &S, string s)
{
    InitSString(S);

    for (int i = 0; i < s.length(); i++)
        AppendSString(S, s[i]);
    printf("%2d ", S.length);
    PrintSString(S);
    cout << endl;
}

void PrintSStringInfo(SString T)
{

    int tmp_next[TStringLength] = {};
    int tmp_nextval[TStringLength] = {};
    getNext(T, tmp_next);
    get_nextval(T, tmp_nextval);
    cout << "   ";
    for (int i = 1; i <= TStringLength; i++)
        cout << tmp_next[i];
    cout << endl
         << "   ";
    for (int i = 1; i <= TStringLength; i++)
        cout << tmp_nextval[i];
    cout << endl;
}

int main()
{
    SString S, T;
    CreateSString(S, "acbdaababaabcacbcdbac");
    // CreateSString(T, "abaabcac");
    CreateSString(T, "abaabcac");
    
    PrintSStringInfo(T);

    cout << "Index_BF:" << Index_BF(S, T) << endl;
    cout << "Index_KMP:" << Index_KMP(S, T) << endl;

    return 0;
}