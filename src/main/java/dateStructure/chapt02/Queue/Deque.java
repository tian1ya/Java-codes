package dateStructure.chapt02.Queue;

public interface Deque {
    int getSize();
    boolean isEmpty();
    Object first();
    Object last();
    void insertFirst(Object obj);
    void insertLast(Object obj);
    Object removeFirst();
    Object removeLast();
    void traversal();
}
