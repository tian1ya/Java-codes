package dateStructure.dsPlay.dsa.queue;

import dateStructure.dsPlay.dsa.list.LinkedList;

public class ListQueue<E> implements Queue<E> {

    private LinkedList list;

    public ListQueue() {
        list = new LinkedList();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E dequeue() {
        return (E)list.removeFirst();
    }

    @Override
    public E getFront() {
        return (E) list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(list);
        return sb.toString();
    }
}
