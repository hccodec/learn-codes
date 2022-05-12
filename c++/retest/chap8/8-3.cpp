#include <stdio.h>

int main() {
    printf("求分数序列的前20项和\n");
    int child = 2, mom = 1, count = 20;
    float sum = 0;
    printf("sum=(%d/%d)", child, mom);
    while (count--) {
        sum += 1.0 * child / mom;
        child += mom;
        mom = child - mom;
        if (count) printf("+(%d/%d)", child, mom);
    }
    printf("=%.6f", sum);
    return 0;
}