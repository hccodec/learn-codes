cdef class PyQueue:
    cdef Queue *_c_queue

    def __cinit__(self):
        self._c_queue = queue_new()

    def __dealloc__(self):
        if self._c_queue is not NULL:
            queue_free(self._c_queue)