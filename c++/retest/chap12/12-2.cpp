#include <stdio.h>
#include <string.h>
#ifdef _WIN32
#include <windows.h>
#endif
#ifdef linux
#include <unistd.h>
#endif

void mSleep(float time) {    
    fflush(stdout);
    #ifdef _WIN32
    Sleep(time * 1000);
    #endif
    #ifdef linux
    #endif
}

int dp[101][101];
int max(int a, int b) { return a > b ? a : b; }
int main() {

    printf("判断两字符串重合个数\n");
    char S1[101], S2[101];
    while (1) {
        fflush(stdin);
        printf("S1> ");
        scanf("%s", S1);
        if (!strcmp(S1, "exit")) break;
        fflush(stdin);
        printf("S2> ");
        scanf("%s", S2);
        if (!strcmp(S2, "exit")) break;
        int L1 = strlen(S1), L2 = strlen(S2);
        for (int i = 0; i <= L1; i++) dp[i][0] = 0;
        for (int i = 0; i <= L2; i++) dp[0][i] = 0;
        for (int i = 1; i <= L1; i++) {
            for (int j = 1; j <= L2; j++) {
                if (S1[i - 1] != S2[j - 1])
                    dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                printf("\e[42m \e[0m");
                mSleep(0.01);
            }
            printf(" ");
        }
        printf("\e[1;32m\n%d\e[0m\n", dp[L1][L2]);
        for (int i = 0; i < L1 + 1; i++) {
            printf("\n");
            for (int j = 0; j < L2 + 1; j++) {
                printf("%2d", dp[i][j]);
            }
        }
        printf("\n");
    }
    return 0;
}

/*//////////////////////////////////////////
动态规划思想 解题思路：

1. dp[i][j] 表示 S1[0]-S1[i-1] 和 S2[0]-S2[j-1] 最长公共子串长度
2. S1[i]=S2[j] 时，dp[i][j]=dp[i-1][j-1]+1


*///////////////////////////////////////////
