package dateStructure.chapt07.bsTree.linkedBinaryTree;

public class BTreeTest {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = new TreeNode(1);
        binaryTree.setRoot(root);

        TreeNode leftNode = new TreeNode(2);
        root.setLeftNode(leftNode);

        TreeNode rightNode = new TreeNode(3);
        root.setRightNode(rightNode);

        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        leftNode.setLeftNode(node4);
        leftNode.setRightNode(node5);

        rightNode.setLeftNode(node6);
        rightNode.setRightNode(node7);

        binaryTree.preOrder();

        binaryTree.inOrder();

        binaryTree.postOrder();

        System.out.println("============= search ===============");
        TreeNode node = binaryTree.preOrderSearch(5);
        System.out.printf("Node(value=%d)\n", node.value);

        System.out.println("删除");
        binaryTree.delete(3);
        binaryTree.postOrder();

    }
}
