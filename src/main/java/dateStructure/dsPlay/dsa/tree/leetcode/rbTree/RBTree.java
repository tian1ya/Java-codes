package dateStructure.dsPlay.dsa.tree.leetcode.rbTree;


public class RBTree<K extends Comparable<K>, V> {

    private final static boolean RED = true;
    private final static boolean BLACK = false;

    private class Node {

        public K key;
        public V val;
        public Node left, right;
        public Boolean color;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.color = RED;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    private boolean isRed(Node node) {
        return node == null ? false : node.color;
    }

    // 二分搜索树中添加新的元素
    public void add(K key, V val) {
        root = add(root, key, val);
        root.color = BLACK; //  最终更节点是黑色的
    }

    /*

     */
    private Node add(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val); // 默认会插入红色节点
        }
        if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, val);
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, val);
        } else
            node.val = val;

        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    private void flipColors(Node node) {

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node rightRotate(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        // 相当于是一个新加进来的一个节点，这里可能会有一个连续的红色节点
        // 返回 x 节点之后还会有后续的颜色操作
        return x;
    }

    public static void main(String[] args) {
        String[] words = {"2", "4", "2", "4", "2", "7", "8", "8", "0", "33", "66", "77", "333", "9999", "343", "76", "2323", "4", "2", "7", "8", "9999", "343", "76"};

        RBTree<String, Integer> tree = new RBTree<>();

        for (String word : words) {
            tree.add(word,1);
        }
    }
}
