package dateStructure.chapt07.bsTree.linkedBinaryTree;

public class BinaryTree {

    TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (root != null)
            root.preOrder();
    }

    public void inOrder() {
        if (root != null)
            root.inOrder();
    }

    public void postOrder() {
        if (root != null)
            root.postOrder();
    }

    public TreeNode preOrderSearch(int i) {
        return root.preOrderSearch(i);
    }

    public void delete(int i) {
        if (root.value == i)
            root = null;
        else
            root.delete(i);
    }
}
