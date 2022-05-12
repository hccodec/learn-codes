#include <stdio.h>
#include <stdlib.h>

int flag[5] = {0};

// 深度优先算法 (在 for 循环中递归，用递归栈实现的回溯)
void dfs(int a[][5], int v, int k, int path[], int i = 0) {
    path[i] = v;
    flag[v] = 1;

    if (i == k) { // 达到步长，输出
        for (int j = 0; j <= k; j++) {
            if (j) printf(" -> ");
            printf("%c", path[j] + 'A');
        }
        printf("\n");
    } else {
        for (int j = 0; j < 5; j++)
            if (a[v][j] && !flag[j]) { // 如果这个节点未访问过，递归调用
                dfs(a, j, k, path, i + 1);
            }
    }
    flag[v] = 0;

}

int main() {
    printf("计算顶点 i 经 k 步能到达的所有节点\n");
    int i = 0, k = 0;
    int path[5] = {0}, a[5][5] = {
        0, 1, 0, 1, 0,
        1, 0, 1, 0, 1,
        0, 1, 0, 1, 1,
        1, 0, 1, 0, 0,
        0, 1, 1, 0, 0
    };
    while (1) {
        // 输入 i 和 k
        for (int i = 0; i < 5; i++) path[i] = INT_MAX;
        printf("===========\n");
        printf("i k = ");
        scanf("%d %d", &i, &k); if (!(i || k)) break; fflush(stdin);
        if (i < 0 || i >= 5 || k < 0 || k >= 5) continue;
        dfs(a, i, k, path);
    }
    return 0;
}