/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-16 15:38:02
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-16 15:51:51
 */

typedef char ElemType;
struct String { ElemType *str; int size = 0, FastFind(String pat) const; int f[]; };

int String::FastFind(String pat) const {
    int p = 0, s = 0;
    int m = pat.size, n = size;
    while (p < m && s < n) {
        if (pat.str[p] == str[s]) { p++; s++; }
        else if (p == 0) s++;
        else p = pat.f[p - 1] + 1;
        if (p < m) return -1;
        return s - m;
    }

}

String NewString(char* target, int size) {
    String s;
    s.str = target; s.size =size;
}

int main(int argc, char const *argv[])
{
    /* code */
    return 0;
}
