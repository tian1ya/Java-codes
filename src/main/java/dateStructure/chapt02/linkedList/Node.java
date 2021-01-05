package dateStructure.chapt02.linkedList;

public class Node {
    private Object ele;
    private Node next;

    public Node(Object ele, Node next) {
        this.ele = ele;
        this.next = next;
    }

    public Node(Object ele) {
        this(ele, null);
    }

    public Node() {
        this(null, null);
    }

    public Object getEle() {
        return ele;
    }

    public Object setEle(Object newEle) {
        Object oldEle = ele;
        this.ele = newEle;
        return oldEle;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }


    public boolean isNull() {
        return ele == null;
    }
}
