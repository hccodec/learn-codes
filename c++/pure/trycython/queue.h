// file: queue.h
typedef struct _Queue Queue;
typedef void *QueueValue;
struct _Queue {
    QueueEntry *head;
    QueueEntry *tail;
};
Queue *queue_new(void);
void queue_free(Queue *queue);
