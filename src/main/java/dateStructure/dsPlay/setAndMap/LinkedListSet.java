package dateStructure.dsPlay.setAndMap;

import dateStructure.dsPlay.dsa.list.LinkedListRecur;

public class LinkedListSet<E> implements Set<E> {

    private LinkedListRecur<E> list;

    public LinkedListSet() {
        list = new LinkedListRecur<>();
    }

    @Override
    public void add(E e) {
        if (!contains(e)) list.addFirst(e);
    }

    @Override
    public void remove(E e) {
        if (contains(e)) list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int geiSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
