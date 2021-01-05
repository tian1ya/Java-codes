package dateStructure.chapt02.linkedList;

import dateStructure.chapt02.Queue.ExceptionQueueEmpty;
import dateStructure.chapt02.Queue.ExceptionQueueFull;
import dateStructure.chapt02.Queue.Queue;

public class QueueList implements Queue {
    protected Node head;
    protected Node tail;
    protected int size;


    public QueueList() {
        this.head = this.tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    public Object front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("queue empty");
        }
        return head.getEle();
    }

    @Override
    public void enqueue(Object obj) throws ExceptionQueueFull {
        Node node = new Node(obj);;
        if (isEmpty())
            head = node;
        else
            tail.setNext(node);
        tail = node;
        size++;
    }

    @Override
    public Object dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("queue empty");
        }
        Object ele = head.getEle();
        head = head.getNext();
        size--;
        if (isEmpty())
            tail = null;
        return ele;
    }

    @Override
    public void traversal() {
        Node tmpHead = this.head;
        while (tmpHead != null) {
            System.out.println(tmpHead.getEle() + " ");
            tmpHead = tmpHead.getNext();
        }
    }
}
