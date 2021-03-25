package dateStructure.dsPlay.dsa.tree;

import dateStructure.dsPlay.dsa.Array;
import dateStructure.dsPlay.dsa.queue.ArrayQueue;

/*
    二分搜索树：
        是一个二叉树，其右子树值大于父节点，其左子树值小于父节点
        (不包含重复元素，如果插入的元素以及从在，则不作操作，原来的相同元素，还保持一切)

 */
public class BST<E extends Comparable<E>> {

    private class Node<E> {
        public E val;
        public Node left, right;

        public Node(E val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }


    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void addv1(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            root = add(root, e);
        }
    }

    private void addv1(Node node, E e) {
        if (e.equals(node.val)) {
            return;
        } else if (e.compareTo((E) node.val) > 0) {
            if (node.right == null) {
                node.right = new Node(e);
                return;
            }
            addv1(node.right, e);
        } else {
            if (node.left == null) {
                node.left = new Node(e);
                return;
            }
            addv1(node.left, e);
        }
    }

    /*
        注意这里和上面的思想的改变，上面的算法，添加节点的时候，保持着上一次的node
        上后 node.nextNode = newNode

        而新的方法，是直接函数返回新的Node
     */

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo((E) node.val) > 0) {
            node.right = add(node.right, e);
        } else if (e.compareTo((E) node.val) < 0) {
            node.left = add(node.left, e);
        }
        return node; // node 没有更新，始终指向root
    }

    public boolean contains(E e) {
        return contains(root, e);

    }

    private boolean contains(Node node, E e) {

        if (node == null)
            return false;
        if (e.compareTo((E) node.val) == 0)
            return true;
        if (e.compareTo((E) node.val) > 0)
            return contains(node.right, e);
        else
            return contains(node.left, e);
    }

    public Array<E> preOrder() {
        Array<E> preOrderTreeNodeArray = new Array<>();
        return preOrder(preOrderTreeNodeArray, root);
    }

    private Array<E> preOrder(Array<E> array, Node node) {
        if (node == null) return array;

        array.addLast((E) node.val);
        array = preOrder(array, node.left);
        return preOrder(array, node.right);
    }

    public Array<E> inOrder() {
        Array<E> inOrderTreeNodeArray = new Array<>();
        return inOrder(inOrderTreeNodeArray, root);
    }

    private Array<E> inOrder(Array<E> array, Node node) {
        if (node == null) return array;

        array = preOrder(array, node.left);
        array.addLast((E) node.val);
        array = preOrder(array, node.right);
        return array;
    }


    public Array<E> postOrder() {
        Array<E> inOrderTreeNodeArray = new Array<>();
        return postOrder(inOrderTreeNodeArray, root);
    }

    private Array<E> postOrder(Array<E> array, Node node) {
        if (node == null) return array;

        array = preOrder(array, node.left);
        array = preOrder(array, node.right);
        array.addLast((E) node.val);
        return array;
    }

    public Array<E> levelOrder() {
        ArrayQueue<Node> queue = new ArrayQueue<>();
        Array<E> levelOrderTreeNodeArray = new Array<>();

        Node dummyRoot = this.root;

        if (dummyRoot != null) queue.enqueue(dummyRoot);
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();

            levelOrderTreeNodeArray.addLast((E) node.val);

            if (node.left != null)
                queue.enqueue(node.left);

            if (node.right != null)
                queue.enqueue(node.right);
        }
        return levelOrderTreeNodeArray;
    }

    public E getMax() {
        if (root == null)
            throw new RuntimeException("Tree empty");
        return (E) getMax(root).val;
    }

    private Node getMax(Node node) {
        return node.right == null ? node : getMax(node.right);
    }


    public E getMin() {
        if (root == null)
            throw new RuntimeException("Tree empty");
        return (E) getMin(root).val;
    }

    private Node getMin(Node node) {
        return node.left == null ? node : getMin(node.left);
    }

    public E removeMin() {
        if (root == null)
            throw new RuntimeException("Tree empty");

        E min = getMin();
        root = removeMin(this.root);
        return min;
    }

    // 删除最小然后返回 树的 root
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode; // 这里不需要去判断 right 空否，如果是空，那么返回空，如果非空，那么就返回非空
        }

        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        if (root == null)
            throw new RuntimeException("Tree empty");

        E max = getMax();
        root = removeMax(this.root);
        return max;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /*
        节点在右侧：
            删除叶子节点，比较简单，都不需要特殊的情况去对待处理
            删除左右都有孩子节点 d，需要找到d 的后继节点s，s = min(d -> right)
            删除d 的右子树最小值(s)，然后将最小值(s)作为d 的替换。
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) return null;

        if (e.compareTo((E) node.val) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo((E) node.val) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // e == node.val
            // 找到要删除的结果
            if (node.left == null) {
                size--;
                Node newNode = node.right;
                node.right = null;
                return newNode;
            } else if (node.right == null) {
                size--;
                Node newNode = node.left;
                node.left = null;
                return newNode;
            } else {
                // 右节点最小节点
                Node successor = getMin(node.right);
                successor.right = removeMin(node.right);
                // 删除并返回 root, 这里进行了 size--, 这类并不需要在进行 size--了。
                successor.left = node.left;

                node.left = node.right = null;
                return successor;
            }
        }
    }
}
