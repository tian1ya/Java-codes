package dateStructure.dsPlay.dsa.stack;

/*
    数字的子集
    只能从一段添加，以及取出元素

    这一端称为是栈顶

    先进后出： LIFO

    栈的应用： 撤销曹操传
              程序调用的系统栈
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
