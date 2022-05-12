/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-10 22:29:26
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-10 23:15:57
 */

#include <iostream>
#include <cmath>
using namespace std;

// 判断完全平方数
int isCompSquare(int n) {
    int i = sqrt(n);
    return i * i == n;
}

// 提取三位数中每一位的数并判断是否有两位数字相同
int isDigitSame(int n) {
    int a = n / 100, b = n % 100 / 10, c = n % 10;
    return a == b || a == c || b == c;
}

int main() {
    cout << "输出所有三位数中，既是完全平方数，又有两位数字相同的数: " << endl;
    for (int n = 100; n < 1000; n++) {
        if (isCompSquare(n) && isDigitSame(n)) cout << n << " ";
    }
    return 0;
}