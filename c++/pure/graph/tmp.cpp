/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-12-16 01:27:22
 * @LastEditors: hccodec
 * @LastEditTime: 2021-12-16 01:41:26
 */

#include <iostream>
using namespace std;

#define Keytype int
#define Elemtype int

typedef struct index { Keytype key, no; } indextype;
typedef struct { Elemtype data; Keytype key; } Recttype;

void Createindex ( Recttype r[], indextype (&idx)[6], int n) {
    int i, j;
    for (i = 1; i <= n; i++) {
        j = i - 1;
        while (j > 0 && idx[j].key > r[i].key) {
            idx[j + 1] = idx[j];
            j--;
        }
        idx[j + 1].key = r[i].key;
        idx[j + 1].no = i;
    }
}

int main(int argc, char const *argv[])
{
    int n = 6;
    Recttype r[6];
    for (int i = 0; i < n; i++) { r[i].data = 3*i; r[i].key = 2*i; }
    cout << r[3].data << r[3].key;
    indextype idx[6];
    Createindex(r, idx, n);
    return 0;
}
