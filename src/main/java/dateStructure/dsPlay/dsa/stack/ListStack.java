package dateStructure.dsPlay.dsa.stack;

import dateStructure.dsPlay.dsa.list.LinkedList;

public class ListStack<E> implements Stack<E>{

    private LinkedList<E> list;

    public ListStack() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(list);
        return sb.toString();
    }
}
