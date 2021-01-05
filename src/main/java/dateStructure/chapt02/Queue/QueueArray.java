package dateStructure.chapt02.Queue;

// 请注意，无论队列的实际规模 有多大，这一复杂度都在事先就注定了，因此，在实际应用中通常效率很低
public class QueueArray implements Queue{
    public static final int CAPACITY = 500;
    private int capacity;
    private  Object[] _queue;
    private int f = 0;
    private int r = 0;

    public QueueArray(int capacity) {
        this.capacity = capacity;
        _queue = new Object[capacity];
    }

    public QueueArray() {
        capacity = CAPACITY;
        _queue = new Object[CAPACITY];
    }

    @Override
    public int getSize() {
        return (capacity - f + r) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return f == r;
    }

    @Override
    public Object front() throws ExceptionQueueEmpty {
        if (isEmpty())
            throw new ExceptionQueueEmpty("意外:队列空");
        return _queue[f];
    }

    @Override
    public void enqueue(Object obj) throws ExceptionQueueFull {
        if (getSize() == capacity - 1){
            throw new ExceptionQueueFull("队列已满");
        }
        _queue[r] = obj;
        r = (r + 1) % capacity;
    }

    @Override
    public Object dequeue() throws ExceptionQueueEmpty {
        Object elem;
        if (isEmpty())
            throw new ExceptionQueueEmpty("意外:队列空");
        elem = _queue[f];
        _queue[f] = null;
        f = (f+1) % capacity;
        return elem;
    }

    @Override
    public void traversal() {
        for (int i = f; i < r; i++)
            System.out.print(_queue[i]+" ");
        System.out.println();
    }
}
