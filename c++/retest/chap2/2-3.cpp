/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-10 08:48:30
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-10 23:09:54
 */

#include <iostream>
#include <cmath>
using namespace std;

// 计算因数和
int calSum(int n) {
    int res = 1;
    for (int i = 2; i <= sqrt(n); i++) {
        if (n % i == 0) {
            res += i;
            res += n / i;
        }
    }
    return res;
}

int main() {
    cout << "找出 1000 以内的所有完数: " << endl;
    int count = 0;
    for (int i = 1; i <= 1000; i++) {
        if (calSum(i) == i) {
            printf("%d 是完数\n", i);
            count++;
        }
    }
    printf("\e[1;32m共 %d 个完数\e[0;0m", count);
    return 0;
}