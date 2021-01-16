package dateStructure.dsPlay.dsa.setAndMap;


public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }


        public Node() {
            this(null, null, null);
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        @Override
        public String toString() {
            return key + "=>" + value;
        }
    }


    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        add(this.dummyHead).next = new Node(key, value);
        size++;
    }

    private Node add(Node prep) {
        return prep.next == null
                ? prep
                : add(prep.next);
    }

    @Override
    public void remove(K key) {
        Node node = getPrevNodeByKey(dummyHead, key);

        if (node.next != null && node.next.key == key) {
            V deleteValue = node.next.value;
            node.next = node.next.next;
            size--;
        }
        throw new RuntimeException(key + " is not exist");
    }

    private Node getPrevNodeByKey(Node node, K key) {
        if (node.next == null)
            return node;
        return key.equals(node.next.key) ? node : getPrevNodeByKey(node.next, key);
    }

    @Override
    public boolean contains(K key) {
        return getPrevNodeByKey(dummyHead, key) != null;
    }

    @Override
    public void set(K key, V newValue) {
        Node oldNode = getPrevNodeByKey(dummyHead, key);
        oldNode.next.value = newValue;
    }

    @Override
    public V get(K key) {
        Node oldNode = getPrevNodeByKey(dummyHead, key);
        return oldNode == null ? null : oldNode.next.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }
}
