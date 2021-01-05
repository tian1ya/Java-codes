package dateStructure.dsPlay.setAndMap;

public interface Set<E> {
    void add(E e); // 不添加重复元素

    void remove(E e);

    boolean contains(E e);

    int geiSize();

    boolean isEmpty();
}
