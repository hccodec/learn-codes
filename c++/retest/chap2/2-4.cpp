/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-10 19:53:51
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-10 22:19:10
 */

#include <iostream>
#include <cmath>
using namespace std;

int main(void) {
    cout << "将一个正整数分解质因数：" << endl;
    int n = 1, i = 1, flag = 1;
    while (n) {                     // 反复执行直到输入 0
        cin >> n;
        if (!n) {
            cout << "接收到数字 0，程序退出";
            break;
        }
        i = 1, flag = 1;
        cout << n << "=";
        while (n - 1) {
            i = 2;
            while (n % i != 0) i++; // 寻找除 1 以外的最小因数
            if (n == i) break;      // 判断该因数是否已是 n 本身，若是则跳出循环直接输出最后的因数
            printf("%d*", i);       // 将因数和乘号一并输出
            n /= i;                 // 用 i 除 n 以便进一步寻找因数
            flag = 0;
        }
        if (flag) printf("1*");
        cout << i << endl;          // 输出最后的因数
    }
    return 0;
}