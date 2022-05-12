/*
 * @Description:
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 08:50:06
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 12:00:44
 */
#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    cout << "求立方小于M的最大整数" << endl;
    int M = 1;
    while (M)
    {
        cin >> M;
        for (int i = 1; i < M; i++)
        {
            if (pow(i, 3) < M && pow(i + 1, 3) >= M)
            {
                cout << i << endl;
                break;
            }
        }
    }
    return 0;
}