package dateStructure.dsPlay.dsa.queue;

public class LoopQueue<E> implements Queue<E> {
    private Object[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = new Object[capacity + 1];
        front = 0;
        tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }


    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int size) {
        Object[] newData = new Object[size + 1];
        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = getSize();
    }

    @Override
    public E dequeue() {
        if (getSize() == 0)
            throw new RuntimeException("Empty Queue");
        E ret = (E) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront() {
        if (getSize() == 0)
            throw new RuntimeException("Empty Queue");
        return (E) data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue: size= " + getSize() + " capacity= " + getCapacity() + " \n");
        stringBuilder.append("tail: [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            stringBuilder.append(data[i]);
            if ((1 + i) % data.length != tail)
                stringBuilder.append(", ");
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }
}
