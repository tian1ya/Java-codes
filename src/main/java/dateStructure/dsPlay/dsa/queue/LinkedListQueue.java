package dateStructure.dsPlay.dsa.queue;

import JSRValidation.NotEmpty;
import dateStructure.dsPlay.dsa.list.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {

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


    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        Node tempHead = this.head;
        head = head.next;
        if (head == null) // 最后一个元素还需要维护下tail
            tail = null;
        size--;
        tempHead.next = null;
        return tempHead.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;

        while (cur != null) {
            sb.append(cur + " => ");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
