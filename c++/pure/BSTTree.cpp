/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-09-15 15:18:40
 * @LastEditors: hccodec
 * @LastEditTime: 2021-09-15 15:53:47
 */

#include <iostream>
#include <cstring>
#include <cmath>
#include <queue>
#include <stack>
using namespace std;

typedef struct BSTNode {
    int key;
    struct BSTNode *lchild, *rchild;
} BSTNode, *BSTree;

void Create_BST(BSTree &T, int str[], int n) {
    T = NULL;
    int i = 0;
    while (i < n) BSTInsert(T, str[i++]);
}

BSTNode *BST_Search(BSTree T, int key) {
    while (T && key != T->key) {
        if (key < T->key) T = T->lchild;
        else T = T->rchild;
    }
    return T;
}

BSTNode *BSTSearch(BSTree T, int key) {
    if (!T || key == T->key) return T;
    if (key > T->key) return BSTSearch(T->rchild, key);
    else return BSTSearch(T->lchild, key);
}

bool BST_Insert(BSTree &T, int k) {
    while (T && k != T->key)
        if (k < T->key) T = T->lchild;
        else T = T->rchild;
    if (T) return false;
    T = new BSTNode;
    T->key = k;
    T->lchild = T->rchild = NULL;
    return true;
}

bool BSTInsert(BSTree &T, int k) {
    if (!T) {
        T = new BSTNode;
        T->key = k;
        T->lchild = T->rchild = NULL;
        return true;
    }
    else if (k == T->key) return false;
    if (k < T->key) return BSTInsert(T->lchild, k);
    else return BSTInsert(T->rchild, k);
}

int main() {
    
}
