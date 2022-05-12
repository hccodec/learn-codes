/*
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-23 17:43:57
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-23 17:47:10
 */

#define ElemType char
typedef struct {
    ElemType *elem;
    int length;    
} SSTable;

int Search(SSTable ST, ElemType key, int low, int high) {
    if (low > high) return 0;
    int mid = (low + high) / 2;
    if (key > ST.elem[mid]) Search(ST, key, mid + 1, high);
    if (key < ST.elem[mid]) Search(ST, key, low, mid - 1);
    else return mid;
}

int main() {

}