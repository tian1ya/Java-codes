package dateStructure.chapt02.Stack;


public class StackArray implements Stack {
    private static final int CAPACITY = 1024;
    protected int capacity;
    protected Object[] _stack;
    // 栈顶元素未知
    private int top = -1;

    public StackArray(int capacity) {
        this.capacity = capacity;
        _stack = new Object[capacity];
    }

    public StackArray() {
        this(CAPACITY);
    }


    public boolean isEmpty() {
        return this.top < 0;
    }

    @Override
    public int getSize() {
        return this.top + 1;
    }

    @Override
    public Object top() throws ExceptionStackEmpty {
        if (this.isEmpty()) {
            throw new ExceptionStackEmpty("栈空");
        }
        return this._stack[this.top];
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        if (this.top == this.capacity) {
            throw new ExceptionStackFull("栈满");
        }

        Object o = this._stack[this.top];
        this._stack[top--]=null;
        return o;
    }

    @Override
    public void push(Object ele) {
        if (getSize() > capacity) {
            throw new ExceptionStackFull("栈满");
        }
        _stack[++top] = ele;
    }
}
