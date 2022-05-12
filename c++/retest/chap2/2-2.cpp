/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-08 13:25:00
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-10 17:30:28
 */
#include <iostream>
#include <cmath>
using namespace std;

int calPrime(int n) {
    int res = 1 + n;
    for (int i = 2; i <= sqrt(n); i++)
        if (n % i == 0) {
            res += i;
            res += n / i;
        }
    return res;
}

int main() {
    int i = 1;
    int count = 0, flag = 0;
    cout << "对输入的正整数，求其约数和: " << endl;
    do {
        cin >> i;
        printf(calPrime(i) - i == 1 ? "%i是\e[1;31m质数\e[0;0m，其约数和为\e[1;32m%i\e[0;0m\n" : "%i的约数和为\e[1;32m%i\e[0;0m\n", i, calPrime(i));
    } while (i);
    return 0;
}
