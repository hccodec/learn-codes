#define MaxSize 10
#define ElemType double
#include <iostream>
#include <ctime>
using namespace std;

#include <fstream>

ElemType F(ElemType n)
{
    if (n == 0 || n == 1)
        return n;
    else
        return F(n - 1) + F(n - 2);
}

int test (ostream &fout, int n = 10, bool printit = true) {
    int i = 0, j = 0, k = 0; int jtem = 0;
    i = rand() % n + 1; if (i == 1) j = 1 + 2 * rand() % 1; else if (i == n) j = n - 2 * rand() % 1; else j = i - rand() % 3 + 1; k = 2 * i + j - 3;

    k = rand() % (n * (n + 1) / 2) + 1;
    i = (k + 1) / 3 + 1;
    j = k - 2 * i + 3;
    jtem = k - (k + 1) / 3 * 3 + i;
     
    // printf("k=%d, i=%d, j=%d, jtem = %d", k, i, j, jtem);
    if (printit) {
        fout << "n=" << n << ",\tk=" << k << ",\ti=" << i << ",\tj=" << j << ",\tjtem = " << jtem;
        fout << "\t" << (j == jtem ? "验证 √" : "验证 ×") << endl;
    }
    
    return j == jtem;
}

int main(int argc, char const *argv[])
{
    srand((unsigned int)time(NULL));

    ofstream fout;
    fout.open("data.txt");

    int i = 1; bool res, printit;
    time_t s = clock();
    fout << "HCCODEC LOG " << endl;

    while(i <= 200000) {
        printit = i % (200000 / 60) == 0;
        if (printit) fout << i << "\t";
        res = test(fout, rand() % 10 + 1, printit);
        i++;
        if (!res) { printf("出错"); break; }
    }
    ElemType n = 6;
    printf("F(%.f) = %.f\n", n, F(n));
    fout.close();

    return 0;
}
