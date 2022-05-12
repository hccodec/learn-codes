/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 12:01:09
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 12:23:17
 */
#include <iostream>
using namespace std;

int rollingDivision(int a, int b) {
    if (!b) return -1;
    int c = a % b;
    while(c) {
        a = b;
        b = c;
        c = a % b;
    }
    return b;
}

int main() {
    cout << "判断两个整数是否互质：" << endl;
    int a = 1, b = 1;
    cout << "可依次输入被除数和除数，用空格隔开: ";
    while (b) {
        cin >> a >> b;
        if (rollingDivision(a, b) == 1) printf("两数互质\n");
        else printf("两数不互质，最大公约数为 %d\n", rollingDivision(a, b));
    }
    return 0;
}