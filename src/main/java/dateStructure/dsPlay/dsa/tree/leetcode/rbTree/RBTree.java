package dateStructure.dsPlay.dsa.tree.leetcode.rbTree;


import java.util.LinkedList;
import java.util.Queue;

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

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node root, K key) {
        if (root == null) return false;

        if (key.compareTo(root.key) > 0)
            return contains(root.right, key);
        else if (key.compareTo(root.key) < 0)
            return contains(root.left, key);
        else
            return true;
    }

    public V set(K key, V val) {
        if (!contains(key)) add(key, val);
        return set(root, key, val);
    }

    private V set(Node root, K key, V val) {
        if (key.compareTo(root.key) > 0)
            return set(root.right, key, val);
        else if (key.compareTo(root.key) < 0)
            return set(root.left, key, val);
        else {
            V tempVal = root.val;
            root.val = val;
            return tempVal;
        }
    }

    public V get(K key) {
        if (!contains(key)) throw new RuntimeException("key: " + key + " not exist");
        return get(root, key);
    }

    private V get(Node root, K key) {
        if (key.compareTo(root.key) > 0)
            return get(root.right, key);
        else if (key.compareTo(root.key) < 0)
            return get(root.left, key);
        else {
            return root.val;
        }
    }

    public void toStringPreOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("preOrder: ");
        preOrder(root, sb);
        System.out.println(sb.toString());
    }

    private void preOrder(Node root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.key + " -> " + root.val + ", ");
        preOrder(root.right, sb);
        preOrder(root.left, sb);
    }

    public void toStringPostOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("postOrder: ");
        postOrder(root, sb);
        System.out.println(sb.toString());
    }

    private void postOrder(Node root, StringBuilder sb) {
        if (root == null) return;
        preOrder(root.right, sb);
        preOrder(root.left, sb);
        sb.append(root.key + " -> " + root.val + ", ");
    }

    public void toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("middleOrder: ");
        inOrder(root, sb);
        System.out.println(sb.toString());
    }

    private void inOrder(Node root, StringBuilder sb) {
        if (root == null) return;
        preOrder(root.right, sb);
        sb.append(root.key + " -> " + root.val + ", ");
        preOrder(root.left, sb);
    }

    public void levelOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("levelOrder: ");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            sb.append(node.key + " -> " + node.val + ", ");

            if (node.left != null)
                queue.add(node.left);

            if (node.right != null)
                queue.add(node.right);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        String[] words = {"2", "4", "2", "4", "2", "7", "8", "8", "0", "33", "66", "77", "333", "9999", "343", "76", "2323", "4", "2", "7", "8", "9999", "343", "76"};

        RBTree<String, Integer> tree = new RBTree<>();

        for (String word : words) {
            if (tree.contains(word)) {
                tree.set(word, 1 + tree.get(word));
            } else {
                tree.add(word, 1);
            }
        }

        tree.levelOrder();
    }
}
