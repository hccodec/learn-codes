/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-08 13:25:00
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-08 18:57:35
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
    cout << "求100-200之间的所有素数:"  << endl;
    int count = 0, flag = 0;
    for (int i = 100; i <= 200; i++) {
        if (calPrime(i) - i == 1) {
            printf("%i ", i);
            // printf("%i(%i)\t", i, calPrime(i));
            flag = 1;
            count++;
        }
        if (count % 7 == 0 && flag) {
            cout << endl;
            flag = 0;
        }
    }
    return 0;
}
