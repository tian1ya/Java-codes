package dateStructure.chapt02.Queue;

import dateStructure.chapt02.Position;

/*
    在基于NLNode类实现双向链表的时候，为了使编程更加简洁，通常我们都要在最前端和最后 端各设置一个哑元节点(Dummy node )。
    这两个节点分别称作头节点(Header node )和尾节点 (Trailer node)(一)，起哨兵(Sentinel)的作用。
    也就是说，它们并不存储任何实质的数据对象，头(尾)节点的next(prev)引用指向首(末)节点，而prev(next)引用为空。如此构成的双向链表
    结构
 */
public class DLNode implements Position {

    private Object element;
    private DLNode prev;
    private DLNode next;

    public DLNode() {
        this(null, null, null);
    }

    public DLNode(Object element, DLNode prev, DLNode next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public Object getEle() {
        return element;
    }

    @Override
    public Object setEle(Object ele) {
        Object old = this.element;
        this.element = ele;
        return old;
    }

    public DLNode getNext() {
        return next;
    }

    public DLNode getPrev() {
        return prev;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }

    public void setPrev(DLNode prev) {
        this.prev = prev;
    }
}
