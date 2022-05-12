#define MaxSize 10
#define ElemType int
typedef struct {
    ElemType data[MaxSize];
    int front, rear;
} SqQueue;
// bool initQueue(SqQueue &Q);
// bool isQueueFull(SqQueue Q);
// bool enQueue(SqQueue &Q);
void testQueue();