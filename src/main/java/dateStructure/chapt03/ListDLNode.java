package dateStructure.chapt03;

import dateStructure.chapt02.Position;
import dateStructure.chapt02.Queue.DLNode;


public class ListDLNode implements List {
    protected int numElem;
    protected DLNode header;
    protected DLNode tailer;

    public ListDLNode() {
        numElem = 0;
        header = new DLNode(null, null, null);
        tailer = new DLNode(null, header, null);
        header.setNext(tailer);
    }

    private DLNode checkPosition(Position position) {
        if (position == null) {
            throw new ExceptionPositionInvalid("传递的位置为null");
        }
        if (header == position) {
            throw new ExceptionPositionInvalid("传递的位置（头）非法");
        }

        if (tailer == position) {
            throw new ExceptionPositionInvalid("传递的位置（尾）非法");
        }

        return (DLNode) position;
    }

    @Override
    public int getSize() {
        return numElem;
    }

    @Override
    public boolean isEmpty() {
        return numElem == 0;
    }

    @Override
    public Position first() {
        if (isEmpty())
            throw new ExceptionListEmpty("空列表");
        return header.getNext();
    }

    @Override
    public Position last() {
        if (isEmpty())
            throw new ExceptionListEmpty("空列表");
        return tailer.getPrev();
    }

    @Override
    public Position getNext(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
        DLNode currentPosition = checkPosition(position);
        DLNode nextNode = currentPosition.getNext();
        if (nextNode == header)
            throw new ExceptionBoundaryViolation("企图越过列表末端");
        return nextNode;
    }

    @Override
    public Position getPrev(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
        DLNode currentPosition = checkPosition(position);
        DLNode prevNode = currentPosition.getPrev();
        if (prevNode == tailer)
            throw new ExceptionBoundaryViolation("企图越过列表始端");
        return prevNode;
    }

    @Override
    public Position insertFirst(Object e) {
        DLNode node = new DLNode(e, header, header.getNext());
        header.setNext(node);
        header.getNext().setPrev(node);
        numElem ++;
        return node;
    }

    @Override
    public Position insertLast(Object e) {
        DLNode node = new DLNode(e, tailer.getPrev(), tailer);
        tailer.getPrev().setNext(node);
        tailer.setPrev(node);
        numElem ++;
        return node;
    }

    @Override
    public Position insertBefore(Position position, Object e) throws ExceptionPositionInvalid {
        DLNode currentPosition = checkPosition(position);
        DLNode node = new DLNode(e, currentPosition.getPrev(), currentPosition);
        currentPosition.getPrev().setNext(node);
        currentPosition.setPrev(node);
        numElem++;
        return node;
    }

    @Override
    public Position insertAfter(Position position, Object e) throws ExceptionPositionInvalid {
        DLNode currentPosition = checkPosition(position);
        DLNode node = new DLNode(e, currentPosition, currentPosition.getNext());
        currentPosition.setNext(node);
        currentPosition.getNext().setPrev(node);
        numElem++;
        return node;
    }

    @Override
    public Object remove(Position p) throws ExceptionPositionInvalid {
        DLNode currentPosition = checkPosition(p);
        currentPosition.getPrev().setNext(currentPosition.getNext());
        currentPosition.getNext().setPrev(currentPosition.getPrev());
        currentPosition.setPrev(null);
        currentPosition.setNext(null);
        numElem--;
        return currentPosition.getEle();
    }

    @Override
    public Object removeFirst() {
        return remove(header.getNext());
    }

    @Override
    public Object removeLast() {
        return remove(tailer.getPrev());
    }

    @Override
    public Object replace(Position p, Object obj) throws ExceptionPositionInvalid {
        DLNode currentPosition = checkPosition(p);
        DLNode prev = currentPosition.getPrev();
        DLNode next = currentPosition.getNext();

        DLNode node = new DLNode(obj, prev, next);
        prev.setNext(node);
        next.setPrev(node);
        return node.getEle();
    }

    @Override
    //位置迭代器
    public Iterator positions() {
        return new IteratorPosition(this);
    }

    @Override
    //元素迭代器
    public Iterator elements() {
        return new IteratorElement(this);
    }
}
