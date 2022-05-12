/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2022-02-13 17:30:52
 * @LastEditors: hccodec
 * @LastEditTime: 2022-02-13 18:41:01
 */
#include <stdio.h>

int isValid(char *input, int n) {
    for (int i = 0; i < n; i++)
        if (input[i] < '0' || input[i] > '9') return 0;
    return 1;
}

int char2int(char *input, int n) {
    int res = 0;
    for (int i = 0; i < n; i++) {
        res = input[i] - '0' + res * 10;
    }
    if (res < 100) return res;
    else {
        printf("数据 %d 控制在 100 以内，改为封顶值 99", res);
        return 99;
    }
}

// length1 表示元素即下标的集合长度，length2 表示输出交并集的数组长度
void printSet(int *set, int length1, int length2) {
    printf("{");
    int count = 0;
    for (int i = 0; i < length1; i++) {
        if (set[i]) {
            printf("%d", i + 1);
            if (length2 - count++ > 1) printf(", ");
        }
    }
    printf("}");
}

int main() {
    printf("（用稀疏数组存储）计算交集\n请输入两个数组，元素间用空格隔开\n数组间请用独立的半角逗号隔开:\n");

    // 数组输入
    int A[100] = {0}, B[100] = {0}; // 接受输入的数组
    int linput = 0, inputCount = 0, inputint = 0;
    int Amax = 0, Bmax = 0; // 分别存储两个数组中的最大值
    char input[20]; // 暂存接收到的数据

    // 循环接受输入，检查后放到对应数组中，扫描到,则inputCount加一
    while (2 - inputCount) {
        linput = 0;
        scanf("%s", &input);
        while (input[linput]) linput++;
        if (linput > 2) printf("数据限制在 100 以内\n");
        else if (linput == 1 && input[0] == ',') inputCount++;
        else if (!isValid(input, linput)) printf("输入有误\n");
        else {
            inputint = char2int(input, linput);
            if (inputCount) {
                if (inputint > Bmax) Bmax = inputint;
                if (B[inputint - 1]) printf("B 数组忽略 %d\n", inputint);
                else B[inputint - 1] = 1;
            }
            else {
                if (inputint > Amax) Amax = inputint;
                if (A[inputint - 1]) printf("A 数组忽略\n", inputint);
                else A[inputint - 1] = 1;
            }
        }
    }


    int max = Amax > Bmax ? Amax : Bmax, min = Amax < Bmax ? Amax : Bmax;
    int unionSet[max] = {0}, interSet[min] = {0}, lu = 0, li = 0;

    // 求交集和并集
    for (int i = 0; i < min; i++) if (A[i] && B[i]) {
        interSet[i] = 1;
        li++;
    }
    for (int i = 0; i < max; i++) if (A[i] || B[i]) {
        unionSet[i] = 1;
        lu++;
    }

    printf("交集长 %d，为：", li);
    printSet(interSet, min, li);
    printf("，并集长 %d，为：", lu);
    printSet(unionSet, max, lu);
    printf("\n");
    
    return 0;
}