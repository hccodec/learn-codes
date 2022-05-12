/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-16 21:27:01
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 21:28:01
 */
/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-16 21:27:01
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-16 21:27:01
 */
#include <stdio.h>

int main() {
    printf("求字符串长度\n");
    char str[50];
    int len = 0;
    while (1) {
        len = 0;
        printf("输入字符串：");
        scanf("%s", str);
        while (str[len]) len++;
        if (len == 1 && str[0] == '0') break;
        printf("长度为 %d\n", len);
    }
    return 0;
}