#include <stdio.h>

float k(float x) {
    return 6 * x * x - 8 * x + 3;
}

float y(float x) {
    return 2 * x * x * x - 4 * x * x + 3 * x - 6;
}

float equals(float a, float b) {
    return b > a ? b - a < 1e-5 : a - b < 1e-5;
}

int main() {
    printf("牛顿迭代法求方程 2x³-4x²+3x-6=0 在 x=1.5 附近的根\n");
    float x = 1.5;

    while (!equals(y(x), 0)) {
        if (!(equals(k(x), 0))) x -= y(x) / k(x);
        else printf("无法用牛顿迭代法求解");
    }

    if (equals(x, (int)x)) printf("根的值为 %d", (int)x);
    else printf("根的值为 %f", x);
    
    return 0;
}
