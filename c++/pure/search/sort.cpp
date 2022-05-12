/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-28 11:11:14
 * @LastEditors: hccodec
 * @LastEditTime: 2021-12-28 18:03:42
 */
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <ctime>
using namespace std;

#define ElemType int
struct SSTable { ElemType *elem; int length = 0; void Ins(ElemType i) { elem[length++] = i; }; };
typedef struct LNode {ElemType data; LNode *next; } *LList; 

class Util {
    public:
    static void Swap(int &a, int &b) {
        int tmp = a; a = b; b = tmp;
    }
    static LList ConvertToList(ElemType A[], int n) {
        LList L = new LNode; int i = 1;
        L->data = n;
        LNode *p = L;
        while (i <= n) {
            LNode *q = new LNode;
            q->data = A[i]; p->next = q;
            
        }
        return L;
    }
};

void InsertSort(int A[], int n, bool print = false)
{
    int i, j, low, high, mid;
    for (i = 2; i <= n; i++)
    {                          //针对 i 所在元素做的一轮查找和插入
        A[0] = A[i];           //待排序元素放入哨兵
        low = 1, high = i - 1; //指定对半查找的首位指针
        while (low <= high)
        {                           //执行对半的查找循环的条件
            mid = (low + high) / 2; //指定mid指针
            if (A[mid] > A[0])
                high = mid - 1; //比对 mid 所在元素的值。若比哨兵大说明哨兵在左，去左半边查找。high指针指到mid左边
            else
                low = mid + 1; //哨兵不在左就去右半边查找。low指针指到mid右边
        }                      //结果就是 mid = high < low
        for (j = i - 1; j >= high + 1; --j)
            A[j + 1] = A[j];
        A[high + 1] = A[0];
    }
}

void ShellSort(int A[], int n, bool print = false)
{
    int d = n / 2, i, j;
    while (d > 0)
    {
        for (i = d + 1; i <= n; i++)
        { // 向右移动子表
            if (A[i] < A[i - d])
            {
                A[0] = A[i];
                for (j = i - d; j > 0 && A[0] < A[j]; j -= d) // 对子表进行仅一次的直接插入排序
                    A[j + d] = A[j];
                A[j + d] = A[0];
            }
        }
        d /= 2;
    }
}

void BubbleSort(int A[], int n, bool print = false)
{
    for (int i = 1; i <= n - 1; i++)
    {
        bool flag = false;
        // 下沉
        for (int j = n; j > i; j--)
            if (A[j - 1] > A[j])
            {
                Util::Swap(A[j], A[j - 1]);
                flag = true;
            }
        if (flag == false)
            return;
    }
}

void BubbleSort2(int A[], int n, bool print = false)
{
    for (int i = 1; i <= n - 1; i++)
    {
        bool flag = false;
        int last = i;
        for (int j = n; j > i; j--)
        {
            if (A[j - 1] > A[j])
            {
                Util::Swap(A[j], A[j - 1]);
                flag = true; last = j - 1;
            }
            if (n <= 50 && print)
            {
                printf("\n- ：");
                for (int i = 1; i <= n; i++)
                {
                    if (i == j || i == j - 1) printf("\e[33m");
                    printf("%d\e[0m ", A[i]);
                }
            }
        }
        i = last;
        if (flag == false) return;
        if (n <= 50 && print) cout << "\n--------------------------------";
    }
}

void BubbleSort3(int A[], int n, bool print= false) {
    int low = 1, high = n; bool flag = true; int count = 0;
    while (low < high && flag) {
        flag = false;
        for (int i = low; i < high; i++)
            if (A[i] > A[i + 1]) {
                int tmp = A[i]; A[i] = A[i + 1]; A[i + 1] = tmp;
                flag = true;
            }
        high--;
        for (int i = high; i > low; i--)
            if (A[i] < A[i - 1]) {
                int tmp = A[i]; A[i] = A[i - 1]; A[i - 1] = tmp;
                flag = true;
            }
        low++;
        if (print) {
            printf("\n%d): ", ++count);
            for (int i = 1; i <= n; i++)
            {
                if (i == high + 1 || i == low - 1)
                    printf("\e[33m");
                printf("%d ", A[i]);
                printf("\e[0m");
            }
        }
    }
}

