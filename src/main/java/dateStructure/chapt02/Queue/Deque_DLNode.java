package dateStructure.chapt02.Queue;

public class Deque_DLNode implements Deque {
    private DLNode header;
    private DLNode trailer;
    private int size;

    public Deque_DLNode() {
        header = new DLNode();
        trailer = new DLNode();
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
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
    public Object first() {
        checkDequeNotEmpty();
        return header.getNext().getEle();
    }

    @Override
    public Object last() {
        checkDequeNotEmpty();
        return trailer.getPrev().getEle();
    }

    @Override
    public void insertFirst(Object obj) {
        DLNode firstEle = header.getNext();
        DLNode currentNode = new DLNode(obj, header, firstEle);
        header.setNext(currentNode);
        firstEle.setPrev(currentNode);
        size++;
    }

    @Override
    public void insertLast(Object obj) {
        DLNode prev = trailer.getPrev();
        DLNode currentNode = new DLNode(obj, prev, trailer);
        trailer.setPrev(currentNode);
        prev.setNext(currentNode);
        size++;
    }

    @Override
    public Object removeFirst() {
        checkDequeNotEmpty();
        DLNode skipedNextNode = header.getNext().getNext();
        header.setNext(skipedNextNode);
        skipedNextNode.setPrev(header);
        size--;
        return header.getNext().getEle();
    }

    @Override
    public Object removeLast() {
        checkDequeNotEmpty();
        DLNode prevPrevNode = trailer.getPrev().getPrev();

        prevPrevNode.setNext(trailer);
        trailer.setNext(prevPrevNode);
        size--;
        return trailer.getPrev().getEle();
    }

    @Override
    public void traversal() {
        DLNode next = header.getNext();
        while (next != null){
            Object ele = next.getEle();
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public void checkDequeNotEmpty(){
        if (isEmpty()){
            throw new ExceptionQueueEmpty("empty queue");
        }
    }
}
