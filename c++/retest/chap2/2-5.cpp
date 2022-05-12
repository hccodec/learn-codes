/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-10 22:19:35
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-10 22:27:39
 */
#include <iostream>
#include <cmath>
using namespace std;

int main() {
    cout << "编写程序，计算是否为完全平方数:";
    int n = 1, foo = 1;
    while (n) {
        cin >> n;
        if (!n) break;
        foo = sqrt(n);
        if (foo * foo == n) printf("%d \e[1;32m是\e[0;0m完全平方数。", n);
        else printf("%d \e[1;31m不是\e[0;0m完全平方数", n);
        cout << endl;
    }
    return 0;
}