package dateStructure.dsPlay.dsa;

import java.util.Objects;

/*
    泛型是不可以是基本数据类型，只能是类对象
    Java 对每一个基本的数据类型，都有对应的包装类
 */
public class Array<E> {
    private Object[] data;
    private int size;

    public Array(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr) {
        data = new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
            size++;
        }
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void addLast(E e) {
        add(getSize(), e);
    }

    public void add(int index, E e) {

        if (index > getSize() || index < 0)
            throw new IllegalArgumentException("index is invalid,");

        if (getSize() == getCapacity())
            resize(getSize() * 2);

        for (int i = getSize() - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity=").append(getCapacity()).append(", size=").append(getSize()).append("\n");
        sb.append("[");
        for (int i = 0; i < getSize(); i++) {
            sb.append(data[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();

    }

    public E get(int index) {
        if (index >= getSize() || index < 0)
            throw new IllegalArgumentException("index is invalid");
        return (E) data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index > getSize() || index < 0)
            throw new IllegalArgumentException("index is invalid");
        data[index] = e;
        size++;
    }

    public boolean contains(int e) {
        return Objects.equals(find(e), -1);
    }

    public int find(int e) {
        for (int i = 0; i < getSize(); i++) {
            if (Objects.equals(data[i], e))
                return i;
        }
        return -1;
    }

    public E remove(int index) {
        if (index >= getSize() || index < 0)
            throw new IllegalArgumentException("index is invalid");

        E deletedValue = (E) data[index];
        for (int i = index + 1; i < getSize(); i++) {
            data[i - 1] = data[i];
        }
        size--;
        if (size == getCapacity() / 4 && data.length / 2 != 0)
            resize(getCapacity()/2);

        return deletedValue;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(getSize() - 1);
    }

    public void removeElement(int e) {
        // 只会数组中存在的第一个 e
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    private void resize(int capacity) {
        Object[] newArray = new Object[capacity];;
        for (int i = 0; i < getSize(); i++) {
            newArray[i] = data[i];
        }
        data = newArray;
        newArray = null;
    }


    public void swap(int indexA, int indexB) {
        if (!isIndexInvalid(indexA) && !isIndexInvalid(indexB)){
            E temp = (E) data[indexA];
            data[indexA] = data[indexB];
            data[indexB] = temp;
        } else
            throw new RuntimeException("index out of bound");
    }

    private boolean isIndexInvalid(int index) {
        return index < 0 && index >= getSize();
    }
}
