#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>

int equals(float a, float b) {
    return fabs(a - b) < 1e-5;
}

void normalize(float *a, float *b) {
    if (equals(*a, 0)) *a = 0;
    if (equals(*b, 0)) *b = 0;
}

int main() {
    printf("求解一元二次方程：");
    const int thr = 20;
    srand((unsigned int)time(NULL));
    int cooe[3] = {0}, tmp0 = 0, tmp = 0, count = 3;
    float delta = 0, x1 = 0, x2 = 0;
    while (count--) {
        cooe[0] = 0;
        while (!cooe[0]) for (int i = 0; i < 3; i++) {
            tmp0 = rand() % thr + 1;
            tmp = rand() % thr / tmp0 + 1;
            cooe[i] = rand() % thr / tmp; // 随机生成三个系数。分别对应 ax²+bx+c=0
        }
        printf("\n方程 %dx²+%dx+%d=0 ", cooe[0], cooe[1], cooe[2]);
        delta = cooe[1] * cooe[1] - 4 * cooe[0] * cooe[2];
        if (delta < 0) printf("无实数解");
        else {
            x1 = - (cooe[1] + sqrt(delta)) / (2 * cooe[0]);
            x2 = - (cooe[1] - sqrt(delta)) / (2 * cooe[0]);
            normalize(&x1, &x2);
            if (delta) printf("方程有两个实根：x1 = %.2f, c2 = %.2f", x1, x2);
            else printf("有两个相同实数解：x1 = x2 = %.2f\n", x1);
        }
    }

    return 0;
}