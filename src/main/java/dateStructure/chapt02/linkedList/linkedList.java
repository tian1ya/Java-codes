package dateStructure.chapt02.linkedList;


import java.util.Objects;

public class linkedList {
    private Node head;
    private Node tail;

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public linkedList(Node head) {
        this.head = head;
    }


    public linkedList() {
        head = new Node();
    }



    public void insert(Object obj) {
        if (head.isNull()){
            head.setEle(obj);
            tail = head;
        }
        else {
            Node node = new Node(obj);
            Node currentHead = head;
            node.setNext(currentHead);
            head = node;
        }
    }

    public boolean hasValue(Object obj) {
        Node tmpHeader = head;
        while (tmpHeader != null){
            Object ele = tmpHeader.getEle();
            if (Objects.equals(ele, obj)) {
                return true;
            }
            tmpHeader = tmpHeader.getNext();
        }
        return false;
    }

    public void delete(Object obj) {
        if (!hasValue(obj)) {
            throw new RuntimeException(String.format("%s is not exist", obj));
        }

        Node tmpHead = head;
        Node tmpNext = head.getNext();

        if (Objects.equals(tmpHead.getEle(), obj)) {
            head = tmpNext;
            return;
        }
        while (tmpNext != null) {
            if (tmpNext.getEle() == obj) {
                tmpHead.setNext(tmpNext.getNext());
                break;
            }
            tmpHead = tmpNext;
            tmpNext = tmpNext.getNext();
        }
    }

    public int length() {
        int length = 0;
        Node tmpHeader = head;
        while (tmpHeader != null){
            length ++;
            tmpHeader = tmpHeader.getNext();
        }
        return length;
    }
}
