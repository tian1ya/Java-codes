package dateStructure.chapt07.bsTree.linkedBinaryTree;

public class TreeNode {
    int value;
    TreeNode leftNode;
    TreeNode rightNode;

    public TreeNode(int value) {
        this.value = value;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public void preOrder() {
        System.out.printf("Node(value=%d)\n", value);
        if (leftNode != null)
            leftNode.preOrder();
        if (rightNode != null)
            rightNode.preOrder();
    }

    public void inOrder() {
        if (leftNode != null)
            leftNode.inOrder();
        System.out.printf("Node(value=%d)\n", value);
        if (rightNode != null)
            rightNode.inOrder();
    }

    public void postOrder() {
        if (leftNode != null)
            leftNode.postOrder();
        if (rightNode != null)
            rightNode.postOrder();
        System.out.printf("Node(value=%d)\n", value);
    }

    public TreeNode preOrderSearch(int i) {
        TreeNode node = null;
        if (value == i)
            node = this;
        if (node != null)
            return node;
        else if (leftNode != null)
            node = leftNode.preOrderSearch(i);
        if (node != null)
            return node;
        else if (rightNode != null)
            node = rightNode.preOrderSearch(i);
        return node;
    }

    public void delete(int i) {
        if (this.leftNode == null) return;
        if (this.leftNode.value == i) {
            this.leftNode = null;
            return;
        } else
            this.leftNode.delete(i);

        if (this.rightNode == null) return;
        if (this.rightNode.value == i) {
            this.rightNode = null;
            return;
        } else
            this.rightNode.delete(i);
    }
}
