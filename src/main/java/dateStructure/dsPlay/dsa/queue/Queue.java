package dateStructure.dsPlay.dsa.queue;

/*
    先进先出 FIFO
 */
public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