int Partion(int A[], int low, int high, int n, bool print = false)
{
    int pivot = A[low];
    while (low < high)
    {
        while (low < high && A[high] >= pivot) --high; A[low] = A[high]; // 比 p 小的移到左端
        while (low < high && A[low] <= pivot) ++low; A[high] = A[low]; // 比 p 大的移到右端
    }
    A[low] = pivot;
    
        
    return low;
}

void PRINT(int A[], int state[], int n, int i, int j, int low, int high, bool print) {
    if (print) {
        printf("\n%2d] ", A[low]); for (int k = 1; k <= n; k++) { if (state[k]) printf("\e[33m"); if (k == i || k == j) printf("\e[36m"); printf("%d \e[0m", A[k]); }
        printf(" (%d, %d, %d)", low - 1, high - 1, high);
    }
}

void QuickSort(int A[], int low, int high, int n, int state[], int mode = 0, bool print = false)
{
    int i, j, k, pivotPos;
    if (low < high)
    {
        if (mode == 0) {
            pivotPos = Partion(A, low, high, n, print);
            QuickSort(A, low, pivotPos - 1, n, state, mode, print);
            QuickSort(A, pivotPos + 1, high, n, state, mode, print);
        }
        else {
            i = low, j = high + 1, k = A[i];
            while (i < j) {
                i++; while (A[i] < k) i++;
                j--; while (A[j] > k) j--;
                if (i < j) Util::Swap(A[i], A[j]);
                // PRINT(A, state, n, i, j, low, high, print);
            }
            Util::Swap(A[low], A[j]);
            state[pivotPos] = true;
        }
    }
    if (low <= high) PRINT(A, state, n, i, j, low, high, print);
    if (low < high) {
        if (mode == 0) {
            QuickSort(A, low, pivotPos - 1, n, state, mode, print);
            QuickSort(A, pivotPos + 1, high, n, state, mode, print);
        } else {
            QuickSort(A, low, j - 1, n, state, mode, print);
            QuickSort(A, j + 1, high, n, state, mode, print);
        }
    }
}

void SimpleSelect(int A[], int n, bool print = false)
{
    int minPos, tmp;
    for (int i = 1; i < n; i++)
    {
        for (int j = minPos = i; j <= n; j++)
            if (A[minPos] > A[j])
                minPos = j;
        tmp = A[minPos];
        A[minPos] = A[i];
        A[i] = tmp;
    }
}

void HeadAdjust(int A[], int k, int len, bool print = false) {
    A[0] = A[k];
    for (int i = 2 * k; i <= len; i *= 2) {
        if (i < len && A[i] < A[i + 1]) i++;
        if (A[0] >= A[i]) break;
        else { A[k] = A[i]; k = i; }
        A[k] = A[0];
    }
}


void HeapSort(int A[], int len, bool print = false) {
    for (int i = len / 2; i > 0; i--) HeadAdjust(A, i, len); // BuildMaxHeap
    for (int i = len; i > 1; i--) {
        int tmp = A[1]; A[1] = A[i]; A[i] = tmp;
        HeadAdjust(A, 1, i - 1);
    }
}

void Merge(int A[], int low, int mid, int high) {
    int i, j, k, B[high - low + 1];
    for (k = low; k < high; k++) B[k] = A[k];
    for (i = low, j = mid + 1, k = i; i <= mid && j <= high; k++) {
        if (B[i] <= B[j]) A[k] = B[i++];
        else A[k] = B[j++];
    while (j <= mid) A[k++] = B[i++];
    while (j <= high) A[k++] = B[i++];
    }
}

void MergeSort(int A[], int low, int high, bool printDetail = false) {
    if (low < high) {
        int mid = (low + high) / 2;
        MergeSort(A, low, mid);
        MergeSort(A, mid + 1, high);
        MergeSort(A, low, mid, high);
    }
}

