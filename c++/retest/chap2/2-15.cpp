/* Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-11 17:37:04
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-11 17:37:05
*/
#include <stdio.h>

int main() {
    printf("乘法口诀表\n");
    for (int i = 1; i < 10; i++) {
        for (int j = 1; j <= i; j++) {
            printf("%d*%d=%d ", i, j, i * j);
        }
        printf("\n");
    }
    return 0;
}
