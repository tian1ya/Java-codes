package dateStructure.dsPlay.setAndMap;


/*
    是一个二叉树，其右子树值大于父节点，其左子树值小于父节点
 */
public class BinSearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;

        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BinSearchTreeMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = new Node(key, value);
        root = add(root, node);
    }

    private Node add(Node root, Node node) {
        if (root == null) {
            size++;
            return node;
        }

        if (root.key.compareTo(node.key) < 0) {
            root.right = add(root.right, node);
        } else if (root.key.compareTo(node.key) > 0) {
            root.left = add(root.left, node);
        }
        return root; // 这里每次都是返回node的孩子
    }

    /*
        叶子节点之间山存储

        删除中间节点，使用当前节点的右节点的最小节点作为当前节点
     */

    public V getMin() {
        Node min = getMin(root);
        return min.value;
    }

    private Node getMin(Node node) {
        return node.left == null ? node : getMin(node.left);
    }


    public V removeMin() {
        V min = getMin();
        removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        } else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    public V getMax() {
        return getMax(root);
    }

    private V getMax(Node node) {
        return node.right == null ? node.value : getMax(node.right);
    }

    public V removeMax() {
        V max = getMax();
        removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        else {
            node.right = removeMax(node.right);
            return node;
        }
    }

    @Override
    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node== null) return node;

        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else {
            if (node.right == null) {
                size --;
                Node leftNode = node.left; // 待返回的下一个节点，不返回当前节点，就是直接将当前节点给删除了
                node.left = null; // 当前节点和下一个节点断开
                return leftNode;
            } else if (node.left == null) {
                // 左边没有节点，那么右边直接将下一个节点返回
                size--;
                Node nextRightNode = node.right;
                node.right = null;
                return nextRightNode;
            } else {
                /*
                    左右节点都有，那就需要将右边节点的最小值返回最小值,这个最小值是右边子树得到的，所以肯定是小于全部右边子树的
                    且刚好是仅仅大于左边子树的，所以是符合二叉树的基本定义的
                 */

                Node leftMinNode = getMin(node.right);
                leftMinNode.right = removeMin(node.right);
                leftMinNode.left = node.left;
                node.left = node.right = null;
                // size--; 在 removeMin 中已经执行过了
                return leftMinNode;
            }
        }
    }

    @Override
    public boolean contains(K key) {
        return get(root, key) != null;
    }


    @Override
    public void set(K key, V newValue) {
        Node node = get(root, key);
        node.value = newValue;
    }

    @Override
    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    public Node get(Node node, K key) {
        if (node == null) return node;
        if (key.compareTo(node.key) == 0) return node;
        return key.compareTo(node.key) > 0 ? get(node.right, key) : get(node.left, key);
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
