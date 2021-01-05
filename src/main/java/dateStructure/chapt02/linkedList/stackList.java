package dateStructure.chapt02.linkedList;

import dateStructure.chapt02.Stack.ExceptionStackEmpty;
import dateStructure.chapt02.Stack.Stack;

public class stackList implements Stack {
    protected Node top;
    protected  int size;

    public stackList() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Object top() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("stack empty");
        }
        return top.getEle();
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("empty stack");
        }
        Object oldvalue = top.getEle();
        top = top.getNext();
        size--;
        return oldvalue;
    }

    @Override
    public void push(Object ele) {
        //创建一个新节点，将其作为首节点插入
        Node v = new Node(ele, top);
        top = v;
        size ++;
    }
}
