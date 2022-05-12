/* 查找算法
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-16 08:27:01
 * @LastEditors: hccodec
 * @LastEditTime: 2021-10-16 08:27:02
 */

typedef int ElemType;
typedef struct SSTable { ElemType *elem; int TableLen; } SeqList;

/** 顺序查找
 * @param {SSTable} ST
 * @param {ElemType} key
 * @return {*}
 * @author: hccodec
 */
int Search_Seq (SSTable ST, ElemType key) {
    ST.elem[0] = key; int i; //哨兵
    for (i = ST.TableLen; ST.elem[i] != key; i--);
    return i;
}

/** 折半查找
 * @param {SeqList} L
 * @param {ElemType} key
 * @return {*}
 * @author: hccodec
 */
int Binary_srerach(SeqList L, ElemType key) {
    int low = 0, high = L.TableLen - 1, mid;
    while (low <= high) {
        mid = (low + high) / 2;
        if (L.elem[mid] == key) return mid;
        else if (L.elem[mid] > key) high = mid - 1;
        else low = mid + 1;
    }
    return -1;
}

