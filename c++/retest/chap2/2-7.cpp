/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-10 23:01:04
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-10 23:12:03
 */
#include <iostream>
#include <cmath>
using namespace std;

// 判断完全平方数
int isCompSquare(int n) {
    int i = sqrt(n);
    return i * i == n;
}

int main() {
    cout << "输出符合条件 “加上 100 是完全平方数，加上 168 仍是完全平方数” 的整数: ";
    for (int n = 0; n < 10000; n++) {
        if (isCompSquare(n + 100) && isCompSquare(n + 168)) printf("\e[1;32m%d\e[0m", n);
    }
    return 0;
}