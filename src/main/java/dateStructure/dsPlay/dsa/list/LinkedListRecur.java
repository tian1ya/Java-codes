package dateStructure.dsPlay.dsa.list;

import java.util.Objects;


public class LinkedListRecur<E> {

    // 私有内部类，对外并不需要知道内部的的存储防止，对外屏蔽底层细节
    private class Node {
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return String.valueOf(e);
        }
    }


    private Node dummyHead;
    private int size;

    public LinkedListRecur() {
        this.dummyHead = new Node(null);
        this.size = 0;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index out of bound");

        Node prev = dummyHead;
        add(index, e, prev);
        size++;
    }

    private void add(int index, E e, Node node) {
        if (index == 0)
            node.next = new Node(e, node.next);
        else
            add(index - 1, e, node.next);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void removeElement(E e) {
        removeElement(dummyHead, e);
    }

    private Node removeElement(Node node, E e) {
        if (Objects.equals(node.e, e)) {
            return node.next;
        } else {
            node.next = removeElement(node.next, e);
        }
        return node;
    }


    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index out of bound");
        return get(index, dummyHead.next);
    }

    public E get(int index, Node cur) {
        return index == 0 ? cur.e : get(index - 1, cur.next);
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size);
    }


    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index out of bound");
        set(index + 1, dummyHead, e);
    }

    private void set(int index, Node cur, E e) {
        if (index == 0)
            cur.e = e;
        else
            set(index - 1, cur.next, e);
    }

    public boolean contains(E e) {
        return contains(dummyHead, e);
    }

    private boolean contains(Node cur, E e) {
        if (cur == null) return false;
        else return Objects.equals(cur.e, e) ? true : contains(cur.next, e);
    }


    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index out of bound");

        Node prevNode = remove(index, dummyHead);
        E e = prevNode.next.e;
        prevNode.next = prevNode.next.next;
        size--;
        return e;
    }

    public Node remove(int index, Node cur) {
        return index == 0 ? cur : remove(index - 1, cur.next);
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;

        while (cur != null) {
            sb.append(cur + " => ");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
