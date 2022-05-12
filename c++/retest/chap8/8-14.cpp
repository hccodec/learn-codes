#include <stdio.h>
#include <stdlib.h>

struct frac {
    int child;
    int mom;
    int operator-(frac a) {return this->child * a.mom - this->mom * a.child; }
};

void bubble(frac *list, int n) {
    n--;
    frac tmp;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n - i; j++)
            if (list[j] - list[j + 1] > 0) {
                tmp = list[j];
                list[j] = list[j + 1];
                list[j + 1] = tmp;
            }
}

int main() {
    printf("给定 N，输出最简真分数序列：\n");
    int N = 0, n = 0;
    int judge(int j, int i); // 判断一个分数是否是最简真分数（j < i 且不可再通分）
    frac *res = (frac*)malloc(sizeof(frac) * N * N);
    while (1) {
        n = 0;
        printf("\n请输入 n: ");
        scanf("%d", &N);
        if (!N) break;

        // 对所有真分数进行判断并将合法结果放入数组 res 中
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (judge(j, i)) {
                    res[n++] = {j, i};
                }
            }
        }

        bubble(res, n); // 进行冒泡排序

        // 打印结果
        for (int i = 0; i < n; i++) {
            printf("%d/%d ", res[i].child, res[i].mom);
        }
        printf("\n");
    }
    return 0;
}

// 判断最简真分数，j 为分子，i 为分母
int judge(int j, int i) {
    if (j >= i) return 0; // j >= i 说明分子大于分母，不是真分数
    int k = i % j;
    while (k) {
        i = j;
        j = k;
        k = i % j;
    }
    return j == 1; // j 为 1 说明该真分数不可通分。即为最简真分数
}
