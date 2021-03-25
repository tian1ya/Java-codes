package dateStructure.dsPlay.dsa.list;

import java.util.Objects;

/*
    真正的动态数据结构，而之前学的Queue、Stack、Array 等均是静态的数据结构，其底层均是数据
    当然也是可以使用链表去实现 Queue、Stack使其动态

    最简单的动态数据结构。

    可以更加深入理解引用(指针)

    更深入理解递归

    数据存储在Node 中

    真正的动态，不需要处理固定容量的问题
    和数组对比，其最大的问题就是失去了随机访问的能力

    数组，最好是用于索引有语义的情况，
         最大的有点，支持快速查询

    链表：不适合用哦关于索引有语义的情况
         优点：动态

 */
public class LinkedList<E> {

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


    /*
        头节点有2种方式
            1. dummyHead， 虚拟头节点什么也不存储，下一个节点才是真正的头节点有了它就不需要对头结点添加元素进行特殊处理
            2. head 头结点，这里存储的是真正的有数据的头结点, 注意这里插入位置 index， 需要先找到 index-1的位置
     */
    private Node dummyHead;
    //    private Node head;
    private int size;

    public LinkedList() {
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
            // == size 插在最开始
            throw new IllegalArgumentException("index out of bound");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;// 找到最后一个节点

        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index > size)
            // == size 插在最开始
            throw new IllegalArgumentException("index out of bound");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;// 找到前一个节点

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size);
    }

    public void set(int index, E e) {
        if (index < 0 || index > size)
            // == size 插在最开始
            throw new IllegalArgumentException("index out of bound");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;// 找到前一个节点

        cur.e = e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (Objects.equals(cur.e, e)) return true;
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index > size)
            // == size 插在最开始
            throw new IllegalArgumentException("index out of bound");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;// 找到前一个节点
        Node deletedNode = prev.next;
        E e = deletedNode.e;
        prev.next = prev.next.next;
        deletedNode = null;
        size--;
        return e;
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
