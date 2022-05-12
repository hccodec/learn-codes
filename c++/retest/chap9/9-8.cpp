#include <stdio.h>
#include <math.h>

const int line = 10;

int equals(float a, float b) {
    return fabsf(a - b) < 1e-5;
}

float normalize(float a) {
    if (equals(a, (int)a)) return (int)a;
    return a;
}

float equation(float x) {
    return 2 * pow(x, 3) + 4 * pow(x, 2) + 3 * x - 6;
}

float solve(float a, float b, int count = 0) {
    if (equals(a, b)) return normalize(a);

    float mid = normalize((a + b) / 2);
    
    float resa = equation(a);             // 左端点函数值
    float resb = equation(b);             // 右端点函数值
    float resc = equation((a + b) / 2);   // 中点函数值

    if (resa * resb >= 0) return 0;    
    if (resa * resc < 0) return solve(a, (a + b) / 2);
    else return solve((a + b) / 2, b);
}

int main() {
    printf("二分法求方程 2x³+4x²+3x-6=0 位于 (-10,10) 之间的一个根\n");
    float res = solve(-10, 10);
    printf("%.2f", res);
    return 0;
}