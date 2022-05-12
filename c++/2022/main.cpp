/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-01-15 19:56:49
 * @LastEditors: hccodec
 * @LastEditTime: 2022-01-18 10:09:46
 */
#include <iostream>
using namespace std;

int _qpow(int a, int n) {
    if (n == 0) return 1;
    else if (n % 2 == 1) return _qpow(a, n - 1) * a;
    else {
        int tmp = _qpow(a, n / 2);
        return tmp * tmp;
    }
}

int qpow(int a, int n) {
    int ans = 1;
    while (n) {
        if (n & 1) ans *= a;
        a *= a;
        n >>= 1;
    }
    return ans;
}


int main() {
    int res = qpow(2, 10);
    cout << res << endl;
    cout << (0b10110001 >> 4 == 11 ? "\e[32mtrue\e[0m" : "\e[31mfalse\e[0m");
    return 0;
}