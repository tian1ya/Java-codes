package dateStructure.chapt07.binarySortTree;

public class Node {
    Integer value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node() {
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public int leftRightNodeHeightDiff() {
        return left != null && right != null ? left.height() - right.height() : 0;
    }

    public void add(Node node) {
        if (node.value <= value) {
            if (left != null)
                left.add(node);
            else
                left = node;
        } else if (right != null)
            right.add(node);
        else
            right = node;

        // 检查树是否平衡
        if (leftRightNodeHeightDiff() >= 2) {

            // 双旋转
            if (left != null && left.leftRightNodeHeightDiff() < 0) {
                left.leftRotate();
                rightRotate();
            }else {
                // 右旋转
                rightRotate();
            }
        }
        // 左旋转
        if (leftRightNodeHeightDiff() <= -2) {
            leftRotate();
        }
    }

    private void leftRotate() {
        Node newLeft = new Node(value);

        newLeft.left = left;

        newLeft.right = right.left;

        value = right.value;

        right = right.right;

        left = newLeft;
    }

    private void rightRotate() {
        // 创建一个新节点，值等于当前节点的值
        Node newRight = new Node(value);

        // 将当前新节点的右子树设置了当前节点的右子树
        newRight.right = right;

        // 新节点的左子树设置为当前节点的左子树的右子树
        newRight.left = left.right;

        //当前节点的值变为左子树的值
        value = left.value;

        // 当前节点的左子树左子树的左子树
        left = left.left;

        //当前节点的右子树设置为新节点
        right = newRight;
    }

    public void inOrder() {
        if (left != null) left.inOrder();
        System.out.printf("Node(value=%d) ", value);
        if (right != null) right.inOrder();
    }

    public Node search(int value) {
        if (value == this.value)
            return this;
        else if (value < this.value && left != null)
            return left.search(value);
        else if (right != null)
            return right.search(value);
        else
            throw new RuntimeException("node value is: " + value + " is not exist");
    }

    public Node delete(Node parent, Node node) {
        Node tmp = node;
        if (node.right == null && node.left == null) {
            if (parent.left.value == node.value)
                parent.left = null;
            else
                parent.right = null;
        } else if (node.left == null && node.right != null) {
            if (parent.left.value == node.value)
                parent.left = node.right;
            else
                parent.right = node.right;
        } else if (node.right == null && node.left != null) {
            if (parent.left.value == node.value)
                parent.left = node.left;
            else
                parent.right = node.left;
        } else {
            Node replaceNode = findMinNode(node);
            replaceNode.left = node.left;
            replaceNode.right = node.right;
            if (parent.left.value == node.value)
                parent.left = replaceNode;
            else
                parent.right = replaceNode;
        }

        return tmp;

    }

    public Node findMinNode(Node node) {
        if (node.right.left == null) {
            Node right = node.right;
            node.right = null;
            return right;
        }
        return _findMinNode(node.right);
    }

    private Node _findMinNode(Node node) {
        if (node.left.left == null) {
            Node tmp = node.left;
            node.left = null;
            return tmp;
        }
        return findMinNode(node.left);
    }

    private boolean isValueFromNode(int value, Node node) {
        return node != null && node.value == value;
    }

    private boolean isLeafNode(Node node) {
        return node.left == null && node.right == null;
    }
}
