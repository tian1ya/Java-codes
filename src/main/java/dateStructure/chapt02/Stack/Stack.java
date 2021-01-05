package dateStructure.chapt02.Stack;

public interface Stack {
    boolean isEmpty();

    int getSize();

    Object top() throws ExceptionStackEmpty;

    Object pop() throws ExceptionStackEmpty;

    void push(Object ele);
}
