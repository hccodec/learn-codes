/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-11-12 21:07:41
 * @LastEditors: hccodec
 * @LastEditTime: 2021-11-12 22:11:02
 */
#include <iostream>
using namespace std;


int main(int argc, char const *argv[])
{
    int m = 5, n = 5, count = 0, tmpi, tmpj;
    int a[m][n] = {
        73, 47, 29, 77, 2, 79, 25, 61, 65, 91, 87, 28, 46, 41, 71, 0, 90, 77, 21, 53, 42, 15, 87, 8, 92
    };

    // printf("%d ", m * n);
    // return 0;

    for (int i = 0; i < m * n - 1; i++)
        for (int j = i + 1; j < m * n; j++) {
            
            tmpi = i, tmpj = j;

            if (a[0][i] > a[0][j]) {
                int tmp = a[0][i];
                a[0][i] = a[0][j];
                a[0][j] = tmp;
            }
            ++count;
            printf("========= 第 %d 次 =====\n", count);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i * 5 + j == tmpj || i * 5 + j == tmpi) printf("\e[1;32m");
                    printf("%3d\e[0m ", a[i][j]);
                }
                printf("\n");
            }
        }
            printf("========= %d =====\n", count);

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) printf("%3d ", a[i][j]);
        printf("\n");
    }
    
    return 0;
}
