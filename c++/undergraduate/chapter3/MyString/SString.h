#ifndef MYSTRING_H
#define MYSTRING_H

#include "../../CLike.h"

#define CHUNKSIZE 80
#define MAXLEN 255

//串的顺序存储结构
typedef struct
{
    char ch[MAXLEN + 1];
    int length;
} SString;

//堆式顺序存储结构
typedef struct
{
    char *ch;
    int length;
} HString;

//块链结构
typedef struct Chunk
{
    char ch[CHUNKSIZE];
    struct Chunk *next;
} Chunk;

typedef struct
{
    Chunk *head, *tail;
    int curlen;
} LString;

#endif