void RadixSort (LList L) {
    
}

double testSort(int type, int num, bool printDetail = false, bool refresh = true)
{
    clock_t start, end;

    int b[num + 1]; // a 为待排序数组，b 为存放排序状态的数组
    if (refresh) srand((unsigned int)time(NULL));
    int a[num + 1]; for (int i = 1; i <= num; i++) a[i] = rand() % (2 * num) + 1;
    // for (int i = 1; i <= num; i++) printf("%d ", a[i]); printf("\n"); // 打印数组

    // num = 10;
    // int a[num + 1] = {0, 12, 2, 16, 30, 28, 10, 16, 20, 6, 18};

    for (int i = 1; i <= num; i++) b[i] = false;
    if (printDetail)
    {
        cout << "前"
             << "：";
        for (int i = 1; i <= num; i++)
            cout << a[i] << " ";
    }
    start = clock();
    switch (type)
    {
    case 0:
        QuickSort(a, 1, num, num, b, 1, printDetail);
        break;
    case 1:
        HeapSort(a, num, printDetail);
        break;
    case 2:
        ShellSort(a, num, printDetail);
        break;
    case 3:
        MergeSort(a, 1, num, printDetail);
        break;
    case 4:
        InsertSort(a, num, printDetail);
        break;
    case 5:
        SimpleSelect(a, num, printDetail);
        break;
    case 6:
        BubbleSort3(a, num, printDetail);
        break;
    case 7:
        BubbleSort(a, num, printDetail);
        break;
    case 8:
        BubbleSort2(a, num, printDetail);
        break;
    default:
        printf("比对(%d)无效", type);
    }
    end = clock();
    if (printDetail)
    {
        printf("\n后：\e[33m");
        for (int i = 1; i <= num; i++)
            cout << a[i] << " ";
        printf("\e[0m");
    }
    return (double)(end - start) / CLOCKS_PER_SEC;
}

void PrintTime(double time)
{
    if (time >= 60)
        printf("\e[31m%.f min %.3f s\e[0m\n", time / 60, time - floor(time / 60) * 60);
    else if (time > 10)
        printf("\e[31m%.3f s\e[0m\n", time);
    else if (time > 3)
        printf("\e[33m%.3f s\e[0m\n", time);
    else
        printf("\e[32m%.f ms\e[0m\n", time * 1000);
}

int main(int argc, char const *argv[])
{
    int type = 0;
    if (argc == 2) type = atoi(argv[1]);

    int n[] = {10000, 100000, 200000};
    char *words[] = {(char *)"快速排序：\n", (char *)"堆排序：\n", (char *)"希尔排序：\n", (char *)"（二路）归并排序：\n", (char *)"直接插入排序：\n", (char *)"简单选择排序：\n", (char *)"交替冒泡排序：\n", (char *)"冒泡排序：\n", (char *)"改进的冒泡排序：\n"};

    switch (type)
    {
    case 1:
        if (argc >= 3) for (int i = 0; i < sizeof(words) / sizeof(words[0]); i++) printf("\e[32m%d. %s\e[0m", i, words[i]);
        printf("查看排序过程：");
        int i;
        if (argc >= 3) i = atoi(argv[2]); else i = 0;
        {
            printf("\e[36m%d. %s\e[0m", i, words[i]);
            PrintTime(testSort(i, 15, true));
        }
        break;

    case 0:
        for (int i = 0; i < sizeof(words) / sizeof(words[0]); i++)
        {
            printf("\e[36m%s\e[0m", words[i]);
            // PrintTime(testSort(i, 10, true));
            for (int j = 0; j < sizeof(n) / sizeof(n[0]); j++)
                if (n[j] >= 100000 && i >= sizeof(words) / sizeof(words[0]) - 2)
                printf("- 关键字 %6d 用时大于数十秒 O(n^2)\n", n[j]);
                else
            {
                printf("- 关键字 %6d 用时 ", n[j]); PrintTime(testSort(i, n[j]));
            }
        }
        break;

        default: printf("检查选项 %d", type); break;
    }
    return 0;
}
