package dateStructure.dsPlay.dsa.setAndMap;

public interface Map<K,V> {
    void add(K key, V value);
    void remove(K key);
    boolean contains(K key);
    void set(K key, V newValue);
    V get(K key);
    int getSize();
    boolean isEmpty();
}
